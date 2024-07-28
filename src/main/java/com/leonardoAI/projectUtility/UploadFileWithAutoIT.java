package com.leonardoAI.projectUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UploadFileWithAutoIT {
    private static final Logger logger = LogManager.getLogger(UploadFileWithAutoIT.class);
  

    public static void uploadFileWithAutoIT(String autoItExecutableName, String uploadFileAddress, String uploadFileName) {
    	StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("Method called uploadFileWithAutoIT & caller method name: " + callerMethodName);
		
		String autoItExecutablePath = System.getProperty("user.dir") + "\\AutoIT\\"+autoItExecutableName;
    	logger.info("autoItExecutablePath: "+autoItExecutablePath);
    	
        try {
        	Thread.sleep(2000);
        	//Update the autoIT script
        	logger.info("uploadFileAddress: "+uploadFileAddress);
        	logger.info("uploadFileName: "+uploadFileName);
        	AddArgumentsInAutoITScriptFile.setFileDirectoryAndName(uploadFileAddress, uploadFileName);
        	Thread.sleep(3000);
        	
        	//Compile the autoIT script
        	OpenCMDAndRunBatFile.openCMDAndRunBatFile(System.getProperty("user.dir")+"\\AutoIT","AutoIT.bat");
        	Thread.sleep(5000);
            logger.info("Starting AutoIt script execution...");
            
            // Construct the path to the AutoIt executable
            ProcessBuilder processBuilder = new ProcessBuilder(autoItExecutablePath);
            Process process = processBuilder.start();
            
            // Capture and log the output of the process
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s;
            while ((s = stdInput.readLine()) != null) {
            	logger.info("AutoIt Output: " + s);
            }
            while ((s = stdError.readLine()) != null) {
            	logger.info("AutoIt Error: " + s);
            }
            
            int exitCode = process.waitFor();  // Wait for the process to complete
            logger.info("AutoIt script execution completed with exit code: " + exitCode);
            
            Thread.sleep(2000); // Optional delay
            
        } catch (IOException | InterruptedException e) {
        	logger.info("Exception during AutoIt script execution: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for detailed error analysis
        }
    }
    
    public static void main(String[] args) {
    	String autoItExecutablePath = System.getProperty("user.dir") + "\\autoITApplication.exe";
    	String uploadFileAddress = "C:\\Users\\koiri\\Downloads\\LeonardoAI";
        String uploadFileName = "lord-hanuman-8535687.png";
    	uploadFileWithAutoIT(autoItExecutablePath,uploadFileAddress,uploadFileName);
    }
}
