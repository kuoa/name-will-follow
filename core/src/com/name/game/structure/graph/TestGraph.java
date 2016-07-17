package com.name.game.structure.graph;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class TestGraph {

    public static void main(String[] args) {


        ArrayList<Vertex> vertices = new ArrayList<Vertex>();

        for(int i = 0; i < 5; i++){
            vertices.add(new Vertex(new Vector2(i * 30, i * 50)));
        }

        Graph graph = new Graph(vertices);

        graph.addVertex(new Vertex(new Vector2(30, 50)));

        graph.addEdge(vertices.get(1), vertices.get(2));
        graph.addEdge(vertices.get(2), vertices.get(3));
        graph.addEdge(vertices.get(4), vertices.get(3));
        graph.addEdge(new Vertex (new Vector2(1 * 30, 1 * 50)),
                new Vertex(new Vector2(2 * 30, 2 * 50)));


        System.out.println(graph);

        System.out.println(graph.removeVertex(vertices.get(2)));
        //Edge e43 = vertices.get(3).getEdges().get(0);
        //graph.removeEdge(e43);

        System.out.println(graph);
    }

}
