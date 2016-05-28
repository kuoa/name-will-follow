package com.name.game.states;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {

	private Stack<State> states;
	
	public GameStateManager(){
		states = new Stack<State>();
	}
	
	public void push(State s){
		states.push(s);
	}
	
	public void pop(State s){
		states.pop();
	}
	
	public void set(State s){
		states.pop();
		states.push(s);
	}
	
	public void update(float deltaTime){
		states.peek().update(deltaTime);
	}
	
	public void render(SpriteBatch sb){
		states.peek().render(sb);
	}
	
}
