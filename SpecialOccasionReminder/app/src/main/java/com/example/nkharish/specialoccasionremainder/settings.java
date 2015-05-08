package com.example.nkharish.specialoccasionremainder;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View.OnClickListener;


import java.util.Collections;
import java.util.List;


public class settings extends Activity implements OnClickListener {
    private CheckBox check1, check2;
    private Button btn;
    DatesDataSource datasource;
    public static final String LOGTAG="Ravi";
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
         datasource=new DatesDataSource(this);
        datasource.Open();
        Log.i(LOGTAG,"In settings page before checking button");
        addListenerToCheckBox();
    }

    public  void  addListenerToCheckBox(){
        check1=(CheckBox)findViewById(R.id.checkBox);
        btn=(Button)findViewById(R.id.setreminder);
        check1.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if(((CheckBox)v).isChecked()){
                                              List<Occasions> occ= datasource.findAll();
                                              Collections.sort(occ);
                                              Log.i(LOGTAG,"In settings page getting days left");
                                              //btn.setOnClickListener(this);
                                              for(Occasions o:occ)
                                              {
                                                  Log.i(LOGTAG, String.valueOf(o.getDiff()));
                                                      a=(int)o.getDiff();
                                                  if(String.valueOf(a).equals(7)) {
                                                      btn.setOnClickListener(this);
                                                  }
                                              }
                                              Log.i(LOGTAG,"Going to Notification setting");
                                          }
                                      }
                                  }

        );

    }

    @Override
    public void onClick(View v){
        Log.i(LOGTAG,"Entered Notification range");

            Intent intent = new Intent();
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
            Notification notification = new Notification.Builder(this)
                    .setTicker("Special Occasion Reminder")
                    .setContentTitle("You have a special occasion tomorrow")
                    .setContentText("Open app to view occasion")
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentIntent(pIntent).getNotification();
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification);
        
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
