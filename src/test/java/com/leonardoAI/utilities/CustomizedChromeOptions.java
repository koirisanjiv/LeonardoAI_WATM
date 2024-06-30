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
		
		if (wantToTackActionOnFiles) {
            try {
                String downloadFilePath = fileLocation;
                Map<String, Object> prefs = new HashMap<>();
                logger.info("1");
                prefs.put("download.default_directory", downloadFilePath);
                logger.info("2");
                prefs.put("download.prompt_for_download", false);
                logger.info("3");
                prefs.put("download.directory_upgrade", true);
                logger.info("4");
                prefs.put("safebrowsing.enabled", true);
                logger.info("5");
                options.setExperimentalOption("prefs", prefs);
                logger.info("File actions parameters set successfully");
            } catch (Exception e) {
                logger.warn("Exception while file actions: " + e.getMessage());
            }
        }
		
		return options;
	}
}
