package com.acadgild.android.intentsessiondemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by AdityaDua on 11/07/17.
 */

public class SecondActivty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_second);
        String message;
        Intent i= getIntent();
        String str=i.getStringExtra("UserName");
        String intentName = i.getStringExtra("IntentName");
        if(intentName.equals("Intent1")){
            // Some operation will be performed
        }
        message="UserName :"+str;
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
