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
        List list = getEmptyTiles();
        if (list!=null&&list.size()!=0){
        int randomNumber = (int) (list.size()* Math.random());
        getEmptyTiles().get(randomNumber).value=(Math.random()<0.9?2:4);}
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

    private boolean compressTiles(Tile[] tiles){
        boolean isChanged=false;
        for (int i=0;i<tiles.length-1;i++){
            if (tiles[i].value==0&&tiles[i+1].value!=0){
                int temp;
                temp=tiles[i].value;
                tiles[i].value=tiles[i+1].value;
                tiles[i+1].value=temp;
                isChanged=true;
                i=-1;
            }
        }
        return isChanged;
    }
    private boolean mergeTiles(Tile[] tiles){
        boolean isChanged=false;
        for (int i=0;i<tiles.length-1;i++){
            if (tiles[i].value==tiles[i+1].value&&tiles[i].value!=0){
                    tiles[i].value = tiles[i].value + tiles[i + 1].value;
                    tiles[i+1].value=0;
                    if (tiles[i].value>maxTile){
                        maxTile=tiles[i].value;
                    }
                    score+=tiles[i].value;
                isChanged=true;
            }
        }
        compressTiles(tiles);
        return isChanged;
    }
    public void left(){
        boolean isCompressed=false;
        boolean isMerged=false;
        for (int i=0;i<gameTiles.length;i++){
            isCompressed=compressTiles(gameTiles[i]);
            isMerged=mergeTiles(gameTiles[i]);
        }
        if (isCompressed==true||isMerged==true){
            addTile();
        }
    }
}
