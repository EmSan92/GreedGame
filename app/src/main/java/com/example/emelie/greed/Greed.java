package com.example.emelie.greed;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Emelie on 2015-06-09.
 */
public class Greed {
    private ArrayList<Integer> diceList;
    private Dice dice;
    private int dicevalue;
    private int one, two, three, four, five, six, points;

    public Greed(Dice dice) {
        diceList = new ArrayList<Integer>();
        this.dice = dice;
        dicevalue = 0;
        points = 0;
    }

    public int getImageResource(int diceNumber) {
        diceList.add(0, 0);
        int x = updateImage();
        dicevalue = dice.getCurrentValue();
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

    public ArrayList<Integer> threeOfAKind() {
        ArrayList<Integer> templist = new ArrayList<Integer>();
        if (one == 3) templist.add(1);
        else if (two == 3) templist.add(2);
        else if (three == 3) templist.add(3);
        else if (four == 3) templist.add(4);
        else if (five == 3) templist.add(5);
        else if (six == 3) templist.add(6);

        return templist;
    }


    public boolean straight() {
        if (one == 1 && two == 1 && three == 1 && four == 1 && five == 1 && six == 1) {
            return true;
        }
        return false;

    }

    public void updateScore() {
        switch (dicevalue) {
            case 1:
                one++;
                break;
            case 2:
                two++;
                break;
            case 3:
                three++;
                break;
            case 4:
                four++;
                break;
            case 5:
                five++;
                break;
            case 6:
                six++;
                break;
        }
    }

    public void clearScore() {
        one = 0;
        two = 0;
        three = 0;
        four = 0;
        five = 0;
        six = 0;
    }

    public int getScore() {
        points = 0;
        if (straight()) {
            points = 1000;

        } else if (!threeOfAKind().isEmpty()) {
            int x = threeOfAKind().get(0);
            points = points + (one%3)*100 + (five%3)*50;
            if (threeOfAKind().size() > 1) {
                int y = threeOfAKind().get(1);
                if (x == 1) {
                    points = points + 1000 + y * 100;
                } else if (y == 1) {
                    points = points + 1000 + x * 100;
                } else {
                    points = points + x * 100 + y * 100;
                }

            }else{
                if (x == 1) {
                    points = points + 1000;
                }else{
                    points = points + x*100;
                }
            }


        }
        else{
            points = points + (one%3)*100 + (five%3)*50;
        }
        clearScore();

        return points;
    }
}