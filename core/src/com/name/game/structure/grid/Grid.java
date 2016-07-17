package com.name.game.structure.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.name.game.MyGame;
import com.name.game.structure.graph.Edge;
import com.name.game.structure.graph.Graph;

/**
 * Created by kuoa on 7/12/16.
 */
public class Grid implements InputProcessor {

    private MyGame game;

    private int rows;
    private int cols;
    private Color bgColor;
    private Cell[][] grid;
    private Graph graph;

    private Cell fromCell;
    private Cell toCell;

    private Cell startCell;
    private Cell endCell;

    public final int[][] types;
    public final float normalPadding;
    public final float topPadding;
    public final float cellWidth;
    public final float cellHeight;

    private ShapeRenderer render = new ShapeRenderer();

    public Grid(MyGame game, int[][] types){

        render.setProjectionMatrix(game.batch.getProjectionMatrix());

        this.game = game;
        this.types = types;

        rows = types.length;
        cols = types[0].length;
        bgColor = new Color(0.2f, 0.2f, 0.2f, 1);
        graph = new Graph();

        normalPadding = 10;
        topPadding = 60;

        cellWidth = (MyGame.WIDTH - 2 * normalPadding) / cols;
        cellHeight = (MyGame.HEIGHT - normalPadding - topPadding) / rows;

        grid = new Cell[rows][cols];

        System.out.println("Rows: " + rows + " Cols: " + cols);

        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                Cell cell = new Cell(this, new Vector2(j, i));
                grid[i][j] = cell;
                graph.addVertex(cell);
            }
        }

        startCell = grid[0][0];
        startCell.touch();
        endCell = grid[rows - 1][cols - 1];
        endCell.touch();
        fromCell = startCell;


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

        // draw cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].draw(game.batch);
            }
        }

        // draw graph
        render.setAutoShapeType(true);
        render.begin();
        render.setColor(Color.CYAN);

        for (Edge e : graph.getEdges()) {

            Vector2 from = new Vector2(e.getFrom().getX(), e.getFrom().getY());
            Vector2 to = new Vector2(e.getTo().getX(), e.getTo().getY());

            game.port.project(from);
            game.port.project(to);

            render.rectLine(from, to, 2);
        }

        render.end();
    }

    public void dispose() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].dispose();
            }
        }
    }

    private Vector2 getCellGridPos(Vector2 cellScreenPos){
        int x = (int) ((cellScreenPos.x - normalPadding) / cellWidth);
        int y = (int) ((cellScreenPos.y - normalPadding) / cellHeight);

        return new Vector2(x, y);
    }


    private boolean validConnection(Vector2 input){
        // line ecuation a * x + b = y

        Vector2 endPoint = toCell != null ? toCell.getGridPosition() : fromCell.getGridPosition();

        float a = (endPoint.y - input.y) / (endPoint.x - input.x);

        float b = endPoint.y - a * endPoint.x;

        for(float x = endPoint.x; x < input.x; x++){
            float y = a * x + b;

            Vector2 cellGridPos = getCellGridPos(new Vector2(x, y));
            Cell nextCell = grid[(int)cellGridPos.y][(int)cellGridPos.x];

            if(nextCell.getCellType() != CellType.EMPTY && !nextCell.wasSeen()){
                nextCell.setBetween();
                return false;
            }
        }

        return true;
    }

    private void connectCells(Vector2 cellScreenPos){

        if(cellScreenPos.x >= normalPadding  && cellScreenPos.x <= MyGame.WIDTH - normalPadding &&
                cellScreenPos.y >= normalPadding && cellScreenPos.y <= MyGame.HEIGHT - topPadding){

            Vector2 cellGridPos = getCellGridPos(cellScreenPos);

            Cell newCell = grid[(int)cellGridPos.y][(int)cellGridPos.x];

            // click on new cell
            if(newCell.wasSeen() == false) {

                if (validConnection(newCell.getScreenPosition())) {

                    if (toCell == null) {
                        toCell = newCell;
                        toCell.touch();
                        graph.addEdge(fromCell, toCell);

                    } else {
                        fromCell.touch();
                        fromCell = toCell;
                        toCell = newCell;
                        toCell.touch();
                        graph.addEdge(fromCell, toCell);
                    }
                }
            }

            // reclick on last cell
            else if(newCell == toCell) {
                toCell.touch();
                toCell.unSee();

                graph.removeEdge(toCell.getFirstEdge());
                toCell = fromCell;

                if(toCell.getFirstEdge() != null) {
                    fromCell = (Cell) toCell.getFirstEdge().getFrom();
                    fromCell.touch();
                }
                else{
                    fromCell = startCell;
                    toCell = null;
                }
            }
            Gdx.app.log((int)cellGridPos.x + "->" + (int)cellGridPos.y, "Hey");
        }

        //Gdx.app.log(cellScreenPos.x + "->" + cellScreenPos.y, "Hey");
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

        Vector2 input = new Vector2(screenX, screenY);
        game.port.unproject(input);

        connectCells(input);
        return true;
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
