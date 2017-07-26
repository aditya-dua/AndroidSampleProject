package com.acadgild.android.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /** Each XML file has an Activity :: **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int addition(){
        int a,b;
        a=10;
        b=20;
        int c=a+b;

        return  c;
    }
}
