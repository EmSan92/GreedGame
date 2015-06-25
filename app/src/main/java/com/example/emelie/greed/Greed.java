package com.example.emelie.greed;

import java.util.ArrayList;

/**
 * Created by Emelie on 2015-06-09.
 */
public class Greed {
    private ArrayList<Integer> diceList;
    private Dice dice;

    public Greed(Dice dice){
        diceList = new ArrayList<Integer>();
        this.dice = dice;
    }

    public int getImageResource(int diceNumber){
        diceList.add(0,0);
        int x = updateImage();
        int dicevalue = dice.getCurrentValue();
        diceList.add(diceNumber, dicevalue);
        return x;
    }

    public int updateImage() {
        int x = dice.throwDice();
        int temp = 0;
        switch (x) {
            case 1:
                temp = R.drawable.white1;
                break;
            case 2:
                temp = R.drawable.white2;
                break;
            case 3:
                temp = R.drawable.white3;
                break;
            case 4:
                temp = R.drawable.white4;
                break;
            case 5:
                temp = R.drawable.white5;
                break;
            case 6:
                temp = R.drawable.white6;
                break;

        }
        return temp;

    }


}
