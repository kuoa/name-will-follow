package com.name.game.structure.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 * Created by kuoa on 7/23/16.
 */
public final class LevelFactory {

    public static Level loadLevel(String name){

        FileHandle file = Gdx.files.internal("levels/" + name + ".dat");
        String jsonText = file.readString();
        Json json = new Json();
        Level level = json.fromJson(Level.class, jsonText);

        return level;
    }
}
