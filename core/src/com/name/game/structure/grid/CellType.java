package com.name.game.structure.grid;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by kuoa on 7/12/16.
 */
public enum CellType {
    BLUE("blue"),
    GREEN("green"),
    PURPLE("purple"),
    EMPTY("empty"),
    YELLOW("yellow"),
    RED("red"),
    GREY("grey");

    private String name;

    CellType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}