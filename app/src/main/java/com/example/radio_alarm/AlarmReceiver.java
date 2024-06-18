package com.example.radio_alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.CombinedVibration;
import android.os.StrictMode;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    // implement onReceive() method
    public void onReceive(Context context, Intent intent) {
        //we will use vibrator first
        VibratorManager vibrator = (VibratorManager) context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
        VibrationEffect effect = (VibrationEffect) VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE);
        CombinedVibration combinedVibration = (CombinedVibration) CombinedVibration.createParallel(effect);
        vibrator.vibrate(combinedVibration);

        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
    }
}
