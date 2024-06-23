package com.leonardoAI.projectUtility;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeOptions {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chromedriver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		Capabilities cap =  driver.getCapabilities();
		Map<String, Object> capObject = cap.asMap();
		System.out.println(capObject);
		
	}

}
