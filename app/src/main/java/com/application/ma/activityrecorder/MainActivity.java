package com.application.ma.activityrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected Button setButton,viewButton, startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButton = (Button) findViewById(R.id.setButton);
        viewButton = (Button) findViewById(R.id.viewButton);
        startButton = (Button) findViewById(R.id.startButton);
    }

    public void setButtonClicked(View view)
    {
        startActivity(new Intent(MainActivity.this, SetActivity.class));
    }

    public void viewButtonClicked(View view)
    {
        startActivity(new Intent(MainActivity.this, ViewActivity.class));
    }

    public void startButtonClicked(View view)
    {
        startActivity(new Intent(MainActivity.this, StartActivity.class));
    }
}
