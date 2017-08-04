package com.adityadua.service17demo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by AdityaDua on 04/08/17.
 */

public class MyService extends Service {

    MediaPlayer music;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        music = MediaPlayer.create(getApplicationContext(),R.raw.song);
        Toast.makeText(getApplicationContext(),"Service Created",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if(music.isPlaying()){

        }else{
            music.start();
        }
       // music.
        Toast.makeText(getApplicationContext(),"Service Started",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(music.isPlaying()){
            music.pause();
            music.reset();
        }else{

        }
        Toast.makeText(getApplicationContext(),"Service Stoped",Toast.LENGTH_LONG).show();
    }
}
