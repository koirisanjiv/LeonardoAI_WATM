package com.leonardoAI.testCases;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.leonardoAI.ReUseAble.PageObject.ReUseAbleElement;
import com.leonardoAI.pageObject.PO_ImageGenerationPage;
import com.leonardoAI.pageObject.PO_HomePage;
import com.leonardoAI.pageObject.PO_LoginPage;
import com.leonardoAI.utilities.FindFileAndRename;

public class TC_ImageGenerationPage extends BaseClass {
	// HOME PAGE CONSTRUCTOR
	public TC_ImageGenerationPage() {
		super();
	}

	// CONSTRUCTOR DECLARATION
	public PO_LoginPage lp; // lp = LOGIN PAGE
	public PO_HomePage hp; // hp = HOME PAGE
	public Faker faker = new Faker();
	public PO_ImageGenerationPage po_igp; // MAIN USER LABLES PAGE

	// VARIABLES
	String writeYourPrompt = "Create a cute baby";
	int imageSerialNumber = 1;
	String newFileName = "12.jpg";
	String newFolderName = "LeonardoAI";
	boolean wantToRenameFileBeforeMove = false;
	public FindFileAndRename findFileandRename = new FindFileAndRename();

	// TO GENERATE IAMGE
	//@Test(priority = 1)
	public void test_GenerateImage() throws Throwable {
		po_igp = callMeBeforePerformAnyAction();
		po_igp.generateImage(writeYourPrompt);
	}

	// TO DOWNLAOD IAMGE
	@Test(priority = 2)
	public void test_DownLoadGenerateImage() throws Throwable {
		po_igp = callMeBeforePerformAnyAction();
		po_igp.downloadImage(imageSerialNumber);
	}

	// TO RENAME DOWNLAOD FILE IN THE SYSTEM
	@Test(priority = 3)
	public void test_RenameDonwloadedFile() throws Throwable {		
		findFileandRename.actionOnDownloadedFiles(newFileName,newFolderName,wantToRenameFileBeforeMove);
	}

	// CALL ME IN EVERY @TEST METHODS EXCEPT LOGIN AND LOGOUT
	public PO_ImageGenerationPage callMeBeforePerformAnyAction() throws InterruptedException {
		// TO ACCESS ANY ELEMENT IT CHECK IT IS COME BACK ON THE HOME PAGE FIRST
		hp = new PO_HomePage(driver);
		hp.clickOnMenuImageGeneration();
		Thread.sleep(4000);
		return new PO_ImageGenerationPage(driver);
	}

}
