package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by AMalakhov on 12.07.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile [][] gameTiles;

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    protected int score=0;
    protected int maxTile=2;
    private Stack previousStates = new Stack();
    private Stack previousScores = new Stack();
    private boolean isSaveNeeded = true;

    public int getScore() {
        return score;
    }

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
        int number = 0;
        for (int i=0;i<tiles.length-1;i++){
            if (tiles[i].value==0&&tiles[i+1].value!=0){
                isChanged=true;
                int temp;
                temp=tiles[i].value;
                tiles[i].value=tiles[i+1].value;
                tiles[i+1].value=temp;
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

    public boolean canMove(){
        if (!getEmptyTiles().isEmpty()){
            return true;
        }
        for(int i = 0; i < gameTiles.length; i++) {
            for(int j = 1; j < gameTiles.length; j++) {
                if(gameTiles[i][j].value == gameTiles[i][j-1].value)
                    return true;
            }
        }
        for(int j = 0; j < gameTiles.length; j++) {
            for(int i = 1; i < gameTiles.length; i++) {
                if(gameTiles[i][j].value == gameTiles[i-1][j].value)
                    return true;
            }
        }
        return false;
    }
    public void left(){
        if (isSaveNeeded){
            saveState(gameTiles);
        }
        boolean isCompressed=false;
        boolean isMerged=false;
        int counter=0;
        for (int i=0;i<gameTiles.length;i++){
            isCompressed=compressTiles(gameTiles[i]);
            isMerged=mergeTiles(gameTiles[i]);
            if (isCompressed||isMerged){
                counter++;
            }
        }
        if (counter>0){
            addTile();
        }
        isSaveNeeded=true;
    }

    private void rotateCW(Tile[][] matrix) {
        int len = matrix.length;
        Tile temp = null;
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len - 1 - i ; j++){
                temp = matrix[i][j];
                matrix[i][j] = matrix[len -1 - j][len-1-i];
                matrix[len -1 - j][len-1-i] = temp;
            }
        }
        for(int i = 0; i < len/2; i++){
            for(int j = 0;j < len; j++){
                temp = matrix[i][j];
                matrix[i][j] = matrix[len-1 -i][j];
                matrix[len -1 -i][j] = temp;
            }
        }
    }
    public void up(){
        saveState(gameTiles);
        rotateCW(gameTiles);
        rotateCW(gameTiles);
        rotateCW(gameTiles);
        left();
        rotateCW(gameTiles);
    }
    public void right(){
        saveState(gameTiles);
        rotateCW(gameTiles);
        rotateCW(gameTiles);
        left();
        rotateCW(gameTiles);
        rotateCW(gameTiles);
    }
    public void down(){
        saveState(gameTiles);
        rotateCW(gameTiles);
        left();
        rotateCW(gameTiles);
        rotateCW(gameTiles);
        rotateCW(gameTiles);
    }

    private void saveState(Tile[][] tiles){
        Tile[][] savedTiles = new Tile[gameTiles.length][gameTiles[0].length];
        for (int i=0;i<savedTiles.length;i++){
            for (int j=0;j<savedTiles[0].length;j++){
                savedTiles[i][j]=new Tile(gameTiles[i][j].value);
            }
        }
        previousStates.push(savedTiles);
        previousScores.push(score);
        isSaveNeeded=false;
    }

    public void rollback(){
        if (!previousStates.isEmpty()&&!previousScores.isEmpty()) {
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
        }
    }
}
