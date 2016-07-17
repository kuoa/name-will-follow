package com.name.game.structure.graph;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Vertex {
	

	private Vector2 screenPosition;
	private ArrayList<Edge> edges;
	
	public Vertex(Vector2 screenPosition){
		this.screenPosition = screenPosition;
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

	public Vector2 getScreenPosition(){
		return screenPosition;
	}

	public float getX(){
		return screenPosition.x;
	}
	
	public float getY(){
		return screenPosition.y;
	}
	
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	
	@Override
	public String toString(){
		return "Vertex: -> x: " + screenPosition.x + " y: " + screenPosition.y;
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
		
		return (screenPosition.x == o.getX() && screenPosition.y == o.getY());
	}
}
