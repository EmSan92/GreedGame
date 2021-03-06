package se.umu.emsa0114.greed;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emelie.greed.R;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayList<ImageView> dice;
    private boolean[] marked;

    private ImageView die1;
    private ImageView die2;
    private ImageView die3;
    private ImageView die4;
    private ImageView die5;
    private ImageView die6;

    private TextView this_round;
    private TextView turn_score_points;
    private TextView score_points;


    private Button throwButton;

    private Button saveButton;

    private ArrayList<Integer> list;

    private Die die;
    private Greed greed;
    private int k;

    private boolean hasIncreasedRound;
    private int scoreCounter;
    private int round;
    private int totalScore;


    public static ArrayList<Boolean> markedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dice = new ArrayList<ImageView>();


        marked = new boolean[6];

        k = 0;
        scoreCounter = 0;
        round = 0;
        totalScore = 0;
        die = new Die();
        greed = new Greed(die);

        hasIncreasedRound = false;


        die1 = (ImageView) findViewById(R.id.die1);
        die2 = (ImageView) findViewById(R.id.die2);
        die3 = (ImageView) findViewById(R.id.die3);
        die4 = (ImageView) findViewById(R.id.die4);
        die5 = (ImageView) findViewById(R.id.die5);
        die6 = (ImageView) findViewById(R.id.die6);

        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        dice.add(die4);
        dice.add(die5);
        dice.add(die6);

        turn_score_points = (TextView) findViewById(R.id.turnscorepoints);
        this_round = (TextView) findViewById(R.id.scorepointsofthisround);
        score_points = (TextView) findViewById(R.id.scorepoints);


        throwButton = (Button) findViewById(R.id.throwButton);
        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Highlights the dices and the save button to indicate that they are clickable*/
                saveButton.setEnabled(true);
                if (!marked[0] && !marked[1] && !marked[2] && !marked[3] && !marked[4] && !marked[5]) {
                    for (int c = 0; c < 6; c++) {
                        dice.get(c).setAlpha(1f);
                    }
                }


               /* Throw and update all imageviews(Dices) and update the score in the updateScore method */

                for (int i = 0; i < 6; i++) {
                    if (!marked[i] || (marked[0] && marked[1] && marked[2] && marked[3] && marked[4] && marked[5]) ) {
                        dice.get(i).setImageResource(greed.getImageResource(i));
                        greed.updateScore();


                    }
                }
                /**
                 * slå alla tärningar.
                 * räkna poäng för nya tärningar.
                 * kolla om alla är markerade och poäng ska ges.
                 *
                 * kolla om alla är markerade och om poäng ej ska ges.
                 * isf öka rounds och nollställ variabler
                 *
                 * kolla om några är markerade och scoreOfRounds för nya
                 */

                int scoreOfRound = greed.getScore();
                if(scoreOfRound >0 && (marked[0] && marked[1] && marked[2] && marked[3] && marked[4] && marked[5])){
                    for (int c = 0; c < 6; c++) {
                        marked[c] = false;
                        dice.get(c).setAlpha(1f);
                    }
                    scoreCounter = scoreCounter + scoreOfRound;
                }else if(scoreOfRound <= 0 && (marked[0] && marked[1] && marked[2] && marked[3] && marked[4] && marked[5])){
                    scoreCounter = 0;
                    scoreOfRound = 0;
                    round = round + 1;

                }



                /*Makes the save button and the dices transparent and unclickable*/
                else if (scoreOfRound == 0 && (!marked[0] || !marked[1] || !marked[2] || !marked[3] || !marked[4] || !marked[5])) {
                    scoreCounter = 0;
                    if (!hasIncreasedRound) {
                        round = round + 1;
                        hasIncreasedRound = false;
                    }

                    for (int i = 0; i < 6; i++) {
                        marked[i] = false;
                        dice.get(i).setAlpha(0.5f);
                    }

                    saveButton.setEnabled(false);

                }


                /*If no dices are marked and the score is not saved when clicking on the throw button,
                 *no points is saved and it counts as a round*/

                else if(scoreOfRound > 0 && (!marked[0] && !marked[1] && !marked[2] && !marked[3] && !marked[4] && !marked[5])) {
                    scoreCounter = scoreOfRound;
                    round++;
                    hasIncreasedRound = true;


                }
                else if(scoreOfRound > 0 && (marked[0] || marked[1] || marked[2] || marked[3] || marked[4] || marked[5])){
                    scoreCounter += scoreOfRound;
                }
                //scoreCounter = scoreCounter + scoreOfRound;
                turn_score_points.setText(String.valueOf(scoreOfRound));
                this_round.setText(String.valueOf(scoreCounter));




            }

        });


        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = 0;
                saveButton.setEnabled(false);
                turn_score_points.setText(String.valueOf(0));
                this_round.setText(String.valueOf(0));

                if (temp != scoreCounter && scoreCounter != 0) {
                    temp = scoreCounter;
                }
                totalScore = totalScore + scoreCounter;
                score_points.setText(String.valueOf(totalScore));
                if (totalScore > 10000) {
                    Intent iinent = new Intent(MainActivity.this, WinningActivity.class);
                    iinent.putExtra("Round", String.valueOf(round));
                    iinent.putExtra("Totalscore", String.valueOf(totalScore));
                    round = 0;
                    startActivity(iinent);
                    finish();
                }
                scoreCounter = 0;

                for (int i = 0; i < 6; i++) {
                    marked[i] = false;
                }

                for (int b = 0; b < 6; b++) {
                    dice.get(b).setAlpha(0.5f);
                }
            }
        });

    }


    public void onBackPressed() {
        finish();
    }

    public void setMarked(View view) {
        switch (view.getId()) {
            case R.id.die1:
                if (!marked[0] && greed.givePoints(0)) {
                    marked[0] = true;
                    dice.get(0).setAlpha(0.5f);
                } else {
                    dice.get(0).setAlpha(1f);
                    marked[0] = false;

                }
                break;
            case R.id.die2:
                if (!marked[1] && greed.givePoints(1)) {
                    marked[1] = true;
                    dice.get(1).setAlpha(0.5f);
                } else {
                    dice.get(1).setAlpha(1f);
                    marked[1] = false;

                }
                break;
            case R.id.die3:
                if (!marked[2]&& greed.givePoints(2)) {
                    marked[2] = true;
                    dice.get(2).setAlpha(0.5f);
                } else {
                    dice.get(2).setAlpha(1f);
                    marked[2] = false;

                }
                break;
            case R.id.die4:
                if (!marked[3]&& greed.givePoints(3)) {
                    marked[3] = true;
                    dice.get(3).setAlpha(0.5f);
                } else {
                    dice.get(3).setAlpha(1f);
                    marked[3] = false;

                }
                break;
            case R.id.die5:
                if (!marked[4]&& greed.givePoints(4)) {
                    marked[4] = true;
                    dice.get(4).setAlpha(0.5f);
                } else {
                    dice.get(4).setAlpha(1f);
                    marked[4] = false;

                }
                break;
            case R.id.die6:
                if (!marked[5] && greed.givePoints(5)) {
                    marked[5] = true;
                    dice.get(5).setAlpha(0.5f);
                } else {
                    dice.get(5).setAlpha(1f);
                    marked[5] = false;

                }
                break;
        }


    }

}


