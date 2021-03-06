package com.name.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.name.game.screens.PlayScreen;
import com.name.game.utilities.ResourceManager;

public class MyGame extends Game {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Name Will Follow";

	public SpriteBatch batch;
    public OrthographicCamera camera;
    public Viewport port;

    public ResourceManager resources;

	@Override
	public void create() {

        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        port = new ExtendViewport(WIDTH, HEIGHT, camera);
        port.apply();

        resources = new ResourceManager();

        camera.translate(WIDTH / 2, HEIGHT / 2);

		setScreen(new PlayScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
        resources.dispose();
        super.dispose();
	}
}
