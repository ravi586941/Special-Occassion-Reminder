package com.example.nkharish.specialoccasionremainder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View.OnClickListener;
import android.widget.EditText;

import java.util.Calendar;


public class DataPicker extends ActionBarActivity implements OnClickListener {

    static final int dialog_id=1;
    int yr, month,day;
    //Spinner spinner1;
    private Button add;
    EditText search, occasion;
    Button btnAdd,btnViewAll,btnShowInfo;
    SQLiteDatabase db;
    private TextView dayTextView;
    private Button changeDayButton;

   DatesDataSource datasource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_picker);

        Calendar date=Calendar.getInstance();
        yr=date.get(Calendar.YEAR);
        month=date.get(Calendar.MONTH);
        day=date.get(Calendar.DAY_OF_MONTH);

        showDialog(dialog_id);
        // addListenerOnButton();
        // addListenerOnSpinnerItemSelection();

        search=(EditText)findViewById(R.id.search);

        occasion=(EditText)findViewById(R.id.occasion);
        dayTextView = (TextView)findViewById(R.id.day_text_view);
        changeDayButton = (Button)findViewById(R.id.btn_change_day);
        changeDayButton.setOnClickListener(this);
        //spinner1 = (Spinner) findViewById(R.id.spinner1);
        add=(Button)findViewById(R.id.add);
        btnViewAll=(Button)findViewById(R.id.btnViewAll);
        btnShowInfo=(Button)findViewById(R.id.btnShowInfo);
        add.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnShowInfo.setOnClickListener(this);
        /*db=openOrCreateDatabase("SorDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS jaffa(name VARCHAR, occasion VARCHAR, date Date);");
        */
        datasource=new DatesDataSource(this);
    }

    protected Dialog onCreateDialog (int id)
    {

        switch(id)
        {
            case dialog_id:
                return new DatePickerDialog(this, mDateSetListener, yr, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener= new DatePickerDialog.OnDateSetListener() {


        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            yr = year;
            month = monthOfYear;
            day = dayOfMonth;
            //Toast.makeText(getBaseContext(), "Selected Date :" + day + "/" + (month + 1) + "/" + year, Toast.LENGTH_LONG).show();
            dayTextView.setVisibility(View.VISIBLE);
            dayTextView.setText(day + "/" + (month + 1) + "/" + yr);
        }
    } ;
/*

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
*/
    private void createData(){

        datasource=new DatesDataSource(this);
        Occasions occasions=new Occasions();
        /*occasions.setName("Anusha");
        occasions.setOccasion("Birthday");
        occasions.setDate("29/11/1991");*/
        occasions.setName(search.getText().toString());
        occasions.setOccasion(occasion.getText().toString());
        occasions.setDate(dayTextView.getText().toString());
        occasions=datasource.create(occasions);
    }

    public void onClick(View view)
    {
        int id = view.getId();
        switch(id){
            case R.id.add:
                /*if(search.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO jaffa VALUES('"+search.getText()+"','"+occasion.getText()+"','"+dayTextView.getText()+"');");
                showMessage("Success", "Occasion added");
                clearText();
                */
                datasource=new DatesDataSource(this);
                datasource.Open();
                createData();
                showMessage("Success", "Occasion added");
                break;

            case R.id.btnViewAll:
                Cursor c=db.rawQuery("SELECT * FROM jaffa order by date asc", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Name: "+c.getString(0)+"\n");
                    buffer.append("Occasion: "+c.getString(1)+"\n");
                    buffer.append("Date: "+c.getString(2)+"\n"+"\n");

                }
                showMessage("Occasion Details", buffer.toString());
                break;

            case R.id.btnShowInfo:
                showMessage("Special Occasion Reminder", "Coming Soon !!!");
                break;

            case R.id.btn_change_day:
                showDialog(dialog_id);
                break;
        }

    }

    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        search.setText("");
        occasion.setText("");
    }

    // get the selected dropdown list value
    /*
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(DataPicker.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });
    } */

};
