package com.name.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.name.game.NameWillFollow;
import com.name.game.structure.grid.Grid;

public class PlayScreen implements Screen {

	private Grid grid;
	private SpriteBatch batch;
	
	public PlayScreen() {

		batch = NameWillFollow.spriteBatch;

		int w = 10;
		int h = 20;

		int[][] types = new int[w][h];

		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				types[i][j] = (int)(Math.random() * 4);
			}
		}

		grid = new Grid(types);

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	private void update(float delta){
		grid.update(delta);
	}

	private void draw(){

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		grid.draw();
		batch.end();
	}

	@Override
	public void render(float delta) {

		update(delta);
		draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
