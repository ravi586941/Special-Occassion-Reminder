package com.example.nkharish.specialoccasionremainder;


import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.facebook.internal.CollectionMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class MainActivity extends ListActivity {

    private long Year;
    private int Month;
    DatesDataSource datasource;
    SQLiteDatabase database;
    SQLiteOpenHelper dbhelper;
    public static final String LOGTAG="Ravi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datasource=new DatesDataSource(this);
        datasource.Open();
        Log.i(LOGTAG,"Started MainActivity");
        dbhelper=new DatesDBOpenHelper(this);
        /*Button FbButton = (Button) findViewById(R.id.FbButton);
        FbButton.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick (View V)
                    {
                        //Button change=(Button) findViewById(R.id.FbButton);
                        //      change.setText("Importing Please wait....");
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                }
        );

        addOccasion();*/
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
        Log.i(LOGTAG,"Today's date is "+dateFormat.format(date));
        Log.i(LOGTAG,"Before getWritableDatabase()");
        database=dbhelper.getWritableDatabase();
         Log.i(LOGTAG,"After getWritableDatabase()");
        //createData();
        Log.i(LOGTAG,"Before findAll()");
        List<Occasions> occ= datasource.findAll();
        Log.i(LOGTAG,occ.get(0).getName());
        Collections.sort(occ);
        for(Occasions o:occ)
        {
            Log.i(LOGTAG,String.valueOf(o.getDiff()));
        }
        List l=new ArrayList();
        int i=1;

        for(Occasions occi: occ)
        {
            l.add( i+"." +occi.getName());
            l.add(occi.getOccasion());
            l.add(occi.getDate());
            l.add("Number of days left "+occi.getDiff());
            l.add(" ");
            i++;

        }



       // Collections.sort(l, Occasions.DiffComparator);

        ArrayAdapter arrayAdapter= new ArrayAdapter(getListView().getContext(),android.R.layout.simple_list_item_1,l);
        getListView().setAdapter(arrayAdapter);
    }







/*
public void addOccasion()
{

    Button CalendarButton = (Button) findViewById(R.id.CalendarButton);

    CalendarButton.setOnClickListener(
            new Button.OnClickListener() {

                public void onClick (View V)
                {
                    //Button change=(Button) findViewById(R.id.CalendarButton);
                    //change.setText("Adding Please wait....");
                    Intent intent2 = new Intent(MainActivity.this, DataPicker.class);
                    startActivity(intent2);
                }
            }
    );
}
*/

    private void createData(){
        Log.i(LOGTAG,"Entered createData()");
        datasource=new DatesDataSource(this);
        Occasions occasions=new Occasions();
        occasions.setName("Anusha");
        occasions.setOccasion("Birthday");
        occasions.setDate("29/11/1991");

        occasions=datasource.create(occasions);

        /*occasions.setName(search.getText().toString());
        occasions.setOccasion(occasion.getText().toString());
        occasions.setDate(dayTextView.getText().toString());
*/


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {
            Intent intent2 = new Intent(MainActivity.this, settings.class);
            startActivity(intent2);
        }

        if (id == R.id.addoccasion) {

            Intent intent2 = new Intent(MainActivity.this, DataPicker.class);
            startActivity(intent2);
        }

        if (id == R.id.fbgc) {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);

    }


}
