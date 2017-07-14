package com.javarush.task.task35.task3513;

/**
 * Created by AMalakhov on 12.07.2017.
 */
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Tile[][] tiles = model.getGameTiles();
        System.out.println("---------------before-------------------");
        for (int i=0;i<tiles.length;i++){
            for (int j=0;j<tiles[0].length;j++){
                System.out.print(tiles[i][j].value+" ");
            }
            System.out.println();
        }
        model.left();
        System.out.println("---------------left-------------------");
        for (int i=0;i<tiles.length;i++){
            for (int j=0;j<tiles[0].length;j++){
                System.out.print(tiles[i][j].value+" ");
            }
            System.out.println();
        }
        model.left();
        System.out.println("---------------left-------------------");
        for (int i=0;i<tiles.length;i++){
            for (int j=0;j<tiles[0].length;j++){
                System.out.print(tiles[i][j].value+" ");
            }
            System.out.println();
        }
        System.out.println("---------------up-------------------");
        model.up();
        for (int i=0;i<tiles.length;i++){
            for (int j=0;j<tiles[0].length;j++){
                System.out.print(tiles[i][j].value+" ");
            }
            System.out.println();
        }
        System.out.println("---------------right-------------------");
        model.right();
        for (int i=0;i<tiles.length;i++){
            for (int j=0;j<tiles[0].length;j++){
                System.out.print(tiles[i][j].value+" ");
            }
            System.out.println();
        }
        System.out.println("---------------down-------------------");
        model.down();
        for (int i=0;i<tiles.length;i++){
            for (int j=0;j<tiles[0].length;j++){
                System.out.print(tiles[i][j].value+" ");
            }
            System.out.println();
        }



    }
}
