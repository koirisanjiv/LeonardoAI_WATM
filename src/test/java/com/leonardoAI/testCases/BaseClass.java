package com.leonardoAI.testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;
import com.leonardoAI.pageObject.PO_HomePage;
import com.leonardoAI.pageObject.PO_LoginPage;
import com.leonardoAI.utilities.CustomizedChromeOptions;
import com.leonardoAI.utilities.ReadConfigFiles;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	// FOR FILE ACTION
	public static String fileLocation = "C:\\Users\\koiri\\Downloads";
	private static boolean wantToTackActionOnFiles = false;

	public static WebDriver driver;
	public PO_HomePage hp;
	public PO_LoginPage lp;
	public SoftAssert softAssert = new SoftAssert();
	private CustomizedChromeOptions cco = new CustomizedChromeOptions();

	// TO LOG THE MESSAGES ON THE CONSOLE AND LOG FILES BOTH
	public Logger logger = LogManager.getLogger(this.getClass());

	// TO READ THE FILE FROM THE utilities.ReadConfigFiles
	private ReadConfigFiles rcf = new ReadConfigFiles();
	private String baseUrl = rcf.getApplicationUrl();
	private String baseUrlDebuggerMode = rcf.getApplicationUrlDebuggerMode();

	// WHILE COMMENTING THIS TWO LINE ENSURES FIRST, THIS TWO PARAMETER PASS THROUGH
	// DATA PROVIDES METHODS FOR THAT WHERE IT USING PASS THERE @DATAPROVIDER NAME
	private String userEmail = rcf.getUserEmail();
	private String userPassword = rcf.getUserPassword();
	private String adminEmail = rcf.getAdminEmail();
	private String adminPassword = rcf.getAdminPassword();

	// FAKER LIBRARY TO GENERATE RADOM DATA FOR THE TEST
	public Faker faker = new Faker();

	// DATA FOR BROWSER SETTINGS FROM TEXT.XML FILES
	private boolean wantToByPassLoginLogout;
	private boolean wantToEnableDebuggerMode;
	private int debuggerPort;
	private boolean incognitoMode;
	private boolean headerLessBrowsing;
	private boolean wantToBlockAdsAndNotifications;

	@BeforeTest
	public void getDataFromTestNg(ITestContext context) {
		// TO ENABLE/DISABLE DEBUGGGER MODE
		String paramEnableDisableDebuggerMode = context.getCurrentXmlTest().getParameter("enableDebuggerMode");
		String[] debuggerModeAndPort = paramEnableDisableDebuggerMode.split(",");
		wantToEnableDebuggerMode = Boolean.parseBoolean(debuggerModeAndPort[0].trim());
		debuggerPort = Integer.parseInt(debuggerModeAndPort[1].trim());
		logger.info("wantToEnableDebuggerMode: " + wantToEnableDebuggerMode + " And debugger post: " + debuggerPort);

		// FOR LOGIN BYPASS
		String paramLoginByPass = context.getCurrentXmlTest().getParameter("wantToByPassLoginLogout");
		wantToByPassLoginLogout = Boolean.parseBoolean(paramLoginByPass);
		logger.info("wantToByPassLoginLogout: " + wantToByPassLoginLogout);

		// FOR INCOGNITO MODE
		String paramIncogenitoModde = context.getCurrentXmlTest().getParameter("incognitoMode");
		incognitoMode = Boolean.parseBoolean(paramIncogenitoModde);
		logger.info("incognitoMode: " + incognitoMode);

		// FOR HEADERLESS BROWSING
		String paramHeaderLessBrowsing = context.getCurrentXmlTest().getParameter("headerLessBrowsing");
		headerLessBrowsing = Boolean.parseBoolean(paramHeaderLessBrowsing);
		logger.info("headerLessBrowsing: " + headerLessBrowsing);

		// TO DISABLE ADS AND NOTIFICATIONS
		String paramWantToBlockAdsAndNotifications = context.getCurrentXmlTest()
				.getParameter("wantToBlockAdsAndNotifications");
		wantToBlockAdsAndNotifications = Boolean.parseBoolean(paramWantToBlockAdsAndNotifications);
		logger.info("wantToBlockAdsAndNotifications: " + wantToBlockAdsAndNotifications);

	}

	// to select the driver
	@Parameters("browser")
	@BeforeTest(dependsOnMethods = "getDataFromTestNg")
	public void Setup(String br) throws InterruptedException {
		System.out.println("Current thread name: " + Thread.currentThread().getName());
		
		logger.info("Base class started...");

		if (br.equalsIgnoreCase("chrome")) {
			// logger.info("1");
			// USE THIS LINE IF YOU WANT USE DRIVER FROM THE DRIVER FOLDER
			System.setProperty("webdriver.chromedriver", rcf.getChromePath());

			// OTHER WISE USE BELOW LINE IT WILL TAKES DRIVER FROM THE POM.XML FILES
			//WebDriverManager.chromedriver().setup();
			// logger.info("2");

			// TO INITIALIZE CHROME DRIVER
			driver = new ChromeDriver(cco.customizedChromeOptions(wantToBlockAdsAndNotifications, headerLessBrowsing,
					incognitoMode, wantToEnableDebuggerMode, debuggerPort, wantToTackActionOnFiles, fileLocation));

			logger.info("Chrome driver selected");
		} else if (br.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.geckodriver", rcf.getFireFoxPath());
			driver = new FirefoxDriver();
			logger.info("Fire fox driver selected");
		} else if (br.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.msedgedriver", rcf.getMsEdgePath());
			driver = new EdgeDriver();
			logger.info("Edge driver selected");
		}

		// TO START BASE URL
		if (wantToEnableDebuggerMode) {
			driver.get(baseUrlDebuggerMode);
			logger.info("Login page started with Debugger mode");
		} else {
			driver.get(baseUrl);
			logger.info("Login page started");
		}

		// TO MAXIMISE WINDOW
		driver.manage().window().maximize();
		logger.info("Maximize the window");
		// Thread.sleep(5000);

	}

	// TO LOGIN
	@Parameters("userType")
	@BeforeClass()
	public void Login(String userType) throws InterruptedException {
		if (userType.equalsIgnoreCase("user")) {
			lp = new PO_LoginPage(driver);
			logger.info("Login user Email: " + userEmail + " and Password: " + userPassword);
			hp = lp.Login(userEmail, userPassword);
		}

	}

	// TO LOGOUT
	@Parameters("userType")
	@AfterTest()
	public void Logout(String userType) throws InterruptedException {
		try {
			if (userType.equalsIgnoreCase("user")) {
				//hp.UserLogout();
			}
		} catch (Exception e) {
			logger.warn("Exception From: Logout >> " + e.getMessage());
		}

	}

	// TO CLOSE THE DIRVER
	@AfterTest(dependsOnMethods = "Logout")
	public void Teardown() {
		//driver.quit();
		logger.info("Driver shutdown");
	}

	// TO GENERATES RANDOM STRING HAVING LENGTH 6 CHARACTER
	public static String randomString(int intLength) {
		String generatedstring = RandomStringUtils.randomAlphabetic(intLength);
		return generatedstring;
	}

	// TO GENERATES RANDOM STRING NUMBER WITH MIN AND MAX AS PER USER DATA
	public static String randomStringNumber(int min, int max) {
		String rdmStringNumber = RandomStringUtils.randomNumeric(min, max);
		return rdmStringNumber;
	}

	public static void main(String[] args) {
		System.out.println(randomStringNumber(5, 6));
	}
}