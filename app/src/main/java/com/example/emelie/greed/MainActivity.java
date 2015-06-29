package com.example.emelie.greed;

import android.content.Intent;
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
    private ScoreCounter scores;

    private int scoreCounter;
    private int round;
    private int totalScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreCounter = 0;
        round = 0;
        totalScore = 0;
        dice = new Dice();
        greed = new Greed(dice);
        scores = new ScoreCounter(greed);


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
                Toast.makeText(MainActivity.this, "ThrowButton Clicked", Toast.LENGTH_SHORT).show();


                dice1.setImageResource(greed.getImageResource(1));
                greed.updateScore();
                dice2.setImageResource(greed.getImageResource(2));
                greed.updateScore();
                dice3.setImageResource(greed.getImageResource(3));
                greed.updateScore();
                dice4.setImageResource(greed.getImageResource(4));
                greed.updateScore();
                dice5.setImageResource(greed.getImageResource(5));
                greed.updateScore();
                dice6.setImageResource(greed.getImageResource(6));
                greed.updateScore();

                int x = greed.getScore();
                turn_score_points.setText(String.valueOf(x));
                if(x == 0){
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
                Toast.makeText(MainActivity.this, "SaveButton Clicked", Toast.LENGTH_SHORT).show();
                totalScore = totalScore + scoreCounter;
                score_points.setText(String.valueOf(totalScore));
                if(totalScore > 10000){
                    Intent iinent= new Intent(MainActivity.this,WinningActivity.class);
                    startActivity(iinent);
                }
                scoreCounter = 0;
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




}
