package com.leonardoAI.utilities;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickOnAnyButton {

	public Logger logger = LogManager.getLogger(this.getClass());
	public Actions action;
	public WebDriverWait wait;
	
	//USE THIS TO CLICK ON ANY BUTTON TYPE ELEMENTS
    public boolean callMeToClickOnAnyButtonWithNameAndXpath(WebDriver driver, String buttonName, String xpathAddress)
            throws InterruptedException {
        StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
        String callerMethodName = stackTraceElement[2].getMethodName();
        logger.info("Method called 'callMeToClickOnAnyButtonWithNameAndXpath' and Caller method name: " + callerMethodName);
       
        action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        boolean isClickedOnButton = false;
        try {
            logger.info("Element name and xpath >> "+buttonName + " and >> "+xpathAddress);
            WebElement button = driver.findElement(By.xpath(xpathAddress));
            wait.until(ExpectedConditions.elementToBeClickable(button));
            //button.click();
            action.moveToElement(button).build().perform();
            Thread.sleep(200);
            action.moveToElement(button).click().build().perform();
            isClickedOnButton = true;
            logger.info("clicke on button >> " + buttonName);
            Thread.sleep(1000);

        } catch (Exception e) {
            logger.info("Exception from callMeToClickOnAnyButtonWithNameAndXpath >> " + e.getMessage());
        }
        return isClickedOnButton;
    }
}
