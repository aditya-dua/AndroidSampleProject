package com.adityadua.menuexampledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button optionsMenu,contextMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        optionsMenu = (Button)findViewById(R.id.button);
        optionsMenu.setOnClickListener(this);

        contextMenu = (Button)findViewById(R.id.button2);
        contextMenu.setOnClickListener(this);
    }

    // any number of onClick listeners can be listened over here.
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button :
                Intent opMenu = new Intent(MainActivity.this,OptionMenu.class);
                startActivity(opMenu);
                break;
            case R.id.button2 :
                Intent conMenu = new Intent(MainActivity.this,ContextMenu.class);
                startActivity(conMenu);
                break;

        }
    }
}
