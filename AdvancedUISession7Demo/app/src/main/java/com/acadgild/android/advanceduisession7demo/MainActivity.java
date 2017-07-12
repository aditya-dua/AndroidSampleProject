package com.acadgild.android.advanceduisession7demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView list;
    String [] languages=new String[]{
            "Java","Android","C","C++","C#",
            ".Net","Asp.Net","Servelets","Swings","Python",
            "Java Script","Angular JS","Struts","Springs","Hibernate"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.list);
        // The second param is the individual Row...
        // list of studnets then row means 1 student...
        // if we have to use only string based list , we can use
        // it using the single row given by android :: simple_list_item_1
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,languages);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/#q="+languages[position]));
                startActivity(i);
            }
        });

    }
}
