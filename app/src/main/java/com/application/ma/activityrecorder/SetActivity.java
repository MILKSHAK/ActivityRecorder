package com.application.ma.activityrecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetActivity extends AppCompatActivity {

    private EditText etInput;
    private TextView listView;
    protected Button addButton, deleteButton;
    private ActivityList activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        addButton = (Button) findViewById(R.id.AddActivity);
        deleteButton = (Button) findViewById(R.id.DeleteActivity);
        etInput = (EditText)findViewById(R.id.ActivityInput);
        listView = (TextView)findViewById(R.id.InputList);

        activityList = new ActivityList(this, null, null, 1);
        try {
            printDatabase();
        }catch (Exception e){
            Log.i("exxxx", e.toString());
        }
    }

    public void printDatabase() {
        String dbString = activityList.databaseToString();
        listView.setText(dbString);
        etInput.setText("");
    }

    //Add a product to the database
    public void addButtonClicked(View view){
        Log.i("exxxx", "CLİCKED ADD BUTTON");
        String inputText = etInput.getText().toString();
        activityList.addActivity(inputText);
        printDatabase();
    }

    //Delete a product to the database
    public void deleteButtonClicked(View view){
        Log.i("exxxx", "CLİCKED DELETE BUTTON");
        String inputText = etInput.getText().toString();
        activityList.deleteActivity(inputText);
        printDatabase();
    }
}
