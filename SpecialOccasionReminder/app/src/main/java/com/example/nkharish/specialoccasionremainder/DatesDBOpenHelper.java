package com.example.nkharish.specialoccasionremainder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Raveendra on 4/27/2015.
 */
public class DatesDBOpenHelper extends SQLiteOpenHelper {
    public static final String LOGTAG="Ravi";
    public static final String DATABASE_NAME="GENY";
    public static final int DATABASE_VERSION=1;

    public static final String TABLE="Mata";

    public static final String COLUMN_OCCASION="occasion";
    public static final String COLUMN_DATE="date";
    public static final String COLUMN_PNAME="pname";




    private static final String TABLE_CREATE="CREATE TABLE Mata(pname TEXT, occasion TEXT, date, diff long);";
            //"CREATE TABLE " + TABLE + "(" + COLUMN_OCCASION + " TEXT, "+ COLUMN_PNAME + " TEXT, "+ COLUMN_DATE + " TEXT "+ ");";




    public DatesDBOpenHelper(Context context){


        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(LOGTAG,"In Constructor of DatesDBOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(TABLE_CREATE);
        Log.i(LOGTAG,"Table is created");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS" + TABLE);
        onCreate(db);
    }
}
