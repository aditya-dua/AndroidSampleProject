package com.acadgild.android.animation5demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// Animiation & Logs also.
public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    TextView txtMessage;
    Button btnStart;
    Animation animFadeIn,animFadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //The Flow of Code will be understood
        // Log will tell till where the code has been executed
        Log.i("onCreate()","Execution Started");
        setContentView(R.layout.activity_main);
        Log.i("onCreate()","ContentView Loaded");

        txtMessage = (TextView) findViewById(R.id.animtv);
        btnStart =(Button)findViewById(R.id.anim_btn);

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        Log.i("Fade In Animation",animFadeIn.toString());
        animFadeOut=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
        Log.i("Fade out Animation",animFadeOut.toString());

        animFadeIn.setAnimationListener(this);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMessage.setVisibility(View.VISIBLE);
                Log.i("onClick()","Text View is VISIBLE");
                txtMessage.setTextColor(Color.YELLOW);
                txtMessage.startAnimation(animFadeIn);
                }
        });


    }

    @Override
    public void onAnimationStart(Animation animation) {
        Toast.makeText(getApplicationContext(),"Animiation Started",Toast.LENGTH_LONG).show();
        Log.i("onAnimationStart","Execution Started");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast.makeText(getApplicationContext(),"Animiation Ended",Toast.LENGTH_LONG).show();
        txtMessage.setTextColor(Color.BLUE);
        Log.i("onAnimationEnd","Execution Ended");
       /* if(animation==animFadeIn) {
            txtMessage.startAnimation(animFadeOut);
            animFadeOut.start();
        }*/
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    /*public void startAnim(View v){
        txtMessage.setVisibility(View.VISIBLE);
        Log.i("onClick()","Text View is VISIBLE");
        txtMessage.startAnimation(animFadeIn);

    }*/
}
