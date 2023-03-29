package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.awt.*;

public class Main {
	
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth()*1.5;
		double height = screenSize.getHeight()*1.5;
		System.out.println(width);
		System.out.println(height);
		int maxWidth = (int)(width);
		int maxHeight = (int)(height);
		int minWidth = (int)(width);
		int minHeight = (int)(height);
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Asteroids");
		config.setWindowSizeLimits(minWidth,minHeight,maxWidth,maxHeight);
		config.setWindowedMode(minWidth, minHeight);

		new Lwjgl3Application(new Game(), config);
	}
	
}
