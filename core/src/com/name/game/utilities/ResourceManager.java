package com.name.game.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by kuoa on 7/24/16.
 */
public class ResourceManager implements Disposable {

    public static final String RESOURCES = "res/";
    public static final String IMAGES = "square.pack";

    public AssetManager manager;

    public ResourceManager(){
        manager = new AssetManager();

        manager.load(RESOURCES + IMAGES, TextureAtlas.class);

        while(!manager.update()){
            Gdx.app.log("Loaded: " + manager.getProgress() * 100 + "%", getClass().toString());
        }
    }

    public TextureAtlas getTextureAtlas(){
        manager.finishLoading();

        return manager.get(RESOURCES + IMAGES);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
