package com.name.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.name.game.states.GameStateManager;
import com.name.game.states.TestState;

public class Game extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Name Will Follow";
	
	private SpriteBatch sb;
	private GameStateManager gsm;
	
	@Override
	public void create () {

		sb = new SpriteBatch();
		gsm = new GameStateManager();
		
		gsm.push(new TestState(gsm));
				
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);		
	}

	@Override
	public void render () {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
}
