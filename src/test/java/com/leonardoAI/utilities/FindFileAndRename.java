package com.leonardoAI.utilities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.leonardoAI.testCases.BaseClass;

public class FindFileAndRename {
	// TAKE ACTION ON THE DOWNLOAD FILES
	public Logger logger = LogManager.getLogger(this.getClass());

	public void actionOnDownloadedFiles(String newFileName, String newFolderName, boolean wantToRenameFile) {
		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		logger.info("Caller methods name: " + callerMethodName);

		// Now, visit and rename the most recently downloaded file
		File folder = new File(BaseClass.fileLocation);
		File[] listOfFiles = folder.listFiles();

		if (listOfFiles != null && listOfFiles.length > 0) {
			// remove the desired folder from the list
			List<File> filteredFiles = Arrays.stream(listOfFiles)
					.filter(file -> !file.isDirectory() || !file.getName().equalsIgnoreCase("LeonardoAI"))
					.collect(Collectors.toList());

			if (filteredFiles.isEmpty()) {
				logger.info("No files found in the download directory after filtering out 'LeonardoAI' folder.");
				return;
			}

			// Sort files by last modified date in descending order (latest file first)
            filteredFiles.sort(Comparator.comparingLong(File::lastModified).reversed());

            // Get the latest file
            File latestFile = filteredFiles.get(0);
            
			// Print the latest file name for debugging
			logger.info("Latest downloaded file: " + latestFile.getName());

			// Create new folder if it does not exist
			File newFolder = new File(BaseClass.fileLocation + "/" + newFolderName);
			if (!newFolder.exists()) {
				if (newFolder.mkdir()) {
					logger.info("Folder created successfully: " + newFolder.getPath());
				} else {
					logger.warn("Failed to create folder: " + newFolder.getPath());
				}
			}

			
            
			try {
				// Move the latest file to the new folder with the new name
				File renamedFile = null;
	            if (wantToRenameFile) {
	            	renamedFile = new File(newFolder.getPath() + "/" + newFileName);
	            } else {
	            	renamedFile = new File(newFolder.getPath() + "/" + latestFile.getName());
	            }
				// Use Files.move to rename and move the file
				Files.move(latestFile.toPath(), renamedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				logger.info("File renamed and moved successfully to: " + renamedFile.getPath());
			} catch (Exception e) {
				logger.warn("Failed to rename and move the file: " + e.getMessage());
				e.printStackTrace();
			}

		} else {
			System.out.println("No files found in the download directory");
		}
	}
}