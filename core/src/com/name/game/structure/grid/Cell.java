package com.name.game.structure.grid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
    private Texture texture;

    public Cell(Grid grid, Vector2 gridPosition){

        // middle of the cell in pixels
        super (new Vector2(
                gridPosition.x * grid.cellWidth + grid.normalPadding + (grid.cellWidth / 2),
                gridPosition.y * grid.cellHeight + grid.normalPadding + (grid.cellHeight / 2)));

        this.gridPosition = gridPosition;

        this.grid = grid;

        int typeIndex = grid.types[(int)gridPosition.y][(int)gridPosition.x];
        this.type = CellType.values()[typeIndex];

        texture = new Texture("pixel.jpg");
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

        if(touched){
            batch.setColor(Color.PURPLE);
        }
        else if (between){
            batch.setColor(Color.GOLD);
        }
        else {
            batch.setColor(type.getColor());
        }
        batch.draw(texture, gridPosition.x * grid.cellWidth + grid.normalPadding, gridPosition.y * grid.cellHeight + grid.normalPadding, grid.cellWidth, grid.cellHeight);
    }

    public void dispose() {
        texture.dispose();
    }
}
