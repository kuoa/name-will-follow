package com.name.game.structure.grid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.name.game.structure.graph.Vertex;

/**
 * Created by kuoa on 7/12/16.
 */
public class Cell extends Vertex {

    private int row;
    private int coll;
    private boolean touched;
    private boolean seen;

    private Grid grid;

    private CellType type;
    private Texture texture;

    public Cell(Grid grid, int row, int coll){

        // middle of the cell in pixels
        super(row * grid.cellWidth + grid.normalPadding + (grid.cellWidth / 2),
                coll * grid.cellHeight + grid.normalPadding + (grid.cellHeight / 2));

        this.row = row;
        this.coll = coll;
        this.touched = false;
        this.seen = false;

        this.grid = grid;

        int typeIndex = grid.types[coll][row];
        this.type = CellType.values()[typeIndex];

        texture = new Texture("pixel.jpg");
    }

    public void touch(){
        seen = true;
        touched = !touched;
    }

    public void unSee(){
        seen = false;
    }

    public boolean wasSeen(){
        return seen;
    }

    public void update(float delta){
        // TODO Auto-generated method stub
    }

    public void draw(SpriteBatch batch){

        if(touched){
            batch.setColor(Color.WHITE);
        }
        else {
            batch.setColor(type.getColor());
        }
        batch.draw(texture, row * grid.cellWidth + grid.normalPadding, coll * grid.cellHeight + grid.normalPadding, grid.cellWidth, grid.cellHeight);
    }

    public void dispose() {
        texture.dispose();
    }
}
