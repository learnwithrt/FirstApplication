package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        //If I need the information from the intent
        // Get the intent first
        Intent intent=getIntent();//This is the intent which was used to start this activity
        //Quick fix-> Alt+enter
        //set that score on the scoreText textview
        int score=intent.getIntExtra("SCORE",0);
        TextView scoreText=findViewById(R.id.score_text);
        scoreText.setText("SCORE : "+score+"/5");
        //Functionality for the button home
        findViewById(R.id.home_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to the start activity
                startActivity(new Intent(ScoreActivity.this,StartActivity.class));
            }
        });
    }
}
