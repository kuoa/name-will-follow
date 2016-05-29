package com.name.game.structure;

import java.util.ArrayList;

public class Graph {

	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;

	public Graph() {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}

	public Graph(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
		this.edges = new ArrayList<Edge>();
	}
	
	public boolean addEdge(Vertex from, Vertex to){
		
		if(from.equals(to)){
			return false;
		}
		
		Edge e = new Edge(from, to);
		
		if(edges.contains(e)){
			return false;
		}
		
		if(from.containsEdge(e) || to.containsEdge(e)){
			return false;
		}
		
		edges.add(e);
		from.addEdge(e);
		to.addEdge(e);
		
		return true;		
	}
	
	public boolean removeEdge(Edge e){
		
		e.getFrom().removeEdge(e);
		e.getTo().removeEdge(e);
		
		return edges.remove(e);		
	}
	
	public Vertex getVertex(float x, float y){				
		
		for(Vertex v : vertices){
			if(v.getX() == x && v.getY() == y){
				return v;
			}
		}
		
		return null;		
	}
	
	public boolean addVertex(Vertex v){
		
		if(vertices.contains(v)){
			return false;
		}
		
		return vertices.add(v);
	}
	
	public boolean removeVertex(Vertex v){
		
		if(vertices.remove(v)){
									
			while (v.getEdgesSize() > 0){
				removeEdge(v.getEdges().get(0));
			}			
			return true;
		}
		
		return false;		
	}
	
	@Override
	public String toString(){
		String s = "Graph: \n";
		
		for(Vertex v : vertices){
			s += v + "\n";
		}
		
		for(Edge e : edges){
			s += e + "\n";
		}
		
		return s;
	}

}
