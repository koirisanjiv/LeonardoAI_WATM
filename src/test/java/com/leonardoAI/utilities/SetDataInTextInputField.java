package com.leonardoAI.utilities;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetDataInTextInputField {
	
	public Logger logger = LogManager.getLogger(this.getClass());
	public Actions action;
	public WebDriverWait wait;
	
	 //USE THIS TO FILL THE DATA IN THE INPUT FILED ONLY FOR TEXT FIELD
    public boolean callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(WebDriver driver,String fieldName, String xpathAddress, String value)
            throws InterruptedException {
        StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
        String callerMethodName = stackTraceElement[2].getMethodName();
        logger.info("Method called 'callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue' and Caller method name: " + callerMethodName);
        
        action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        boolean isClickedOnButton = false;
        try {
            logger.info("Element name and xpath >>"+fieldName + " and >> "+xpathAddress);
            WebElement inputField = driver.findElement(By.xpath(xpathAddress));
            wait.until(ExpectedConditions.elementToBeClickable(inputField));
            action.moveToElement(inputField).build().perform();
            Thread.sleep(200);
            inputField.sendKeys(Keys.CONTROL, "a");
            inputField.sendKeys(Keys.DELETE);
            Thread.sleep(200);
            inputField.sendKeys(value);
            logger.info("Value Enteterd into >> "+fieldName+" with data >> "+value);
            Thread.sleep(200);
            isClickedOnButton = true;
            Thread.sleep(1000);

        } catch (Exception e) {
            logger.info("Exception from callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue >> " + e.getMessage());
        }
        return isClickedOnButton;
    }

}
