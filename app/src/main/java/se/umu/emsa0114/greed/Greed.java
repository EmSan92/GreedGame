package se.umu.emsa0114.greed;

import com.example.emelie.greed.R;

import java.util.ArrayList;

/**
 * Created by Emelie on 2015-06-09.
 *
 * This class takes care of most logic of the game,
 * and have all the update methods for the different fields
 * that needs to update during the game.
 */
public class Greed {
    private ArrayList<Integer> diceList;
    private Die die;
    private int dieval;
    private int[] dieValues;
    private int points;



    public Greed(Die die)  {
        diceList = new ArrayList<Integer>();
        this.die = die;
        dieval = 0;
        points = 0;
        dieValues = new int[6];

    }

    /*
    *Update the imageviews and saves the dice values in a list
    *
    * @returns the newly thrown dice image
    */
    public int getImageResource(int dieNumber) {
        diceList.add(0, 0);
        int updateIm = updateImage();
        dieval = die.getCurrentValue();
        diceList.add(dieNumber, dieval);
        return updateIm;
    }



    /*
    *Connects the dice value to the correct image
    *
    *@return the right image of the newly thrown dice
    */
    private int updateImage() {
        int valueFromDie = die.throwDie();
        int temp = 0;
        switch (valueFromDie) {
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

    /* @return a list that contains one or two three of a kind */
    public ArrayList<Integer> threeOfAKind() {
        ArrayList<Integer> templist = new ArrayList<Integer>();
            for(int k = 0; k<6; k++ ){
                if (dieValues[k] >= 3) templist.add(k+1);
            }

        return templist;
    }


    /* @return a boolean that checks if there is a straight or not */
    public boolean straight() {
        int straight = 0;
        for (int i: dieValues){
            if(dieValues[i] == 1) straight++;
        }
        if(straight < 6){
            return false;
        }else{
            return  true;
        }

    }

    /* Checks the dieval after every throw and update the different integers,
     * to se the amout of every number in one round.
     */
    public void updateScore() {
        dieValues[dieval-1]++;

    }

    /* Resets the value, when starting a new round */
    public void clearScore() {
        for(int i = 0; i<6; i++)
        dieValues[i] = 0;

    }

    /* Checks if the dices showing a straight, a three of a kind or/and if it exists any fives or ones,
     * and allocate the right amount of points. Returns an integer with the point of the current throw.
     *
     * @return the total amount of pints of the throw
     * */
    public int getScore() {
        points = 0;
        if (straight()) {
            points = 1000;

        } else if (!threeOfAKind().isEmpty()) {
            int x = threeOfAKind().get(0);
            points = points + (dieValues[0]%3)*100 + (dieValues[4]%3)*50;
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
            points = points + (dieValues[0]%3)*100 + (dieValues[4]%3)*50;
        }
        clearScore();

        return points;
    }

    /*
    * @return list of dice values
    * */
    public ArrayList<Integer> getDiceList(){
    return diceList;
    }

}