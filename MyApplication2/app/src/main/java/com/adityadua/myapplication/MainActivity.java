package com.adityadua.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView atv;
    MultiAutoCompleteTextView matv;

    String [] colors={
            "Black",
            "Blue",
            "Red",
            "Brown",
            "Violet",
            "Orange"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atv = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        matv=(MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextView);


        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,colors);

        atv.setAdapter(adaptor);
        atv.setThreshold(2);
    }
}
