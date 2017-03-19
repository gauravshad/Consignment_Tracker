package com.example.gaurav.login2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FeatureDataBaseAdapter
{
    static final String DATABASE_NAME = "feature.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"FEATURE"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME text,ADVICE text,ANDROID text,DEVICE text,SIM text,NUMBER text); ";
    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelperf dbHelper;
    public  FeatureDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelperf(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  FeatureDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String user,String advice,String android,String device,String sim,String number)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME",user);
        newValues.put("ADVICE",advice);
        newValues.put("ANDROID",android);
        newValues.put("DEVICE",device);
        newValues.put("SIM",sim);
        newValues.put("NUMBER",number);
        // Insert the row into your table
        db.insert("FEATURE", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public int deleteEntry(String advice)
    {
        //String id=String.valueOf(ID);
        String where="ADVICE=?";
        int numberOFEntriesDeleted= db.delete("FEATURE", where, new String[]{advice}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }


    public String getcount(String advice)
    {
        Cursor cursor=db.query("FEATURE", null, " ADVICE=?", new String[]{advice}, null, null, null);
        int count=cursor.getCount();
        // String count= cursor.getString(cursor.getColumnIndex("LC"));
        cursor.close();
        return String.valueOf(count);
    }
}

