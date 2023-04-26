package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.awt.*;

public class Main {
	public static void main(String[] args) {
		double width = 1980;
		double height = 1080;
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
