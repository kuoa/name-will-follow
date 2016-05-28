package com.name.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
	
	protected GameStateManager gsm;
	
	public State(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	public abstract void handleInput();
	public abstract void update(float deltaTime);
	public abstract void render(SpriteBatch sb);		
	
}
