package com.application.ma.activityrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private ActivityList activityList;
    private ListView activityView;
    private final String ID = "myActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        activityList = new ActivityList(this, null, null, 1);
        activityView = (ListView) findViewById(R.id.activityView);

        try{
            // Defined Array values to show in ListView
            String[] values = activityList.databaseToString().split("\n");
            // Define a new Adapter
            // First parameter - Context
            // Second parameter - Layout for the row
            // Third parameter - ID of the TextView to which the data is written
            // Forth - the Array of data
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, values);

            // Assign adapter to ListView
            activityView.setAdapter(adapter);

            // ListView Item Click Listener
            activityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // ListView Clicked item index
                    int itemPosition     = position;

                    // ListView Clicked item value
                    String  itemValue    = (String) activityView.getItemAtPosition(position);

                    Intent i = new Intent(StartActivity.this, RecordActivity.class);
                    i.putExtra(ID, itemValue);
                    startActivity(i);

                    // Show Alert
                    Toast.makeText(getApplicationContext(),
                            "Position :" + itemPosition + "  ListItem : " + itemValue , Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
        catch(Exception e){
            Log.i("exxxx", e.toString());
        }

    }
}
