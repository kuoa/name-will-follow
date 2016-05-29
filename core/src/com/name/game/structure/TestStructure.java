package com.name.game.structure;

import java.util.ArrayList;

public class TestStructure {

	public static void main(String[] args) {
		
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();		
		
		for(int i = 0; i < 5; i++){
			vertices.add(new Vertex(i * 30, i * 50, Integer.toString(i)));
		}
										
		Graph graph = new Graph(vertices);
		
		graph.addVertex(new Vertex(30, 50, "2"));
		
		graph.addEdge(vertices.get(1), vertices.get(2));
		graph.addEdge(vertices.get(2), vertices.get(3));
		graph.addEdge(vertices.get(4), vertices.get(3));
		graph.addEdge(new Vertex(1 * 30, 1 * 50, "1"), 
				new Vertex(2 * 30, 2 * 50, "2"));
		
		
		System.out.println(graph);		
		
		System.out.println(graph.removeVertex(vertices.get(2)));
		Edge e43 = vertices.get(3).getEdges().get(1);		
		graph.removeEdge(e43);
		
		System.out.println(graph);

	}

}
