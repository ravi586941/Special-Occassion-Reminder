package com.example.nkharish.specialoccasionremainder;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;


public class SecondScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        Button screentwofb = (Button) findViewById(R.id.screentwofb);
        screentwofb.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View V) {
                        Button change = (Button) findViewById(R.id.screentwofb);
                        change.setText("Importing Facebook contacts.....");
                    }
                }
        );

        Button screentwogg = (Button) findViewById(R.id.screentwogg);
        screentwogg.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View V) {
                        Button change = (Button) findViewById(R.id.screentwogg);
                        change.setText("Importing Google contacts.....");
                    }
                }
        );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_screen, menu);
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
            return true;
        }

        if (id == R.id.addoccasion) {

            Intent intent2 = new Intent(SecondScreen.this, DataPicker.class);
            startActivity(intent2);
        }


        return super.onOptionsItemSelected(item);
    }
}
