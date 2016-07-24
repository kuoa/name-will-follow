package com.name.game.structure.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.name.game.MyGame;

/**
 * Created by kuoa on 7/20/16.
 */
public class GridInputProcessor implements InputProcessor {

    private Grid grid;

    public GridInputProcessor(Grid grid) {
        this.grid = grid;
    }


    // Converts from screenPosition (pixels) to gridPosition (x, y)
    private Vector2 getCellGridPos(Vector2 cellScreenPos){
        int x = (int) ((cellScreenPos.x - grid.normalPadding) / grid.cellWidth);
        int y = (int) ((cellScreenPos.y - grid.normalPadding) / grid.cellHeight);

        return new Vector2(x, y);
    }


    // Check if these there is a conflict between these two points
    private boolean checkSelectedPoint(Vector2 currentPoint, Vector2 endPoint){

        Vector2 cellGridPos = getCellGridPos(currentPoint);
        Cell nextCell = grid.cells[(int)cellGridPos.y][(int)cellGridPos.x];

        if(nextCell.getCellType() != CellType.EMPTY && nextCell.getScreenPosition() != endPoint && !nextCell.wasSeen()){
            nextCell.setBetween(true);
            grid.betweenCell = nextCell;
            grid.startTime = TimeUtils.millis();
            return false;
        }

        return true;
    }

    // Checks if there are any conflicts, between the two cells, for all points.
    private boolean validConnection(Vector2 endPoint){

        Vector2 startPoint = grid.toCell == null ? grid.startCell.getScreenPosition(): grid.toCell.getScreenPosition();

        float deltaY = (startPoint.y - endPoint.y);
        float deltaX = (startPoint.x - endPoint.x);

        // if vertical line
        if(deltaX == 0){

            if(startPoint.y < endPoint.y) {

                for (float y = startPoint.y; y < endPoint.y; y++) {
                    if (!checkSelectedPoint(new Vector2(startPoint.x, y), endPoint)) {
                        return false;
                    }
                }

                // decrease to show the closest point
            }else {
                for (float y = startPoint.y; y > endPoint.y; y--) {
                    if (!checkSelectedPoint(new Vector2(startPoint.x, y), endPoint)) {
                        return false;
                    }
                }
            }
        }

        // not a vertical line
        else{
            float a = deltaY / deltaX;
            float b = endPoint.y - a * endPoint.x;

            if (startPoint.x < endPoint.x) {
                for (float x = startPoint.x; x < endPoint.x; x++) {
                    float y = a * x + b;

                    if (!checkSelectedPoint(new Vector2(x, y), endPoint)) {
                        return false;
                    }
                }
            }
            else{
                for (float x = startPoint.x; x > endPoint.x; x--) {
                    float y = a * x + b;

                    if (!checkSelectedPoint(new Vector2(x, y), endPoint)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Connect two cells, if there are no conflicts.
    private void connectCells(Vector2 cellScreenPos) {

        if(cellScreenPos.x >= grid.normalPadding  && cellScreenPos.x <= MyGame.WIDTH - grid.normalPadding &&
                cellScreenPos.y >= grid.normalPadding && cellScreenPos.y <= MyGame.HEIGHT - grid.topPadding){

            Vector2 cellGridPos = getCellGridPos(cellScreenPos);

            Cell newCell = grid.cells[(int)cellGridPos.y][(int)cellGridPos.x];

            // click on new cell
            if (newCell.wasSeen()) {

                if(newCell == grid.toCell) {
                     grid.toCell.touch();
                     grid.toCell.unSee();

                        grid.graph.removeEdge( grid.toCell.getFirstEdge());
                        grid.toCell = grid.fromCell;

                        if( grid.toCell.getFirstEdge() != null) {
                            grid.fromCell = (Cell)  grid.toCell.getFirstEdge().getFrom();
                            grid.fromCell.touch();
                        }
                        else{
                            grid.fromCell = grid.startCell;
                            grid.toCell = null;
                        }
                    }
                }

            // reclick on last cell
            else {
                if (validConnection(newCell.getScreenPosition())){

                    if (grid.toCell == null) {
                        grid.toCell = newCell;
                        grid.toCell.touch();
                        grid.graph.addEdge(grid.fromCell,  grid.toCell);

                    } else {
                        grid.fromCell.touch();
                        grid.fromCell =  grid.toCell;
                        grid.toCell = newCell;
                        grid.toCell.touch();
                        grid.graph.addEdge(grid.fromCell,  grid.toCell);
                    }
                }
            }

            Gdx.app.log((int)cellGridPos.x + "->" + (int)cellGridPos.y, getClass().toString());
        }
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
        grid.game.port.unproject(input);

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