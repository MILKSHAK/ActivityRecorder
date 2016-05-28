package com.application.ma.activityrecorder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {

    private TextView activityLabel;
    private final String ID = "myActivity";
    MyService myService;
    boolean isBound = false;
    private RecordDBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        activityLabel = (TextView)findViewById(R.id.ActivityLabel);
        activityLabel.setText(getIntent().getStringExtra(ID));

        Intent i = new Intent(this, MyService.class);
        bindService(i, myConnection, Context.BIND_AUTO_CREATE);

        myDBHandler = new RecordDBHandler(this, null, null, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(myConnection);
    }

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyLocalBinder binder = (MyService.MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void startRecord (View view) {
        String currentTime = myService.getCurrentTime();
        TextView myText = (TextView) findViewById(R.id.TimeView);
        myText.setText(currentTime);

        String currentYear = myService.getCurrentYear();
        String currentMonth = myService.getCurrentMonth();
        String currentDay = myService.getDayInMonth();
        String myActivity = activityLabel.getText().toString();
        final String myStatues = "START";

        myDBHandler.addRecord(currentYear, currentMonth, currentDay, currentTime, myActivity, myStatues);
    }

    public void stopRecord (View view) {
        String currentTime = myService.getCurrentTime();
        TextView myText = (TextView) findViewById(R.id.TimeView);
        myText.setText(currentTime);

        String currentYear = myService.getCurrentYear();
        String currentMonth = myService.getCurrentMonth();
        String currentDay = myService.getDayInMonth();
        String myActivity = activityLabel.getText().toString();
        final String myStatues = "STOP";

        myDBHandler.addRecord(currentYear, currentMonth, currentDay, currentTime, myActivity, myStatues);
    }
}
