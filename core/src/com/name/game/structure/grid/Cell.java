package com.name.game.structure.grid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.name.game.structure.graph.Vertex;

/**
 * Created by kuoa on 7/12/16.
 */
public class Cell extends Vertex {

    private Vector2 gridPosition;
    private boolean touched;
    private boolean seen;
    private boolean between;

    private Grid grid;

    private CellType type;
    private Sprite cellSprite;
    private Sprite touchedSprite;
    private Sprite betweenSprite;
    private Sprite seenSprite;

    public Cell(Grid grid, Vector2 gridPosition){

        // middle of the cell in pixels
        super (new Vector2(
                gridPosition.x * grid.cellWidth + grid.normalPadding + (grid.cellWidth / 2),
                gridPosition.y * grid.cellHeight + grid.normalPadding + (grid.cellHeight / 2)));

        this.gridPosition = gridPosition;

        this.grid = grid;

        int typeIndex = grid.level.types[(int)gridPosition.y][(int)gridPosition.x];
        this.type = CellType.values()[typeIndex];

        cellSprite = grid.atlas.createSprite(type.getName());
        cellSprite.setPosition(gridPosition.x * grid.cellWidth + grid.normalPadding, gridPosition.y * grid.cellHeight + grid.normalPadding);
        cellSprite.setSize(grid.cellWidth, grid.cellHeight);

        touchedSprite = grid.atlas.createSprite("selectorA");
        touchedSprite.setPosition(cellSprite.getX(), cellSprite.getY());
        touchedSprite.setSize(cellSprite.getWidth(), cellSprite.getHeight());

        betweenSprite = grid.atlas.createSprite("red");
        betweenSprite.setPosition(cellSprite.getX(), cellSprite.getY());
        betweenSprite.setSize(cellSprite.getWidth(), cellSprite.getHeight());

        seenSprite = grid.atlas.createSprite("selectorB");
        seenSprite.setPosition(cellSprite.getX(), cellSprite.getY());
        seenSprite.setSize(cellSprite.getWidth(), cellSprite.getHeight());
    }

    public Vector2 getGridPosition(){
        return gridPosition;
    }

    public void touch(){
        seen = true;
        touched = !touched;
    }

    public void setBetween(boolean b){
        between = b;
    }

    public boolean isBetween() { return between; }

    public void unSee(){
        seen = false;
    }

    public boolean wasSeen(){
        return seen;
    }

    public CellType getCellType(){
        return type;
    }

    public void update(float delta){
        // TODO Auto-generated method stub
    }

    public void draw(SpriteBatch batch){


       if (between){
           betweenSprite.draw(batch);
        }
        else {
           cellSprite.draw(batch);
       }

        if(touched){
            touchedSprite.draw(batch);
        }
        else if(seen){
            seenSprite.draw(batch);
        }
    }

    public void dispose() {

    }
}
