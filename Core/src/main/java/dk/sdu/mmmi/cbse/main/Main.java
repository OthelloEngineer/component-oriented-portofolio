package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
@Component("main")
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(GameConfig.class);
		context.scan("dk.sdu.mmmi.cbse");
		context.refresh();
		Game game = context.getBean(Game.class);
		initGame(game);
	}

	public static void initGame(Game game) {

		int width = 1980;
		int height = 1080;
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Asteroids");
		config.setWindowSizeLimits(width, height, width, height);
		config.setWindowedMode(width, height);
		new Lwjgl3Application(game, config);
	}
}
