package com.name.game.structure.graph;

public class Edge {
	
	private Vertex from;
	private Vertex to;
	private float distance;
	
	public Edge(Vertex from, Vertex to){
		this.from = from;
		this.to = to;
		
		float dx = from.getX() - to.getX();
		float dy = from.getY() - to.getY();
		
		distance = (float)Math.sqrt(dx * dx + dy * dy);					
	}
	
	public Vertex getNeighbour(Vertex current){
		
		if(!(current.equals(from) || current.equals(to))){
			return null;
		}
		return (current.equals(from)) ? to : from;
	}
	
	public void calibrateDistance(){
		
		float dx = from.getX() - to.getX();
		float dy = from.getY() - to.getY();
						
		distance = (float)Math.sqrt(dx * dx + dy * dy);
	}				
	
	public Vertex getFrom(){
		return from;
	}
	
	public Vertex getTo(){
		return to;
	}
	
	public float getDistance(){
		return distance;
	}
	
	@Override
	public String toString(){
		return "Edge -> Distance " + distance + " From: " + from + " To: " + to;
	}
	
	@Override
	public boolean equals(Object other){
		
		if(other == null){
			return false;
		}
		
		if(!(other instanceof Edge)){
			return false;
		}
		
		Edge e = (Edge) other;
		
		return (from.equals(e.getFrom()) && to.equals(e.getTo()));
	}			
}
