package com.name.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.name.game.MyGame;
import com.name.game.structure.grid.Grid;
import com.name.game.structure.level.Level;
import com.name.game.structure.level.LevelFactory;


public class PlayScreen implements Screen {

	private Grid grid;
    private MyGame game;
	private Level level;
	
	public PlayScreen(MyGame game) {

        this.game = game;
		grid = new Grid(game);
		level = LevelFactory.loadLevel("01");
		grid.load(level);

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
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
		game.batch.begin();
		grid.draw();
		game.batch.end();
	}

	@Override
	public void render(float delta) {

		update(delta);
		draw();
	}

	@Override
	public void resize(int width, int height) {
		game.port.update(width, height);
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
        grid.dispose();
	}
}
