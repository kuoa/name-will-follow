package com.name.game.structure;

import java.util.ArrayList;

import com.name.game.NameWillFollow;

public class Vertex {
	
	private float x;
	private float y;
	private String label;
	
	private ArrayList<Edge> edges;
	
	public Vertex(float x, float y, String label){
		this.x = x;
		this.y = y;
		this.label = label;
		edges = new ArrayList<Edge>();
	}
	
	public Vertex(String label){
		this.x = (float)Math.random() * NameWillFollow.WIDTH;
		this.y = (float)Math.random() * NameWillFollow.HEIGHT;
		this.label = label;
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
		return "Vertex: " + label + " x: " + x + " y: " + y;
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
