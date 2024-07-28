package com.leonardoAI.projectUtility;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenCMDAndRunBatFile {
	private static final Logger logger = LogManager.getLogger(OpenCMDAndRunBatFile.class);

    public static boolean openCMDAndRunBatFile(String batFileAddress, String batchFileName) {
    	StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("Method called setFileDirectoryAndName & caller method name: " + callerMethodName);
		
        try {
            System.out.println("CMD directory address: " + batFileAddress);

            // Construct the command to change directory and execute the batch file
            String command = "cd /d " + batFileAddress + " && " + batchFileName;

            // Create and start the Command Prompt process
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", "cmd", "/k", command);
            Process process = processBuilder.start();

            // Optionally wait for some time to ensure the command is executed
            Thread.sleep(5000); // Adjust the delay as needed

            // Return true if the process was started
            return process.isAlive();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void main(String[] args) {
        String directory = System.getProperty("user.dir") + "\\AutoIT";
        boolean success = openCMDAndRunBatFile(directory, "AutoIT.bat");
        System.out.println("Command Prompt executed successfully: " + success);
    }
}
