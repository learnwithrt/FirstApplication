package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //Why does the same not work for me?

        //findViewById returns an object of View and then you need to typecast to Button
        // API Level(build.gradle)
        //Button start=(Button)findViewById(R.id.start_button);
        Button start=findViewById(R.id.start_button);
        //a class which doesn't have a name
        //Lambda expression - Java 8 -> Extension of Anonymous classes
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is the start event
                //what to do? 1. create an intent to go from this activity to MainActivity
                //startActivity()-> will put the intent into action
                Intent intent=new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
