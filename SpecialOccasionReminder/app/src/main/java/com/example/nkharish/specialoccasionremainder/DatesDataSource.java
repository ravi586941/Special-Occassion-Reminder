package com.example.nkharish.specialoccasionremainder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Raveendra on 4/27/2015.
 */
public class DatesDataSource {

    //SQLiteDatabase database;
    //SQLiteOpenHelper dbhelper;
    public static final String LOGTAG="Ravi";
    private Cursor mycursor;

    SQLiteDatabase database;
    SQLiteOpenHelper dbhelper;


    //DatesDataSource datasource=new DatesDataSource(Context);

    //datasource.Open();

    private static final String[] columns = {
            DatesDBOpenHelper.COLUMN_PNAME,
            DatesDBOpenHelper.COLUMN_OCCASION,
            DatesDBOpenHelper.COLUMN_DATE
    };

    public DatesDataSource(Context context){

        dbhelper = new DatesDBOpenHelper(context);

    }

    public void Open() {
        database=dbhelper.getWritableDatabase();
    }
    public void Close(){
        dbhelper.close();
    }

    public Occasions create(Occasions occasions){
Open();
        ContentValues values=new ContentValues();
        Log.i(LOGTAG,occasions.getName());
        Log.i(LOGTAG,occasions.getOccasion());
        Log.i(LOGTAG,occasions.getDate());
        values.put("pname",occasions.getName());
        values.put("occasion",occasions.getOccasion());
        values.put("date",occasions.getDate());
        database.insert("Mata",null,values);
        /*values.put(DatesDBOpenHelper.COLUMN_PNAME, occasions.getName());
        values.put(DatesDBOpenHelper.COLUMN_OCCASION,occasions.getOccasion());
        values.put(DatesDBOpenHelper.COLUMN_DATE,occasions.getDate());
        */
        Log.i(LOGTAG,"Appended given values to columns");
        //long insertid=database.insert(DatesDBOpenHelper.TABLE,null,values);
        //occasions.setId(insertid);
        return occasions;
    }


    public List<Occasions> findAll(){
         Open();
        Log.i(LOGTAG, "Entered findAll() in DatesDataSource");
        List<Occasions> occ=new ArrayList<Occasions>();
        Log.i(LOGTAG, "In findall method before Cursor");
        //mycursor=database.query(DatesDBOpenHelper.TABLE,columns,null,null,null,null,null);
       Cursor mycursor1=database.rawQuery("select * from Mata",null);
        Log.i(LOGTAG,mycursor1.getColumnName(2));
        int diff=0;

        Log.i(LOGTAG, "after Cursor"+mycursor1.getCount());
        if (mycursor1.getCount()>0){
            Log.i(LOGTAG, "entered If loop after Cursor");
            while(mycursor1.moveToNext()){
                Log.i(LOGTAG, "entered while loop after Cursor");
                Occasions dob=new Occasions();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();

                Log.i(LOGTAG,"Today's date is "+dateFormat.format(date));
                Calendar now = Calendar.getInstance();
                dob.setOccasion(mycursor1.getString(mycursor1.getColumnIndex(DatesDBOpenHelper.COLUMN_OCCASION)));
                dob.setDate(mycursor1.getString(mycursor1.getColumnIndex(DatesDBOpenHelper.COLUMN_DATE)));
                dob.setName(mycursor1.getString(mycursor1.getColumnIndex(DatesDBOpenHelper.COLUMN_PNAME)));
                Log.i(LOGTAG, "######################"+dob.getDate());
                String []detail=dob.getDate().split("/");
                Log.i(LOGTAG, "Date given"+detail[0]);
                Log.i(LOGTAG, "Current Date"+now.get(Calendar.MONTH));
                Log.i(LOGTAG, "Month given"+detail[1]);
                Log.i(LOGTAG, "Current month"+now.get(Calendar.DATE));
                int mondiff;
                int daydiff;

                if(now.get(Calendar.DATE)-Integer.parseInt(detail[1])<0)
                {
                    Log.i(LOGTAG,"Month is yet to come");
                    mondiff=Integer.parseInt(detail[1])-now.get(Calendar.DATE) - 1;
                    daydiff=31-now.get(Calendar.DATE);
                    diff=mondiff*30+daydiff+Integer.parseInt(detail[0]);
                }
                else
                {
                    Log.i(LOGTAG,"Month is gone");
                    mondiff = 12-Math.abs(Integer.parseInt(detail[1])-now.get(Calendar.DATE)) - 1;
                    daydiff=31-now.get(Calendar.DATE);
                    diff=mondiff*30+daydiff+Integer.parseInt(detail[0]);

                }
                /*if(now.get(Calendar.MONTH)-Integer.parseInt(detail[0])<0)
                {
                    daydiff= Integer.parseInt(detail[0])-now.get(Calendar.MONTH);
                }
                else
                {
                    daydiff= now.get(Calendar.MONTH)-Integer.parseInt(detail[0]);
                }
*/
                //int diff=mondiff*30+daydiff+Integer.parseInt(detail[0]);
                Log.i(LOGTAG, "Months diff"+mondiff);
                Log.i(LOGTAG, "Days diff"+daydiff);
                Log.i(LOGTAG, "Number of days left"+diff);
                dob.setDiff(diff);
                occ.add(dob);
                Log.i(LOGTAG, "Selected appended Data");
            }
        }

        return occ;
    }

}
