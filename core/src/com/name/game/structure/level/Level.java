package com.name.game.structure.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 * Created by kuoa on 7/23/16.
 */

public class Level {

    public final int rows;
    public final int cols;

    public int types[][];

    public Level(){

        int rows = 20;
        int cols = 10;

        types = new int[rows][cols];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(Math.random() < 0.3) {
                    types[i][j] = (int) (Math.random() * 3);
                }
                else{
                    types[i][j] = 3;
                }
            }
        }

       // for(int i = rows - 1; i >= 0; i--){
       //     for(int j = 0; j < cols; j++){
       //         System.out.print(types[i][j]);
       //     }
       //     System.out.println();
       // }

        this.rows = types.length;
        this.cols = types[0].length;
    }

    public void save(String name){

        Json json = new Json();
        FileHandle file = Gdx.files.local(name);
        String text = json.toJson(this);
        file.writeString(text, false);
    }
}
