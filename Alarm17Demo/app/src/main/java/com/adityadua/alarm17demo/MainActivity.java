package com.adityadua.alarm17demo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    AlarmManager alarmManager;
    TimePicker timePicker;
    static MainActivity inst;
    Button stopBtn;
    PendingIntent pendingIntent;
    public static MainActivity instance(){
        return  inst;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker)findViewById(R.id.alarmtimePicker);

        ToggleButton alarmToggle=(ToggleButton) findViewById(R.id.alarmToggle);

        alarmManager= (AlarmManager)getSystemService(ALARM_SERVICE);

        stopBtn = (Button)findViewById(R.id.stop_btn);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        alarmToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ToggleButton)v).isChecked()){
                    Calendar calendar = Calendar.getInstance();

                    calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());

                    Intent i = new Intent(MainActivity.this,AlarmReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,i,0);
                    alarmManager.set(AlarmManager.RTC,calendar.getTimeInMillis(),pendingIntent);
                    Toast.makeText(getApplicationContext(),"Alarm On & Set",Toast.LENGTH_SHORT).show();

                }else{
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(getApplicationContext(),"Alarm Off",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
