package com.adityadua.menuexampledemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by AdityaDua on 19/07/17.
 */

public class ContextMenu extends AppCompatActivity{


    private static final int MENU_ID_01 = 100;
    private static final int MENU_ID_02 = 101;

    ListView listView;
    ArrayList<String> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_example);

        contacts = new ArrayList<String>();
        for(int i=0;i<=10;i++)
        {
            contacts.add("item " + i);
        }

        listView = (ListView)findViewById(R.id.list);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        listView.setAdapter(adapter);

        // Register the ListView  for Context menu
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu Ex");
        menu.add(0, MENU_ID_01, 2, "Context Menu gp 0 1");//groupId, itemId, order, title
        menu.add(0, MENU_ID_02, 1, "Context Menu gp 0 2");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==MENU_ID_01 && item.getGroupId()==0){
            Toast.makeText(getApplicationContext(),"Clicked on " +item.getGroupId()+"..."+item.getItemId(),Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==MENU_ID_02 && item.getGroupId()==0){
            Toast.makeText(getApplicationContext(),"Clicked on " +item.getGroupId()+"..."+item.getItemId(),Toast.LENGTH_LONG).show();
        }
        else{
            return false;
        }
        return true;
    }
}













/*
public class ContextMenu extends AppCompatActivity {

    private static final int MENU_ID_01=100;
    private static final int MENU_ID_02=101;

    ListView listview;
    ArrayList<String> contacts;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.context_example);

        contacts=new ArrayList<String>();

        for(int i=0;i<10;i++){
            contacts.add("Contact "+i);
        }
        listview = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adaptor = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,contacts);
        Log.i("Context Menu ","List setting adaptor");
        listview.setAdapter(adaptor);
        registerForContextMenu(listview);
    }

    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
       super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu Example");
        menu.add(0,MENU_ID_01,2,"Context Menu gp 0 1");
        menu.add(0,MENU_ID_02,1,"Context Menu gp 0 2");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==MENU_ID_01 && item.getGroupId()==0){
            Toast.makeText(getApplicationContext(),
                    "You clicked "+item.getGroupId()+" "+item.getItemId(),Toast.LENGTH_LONG).show();
        }else if(item.getItemId()==MENU_ID_01 && item.getGroupId()==0){
            Toast.makeText(getApplicationContext(),
                    "You clicked ::"+item.getGroupId()+" "+item.getItemId(),Toast.LENGTH_LONG).show();

        }else{
            return  false;
        }
        return true;
    }
}
*/