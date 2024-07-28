package com.leonardoAI.projectUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddArgumentsInAutoITScriptFile {
	private static final Logger logger = LogManager.getLogger(AddArgumentsInAutoITScriptFile.class);

	public static boolean setFileDirectoryAndName(String uploadFileAddress, String uploadFileName)
			throws InterruptedException {
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("Method called setFileDirectoryAndName & caller method name: " + callerMethodName);

		try {
			String autoItScriptFileAddress = "./" + "\\AutoIT" + "\\autoITScript.au3";
			// Read the existing content of the .au3 file
			String content = new String(Files.readAllBytes(Paths.get(autoItScriptFileAddress)));

			// Replace placeholders with actual values
			content = content.replace("$sDir", "\"" + uploadFileAddress + "\"");
			content = content.replace("$sFileName", "\"" + uploadFileName + "\"");

			// Write the updated content back to the file
			Files.write(Paths.get(autoItScriptFileAddress), content.getBytes(), StandardOpenOption.WRITE,
					StandardOpenOption.TRUNCATE_EXISTING);

			System.out.println("AutoIt script updated successfully.");
			Thread.sleep(2000);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) throws InterruptedException {

		String fileAddress = "C:\\Users\\koiri\\Downloads\\LeonardoAI";
		String filename = "lord-hanuman-8535687.png";
		setFileDirectoryAndName(fileAddress, filename);
	}

}
