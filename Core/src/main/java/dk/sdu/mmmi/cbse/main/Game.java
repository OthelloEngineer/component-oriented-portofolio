package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.MapService;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

public class Game extends ApplicationAdapter {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;

    SpriteBatch batch;

    private final GameData gameData = new GameData();
    private ImplementationLocator implementationLocator;
    private TextureRegion map;
    private World world = new World();

    public Game() {
        super();
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameData.setDisplayWidth(1980);
        gameData.setDisplayHeight(1080);
        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();
        setAdjustedMap();
        Gdx.gl.glLineWidth(12);
        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : implementationLocator.getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
    }

    private void setAdjustedMap(){
        Texture texture = new Texture(implementationLocator.getMapServices().getMap());
        TextureRegion region = new TextureRegion(texture, 0, 0, gameData.getDisplayWidth(), gameData.getDisplayHeight());
        region.setTexture(texture);
        map = region;
        Gdx.audio.newMusic(new FileHandle(implementationLocator.getMapServices().getSoundTrack())).play();
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();
        batch.begin();
        drawBackground();
        batch.end();
        draw();

        gameData.getKeys().update();

    }

    private void drawBackground(){
        batch.draw(map, 0, 0);
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : implementationLocator.getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
         for (IPostEntityProcessingService postEntityProcessorService : implementationLocator.getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {

            sr.setColor((float)entity.currentColor.r,
                    (float)entity.currentColor.g,
                    (float)entity.currentColor.b,
                    (float)entity.currentColor.a);
            sr.begin(ShapeRenderer.ShapeType.Line);
            double[] shapex = entity.getShapeX();
            double[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {

                sr.line((float)shapex[i], (float)shapey[i], (float)shapex[j], (float)shapey[j]);
            }

            sr.end();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Autowired
    public void setImplementationLocator(ImplementationLocator implementationLocator) {
        this.implementationLocator = implementationLocator;
    }

}
