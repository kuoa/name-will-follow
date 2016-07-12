package com.name.game.structure.grid;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by kuoa on 7/12/16.
 */
public enum CellType {
    BLUE(Color.BLUE),
    RED(Color.RED),
    GREEN(Color.GREEN),
    EMPTY(new Color(0.2f, 0.2f, 0.2f, 1));

    private Color color;

    CellType(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }
}