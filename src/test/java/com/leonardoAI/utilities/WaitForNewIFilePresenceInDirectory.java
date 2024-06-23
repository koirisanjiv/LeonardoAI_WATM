package com.leonardoAI.utilities;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WaitForNewIFilePresenceInDirectory {
	public Logger logger = LogManager.getLogger(getClass());
//	public static void main(String[] args) {
//		String fileDirectory = "C:\\Users\\Users\\Download\\";
//		File newFileDownload = new File(fileDirectory);
//		
//		waitForNewFileInDirectory(newFileDownload, 6000);
//
//	}

	// Wait till file not downloaded successfully
	public boolean waitForNewFileInDirectory(File directory, long timeout) {
		long endTime = System.currentTimeMillis() + timeout;
		File[] initialFiles = directory.listFiles();

		while (System.currentTimeMillis() < endTime) {
			File[] currentFiles = directory.listFiles();

			if (initialFiles != null && currentFiles != null && currentFiles.length > initialFiles.length) {
				List<File> newFiles = Arrays.stream(currentFiles)
						.filter(file -> !Arrays.asList(initialFiles).contains(file)).collect(Collectors.toList());

				for (File file : newFiles) {
					logger.info("New file detected: " + file.getName());
					if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) { // Add more extensions as
																								// needed
						logger.info("✅✅✅ Image downloaded successfully.");
						return true;
					}
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				logger.warn("❎❎❎ Image download failed or timed out.");
				return false;
			}
		}
		return false;
	}

}
