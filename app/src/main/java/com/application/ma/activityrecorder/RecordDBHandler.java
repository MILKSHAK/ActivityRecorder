package com.application.ma.activityrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ma on 2016/5/26.
 */

public class RecordDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "recordDB.db";
    public static final String TABLE_NAME = "recordDatabase";
    public static final String RECORD_ID = "_id";
    public static final String RECORD_YEAR = "_YEAR";
    public static final String RECORD_MONTH = "_MONTH";
    public static final String RECORD_DAY = "_DAY";
    public static final String RECORD_TIME = "_TIME";
    public static final String RECORD_ACTIVITY = "_ACTIVITY";
    public static final String RECORD_STATUS = "_STATUS";

    public RecordDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                RECORD_YEAR + " TEXT ," +
                RECORD_MONTH + " TEXT ," +
                RECORD_DAY + " TEXT ," +
                RECORD_TIME + " TEXT ," +
                RECORD_ACTIVITY + " TEXT ," +
                RECORD_STATUS + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

    //Add a new record to database
    public void addRecord(String year, String month, String day, String time, String activity, String status){
        ContentValues values = new ContentValues();
        values.put(RECORD_YEAR, year);
        values.put(RECORD_MONTH, month);
        values.put(RECORD_DAY, day);
        values.put(RECORD_TIME, time);
        values.put(RECORD_ACTIVITY, activity);
        values.put(RECORD_STATUS, status);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /*
    //Add new row to database
    public void addActivity(String activity){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, activity);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ACTIVITY, null, values);
        db.close();
    }

    //Delete product from the database
    public void deleteActivity(String activity){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ACTIVITY + " WHERE " + COLUMN_NAME + "=\"" + activity + "\";");
    }
    */

    //Print out the database as a string
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, null);
        //Move to first row in your result
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_ACTIVITY")) != null) {
                dbString += c.getString(c.getColumnIndex("_YEAR")) + " "
                            + c.getString(c.getColumnIndex("_MONTH")) + " "
                            + c.getString(c.getColumnIndex("_DAY")) + " "
                            + c.getString(c.getColumnIndex("_TIME")) + " "
                            + c.getString(c.getColumnIndex("_ACTIVITY")) + " "
                            + c.getString(c.getColumnIndex("_STATUS"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}