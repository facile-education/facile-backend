package com.weprode.nero.document.utils;


import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.document.library.kernel.store.Store;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomFileSystemStore implements Store {

    public CustomFileSystemStore() {

        String path = "data/document_library";

        File rootDir = new File(path);

        if (!rootDir.isAbsolute()) {
            rootDir = new File(PropsUtil.get(PropsKeys.LIFERAY_HOME), path);
        }

        _rootDir = rootDir;
        _rootDir.mkdirs();
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

        _deleteEmptyAncestors(parentFile);
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

        fileNameVersionFile.delete();

        _deleteEmptyAncestors(parentFile);
    }

    @Override
    public InputStream getFileAsStream(
            long companyId, long repositoryId, String fileName,
            String versionLabel)
            throws NoSuchFileException {

        // System.out.println("getFileAsStream with repositoryId=" + repositoryId + ", fileName=" + fileName + ", versionLabel=" + versionLabel);
        if (Validator.isNull(versionLabel)) {
            versionLabel = getHeadVersionLabel(
                    companyId, repositoryId, fileName);
            // System.out.println("versionLabel=" + versionLabel);
        }

        File fileNameVersionFile = getFileNameVersionFile(
                companyId, repositoryId, fileName, versionLabel);
        // System.out.println("fileNameVersionFile=" + fileNameVersionFile.getAbsolutePath());

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

        // System.out.println("getFileVersions for repositoryId " + repositoryId + " and fileName=" + fileName);
        File fileNameDir = getFileNameDir(companyId, repositoryId, fileName);
        // System.out.println("getFileVersions : fileNameDir=" + fileNameDir.getAbsolutePath());

        if (!fileNameDir.exists()) {
            // System.out.println("getFileVersions : Return empty array");
            return StringPool.EMPTY_ARRAY;
        }

        String[] versions = FileUtil.listFiles(fileNameDir);

        Arrays.sort(versions, DLUtil::compareVersions);

        for (int i = 0 ; i < versions.length ; i++) {
            int x = versions[i].lastIndexOf(CharPool.UNDERLINE);
            if (x > -1) {
                int y = versions[i].lastIndexOf(CharPool.PERIOD);
                versions[i] = versions[i].substring(x + 1, y);
                // System.out.println("getFileVersions : Return version = " + versions[i]);
            }
        }
        return versions;
    }

    public File getRootDir() {
        return _rootDir;
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

//        if (ext.equals(StringPool.PERIOD)) {
//            ext += _HOOK_EXTENSION;
//        }

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

        // System.out.println("getFileNameDir returns " + pathSB.toString());

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
            // System.out.println(" > fileNameFragment=" + fileNameFragment);

            buildPath(sb, fileNameFragment);
            // System.out.println(" > sb=" + sb.toString());
            // System.out.println(" > version=" + version);

            File repositoryDir = getRepositoryDir(companyId, repositoryId);
            // System.out.println(" > repositoryDir=" + repositoryDir.getAbsolutePath());
            // System.out.println(" > ext=" + ext);

            String res = repositoryDir + StringPool.SLASH + sb.toString() + StringPool.SLASH + fileNameFragment + ext + StringPool.SLASH + fileNameFragment + StringPool.UNDERLINE + version + ext;
            // System.out.println("getFileNameVersionFile1 returns " + res);

            return new File(repositoryDir + StringPool.SLASH + sb.toString() + StringPool.SLASH + fileNameFragment + ext + StringPool.SLASH + fileNameFragment + StringPool.UNDERLINE + version + ext);
        }
        else {
            File fileNameDir = getDirNameDir(companyId, repositoryId, fileName);

            String fileNameFragment = removeExtension(fileName.substring(pos + 1));

            String res = fileNameDir + StringPool.SLASH + fileNameFragment + StringPool.UNDERLINE + version + ext;
            // System.out.println("getFileNameVersionFile2 returns " + res);
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

        // System.out.println("getHeadVersionLabel returns " + headVersionLabel);
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

        File repositoryDir = new File(_rootDir, StringPool.SLASH + companyId + StringPool.SLASH + newRepoId);

        if (!repositoryDir.exists()) {
            repositoryDir.mkdirs();
        }

        // System.out.println("getRepositoryDir returns " + repositoryDir.getAbsolutePath());
        return repositoryDir;
    }

    private void _deleteEmptyAncestors(File file) {
        while (file != null) {
            if (!file.delete()) {
                return;
            }

            file = file.getParentFile();
        }
    }

    private final File _rootDir;
    private static final String _HOOK_EXTENSION = "afsh";
}
