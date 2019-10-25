package com.example.camaraapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class LanzarAPPReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        Log.d("MIAPP", "ALARMA DISPARADA POR EL RECEIVER");

        // creating Calendar object
        Calendar calendar = Calendar.getInstance();

        // Demonstrate Calendar's get()method

        Log.d("MIAPP", "Current Day: " + calendar.get(Calendar.DATE));

        int day = calendar.get(Calendar.DATE);

        boolean par = (day%2 == 0);

        if(!par){
            Log.d("MIAPP", "DIA PAR");
            NotificationUtil.lanzarNotificiacion(context);
            //Esto no funciona.
            //context.startActivity(new Intent(context, MainActivity.class));

        }else{
            Log.d("MIAPP", "DIA IMPAR");
        }
    }

}
