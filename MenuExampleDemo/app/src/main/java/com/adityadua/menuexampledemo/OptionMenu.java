package com.adityadua.menuexampledemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by AdityaDua on 19/07/17.
 */
// We AppCompatActivity three buttons in the actionbar
public class OptionMenu extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_menu);
        getSupportActionBar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id==R.id.setting){
            Toast.makeText(getApplicationContext(),"Setting clicked",Toast.LENGTH_LONG).show();
            return  true;
        }else if(id==R.id.save){
            Toast.makeText(getApplicationContext(),"Save Clicked",Toast.LENGTH_LONG).show();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
