package com.name.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.name.game.screens.PlayScreen;

public class NameWillFollow extends Game {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Name Will Follow";

	public SpriteBatch spriteBatch;

	@Override
	public void create() {
		
		spriteBatch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render() {
		
		super.render();
	}
}
