package com.name.game.structure.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.name.game.MyGame;
import com.name.game.structure.graph.Edge;
import com.name.game.structure.graph.Graph;

/**
 * Created by kuoa on 7/12/16.
 */
public class Grid {

    MyGame game;

    private int rows;
    private int cols;
    private Color bgColor;

    Cell[][] cells;
    Graph graph;

    Cell fromCell;
    Cell toCell;

    Cell startCell;
    Cell endCell;

    Cell betweenCell;
    private final double betweenTime;
    double startTime = 0.;


    final int[][] types;
    final float normalPadding;
    final float topPadding;
    final float cellWidth;
    final float cellHeight;

    private ShapeRenderer render = new ShapeRenderer();

    public Grid(MyGame game, int[][] types) {

        render.setProjectionMatrix(game.batch.getProjectionMatrix());

        this.game = game;
        this.types = types;

        rows = types.length;
        cols = types[0].length;
        bgColor = new Color(0.2f, 0.2f, 0.2f, 1);
        graph = new Graph();

        normalPadding = 10;
        topPadding = 60;
        betweenTime = 600.;
        
        cellWidth = (MyGame.WIDTH - 2 * normalPadding) / cols;
        cellHeight = (MyGame.HEIGHT - normalPadding - topPadding) / rows;

        cells = new Cell[rows][cols];

        System.out.println("Rows: " + rows + " Cols: " + cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = new Cell(this, new Vector2(j, i));
                cells[i][j] = cell;
                graph.addVertex(cell);
            }
        }

        startCell = cells[0][0];
        startCell.touch();
        fromCell = startCell;

        endCell = cells[rows - 1][cols - 1];
        endCell.setBetween(true);

        Gdx.input.setInputProcessor(new GridInputProcessor(this));
    }

    // Rendering

    public void update(float delta) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].update(delta);
            }
        }
    }

    public void draw() {

        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);

        // draw cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].draw(game.batch);
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

        if (betweenCell != null) {
            double deltaTime = TimeUtils.millis() - startTime;

            if (deltaTime > betweenTime) {
                startTime = 0.;
                betweenCell.setBetween(false);
                betweenCell = null;
            }
        }
    }

    public void dispose() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].dispose();
            }
        }
    }
}