package com.example.gaurav.login2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LocationDataBaseAdapter
{
    static final String DATABASE_NAME = "location.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOCATION"+
            "( " +"ID"+" integer primary key autoincrement,"+ "ADVICE text,ANDROID text,TIME text,LATITUDE text,LONGITUDE text); ";
    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelpert dbHelper;
    public  LocationDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelpert(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LocationDataBaseAdapter open() throws SQLException
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

    public void insertEntry(String advice,String android,String time,String latitude,String longitude)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("ADVICE",advice);
        newValues.put("ANDROID",android);
        newValues.put("TIME",time);
        newValues.put("LATITUDE",latitude);
        newValues.put("LONGITUDE",longitude);
        // Insert the row into your table
        db.insert("LOCATION", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public int deleteEntry(String advice)
    {
        //String id=String.valueOf(ID);
        String where="ADVICE=?";
        int numberOFEntriesDeleted= db.delete("LOCATION", where, new String[]{advice}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getlatitude(String advice,Integer lc)
    {
        Cursor cursor=db.query("LOCATION", null, " ADVICE=?", new String[]{advice},null, null, null);
        if(cursor.getCount()<1) // DOES Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToPosition(lc);
        String latitude= cursor.getString(cursor.getColumnIndex("LATITUDE"));
        cursor.close();
        return latitude;
    }

    public String getlongitude(String advice,Integer lc)
    {
        Cursor cursor=db.query("LOCATION", null, " ADVICE=?", new String[]{advice}, null, null, null);
        if(cursor.getCount()<1) // DOES Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToPosition(lc);
        String longitude= cursor.getString(cursor.getColumnIndex("LONGITUDE"));
        cursor.close();
        return longitude;
    }


    public String gettime(String advice,Integer lc)
    {
        Cursor cursor=db.query("LOCATION", null, " ADVICE=?", new String[]{advice}, null, null, null);
        if(cursor.getCount()<1) // DOES Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToPosition(lc);
        String time= cursor.getString(cursor.getColumnIndex("TIME"));
        cursor.close();
        return time;
    }

    public String getcount(String advice)
    {
        Cursor cursor=db.query("LOCATION", null, " ADVICE=?", new String[]{advice}, null, null, null);
        int count=cursor.getCount();
       // String count= cursor.getString(cursor.getColumnIndex("LC"));
        cursor.close();
        return String.valueOf(count);
    }
}

