package com.name.game.structure.grid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.name.game.structure.graph.Vertex;

/**
 * Created by kuoa on 7/12/16.
 */
public class Cell {

    private int x;
    private int y;
    private boolean touched;

    private Grid grid;
    private Vertex vertex;

    private CellType type;
    private Texture texture;

    public Cell(Grid grid, int x, int y){

        this.x = x;
        this.y = y;

        this.grid = grid;
        this.vertex = new Vertex(x, y);

        int typeIndex = grid.types[y][x];
        this.type = CellType.values()[typeIndex];

        texture = new Texture("pixel.jpg");
    }

    public void touch(){
        touched = !touched;
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
        batch.draw(texture, x * grid.cellWidth + grid.normalPadding, y * grid.cellHeight + grid.normalPadding, grid.cellWidth, grid.cellHeight);
    }

    public void dispose() {
        // TODO Auto-generated method stub

    }
}
