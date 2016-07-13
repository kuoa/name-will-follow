package com.name.game.structure.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.name.game.MyGame;

/**
 * Created by kuoa on 7/12/16.
 */
public class Grid {

    private int rows;
    private int cols;
    private Color bgColor;
    private Cell[][] grid;

    public Grid(int[][] types){

        rows = types.length;
        cols = types[0].length;
        float cellWidth = MyGame.WIDTH / cols;
        float cellHeight = MyGame.HEIGHT / rows;

        bgColor = new Color(0.2f, 0.2f, 0.2f, 1);
        grid = new Cell[rows][cols];
        System.out.println("Rows: " + rows + " Cols: " + cols);

        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                grid[i][j] = new Cell(types[i][j], j, i, cellWidth, cellHeight);
            }
        }
    }

    public void update(float delta) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].update(delta);
            }
        }
    }

    public void draw(SpriteBatch batch){
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].draw(batch);
            }
        }
    }
}
