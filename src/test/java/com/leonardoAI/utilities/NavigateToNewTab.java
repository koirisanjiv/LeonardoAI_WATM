package com.leonardoAI.utilities;

import java.time.Duration;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigateToNewTab {
	public Logger logger = LogManager.getLogger(this.getClass());
	public Actions action;
	public WebDriverWait wait;
	public ClickOnAnyButton clickOnAnyButton = new ClickOnAnyButton();
	
	
	//USE THIS TO NAVIGATE NEW WINDOW
    public void changeBetweenTabs(WebDriver driver, String buttonNameOnclickNewTabOpen, String xpathAddressOnclickNewTabOpen) throws InterruptedException {
    	 StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
         String callerMethodName = stackTraceElement[2].getMethodName();
         logger.info("Method called 'changeBetweenTabs' and Caller method name: " + callerMethodName);
        
    	
    	action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        // Capture the original window handle[use it for come back]
        String originalWindow = driver.getWindowHandle();
        logger.info("originalWindow: " + driver.getTitle());
        // Capture all the open window tabs[show the number of current window opened]
        Set<String> existingWindows = driver.getWindowHandles();
        for (String tabs : existingWindows) {
            logger.info("Open Windows: " + tabs);
        }

        clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver,buttonNameOnclickNewTabOpen, xpathAddressOnclickNewTabOpen);
        // Get the new window handle
        Set<String> newWindowHandles = driver.getWindowHandles();
        newWindowHandles.removeAll(existingWindows);
        String newWindowHandle = newWindowHandles.iterator().next();

        // Switch to the new tab
        driver.switchTo().window(newWindowHandle);
        logger.info("newWindowHandle: " + driver.getTitle());
        driver.getCurrentUrl();
        logger.info("New Window: " + driver.getTitle());
        logger.info("Switched to the new tab");
    }

}
