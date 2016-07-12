package com.name.game.structure.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by kuoa on 7/12/16.
 */
public class Cell {

    private int x;
    private int y;

    private CellType type;
    private float width;
    private float height;

    private Texture texture;

    public Cell(int cellType, int x, int y, float width, float height){

        this.type = CellType.values()[cellType];
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        texture = new Texture("pixel.jpg");

    }

    public void update(float delta){

    }

    public void draw(SpriteBatch batch){
        batch.setColor(type.getColor());
        batch.draw(texture, x * width, y * height, width, height);
    }
}
