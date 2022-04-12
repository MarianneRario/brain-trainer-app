package com.semaphore.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startBtn; // initialize the button property outside the onCreate method

    public void start(View view){
        startBtn.setVisibility(View.INVISIBLE);
    }

    public void answer(View view) {
        Button answerBtn = (Button) view;
        Log.i("Tag", "Tag " + answerBtn.getTag().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtnId); // reference of the start button



    }
}