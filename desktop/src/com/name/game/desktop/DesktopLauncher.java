package com.name.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.name.game.NameWillFollow;

public class DesktopLauncher {
	public static void main (String[] arg) {
						
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = NameWillFollow.WIDTH;
		config.height = NameWillFollow.HEIGHT;
		config.title = NameWillFollow.TITLE;
		
		new LwjglApplication(new NameWillFollow(), config);
	}
}
