package com.adityadua.sharedprefrences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button saveBtn;
    EditText ed1,ed2,ed3;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = (EditText)findViewById(R.id.nameEditText);
        ed2=(EditText)findViewById(R.id.idEditText);
        ed3= (EditText)findViewById(R.id.emailEditText);

        sharedPreferences = getSharedPreferences("mysharedprefrences",MODE_PRIVATE);
        saveBtn = (Button)findViewById(R.id.button);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = ed1.getText().toString();
                String phone = ed2.getText().toString();
                String email = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("nameKey",name);
                editor.putString("phoneKey",phone);
                editor.putString("emailKey",email);
                editor.commit();
                Toast.makeText(getApplicationContext(),"Shared Prefrences Created & Saved ",Toast.LENGTH_LONG).show();
            }
        });

        Button fetch =(Button)findViewById(R.id.button2);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPreferences.contains("nameKey")){
                    ed1.setText("Name :"+sharedPreferences.getString("nameKey",null));
                }
                if(sharedPreferences.contains("phoneKey")){
                    ed2.setText("Phone :"+sharedPreferences.getString("phoneKey",null));
                }
                if(sharedPreferences.contains("emailKey")){
                    ed3.setText("Email :"+sharedPreferences.getString("emailKey",null));
                }

            }
        });

    }
}
