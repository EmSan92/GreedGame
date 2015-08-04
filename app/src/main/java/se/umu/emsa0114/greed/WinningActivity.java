package se.umu.emsa0114.greed;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.emelie.greed.R;


public class WinningActivity extends ActionBarActivity {

    private Button tryAgainButton;

    private TextView totalscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String round = bundle.getString("Round"); //Parameters from MainActivity
        String totalScore = bundle.getString("Totalscore"); //Parameters from MainActivity


        totalscore =(TextView)findViewById(R.id.totalscore);
        totalscore.setText(String.valueOf(totalScore) + " points after " + String.valueOf(round) + " rounds!");



        tryAgainButton = (Button)findViewById(R.id.tryagainButton);
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(WinningActivity.this,MainActivity.class); //Goes back to MainActivity when button is pressed

                startActivity(i);
                finish();
            }
        });
    }




    public void onBackPressed(){
        Intent i= new Intent(WinningActivity.this,MainActivity.class); //Goes back to MainActivity when button is pressed

        startActivity(i);
        finish();
    }
}
