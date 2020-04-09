package com.example.model;

public class Question extends Object {
    //Entity class
    //prefix - m
    private int mQuestion_id;//class variable(member)
    private boolean mAnswer;//camel case
    //getmQuestion - X
    //click -> Alt+insert (Getters and Setters)
    //Constructors
    //Default Constructor
    public Question(){

    }
    //Parameterized Constructor
    public Question(int question_id, boolean answer) {
        mQuestion_id = question_id;
        mAnswer = answer;
    }

    public int getQuestion_id() {
        return mQuestion_id;
    }

    public void setQuestion_id(int question_id) {
        mQuestion_id = question_id;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
