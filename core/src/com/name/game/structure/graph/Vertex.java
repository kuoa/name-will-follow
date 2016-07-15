package com.name.game.structure.graph;

import java.util.ArrayList;

public class Vertex {
	
	private float x;
	private float y;
	
	private ArrayList<Edge> edges;
	
	public Vertex(float x, float y){
		this.x = x;
		this.y = y;
		edges = new ArrayList<Edge>();
	}

	
	public void addEdge(Edge edge){
		if(!edges.contains(edge)){
			edges.add(edge);
		}
	}
	
	public void removeEdge(Edge edge){
		edges.remove(edge);		
	}

    public Edge getFirstEdge(){

        return edges.size() > 0 ? edges.get(0) : null;
    }
	
	public boolean containsEdge(Edge edge){
		return edges.contains(edge);
	}
	
	public int getEdgesSize(){
		return edges.size();
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	
	@Override
	public String toString(){
		return "Vertex: -> x: " + x + " y: " + y;
	}
	
	@Override
	public boolean equals(Object other){
		if (other == null){
			return false;
		}
		
		if (!(other instanceof Vertex)){
			return false;
		}
		
		Vertex o = (Vertex) other;
		
		return (x == o.getX() && y == o.getY());
	}
}
