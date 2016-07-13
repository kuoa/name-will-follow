package com.name.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.name.game.MyGame;
import com.name.game.structure.grid.Grid;



public class PlayScreen implements Screen {

	private Grid grid;
    private MyGame game;
	
	public PlayScreen(MyGame game) {

        this.game = game;

		int r = 20;
		int c = 10;

		int[][] types = new int[r][c];

		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				types[i][j] = (int)(Math.random() * 4);
			}
		}

        for(int i = r - 1; i >= 0; i--){
            for(int j = 0; j < c; j++){
                System.out.print(types[i][j]);
            }
            System.out.println();
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
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
		game.batch.begin();
		grid.draw(game.batch);
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
		// TODO Auto-generated method stub
		
	}
}
