package com.application.ma.activityrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ma on 2016/5/26.
 */
public class ActivityList extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "activityList.db";
    public static final String TABLE_ACTIVITY = "activities";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "activityname";

    public ActivityList(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_ACTIVITY + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_NAME + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_ACTIVITY);
        onCreate(db);
    }

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

    //Print out the database as a string
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ACTIVITY + " WHERE 1";

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, null);
        //Move to first row in your result
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("activityname")) != null) {
                dbString += c.getString(c.getColumnIndex("activityname"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}
