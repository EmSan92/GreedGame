package com.example.emelie.greed;
import java.util.Random;

/**
 * Created by Emelie on 2015-06-09.
 */
public class Dice {
//button , den vet var den är
// which value den har currently
    // en map för white grej.

    private int currentValue;

        // currentValue -> Image
    public Dice(){
    currentValue = 0;
    }


     public int throwDice(){
            Random rand = new Random();
            int k = 1 + rand.nextInt(6); //Randomly assigned a number between 1-6
            currentValue = k;
        return k;
    }

    public boolean isMarked(){

        return true;
    }
    public int getCurrentValue(){
        return currentValue;
    }
}
