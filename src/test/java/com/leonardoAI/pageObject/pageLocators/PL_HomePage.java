package com.leonardoAI.pageObject.pageLocators;

public class PL_HomePage {
	
	public static final String address_logoLeonardoAI = "(//img[@alt='Leonardo.Ai'])[2]";
	public static final String address_userLogoIcon = "(//button[contains(@id,'menu-button')])[1]";;
	public static final String address_logout = "//button[contains(@value,'LogoutMenuItem')]";
	
	public static final String address_imageGeneration = "//a//p[text()='Image Generation']";
	public static final String address_motion = "//a//p[text()='Motion']";
	public static final String address_realtimeCanvas = "//a//p[text()='Realtime Canvas']";
	public static final String address_canvasEditor = "//a//p[text()='Canvas Editor']";
	public static final String address_universalUpscaler = "//a//p[text()='Universal Upscaler']";
	public static final String address_logoBackButton = "(//a[@role='group'])[2]";
	public static final String address_logoHomepage = "(//img[@alt='Leonardo.Ai'])[2]";
	public static final String address_gettingStartedGuider = "//div[contains(@class, 'driver-popover') and contains(@class, 'tour-popover')]";
	
	public static final String address_CloseButtonModelImage2Motion = "(//button[contains(@class,'chakra-modal__close-btn')])[1]";
	
}
