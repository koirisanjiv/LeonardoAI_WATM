package com.leonardoAI.pageObject;

import java.time.Duration;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.leonardoAI.ReUseAble.PageObject.ReUseAbleElement;
import com.leonardoAI.pageObject.pageLocators.PL_HomePage;
import com.leonardoAI.pageObject.pageLocators.PL_LoginPage;
import com.leonardoAI.utilities.ClickOnAnyButton;
import com.leonardoAI.utilities.NavigateToNewTab;
import com.leonardoAI.utilities.SetDataInTextInputField;

public class PO_LoginPage extends ReUseAbleElement {

	public WebDriver driver;
	public Logger logger = LogManager.getLogger(getClass());
	public JavascriptExecutor jsExecutor;
	public ReUseAbleElement ruae;
	public WebDriverWait wait;
	public Actions action;
	public SoftAssert softAssert = new SoftAssert();
	public SetDataInTextInputField setDataInInputField = new SetDataInTextInputField();
	public NavigateToNewTab navigateToNewTab = new NavigateToNewTab();
	public ClickOnAnyButton clickOnAnyButton = new ClickOnAnyButton();

	public PO_LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		jsExecutor = (JavascriptExecutor) driver;
		ruae = new ReUseAbleElement(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);
	}

	// to find page elements
	@FindBy(xpath = PL_LoginPage.address_elementGetStarted)
	@CacheLookup
	public WebElement elementGetStarted;

	@FindBy(xpath = PL_LoginPage.address_elementSingUpOrLoginWith)
	@CacheLookup
	WebElement elementSingUpOrLoginWith;

	@FindBy(xpath = PL_LoginPage.address_tabCloseModel)
	@CacheLookup
	WebElement tabCloseModel;


	public boolean isSingInPageAppears() {
		boolean isSingInPageAppears = false;
		try {
			isSingInPageAppears = elementSingUpOrLoginWith.isDisplayed();
		} catch (Exception e) {
			isSingInPageAppears = false;
		}

		return isSingInPageAppears;
	}

	// TO CLICK ON THE CLOSE MODEL IF PRESENT
	public void clickOnCloseModelIfPresent() throws InterruptedException {
		try {
			if (tabCloseModel.isDisplayed() && tabCloseModel.isEnabled()) {
				tabCloseModel.click();
				logger.info("clicke on tabCloseModel");
				Thread.sleep(200);
			}
		} catch (Exception e) {
			logger.info("Exception from clickOnCloseModelIfPresent: " + e.getMessage());
			logger.info("Close model not present");
		}

	}

	public boolean isLoginSuccessful() {
		boolean isLoginDone = false;
		int loopcount = 1;
		while (loopcount <= 20) {
			try {
				Thread.sleep(500);
				WebElement leonardoLogo = driver.findElement(By.xpath(PL_HomePage.address_logoLeonardoAI));
				isLoginDone = leonardoLogo.isDisplayed();
				break;
			} catch (Exception e) {
				if (loopcount == 20) {
					logger.info("Exception from isLoginSuccessful: " + e.getMessage());
				}
			}
		}
		return isLoginDone;
	}

	// TO CLICK ON THE CLOSE BUTTON MODEL GETTING STARTED
	public void clickOnCloseModelGettingStarted() throws InterruptedException {

		int loopcount = 0;
		boolean isGettingStartedModelPresent = false;
		while (loopcount <= 20) {
			try {
			
				logger.info("Loop count : "+loopcount);
				Thread.sleep(1000);
				WebElement btnGettingStartedCloseButton = driver.findElement(By.xpath(PL_LoginPage.address_gettingStartedCloseButton));
				if (btnGettingStartedCloseButton.isDisplayed()) {
					btnGettingStartedCloseButton.click();
					logger.info("clicke on btnGettingStartedCloseButton");
					
					loopcount++;
					isGettingStartedModelPresent = true;
					break;
				}
			} catch (Exception e) {
				if (loopcount == 20 && isGettingStartedModelPresent) {
					logger.info("Exception from btnGettingStartedCloseButton: " + e.getMessage());
					logger.info("btnGettingStartedCloseButton not present");
				}
				loopcount++;
			}
		}
		if (isGettingStartedModelPresent) {
			clickOnCloseModelIntroGuide(PL_LoginPage.address_introGuide);
		}

	}

	// TO CLICK ON THE BUTTON INTRO GUIDE
	public void clickOnCloseModelIntroGuide(String introGuidePath) throws InterruptedException {
		int loopcount = 0;
		boolean isGettingStartedModelPresent = false;
		while (loopcount <= 20) {
			try {
				logger.info("loopcount: "+loopcount);
				Thread.sleep(1000);
				WebElement btnIntroGuide = driver.findElement(By.xpath(introGuidePath));
				
				if (btnIntroGuide.isDisplayed() && btnIntroGuide.isEnabled()) {
					btnIntroGuide.click();
					logger.info("clicke on btnIntroGuide");
					
					driver.findElement(By.xpath(introGuidePath)).click();
					Thread.sleep(1000);
					loopcount++;
					isGettingStartedModelPresent = true;
					break;
				}
			} catch (Exception e) {
				if (loopcount == 20 && isGettingStartedModelPresent) {
					logger.info("Exception from btnIntroGuide: " + e.getMessage());
					logger.info("btnIntroGuide not present");
				}
				loopcount++;
			}
		}

	}

	// FOR USER LOGIN
	public PO_HomePage Login(String userEmail, String userPassword) throws InterruptedException {
		try {
			logger.info("Method called Login: Login");
			
			navigateToNewTab.changeBetweenTabs(driver, "Launch App", PL_LoginPage.address_btnLaunchApp);
			if (isSingInPageAppears()) {
				setDataInInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "Email", PL_LoginPage.address_textemail, userEmail);
				setDataInInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "Password", PL_LoginPage.address_textpassword, userPassword);
				clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Submit", PL_LoginPage.address_btnSignIn);
			}

			clickOnCloseModelGettingStarted();

			if (isLoginSuccessful()) {
				softAssert.assertTrue(true);
				logger.info("✅✅✅ Login PASS ...");
			} else {
				softAssert.assertTrue(false);
				logger.info("❎❎❎ Login FAILEED !!!");
			}

		} catch (Exception e) {
		}
		softAssert.assertAll();
		return new PO_HomePage(driver);
	}

}
