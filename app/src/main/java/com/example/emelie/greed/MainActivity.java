package com.example.emelie.greed;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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


    private int scoreCounter;
    private int round;
    private int totalScore;
    private boolean isMarked1,isMarked2,isMarked3,isMarked4,isMarked5,isMarked6;

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


        dice1 = (ImageView)findViewById(R.id.dice1);
        dice2 = (ImageView)findViewById(R.id.dice2);
        dice3 = (ImageView)findViewById(R.id.dice3);
        dice4 = (ImageView)findViewById(R.id.dice4);
        dice5 = (ImageView)findViewById(R.id.dice5);
        dice6 = (ImageView)findViewById(R.id.dice6);

        turn_score_points = (TextView)findViewById(R.id.turnscorepoints);
        this_round = (TextView)findViewById(R.id.scorepointsofthisround);
        score_points =(TextView)findViewById(R.id.scorepoints);


        throwButton = (Button)findViewById(R.id.throwButton);
        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "ThrowButton Clicked", Toast.LENGTH_SHORT).show();
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

                int x = greed.getScore();
                turn_score_points.setText(String.valueOf(x));
                if(x == 0){
                    scoreCounter = 0;
                    round = round +1;
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
                if(x >0 && !isMarked6 && !isMarked5 && !isMarked4 && !isMarked3 && !isMarked2 && !isMarked1){
                    scoreCounter = 0;
                    round ++;
                }

                scoreCounter = scoreCounter + x;
                this_round.setText(String.valueOf(scoreCounter));

                x = 0;
                }

        });



        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = 0;
                saveButton.setEnabled(false);
                //Toast.makeText(MainActivity.this, "SaveButton Clicked", Toast.LENGTH_SHORT).show();
                if(temp != scoreCounter && scoreCounter!=0) {
                    round = round + 1;
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

        /*When a imageview is pressed, it change the image to a gray dice and put the isMarked parameter to true */
        dice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMarked1 = true;
                dice1.setAlpha(0.5f);
            }
        });
        dice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMarked2 = true;
                dice2.setAlpha(0.5f);
            }
        });
        dice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMarked3 = true;
                dice3.setAlpha(0.5f);
            }
        });
        dice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMarked4 = true;
                dice4.setAlpha(0.5f);
            }
        });
        dice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMarked5 = true;
                dice5.setAlpha(0.5f);
            }
        });
        dice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMarked6 = true;
                dice6.setAlpha(0.5f);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int getRound(){
        return round;
    }
    public int getTotalScore(){
        return totalScore;
    }

    public void onBackPressed(){
        finish();
    }


}
