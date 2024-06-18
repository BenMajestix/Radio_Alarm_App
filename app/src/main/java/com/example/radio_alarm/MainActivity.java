package com.example.radio_alarm;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.StrictMode;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
    }

    // OnToggleClicked() method is implemented the time functionality
    public void OnToggleClicked(View view)
    {
        long alarm_time;
        // Alarm Enabled
        if (((ToggleButton) view).isChecked())
        {
            // Create Intent
            Intent intent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            // Get Alarm Time
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            calendar.set(Calendar.MINUTE, timePicker.getMinute());
            System.out.println("Calendar Time: " + calendar.getTimeInMillis());
            alarm_time =(calendar.getTimeInMillis());

            // Set Alarm
            AlarmManager.AlarmClockInfo info = new AlarmManager.AlarmClockInfo(alarm_time, pendingIntent);
            if(alarmManager.canScheduleExactAlarms()) {
                alarmManager.setAlarmClock(info, pendingIntent);
            }

            // Info Toast Text
            Toast.makeText(MainActivity.this, "ALARM SCHEDULED", Toast.LENGTH_SHORT).show();
        }
        // Alarm Disabled
        else
        {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(MainActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
    }
}