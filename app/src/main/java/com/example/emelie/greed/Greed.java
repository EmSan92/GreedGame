package com.example.emelie.greed;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Emelie on 2015-06-09.
 *
 * This class takes care of all the logic in the game,
 * and have all the update methods for the different fields
 * that needs to update during the game.
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

    /*Returns the newly thrown dice image, and save the dicevalue in a list */
    public int getImageResource(int diceNumber) {
        diceList.add(0, 0);
        int updateIm = updateImage();
        dicevalue = dice.getCurrentValue();
        diceList.add(diceNumber, dicevalue);
        return updateIm;
    }

    public int getImageResourceIfIsMarked(int diceNumber){ //ÄNDRAS!!!!
        int oldValFromDice = diceList.get(diceNumber);
        return updateImage();

    }

    /* Returns the right image of the newly thrown dice */
    private int updateImage() {
        int valueFromDice = dice.throwDice();
        int temp = 0;
        switch (valueFromDice) {
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

    /* Returns a list that contains one or two three of a kind */
    public ArrayList<Integer> threeOfAKind() {
        ArrayList<Integer> templist = new ArrayList<Integer>();
        if (one >= 3) templist.add(1);
        else if (two >= 3) templist.add(2);
        else if (three >= 3) templist.add(3);
        else if (four >= 3) templist.add(4);
        else if (five >= 3) templist.add(5);
        else if (six >= 3) templist.add(6);

        return templist;
    }


    /* Returns a boolean that checks if there is a straight or not */
    public boolean straight() {
        if (one == 1 && two == 1 && three == 1 && four == 1 && five == 1 && six == 1) {
            return true;
        }
        return false;

    }

    /* Checks the dicevalue after every throw and update the different integers,
     * to se the amout of every number in one round.
     */
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

    /* Resets the value, when starting a new round */
    public void clearScore() {
        one = 0;
        two = 0;
        three = 0;
        four = 0;
        five = 0;
        six = 0;
    }

    /* Checks if the dices showing a straight, a three of a kind or/and if it exists any fives or ones,
     * and allocate the right amount of points. Returns an integer with the point of the current throw.  */
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
                }else if(x ==1 && y==1){
                    points = points + 2000;
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