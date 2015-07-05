package com.example.emelie.greed;
import java.util.Random;

/**
 * Created by Emelie on 2015-06-09.
 *
 * This class represent one dice.
 */
public class Dice {
    private boolean isMarked;
    private int currentValue;

        // currentValue -> Image
    public Dice(){
    currentValue = 0;
    }

    /*Returns the value of the dice*/
     public int throwDice(){
            Random rand = new Random();
            int k = 1 + rand.nextInt(6); //Randomly assigned a number between 1-6
            currentValue = k;
        return k;
    }

   /*Returns the current value of the thrown dice*/
    public int getCurrentValue(){
        return currentValue;
    }


}
