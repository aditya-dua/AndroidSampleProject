package com.adityadua.threadsasynctask16demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by AdityaDua on 03/08/17.
 */

public class SecondActivity  extends Activity{

    private Bitmap mBitmap;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        mImageView = (ImageView)findViewById(R.id.imageView1);

        Button load = (Button)findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconFetch();
            }
        });
        Button other = (Button)findViewById(R.id.other);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Button Working!",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void iconFetch(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.example);
                SecondActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(mBitmap);
                    }
                });
            }

        }).start();
    }
}
