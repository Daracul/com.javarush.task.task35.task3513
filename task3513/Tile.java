package com.javarush.task.task35.task3513;

import java.awt.*;

/**
 * Created by AMalakhov on 12.07.2017.
 */
public class Tile {
     int value;

    public Tile(int value) {
        this.value = value;
    }

    public Tile() {
        this.value = 0;
    }

    public boolean isEmpty() {
        if (value == 0) {
            return true;
        }
        return false;
    }

    public Color getFontColor() {
        if (value < 16) {
            return new Color(0x776e65);
        } else return new Color(0xf9f6f2);
    }

    public Color getTileColor() {
        int intColor = 0;
        switch (value) {
            case 0:
                intColor = 0xcdc1b4;
                break;
            case 2:
                intColor = 0xeee4da;
                break;
            case 4:
                intColor = 0xede0c8;
                break;
            case 8:
                intColor = 0xf2b179;
                break;
            case 16:
                intColor = 0xf59563;
                break;
            case 32:
                intColor = 0xf67c5f;
                break;
            case 64:
                intColor = 0xf65e3b;
                break;
            case 128:
                intColor = 0xedcf72;
                break;
            case 256:
                intColor = 0xedcc61;
                break;
            case 512:
                intColor = 0xedc850;
                break;
            case 1024:
                intColor = 0xedc53f;
                break;
            case 2048:
                intColor = 0xedc22e;
                break;
            default:
                intColor = 0xff0000;
                break;

        }
        return new Color(intColor);

    }
}
