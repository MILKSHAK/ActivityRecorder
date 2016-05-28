package com.application.ma.activityrecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    private TextView recordView;
    private RecordDBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recordView = (TextView)findViewById(R.id.RecordDisplay);
        myDBHandler = new RecordDBHandler(this, null, null, 2);

        recordView.setText(myDBHandler.databaseToString());
    }
}
