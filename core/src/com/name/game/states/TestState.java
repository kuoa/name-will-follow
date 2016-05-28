package com.name.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestState extends State {

	public TestState(GameStateManager gsm) {
		super(gsm);
	}
	
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(float deltaTime) {
		System.out.println("Updating... ");
	}

	@Override
	public void render(SpriteBatch sb) {
		System.out.println("Rendering... ");
	}

}
