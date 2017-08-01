package com.adityadua.widgets14demo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int i=0;i<appWidgetIds.length;i++){
            int myId=appWidgetIds[i];

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("http://www.acadgild.com"));

            PendingIntent intent1 = PendingIntent.getActivity(context,0,intent,0);

            RemoteViews view = new RemoteViews(context.getPackageName(),R.layout.activity_main);


            view.setOnClickPendingIntent(R.id.button,intent1);
            appWidgetManager.updateAppWidget(myId,view);

            Toast.makeText(context,"widget added",Toast.LENGTH_LONG).show();


        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}
