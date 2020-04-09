package com.example.firstapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.Question;


public class MainActivity extends AppCompatActivity {
    private final String TAG="First Application";
    private final String LAST_INDEX="question_index";
    private static int score;//default for static variables in java is 0
    //TAG helps you find the messages
//MainActivity is just a java class
    //Application extends AppCompatActivity - Application Compatible Activity- Backward Compatibility
    //what to do when the activity is created?
    //Database of Questions (QuestionBank)
    //Alt+Enter -> REsolve Issues like import and get suggestions
    private Question mQuestion_Bank[]=new Question[]{
            new Question(R.string.q1,true),
            new Question(R.string.q2,false),
            new Question(R.string.q3,false),
            new Question(R.string.q4,false),
            new Question(R.string.q5,false)
    };
    //something to show which question is on the view
    private int mCurrent_Index=0;//arrays start with the index 0
    @Override//redefining the the implementation of onCreate
    //it will have some information(Bundle)- Later
    //Lifecycle-> What to do when the activity is created
    protected void onCreate(Bundle savedInstanceState) {
        //IT IS IMPORTANT that the first statement in over riden method is super.
        super.onCreate(savedInstanceState);//on create method of super class
        if(savedInstanceState!=null){//there is some information in this bundle
            mCurrent_Index=savedInstanceState.getInt(LAST_INDEX);
        }
        Log.d(TAG,"Activity Created");
        setContentView(R.layout.activity_main);//When the application has started, load activity_main as the content view
        //activity_main -> ID of the resource
        //R.layout.activity_main
        //find the button yes
        //alt+enter - can be used to see suggestions/ solve imports
        Button yes_button=findViewById(R.id.yes_button);
        //find the button no
        Button no_button=findViewById(R.id.no_button);
        //create a new listener for onClick event
        //new click listener for yes button
        yes_button.setOnClickListener(new View.OnClickListener() {
            //override the onClick method
            @Override
            public void onClick(View v) {
                //Now I want to show a message(Toast)
                //context -> where to show (MainActivity)
                //text - strings values
                //For how long do you want to show it? short or long?
                //Toast.makeText(MainActivity.this, R.string.yes_text, Toast.LENGTH_SHORT).show();
                //return true or false
                TextView question=null;
                question=findViewById(R.id.question_text);
                //Breakpoint -> Stop the execution and then go one step by one step
                //Step debugger
                if(mQuestion_Bank[mCurrent_Index].isAnswer()){//if the answer stored is true
                    //show a toast
                    //change color of text view
                    //answer is correct-> increment the score
                    score++;
                    question.setTextColor(Color.GREEN);
                    //Get a NULL pointer exception
                    //change the score
                }
                else{
                    //negative marking -> if the answer is wrong- > decrement the score
                    question.setTextColor(Color.RED);
                }
            }
        });
        no_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,R.string.no_text,Toast.LENGTH_LONG).show();
                TextView question=findViewById(R.id.question_text);
                if(mQuestion_Bank[mCurrent_Index].isAnswer()){//if the answer stored is true
                    //show a toast
                    //change color of text view
                    question.setTextColor(Color.RED);
                    //change the score
                }
                else{
                    score++;
                    question.setTextColor(Color.GREEN);
                }
            }
        });
        //Navigate the questions (Next and Previous Button)
        Button next=findViewById(R.id.next_button);
        Button previous=findViewById(R.id.prev_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrent_Index++;//Go to the next question
                mCurrent_Index=mCurrent_Index%mQuestion_Bank.length;
                updateView();
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrent_Index--;//Go to the previous question
                mCurrent_Index=mCurrent_Index<0?mQuestion_Bank.length-1:mCurrent_Index;
                updateView();//Method to change the content in the textview
            }
        });
        //Show the first question when the activity starts
        updateView();

        findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitExam();//making our code modular
            }
        });
    }
    //Call this function when the submit button is clicked
    private void submitExam(){
        //Go to the score activity and send the score
        Intent intent=new Intent(MainActivity.this,ScoreActivity.class);
        //I want to send data with the intent
        //Data in intent - extras
        //extra information within intent
        intent.putExtra("SCORE",score);
        startActivity(intent);
    }
    private void updateView() {
        //get the reference to the textview
        TextView question=findViewById(R.id.question_text);
        //reset the color of textview back to black
        question.setTextColor(Color.BLACK);
        //update the text view with the current index question
        question.setText(mQuestion_Bank[mCurrent_Index].getQuestion_id());
        //dynamically link the textview to the string resource
        //question.setText(R.string.q1)
    }
    //Alt+insert -> Generate code

    @Override
    protected void onStart() {
        super.onStart();
        //Use the implementation of the super class first
        //Then come back to the implementation of this class
        Log.d(TAG,"Activity has started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Activity has stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Activity has destroyed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Activity has paused");//The activity is in paused state
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Activity has resumed");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Bundle -> a collection containing the information about the activity
        //Save the index of the last question shown
        outState.putInt(LAST_INDEX,mCurrent_Index);//save the current index of the question
        //being shown in landscape/portrait
    }
}
