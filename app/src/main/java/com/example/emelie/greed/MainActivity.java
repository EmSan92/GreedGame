package com.example.emelie.greed;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private HashMap<Integer,ImageButton> dices;


    private ImageButton dice1;
    private ImageButton dice2;
    private ImageButton dice3;
    private ImageButton dice4;
    private ImageButton dice5;
    private ImageButton dice6;

    private Button throwButton;
    private Button scoreButton;
    private Button saveButton;

    private ArrayList<Integer> list;

    private Dice dice;
    private Greed greed;
    private ScoreCounter scores;
    private Update update;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dice = new Dice();
        greed = new Greed(dice);
        scores = new ScoreCounter(greed);
        update = new Update(greed);

        dice1 = (ImageButton)findViewById(R.id.dice1);
        dice2 = (ImageButton)findViewById(R.id.dice2);
        dice3 = (ImageButton)findViewById(R.id.dice3);
        dice4 = (ImageButton)findViewById(R.id.dice4);
        dice5 = (ImageButton)findViewById(R.id.dice5);
        dice6 = (ImageButton)findViewById(R.id.dice6);



        throwButton = (Button)findViewById(R.id.throwButton);
        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();


                dice1.setImageResource(greed.getImageResource(1));
                dice2.setImageResource(greed.getImageResource(2));
                dice3.setImageResource(greed.getImageResource(3));
                dice4.setImageResource(greed.getImageResource(4));
                dice5.setImageResource(greed.getImageResource(5));
                dice6.setImageResource(greed.getImageResource(6));

                }

        });


        scoreButton = (Button)findViewById(R.id.scoreButton);
        saveButton = (Button)findViewById(R.id.saveButton);



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
