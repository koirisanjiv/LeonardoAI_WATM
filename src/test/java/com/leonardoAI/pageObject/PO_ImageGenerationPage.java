package com.leonardoAI.pageObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.leonardoAI.ReUseAble.PageObject.ReUseAbleElement;
import com.leonardoAI.pageObject.pageLocators.PL_ImageGenerationPage;
import com.leonardoAI.pageObject.pageLocators.PL_LoginPage;
import com.leonardoAI.projectUtility.FindThreeDotAndClick;
import com.leonardoAI.testCases.BaseClass;
import com.leonardoAI.utilities.CheckCloseButtonPresece;
import com.leonardoAI.utilities.CheckElementIsEnabled;
import com.leonardoAI.utilities.ClickOnAnyButton;
import com.leonardoAI.utilities.NavigateToNewTab;
import com.leonardoAI.utilities.SetDataInTextInputField;
import com.leonardoAI.utilities.WaitForNewIFilePresenceInDirectory;

public class PO_ImageGenerationPage extends ReUseAbleElement {

	// CONSTRUCTOR DECLARATION
	public WebDriver driver;
	public Logger logger = LogManager.getLogger(getClass());
	public JavascriptExecutor jsExecutor;
	public ReUseAbleElement ruae;
	public WebDriverWait wait;
	public PO_LoginPage lp;
	public Actions action;
	public SoftAssert softAssert = new SoftAssert();

	public SetDataInTextInputField setDataInInputField = new SetDataInTextInputField();
	public NavigateToNewTab navigateToNewTab = new NavigateToNewTab();
	public ClickOnAnyButton clickOnAnyButton = new ClickOnAnyButton();
	public WaitForNewIFilePresenceInDirectory filePreseceIndirectory = new WaitForNewIFilePresenceInDirectory();
	public CheckElementIsEnabled checkElementEnable = new CheckElementIsEnabled();
	public CheckCloseButtonPresece checkCloseButtonPresence = new CheckCloseButtonPresece();

	// CONSTRUCTOR CREATION
	public PO_ImageGenerationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		jsExecutor = (JavascriptExecutor) driver;
		ruae = new ReUseAbleElement(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(150));
		lp = new PO_LoginPage(driver);
		action = new Actions(driver);

	}

	// ELEMENT LOCATORES

	@FindBy(xpath = PL_ImageGenerationPage.address_elementIsImageGeneratePageReady)
	@CacheLookup
	public WebElement elementIsImageGeneratePageReady;

	@FindBy(xpath = PL_ImageGenerationPage.address_listGeneratedImage)
	@CacheLookup
	public List<WebElement> listGeneratedImage;

	@FindBy(xpath = PL_ImageGenerationPage.address_btnDownloadImage)
	@CacheLookup
	public WebElement btnDownloadImage;

	@FindBy(xpath = PL_ImageGenerationPage.address_listBtnSelectImage)
	@CacheLookup
	public List<WebElement> listBtnSelectImage;

	@FindBy(xpath = PL_ImageGenerationPage.address_btnDownloadImageOnSelectImage)
	@CacheLookup
	public WebElement btnDownloadImageOnSelectImage;

	@FindBy(xpath = PL_ImageGenerationPage.address_btnOriginalImage)
	@CacheLookup
	public WebElement btnOriginalImage;

	// TO CLICK ON THE CLOSE BUTTON MODEL GETTING STARTED
	public void clickOnCloseModelGettingStarted() throws InterruptedException {

		int loopcount = 0;
		boolean isGettingStartedModelPresent = false;
		while (loopcount <= 20) {
			try {

				logger.info("Loop count : " + loopcount);
				Thread.sleep(1000);
				WebElement btnGettingStartedCloseButton = driver
						.findElement(By.xpath(PL_ImageGenerationPage.address_gettingStartedCloseButton));
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

	}

	// TO CLICK ON THE BUTTON INTRO GUIDE
	public void clickOnCloseModelIntroGuide(String closeButtonBaseAddress) throws InterruptedException {
		int loopcount = 0;
		while (loopcount < 5) {
			for (int i = 1; i < 4; i++) {
				String closeButtonAddress = "(" + closeButtonBaseAddress + ")[" + i + "]";
				try {
					Thread.sleep(1000);
					WebElement buttonClose = driver.findElement(By.xpath(closeButtonAddress));
					buttonClose.click();
				} catch (Exception e) {
					logger.info("Exception : >> " + "Inner loop count: " + i + "Outer loop count: " + loopcount);
				}
			}
			loopcount++;
		}
	}

	public boolean checkIsImageGeneratePageReady() throws InterruptedException {
		boolean isImageGeneratePageReady = false;
		try {
			clickOnCloseModelIntroGuide(PL_ImageGenerationPage.address_closseButtonGuige);
			wait.until(
					ExpectedConditions.textToBePresentInElement(elementIsImageGeneratePageReady, "Image Generation"));
			if (elementIsImageGeneratePageReady.isDisplayed()) {
				isImageGeneratePageReady = true;
			}
		} catch (Exception e) {
			logger.info("Exception from: checkIsImageGeneratePageReady >> " + e.getMessage());
		}

		return isImageGeneratePageReady;
	}

	public boolean checkIsImageGenerated() {
		boolean isImageGenerated = false;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(500));
			WebElement isImageStillGenerating = driver
					.findElement(By.xpath(PL_ImageGenerationPage.address_isImageStillGenerating));
			logger.info("Going go wait for: 500 seconds dynamically");
			wait.until(ExpectedConditions.invisibilityOf(isImageStillGenerating));

			try {
				isImageStillGenerating.isDisplayed();

			} catch (Exception e) {
				isImageGenerated = true;
				logger.info("✅✅✅ Image generated successfully");
			}

		} catch (Exception e) {
			logger.info("Exception: " + e.getMessage());
		}
		return isImageGenerated;
	}

	public void findImageFromList(int imageSerialNumber) throws InterruptedException {
		String address_specificImage = "(" + PL_ImageGenerationPage.address_listGeneratedImage + ")["
				+ imageSerialNumber + "]";
		logger.info("Catched image address: "+address_specificImage);
		WebElement visitAnySpecificImage = driver.findElement(By.xpath(address_specificImage));
		action.moveToElement(visitAnySpecificImage).pause(200).build().perform();
		Thread.sleep(200);
	}

	public void clickOnBtnDownloadImagePresentOnTheImage(int imageSerialNumber) throws InterruptedException {
		findImageFromList(imageSerialNumber);
		Thread.sleep(3000);
		try {
			action.moveToElement(btnDownloadImage).build().perform();
			//btnDownloadImage.clear();
			action.moveToElement(btnDownloadImage).click().build().perform();
			logger.info("Clicked on the Donwlaod Image");
			filePreseceIndirectory.waitForNewFileInDirectory(new File(BaseClass.fileLocation), 60000);
		} catch (Exception e) {
			logger.info("Exception : "+e.getMessage());
		}
	}

	// ======END======PAGE OBJECT FOR ADD USERS LEBELS ACTOIN METHODS==========//

	// TO GENERATE IMAGE
	public PO_ImageGenerationPage generateImage(String yourPrompt) throws Throwable {

		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		logger.info("Caller methods name: " + callerMethodName);

		if (checkIsImageGeneratePageReady()) {
			setDataInInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(driver, "SetPrompt",
					PL_ImageGenerationPage.address_textareaPromptInput, yourPrompt);
			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(driver, "Generate Image",
					PL_ImageGenerationPage.address_btnGenerateImage);
			checkIsImageGenerated();
		}

		softAssert.assertAll();
		return new PO_ImageGenerationPage(driver);
	}

	// TO DOWNLOAD IMAGE
	public PO_ImageGenerationPage downloadImage(int imageSerialNumber) throws InterruptedException {
		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		logger.info("Caller methods name: " + callerMethodName);

		clickOnBtnDownloadImagePresentOnTheImage(imageSerialNumber);

		softAssert.assertAll();
		return new PO_ImageGenerationPage(driver);

	}

}
