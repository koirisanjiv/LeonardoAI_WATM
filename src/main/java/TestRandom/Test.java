package TestRandom;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test {
	
	// ALERT MESSAGES
	public static String pleaseAddValidLink = "Please add a valid file link.";
	public static String pleaseAddDocumentTitle = "Please add document title.";
	public static String pleaseSelectLocation = "Please select location.";
	public static String pleaseUplaodImage = "Please upload a file.";
	public static String pleaseAddLink = "Please add a file link.";
	public String documentAdded = "Document added.";
	public String documentChanged = "Document updated.";
	public String documentDeactivate = "Document deactivated.";
	public String documentActivated = "Document activated.";
	
	public static void main(String[] args) {
		
		// REQUIRED FIELD SET FOR THE DOCUMENT
		Set<String> requiredFieldToAddOrChangeDocument = new HashSet<>(
				Arrays.asList(pleaseAddValidLink, pleaseAddDocumentTitle, pleaseSelectLocation, pleaseUplaodImage, pleaseAddLink));
		
		System.out.println("Confirmation: " + requiredFieldToAddOrChangeDocument.contains("Please select location."));
	}
}
