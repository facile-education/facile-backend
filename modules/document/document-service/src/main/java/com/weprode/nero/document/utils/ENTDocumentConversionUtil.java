package com.weprode.nero.document.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.*;

import java.io.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ENTDocumentConversionUtil {

    private ENTDocumentConversionUtil() {}
    private static final Log logger = LogFactoryUtil.getLog(ENTDocumentConversionUtil.class);

    private static final Map<String, String> convertState = new HashMap<>();
    private static final Map<String, Process> convertProcess = new HashMap<>();

    /**
     * Convert the source file in input stream to a new file with the targetExtension type
     */
    public static File convert(
            String id, InputStream is, String sourceExtension,
            String targetExtension, Map<String, String> parameters)
            throws SystemException {

        if (id == null) {
            return null;
        }
        logger.warn("BEGIN CONVERSION METHOD : Convert file " + id + " from " + sourceExtension + " to " + targetExtension);

        String fileName = getFileName(id, targetExtension);
        File fileResult = new File(fileName);
        boolean forceConversion = !parameters.isEmpty();

        // If the file is in cache then don't convert it again
        if (fileResult.exists() && !forceConversion) {
            logger.warn("File already converted, directly return file in cache");
            convertState.put(id, "done");
            return fileResult;
        }

        if (targetExtension.equals("mp4") || targetExtension.equals("mp3")) {
            // Start media conversion
            fileResult = startMediaConversion(fileResult, is, id, sourceExtension, targetExtension, fileName);
        }

        // Process has ended without being killed
        if(convertProcess.containsKey(id)){
            convertProcess.remove(id);

            // Check if the conversion has ended correctly (the new file is created)
            if(fileResult.exists()){
                logger.warn("Conversion process successfully done");
                convertState.put(id, "done");
                return fileResult;
            } else {
                logger.error("Conversion process failed");
                convertState.put(id, "error");
                return null;
            }
        }
        else {
            return null;
        }
    }

    /**
     * Build complete temp filename from id and extension
     * @param id ID from the file
     * @param extension file type
     * @return the temp file name
     */
    private static String getFileName(String id, String extension) {
        return SystemProperties.get(SystemProperties.TMP_DIR) +
                "/liferay/document_conversion/" +
                id +
                StringPool.PERIOD +
                extension;
    }

    /**
     * Find infos concerning video or audio file before conversion
     * @param filePath the source file path
     */
    private static void getMediaFileInfos(String filePath, String id) {
        String [] cmdProbe = ("ffprobe " + filePath).split(" ");

        try {
            final Process p = Runtime.getRuntime().exec(cmdProbe);
            convertProcess.put(id+"getInfos", p);
            new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                    String line = "";
                    try {
                        while((line = reader.readLine()) != null) {
                            if (line.contains("Duration")) {
                                //Duration: 01:38:16.28,     We take the hour, minute and seconds values
                                Pattern pattern = Pattern.compile("Duration: (.*?)[.]");
                                Matcher matcher = pattern.matcher(line);
                                if (matcher.find())
                                {
                                    // Get value in seconds
                                    String durationStr = matcher.group(1);

                                    String[] timeValues = durationStr.split(":");
                                    if (timeValues.length == 3) {
                                        mediaDuration = Integer.parseInt(timeValues[0]) * 3600 + Integer.parseInt(timeValues[1]) * 60 + Integer.parseInt(timeValues[2]);
                                    }
                                }
                            }
                            if (line.contains("Video: h264") && line.contains("kb/s")) {
                                isH264 = true;
                            }
                        }
                    } finally {
                        reader.close();
                    }
                } catch(IOException ioe) {
                    logger.error(ioe);
                }
            }).start();

            p.waitFor();
        }catch(Exception e) {
            logger.error(e);
        }
    }

    /**
     * Launch media conversion which will init the needed parameters
     */
    private static File startMediaConversion(File fileResult, InputStream is, String id, String sourceExtension,
                                             String targetExtension, String fileName) {
        String command = "";

        String fileSourceName = getFileName(id, sourceExtension);

        // Write temp source file
        File fileSourceTmp = new File(fileSourceName);
        try {
            FileUtil.write(fileSourceTmp, is);
            logger.info("Tmp file written to " + fileSourceTmp.getAbsolutePath());
        }
        catch (IOException e) {
            logger.error(e);
            return new File(fileResult.getPath());
        }

        isH264 = false;
        getMediaFileInfos(fileSourceTmp.getPath(), id);

        if (targetExtension.equals("mp3")) {
            command = "ffmpeg -i " + fileSourceTmp.getPath() + " -movflags +faststart " + fileName;
        }
        else if(targetExtension.equals("mp4")) {
			/* If we have more args -> need a command update
			String sampleRate = parameters.containsKey("sampleRate") ? parameters.get("sampleRate") : "22050";
			String avSync = parameters.containsKey("avSync") ? parameters.get("avSync") : "-mc 0";
			String frameRate = parameters.containsKey("frameRate") ? parameters.get("frameRate") : ""; // frameRate doit contenir "-ofps 25" par exemple
			String bitrateAudio = parameters.containsKey("bitrateAudio") ? parameters.get("bitrateAudio") : "56";
			String bitrateVideo = parameters.containsKey("bitrateVideo") ? parameters.get("bitrateVideo") : "250"; // frameRate doit contenir "-ofps 25" par exemple

			String videoSize = parameters.containsKey("videoSize") ? parameters.get("videoSize") : "360x240";
			String[] size = videoSize.split("x");
			String videoWidth = size[0];
			String videoHeight = size[1];
			String videoScale = "-vf scale=";
			boolean keepAspect = Boolean.getBoolean(parameters.containsKey("keepAspect") ? parameters.get("keepAspect") : "true");
			if (keepAspect) {
				videoScale += videoWidth + ":-2,expand=:" + videoHeight + ":::,crop=" + videoWidth + ":" + videoHeight + ",harddup";
			}
			else {
				videoScale += videoWidth + ":" + videoHeight + ",harddup";
			}
			*/

            //-movflags +faststart   thanks to this option the video will start before file is entirely loaded
            if (isH264) {
                //If we can detect if video encoding is h264 use copy only to optimize (using ffprobe ?) :
                command = "ffmpeg -i " + fileSourceTmp.getPath() + " -vcodec copy -acodec copy -movflags +faststart " + fileName;
            }
            else {
                command = "ffmpeg -i " + fileSourceTmp.getPath() + " -c:v libx264 -preset ultrafast -c:a aac -movflags +faststart -strict -2 " + fileName;
            }
        }

        logger.warn("Command used : " + command);
        fileResult = runMediaConversion(command, fileResult, fileName, id);

        // Delete the temp source file created
        if (FileUtil.exists(fileSourceTmp)) {
            FileUtil.delete(fileSourceTmp);
        }

        return fileResult;
    }

    /**
     * Run media conversion process that's describe in command parameter.
     * Return the converted file
     */
    private static File runMediaConversion(String command, File fileResult, String fileName, String id) {
        logger.warn("Media conversion");
        final String secureId = id;
        String [] cmd = command.split(" ");

        try {
            final Process p = Runtime.getRuntime().exec(cmd);
            convertProcess.put(secureId, p);
            convertState.put(secureId+"duration", String.valueOf(mediaDuration));
            // Write convert process state informations
            new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                    String line = "";
                    try {
                        while((line = reader.readLine()) != null) {
                            convertState.put(secureId, line);
                            logger.debug(line);
                        }
                    } finally {
                        reader.close();
                    }
                } catch(IOException ioe) {
                    logger.error(ioe);
                }
            }).start();
            // Waiting for the process to end
            p.waitFor();
            fileResult = new File(fileName);

        }catch(Exception e) {
            logger.error(e);
        }

        return fileResult;
    }

    // If video has the right encoding we can handle faster conversion
    private static boolean isH264 = false;

    // Media duration on seconds (Use for the progress bar)
    private static int mediaDuration = 0;

}
