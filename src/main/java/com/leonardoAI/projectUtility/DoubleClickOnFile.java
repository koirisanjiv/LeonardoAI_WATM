package com.leonardoAI.projectUtility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;

public class DoubleClickOnFile {

    public static void main(String[] args) throws IOException {
        try {
            // Hardcoded file path (replace with your actual file path)
        	String autoItExecutablePath = System.getProperty("user.dir") + "\\autoIT_x64.exe";

            // Create a File object from the provided path
            File selectedFile = new File(autoItExecutablePath);

            if (selectedFile.exists()) {
                // Simulate the mouse double-click event on the file/folder
                Robot robot = new Robot();
                robot.setAutoDelay(200);
                int x = 500; // Customize the x-coordinate based on your screen resolution
                int y = 300; // Customize the y-coordinate based on your screen resolution
                robot.mouseMove(x, y);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                System.out.println("Double-clicked on: " + selectedFile.getName());
            } else {
                System.out.println("File/Folder not found: " + autoItExecutablePath);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

}
