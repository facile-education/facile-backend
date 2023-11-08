/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.document.utils;


import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.document.library.kernel.store.Store;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomFileSystemStore implements Store {

    private static final Log logger = LogFactoryUtil.getLog(CustomFileSystemStore.class);

    public CustomFileSystemStore() {

        String path = "data/document_library";

        File rootDirFile = new File(path);

        if (!rootDirFile.isAbsolute()) {
            rootDirFile = new File(PropsUtil.get(PropsKeys.LIFERAY_HOME), path);
        }

        this.rootDir = rootDirFile;
        this.rootDir.mkdirs();
    }

    @Override
    public void addFile(
            long companyId, long repositoryId, String fileName, String versionLabel,
            InputStream inputStream) {

        if (Validator.isNull(versionLabel)) {
            versionLabel = getHeadVersionLabel(
                    companyId, repositoryId, fileName);
        }

        try {
            FileUtil.write(
                    getFileNameVersionFile(
                            companyId, repositoryId, fileName, versionLabel),
                    inputStream);
        }
        catch (IOException ioException) {
            throw new SystemException(ioException);
        }
    }

    @Override
    public void deleteDirectory(
            long companyId, long repositoryId, String dirName) {

        File dirNameDir = getDirNameDir(companyId, repositoryId, dirName);

        if (!dirNameDir.exists()) {
            return;
        }

        File parentFile = dirNameDir.getParentFile();

        FileUtil.deltree(dirNameDir);

        deleteEmptyAncestors(parentFile);
    }

    @Override
    public void deleteFile(
            long companyId, long repositoryId, String fileName,
            String versionLabel) {

        if (Validator.isNull(versionLabel)) {
            versionLabel = getHeadVersionLabel(
                    companyId, repositoryId, fileName);
        }

        File fileNameVersionFile = getFileNameVersionFile(
                companyId, repositoryId, fileName, versionLabel);

        if (!fileNameVersionFile.exists()) {
            return;
        }

        File parentFile = fileNameVersionFile.getParentFile();

        try {
            Files.delete(fileNameVersionFile.toPath());
        } catch (IOException e) {
            logger.error("Could not delete file " + fileNameVersionFile.getAbsolutePath());
        }

        deleteEmptyAncestors(parentFile);
    }

    @Override
    public InputStream getFileAsStream(
            long companyId, long repositoryId, String fileName,
            String versionLabel)
            throws NoSuchFileException {

        if (Validator.isNull(versionLabel)) {
            versionLabel = getHeadVersionLabel(
                    companyId, repositoryId, fileName);
        }

        File fileNameVersionFile = getFileNameVersionFile(
                companyId, repositoryId, fileName, versionLabel);

        try {
            return new FileInputStream(fileNameVersionFile);
        }
        catch (FileNotFoundException fileNotFoundException) {
            throw new NoSuchFileException(
                    companyId, repositoryId, fileName, versionLabel,
                    fileNotFoundException);
        }
    }

    @Override
    public String[] getFileNames(
            long companyId, long repositoryId, String dirName) {

        File dirNameDir = getDirNameDir(companyId, repositoryId, dirName);

        if (!dirNameDir.exists()) {
            return new String[0];
        }

        List<String> fileNames = new ArrayList<>();

        getFileNames(fileNames, dirName, dirNameDir.getPath());

        Collections.sort(fileNames);

        return fileNames.toArray(new String[0]);
    }

    @Override
    public long getFileSize(
            long companyId, long repositoryId, String fileName,
            String versionLabel)
            throws NoSuchFileException {

        if (Validator.isNull(versionLabel)) {
            versionLabel = getHeadVersionLabel(
                    companyId, repositoryId, fileName);
        }

        File fileNameVersionFile = getFileNameVersionFile(
                companyId, repositoryId, fileName, versionLabel);

        if (!fileNameVersionFile.exists()) {
            throw new NoSuchFileException(
                    companyId, repositoryId, fileName, versionLabel);
        }

        return fileNameVersionFile.length();
    }

    @Override
    public String[] getFileVersions(
            long companyId, long repositoryId, String fileName) {

        File fileNameDir = getFileNameDir(companyId, repositoryId, fileName);

        if (!fileNameDir.exists()) {
            return StringPool.EMPTY_ARRAY;
        }

        String[] versions = FileUtil.listFiles(fileNameDir);

        Arrays.sort(versions, DLUtil::compareVersions);

        for (int i = 0 ; i < versions.length ; i++) {
            int x = versions[i].lastIndexOf(CharPool.UNDERLINE);
            if (x > -1) {
                int y = versions[i].lastIndexOf(CharPool.PERIOD);
                versions[i] = versions[i].substring(x + 1, y);
            }
        }
        return versions;
    }

    public File getRootDir() {
        return rootDir;
    }

    @Override
    public boolean hasFile(
            long companyId, long repositoryId, String fileName,
            String versionLabel) {

        if (Validator.isNull(versionLabel)) {
            versionLabel = getHeadVersionLabel(
                    companyId, repositoryId, fileName);
        }

        File fileNameVersionFile = getFileNameVersionFile(
                companyId, repositoryId, fileName, versionLabel);

        return fileNameVersionFile.exists();
    }

    protected File getDirNameDir(
            long companyId, long repositoryId, String dirName) {

        File repositoryDir = getRepositoryDir(companyId, repositoryId);
        return new File(repositoryDir + StringPool.SLASH + dirName);
    }


    protected int getDepth(String path) {
        String[] fragments = StringUtil.split(path, CharPool.SLASH);

        return fragments.length;
    }

    protected void buildPath(StringBundler sb, String fileNameFragment) {
        int fileNameFragmentLength = fileNameFragment.length();

        if (fileNameFragmentLength <= 2) {
            return;
        }

        for (int i = 0; (i + 2) < fileNameFragmentLength; i += 2) {
            sb.append(fileNameFragment.substring(i, i + 2));
            sb.append(StringPool.SLASH);

            if (getDepth(sb.toString()) > 3) {
                return;
            }
        }
    }
    protected File getFileNameDir(
            long companyId, long repositoryId, String fileName) {

        if (fileName.indexOf(CharPool.SLASH) != -1) {
            return getDirNameDir(companyId, repositoryId, fileName);
        }

        String ext = StringPool.PERIOD + FileUtil.getExtension(fileName);

        StringBundler sb = new StringBundler();

        String fileNameFragment = FileUtil.stripExtension(fileName);

        buildPath(sb, fileNameFragment);

        File repositoryDir = getRepositoryDir(companyId, repositoryId);

        StringBundler pathSB = new StringBundler(6);

        pathSB.append(repositoryDir);
        pathSB.append(StringPool.SLASH);
        pathSB.append(sb.toString());

        FileUtil.mkdirs(pathSB.toString());

        pathSB.append(StringPool.SLASH);
        pathSB.append(fileNameFragment);
        pathSB.append(ext);

        return new File(pathSB.toString());
    }

    protected void getFileNames(
            List<String> fileNames, String dirName, String path) {

        String[] pathDirNames = FileUtil.listDirs(path);

        if (ArrayUtil.isNotEmpty(pathDirNames)) {
            for (String pathDirName : pathDirNames) {
                String subdirName = null;

                if (Validator.isBlank(dirName)) {
                    subdirName = pathDirName;
                }
                else {
                    subdirName = dirName + StringPool.SLASH + pathDirName;
                }

                getFileNames(
                        fileNames, subdirName,
                        path + StringPool.SLASH + pathDirName);
            }
        }
        else if (!dirName.isEmpty()) {
            File file = new File(path);

            if (file.isDirectory()) {
                fileNames.add(dirName);
            }
        }
    }

    protected String removeExtension(String fileName) {

        String ext = FileUtil.getExtension(fileName);

        if (ext != null && !ext.equals("")) {
            fileName = fileName.substring(0, fileName.length() - ext.length() - 1);
        }

        return fileName;
    }

    protected File getFileNameVersionFile(
            long companyId, long repositoryId, String fileName, String version) {

        String ext = StringPool.PERIOD + FileUtil.getExtension(fileName);

        if (version.equals("1")) {
            version = "1.0";
        }

        int pos = fileName.lastIndexOf(CharPool.SLASH);

        if (pos == -1) {
            StringBundler sb = new StringBundler();

            String fileNameFragment = removeExtension(fileName);

            buildPath(sb, fileNameFragment);

            File repositoryDir = getRepositoryDir(companyId, repositoryId);

            return new File(repositoryDir + StringPool.SLASH + sb + StringPool.SLASH + fileNameFragment + ext + StringPool.SLASH + fileNameFragment + StringPool.UNDERLINE + version + ext);
        }
        else {
            File fileNameDir = getDirNameDir(companyId, repositoryId, fileName);

            String fileNameFragment = removeExtension(fileName.substring(pos + 1));

            return new File(fileNameDir + StringPool.SLASH + fileNameFragment + StringPool.UNDERLINE + version + ext);
        }
    }

    protected String getHeadVersionLabel(
            long companyId, long repositoryId, String fileName) {

        File fileNameDir = getFileNameDir(companyId, repositoryId, fileName);

        if (!fileNameDir.exists()) {
            return VERSION_DEFAULT;
        }

        String[] versionLabels = FileUtil.listFiles(fileNameDir);

        String headVersionLabel = VERSION_DEFAULT;

        for (String versionLabel : versionLabels) {
            if (DLUtil.compareVersions(versionLabel, headVersionLabel) > 0) {
                headVersionLabel = versionLabel;
            }
        }

        return headVersionLabel;
    }

    protected String hashRepositoryID(String base, String repositoryId) {
        if(repositoryId.length()==0){
            return base;
        }
        if (repositoryId.length()<=4){
            return base + StringPool.SLASH + repositoryId;
        }
        else{
            return hashRepositoryID(base + StringPool.SLASH + repositoryId.substring(0, 4), repositoryId.substring(4));
        }
    }

    protected File getRepositoryDir(long companyId, long repositoryId) {

        // repositoryId is hashed by 4-length

        String newRepoId = hashRepositoryID("", String.valueOf(repositoryId));

        File repositoryDir = new File(rootDir, StringPool.SLASH + companyId + StringPool.SLASH + newRepoId);

        if (!repositoryDir.exists()) {
            repositoryDir.mkdirs();
        }

        return repositoryDir;
    }

    private void deleteEmptyAncestors(File file) {
        while (file != null) {
            try {
                Files.delete(file.toPath());
            } catch (IOException e) {
                return;
            }

            file = file.getParentFile();
        }
    }

    private final File rootDir;
}
