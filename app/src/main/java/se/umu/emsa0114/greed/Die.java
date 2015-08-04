package se.umu.emsa0114.greed;
import java.util.Random;

/**
 * Created by Emelie on 2015-06-09.
 *
 * This class represent one dice.
 */
public class Die {
    private boolean isMarked;
    private int currentValue;

        // currentValue -> Image
    public Die(){
    currentValue = 0;
    }

    /*
    *Gives the dice its value
    *
    *@return the value of the dice
    */
     public int throwDie(){
            Random rand = new Random();
            int k = 1 + rand.nextInt(6); //Randomly assigned a number between 1-6
            currentValue = k;
        return k;
    }

   /*@Returns the current value of the thrown dice*/
    public int getCurrentValue(){
        return currentValue;
    }


}
