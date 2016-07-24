package com.name.game.structure.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.name.game.MyGame;
import com.name.game.structure.graph.Edge;
import com.name.game.structure.graph.Graph;
import com.name.game.structure.level.Level;

/**
 * Created by kuoa on 7/12/16.
 */
public class Grid {

    MyGame game;
    Level level;
    Cell[][] cells;
    Graph graph;
    TextureAtlas atlas;

    private Color bgColor;

    Cell startCell;
    Cell endCell;

    Cell fromCell;
    Cell toCell;

    Cell betweenCell;
    private final double betweenTime;
    double startTime = 0.;


    final float normalPadding;
    final float topPadding;
    float cellWidth;
    float cellHeight;

    private ShapeRenderer render = new ShapeRenderer();

    public Grid(MyGame game) {

        render.setProjectionMatrix(game.batch.getProjectionMatrix());

        this.game = game;
        atlas = game.resources.getTextureAtlas();

        bgColor = new Color(0.2f, 0.2f, 0.2f, 1);

        normalPadding = 10;
        topPadding = 60;
        betweenTime = 600.;

        Gdx.input.setInputProcessor(new GridInputProcessor(this));
    }

    // Rendering

    public void load(Level level){

        this.level = level;
        graph = new Graph();
        cells = new Cell[level.rows][level.cols];

        cellWidth = (MyGame.WIDTH - 2 * normalPadding) / level.cols;
        cellHeight = (MyGame.HEIGHT - normalPadding - topPadding) / level.rows;


        System.out.println("Rows: " + level.rows + " Cols: " + level.cols);

        for (int i = 0; i < level.rows; i++) {
            for (int j = 0; j < level.cols; j++) {
                Cell cell = new Cell(this, new Vector2(j, i));
                cells[i][j] = cell;
                graph.addVertex(cell);
            }
        }

        startCell = cells[0][0];
        startCell.touch();

        fromCell = startCell;

        endCell = cells[level.rows - 1][level.cols - 1];
        endCell.setBetween(true);

    }

    public void update(float delta) {

        for (int i = 0; i < level.rows; i++) {
            for (int j = 0; j < level.cols; j++) {
                cells[i][j].update(delta);
            }
        }
    }

    public void draw() {

        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);

        game.batch.begin();
        for (int i = 0; i < level.rows; i++) {
            for (int j = 0; j < level.cols; j++) {
                cells[i][j].draw(game.batch);
            }
        }
        game.batch.end();

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
        for (int i = 0; i < level.rows; i++) {
            for (int j = 0; j < level.cols; j++) {
                cells[i][j].dispose();
            }
        }
    }
}