package com.name.game.structure.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.name.game.MyGame;

/**
 * Created by kuoa on 7/12/16.
 */
public class Grid implements InputProcessor {

    private MyGame game;

    private int rows;
    private int cols;
    private Color bgColor;
    private Cell[][] grid;
    private Vector2 input;

    private Cell prevCellTouched;
    private Cell currCellTouched;

    public final int[][] types;
    public final float normalPadding;
    public final float topPadding;
    public final float cellWidth;
    public final float cellHeight;

    public Grid(MyGame game, int[][] types){

        this.game = game;
        this.types = types;

        rows = types.length;
        cols = types[0].length;
        bgColor = new Color(0.2f, 0.2f, 0.2f, 1);
        input = new Vector2();

        normalPadding = 10;
        topPadding = 60;

        cellWidth = (MyGame.WIDTH - 2 * normalPadding) / cols;
        cellHeight = (MyGame.HEIGHT - normalPadding - topPadding) / rows;

        grid = new Cell[rows][cols];

        System.out.println("Rows: " + rows + " Cols: " + cols);

        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                grid[i][j] = new Cell(this, j, i);
            }
        }

        Gdx.input.setInputProcessor(this);
    }

    // Rendering

    public void update(float delta) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].update(delta);
            }
        }
    }

    public void draw(){
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].draw(game.batch);
            }
        }
    }

    public void dispose() {
        // TODO Auto-generated method stub

    }

    // Input Processor

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        input = new Vector2(screenX, screenY);
        game.port.unproject(input);

        if(input.x >= normalPadding  && input.x <= MyGame.WIDTH - normalPadding &&
                input.y >= normalPadding && input.y <= MyGame.HEIGHT - topPadding){

            int x = (int) ((input.x - normalPadding) / cellWidth);
            int y = (int) ((input.y - normalPadding) / cellHeight);

            grid[y][x].touch();

            Gdx.app.log(x + "->" + y, "Hey");
        }

        Gdx.app.log(input.x + "->" + input.y, "Hey");

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
