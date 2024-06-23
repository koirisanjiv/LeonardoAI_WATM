package com.leonardoAI.pageObject.pageLocators;

public class PL_ImageGenerationPage {
   
	public static final String address_gettingStartedCloseButton = "(//button[starts-with(@aria-label, 'Close')])[1]";
	public static final String address_introGuide = "(//button[starts-with(@aria-label, 'Close')])[1]";
	public static final String address_elementIsImageGeneratePageReady = "(//*[normalize-space()='Image Generation'])[1]";
    public static final String address_textareaPromptInput = "(//textarea[@id='prompt-input'])[1]";
    public static final String address_btnGenerateImage= "//div[@data-tour-id='generate-button']";
    public static final String address_listGeneratedImage = "//div[contains(@class,'chakra-aspect-ratio')]";
    public static final String address_isImageStillGenerating = "//div[contains(@class,'chakra-spinner')]";
    public static final String address_btnDownloadImage =  "//button[@aria-label='Download image']";
    public static final String address_listBtnSelectImage = "//button[@aria-label='Select Image']";
    public static final String address_btnDownloadImageOnSelectImage = "(//button[contains(@class,'chakra-button chakra-menu__menu-button')])[2]";
    public static final String address_btnOriginalImage =  "(//button[normalize-space()='Original images'])[1]";


}
