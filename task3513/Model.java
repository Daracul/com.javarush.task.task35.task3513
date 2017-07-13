package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMalakhov on 12.07.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile [][] gameTiles;
    protected int score=0;
    protected int maxTile=2;


    public Model() {
        resetGameTiles();
    }
    private List<Tile> getEmptyTiles(){
        List<Tile> emptyTileList = new ArrayList<>();
        for (int i=0;i<gameTiles.length;i++){
            for (int j=0;j<gameTiles.length;j++){
                if (gameTiles[i][j].value==0){
                    emptyTileList.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTileList;

    }
    private void addTile(){
        int randomNumber = (int) (getEmptyTiles().size()* Math.random());
        getEmptyTiles().get(randomNumber).value=(Math.random()<0.9?2:4);
    }
    protected void resetGameTiles(){
        gameTiles=new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i=0;i<gameTiles.length;i++){
            for (int j=0;j<gameTiles.length;j++){
                gameTiles[i][j]=new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void compressTiles(Tile[] tiles){
        for (int i=0;i<tiles.length-1;i++){
            if (tiles[i].value==0&&tiles[i+1].value!=0){
                int temp;
                temp=tiles[i].value;
                tiles[i].value=tiles[i+1].value;
                tiles[i+1].value=temp;
                i=-1;
            }
        }
    }
    private void mergeTiles(Tile[] tiles){
        for (int i=0;i<tiles.length-1;i++){
            if (tiles[i].value==tiles[i+1].value){
                    tiles[i].value = tiles[i].value + tiles[i + 1].value;
                    tiles[i+1].value=0;
                    if (tiles[i].value>maxTile){
                        maxTile=tiles[i].value;
                    }
                    score+=tiles[i].value;
            }
        }
        compressTiles(tiles);
    }
}
