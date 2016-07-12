package com.name.game.structure.grid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.name.game.NameWillFollow;

/**
 * Created by kuoa on 7/12/16.
 */
public class Grid {

    private int rows;
    private int cols;
    private Color bgColor;
    private Cell[][] grid;
    private float cellWidth;
    private float cellHeight;

    private SpriteBatch batch;

    public Grid(int[][] types){
        rows = types.length;
        cols = types[0].length;
        cellWidth = NameWillFollow.WIDTH / rows;
        cellHeight = NameWillFollow.HEIGHT / cols;

        bgColor = new Color(0.2f, 0.2f, 0.2f, 1);
        batch = NameWillFollow.spriteBatch;

        grid = new Cell[rows][cols];

        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                grid[i][j] = new Cell(types[i][j], i, j, cellWidth, cellHeight);
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

    public void draw(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].draw(batch);
            }
        }
    }
}
