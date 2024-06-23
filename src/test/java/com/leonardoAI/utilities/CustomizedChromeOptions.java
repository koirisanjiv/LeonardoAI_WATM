package com.leonardoAI.utilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions; // Correct import

public class CustomizedChromeOptions {

	// THIS IS USED FOR THE CHROME OPTIONS TO BLOCK THE ADS, NOTIFICATIONS,
	// POP-BLOCKING, TO RUN INTO INCOGNITO MODE, OR HEADER LESS BROWSING, OR RUN IN
	// THE DEBUGGER MODE.
	public Logger logger = LogManager.getLogger(this.getClass());

	public ChromeOptions customizedChromeOptions(boolean blockAdsAndNotifications, boolean headlessBrowsing,
			boolean incognitoMode, boolean debuggerMode, int debuggerPort, boolean wantToTackActionOnFiles, String fileLocation) {
		
		// TO INITIALIZE CHROME OPTIONS
		ChromeOptions options = new ChromeOptions(); // Use the correct class name and variable

		if (blockAdsAndNotifications) {
			// Disable pop-ups and intrusive ads
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-ads");
			logger.info("Disabled Ads and Notifications");
		}

		if (headlessBrowsing) {
			// FOR HEADER LESS BROWSING
			options.addArguments("--headless");
			logger.info("Entered into headless browsing");
		}

		if (incognitoMode) {
			// TO OPEN CHROME DRIVER INTO INCOGNITO MODE
			options.addArguments("--incognito");
			logger.info("Entered into incognito mode");
		}

		if (debuggerMode) {
			// TO USE CHROME DRIVER IN DEBUGGER MODE
			options.setExperimentalOption("debuggerAddress", "localhost:"+debuggerPort);
			logger.info("Entered into Debugging mode with port:"+debuggerPort+" Is debuggerMode"+debuggerMode);
		}
		
		if(wantToTackActionOnFiles) {
			
			String downloadFilePath = fileLocation;
	        Map<String, Object> actionsOnFiles = new HashMap<>();
	        actionsOnFiles.put("download.default_directory", downloadFilePath);
	        actionsOnFiles.put("download.prompt_for_download", false);
	        actionsOnFiles.put("download.directory_upgrade", true);
	        actionsOnFiles.put("safebrowsing.enabled", true);
	        options.setExperimentalOption("prefs", actionsOnFiles);
	        logger.info("File actions parameters set successfully");
		}
		
		return options;
	}
}
