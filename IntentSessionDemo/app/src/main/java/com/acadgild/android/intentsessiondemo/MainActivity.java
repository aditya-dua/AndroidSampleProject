package com.acadgild.android.intentsessiondemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button nextActivity;
    EditText userEdt;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        im=(ImageView)findViewById(R.id.imageView);
        nextActivity = (Button)findViewById(R.id.button);
        userEdt = (EditText)findViewById(R.id.editText2);
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Topic for the day !
                // Question : Why we are having .class after the second activity.
                Intent i = new Intent(MainActivity.this,SecondActivty.class);
                // To pass data you have 2 ways ::
                // 1. You put Data in the intent.
                // 2. When you create a bundle and pass the data.
                i.putExtra("IntentName","Intent1");
                i.putExtra("UserName",userEdt.getText().toString());
                i.putExtra("age",12);
                i.putExtra("loggedIn",true);
                startActivity(i);
            }
        });
        Button b3= (Button)findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parsed=userEdt.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"));
                startActivity(intent);
            }
        });

        Button camera =(Button)findViewById(R.id.button4);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,100);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        //setContentView(R.layout.temp_layout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==100 && resultCode==RESULT_OK){
            Bitmap photo=(Bitmap) data.getExtras().get("data");
            im.setImageBitmap(photo);
        }

    }
}
