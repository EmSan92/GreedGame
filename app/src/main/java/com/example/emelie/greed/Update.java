package com.example.emelie.greed;

import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Emelie on 2015-06-09.
 */
public class Update {
    private Dice dice;



    public Update(Greed greed){
        dice = new Dice();
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


    public  void  updateTurnScore(){

    }

    public void updateScore(){

    }

}
