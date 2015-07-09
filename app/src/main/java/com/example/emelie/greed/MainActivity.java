package com.example.emelie.greed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;



public class MainActivity extends ActionBarActivity {

    private HashMap<Integer,ImageButton> dices;


    private ImageView dice1;
    private ImageView dice2;
    private ImageView dice3;
    private ImageView dice4;
    private ImageView dice5;
    private ImageView dice6;

    private TextView this_round;
    private TextView turn_score_points;
    private TextView score_points;



    private Button throwButton;

    private Button saveButton;

    private ArrayList<Integer> list;

    private Dice dice;
    private Greed greed;

    private  boolean hasIncreasedRound;
    private int scoreCounter;
    private int round;
    private int totalScore;
    private boolean isMarked1,isMarked2,isMarked3,isMarked4,isMarked5,isMarked6;

    public static ArrayList<Boolean> markedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scoreCounter = 0;
        round = 0;
        totalScore = 0;
        dice = new Dice();
        greed = new Greed(dice);
        isMarked1 = false;
        isMarked2 = false;
        isMarked3 = false;
        isMarked4 = false;
        isMarked5 = false;
        isMarked6 = false;
        hasIncreasedRound = false;



        dice1 = (ImageView)findViewById(R.id.dice1);
        dice2 = (ImageView)findViewById(R.id.dice2);
        dice3 = (ImageView)findViewById(R.id.dice3);
        dice4 = (ImageView)findViewById(R.id.dice4);
        dice5 = (ImageView)findViewById(R.id.dice5);
        dice6 = (ImageView)findViewById(R.id.dice6);

        turn_score_points = (TextView)findViewById(R.id.turnscorepoints);
        this_round = (TextView)findViewById(R.id.scorepointsofthisround);
        score_points =(TextView)findViewById(R.id.scorepoints);




        /*
        *When an imageview is clicked on, the boolean is set to true and the imageview is set to be transparent.
        * Click on a transparent imageview will set the boolean to false and the view to fully visiable.
        */
        dice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMarked1){
                    dice1.setAlpha(1f);
                    isMarked1 = false;
                }
                else{isMarked1 = true;
                dice1.setAlpha(0.5f);}

            }
        });
        dice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMarked2){
                    dice2.setAlpha(1f);
                    isMarked2 = false;

                }else {
                    isMarked2 = true;
                    dice2.setAlpha(0.5f);
                }
            }
        });
        dice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMarked3){
                    dice3.setAlpha(1f);
                    isMarked3 = false;
                }else {
                    isMarked3 = true;
                    dice3.setAlpha(0.5f);
                }
            }
        });
        dice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMarked4){
                    dice4.setAlpha(1f);
                    isMarked4 = false;
                }else {
                    isMarked4 = true;
                    dice4.setAlpha(0.5f);
                }
            }
        });
        dice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMarked5){
                    dice5.setAlpha(1f);
                    isMarked5 = false;
                }else {
                    isMarked5 = true;
                    dice5.setAlpha(0.5f);
                }
            }
        });
        dice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMarked6){
                    dice6.setAlpha(1f);
                    isMarked6 = false;
                }else {
                    isMarked6 = true;
                    dice6.setAlpha(0.5f);
                }
            }
        });



        throwButton = (Button)findViewById(R.id.throwButton);
        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Highlights the dices and the save button to indicate that they are clickable*/
                saveButton.setEnabled(true);
                if(!isMarked6 && !isMarked5 && !isMarked4 && !isMarked3 && !isMarked2 && !isMarked1){
                    dice1.setAlpha(1f);
                    dice2.setAlpha(1f);
                    dice3.setAlpha(1f);
                    dice4.setAlpha(1f);
                    dice5.setAlpha(1f);
                    dice6.setAlpha(1f);
                }
               /* Throw and update all imageviews(Dices) and update the score in the updateScore method */
                if(!isMarked1){
                dice1.setImageResource(greed.getImageResource(1));
                greed.updateScore();}
                if(!isMarked2){
                dice2.setImageResource(greed.getImageResource(2));
                greed.updateScore();}
                if(!isMarked3){
                dice3.setImageResource(greed.getImageResource(3));
                greed.updateScore();}
                if(!isMarked4){
                dice4.setImageResource(greed.getImageResource(4));
                greed.updateScore();}
                if(!isMarked5){
                dice5.setImageResource(greed.getImageResource(5));
                greed.updateScore();}
                if(!isMarked6){
                dice6.setImageResource(greed.getImageResource(6));
                greed.updateScore();}

                int scoreOfRound = greed.getScore();

                turn_score_points.setText(String.valueOf(scoreOfRound));

                /*Makes the save button and the dices transparent and unclickable*/
                if(scoreOfRound == 0 &&( !isMarked6 || !isMarked5 || !isMarked4 || !isMarked3 || !isMarked2 || !isMarked1)){
                    scoreCounter = 0;
                    if(!hasIncreasedRound){
                        round = round + 1;
                        hasIncreasedRound = false;
                    }

                    isMarked1=false;
                    isMarked2=false;
                    isMarked3=false;
                    isMarked4=false;
                    isMarked5=false;
                    isMarked6=false;
                    dice1.setAlpha(0.5f);
                    dice2.setAlpha(0.5f);
                    dice3.setAlpha(0.5f);
                    dice4.setAlpha(0.5f);
                    dice5.setAlpha(0.5f);
                    dice6.setAlpha(0.5f);
                    saveButton.setEnabled(false);

                }

                /*If no dices are marked and the score is not saved when clicking on the throw button,
                 *no points is saved and it counts as a round*/

                if(scoreOfRound > 0 && (!isMarked6 && !isMarked5 && !isMarked4 && !isMarked3 && !isMarked2 && !isMarked1)){
                    scoreCounter = 0;
                    round ++;
                    hasIncreasedRound = true;


                }
                scoreCounter = scoreCounter + scoreOfRound;

                this_round.setText(String.valueOf(scoreCounter));



                scoreOfRound = 0;

                }

        });



        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = 0;
                saveButton.setEnabled(false);
                turn_score_points.setText(String.valueOf(0));
                this_round.setText(String.valueOf(0));

                if(temp != scoreCounter && scoreCounter!=0) {
                    temp = scoreCounter;
                }
                totalScore = totalScore + scoreCounter;
                score_points.setText(String.valueOf(totalScore));
                if(totalScore > 10000){
                    Intent iinent= new Intent(MainActivity.this,WinningActivity.class);
                    iinent.putExtra("Round", String.valueOf(round));
                    iinent.putExtra("Totalscore", String.valueOf(totalScore));
                    round = 0;
                    startActivity(iinent);
                    finish();
                }
                scoreCounter = 0;
                isMarked1=false;
                isMarked2=false;
                isMarked3=false;
                isMarked4=false;
                isMarked5=false;
                isMarked6=false;
                dice1.setAlpha(0.5f);
                dice2.setAlpha(0.5f);
                dice3.setAlpha(0.5f);
                dice4.setAlpha(0.5f);
                dice5.setAlpha(0.5f);
                dice6.setAlpha(0.5f);
            }
        });

    }



    public void onBackPressed(){
        finish();
    }


}


