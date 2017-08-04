package com.adityadua.threadsasynctask16demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView)findViewById(R.id.imageView1);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);

        Button b1 =(Button)findViewById(R.id.load);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IconAsyncTask().execute(R.drawable.example);
            }
        });

        Button b2 =(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"The button is working",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });

    }

    public class IconAsyncTask extends AsyncTask<Integer,Integer,Bitmap>{

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(),"In PreExecute",Toast.LENGTH_SHORT).show();
            mProgressBar.setVisibility(ProgressBar.VISIBLE);

        }

        @Override
        protected Bitmap doInBackground(Integer... params) {

           // Toast.makeText(getApplicationContext(),"In doInBackground",Toast.LENGTH_SHORT).show();
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),params[0]);

            for(int i=1;i<=10;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i*10);
            }

            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


            Toast.makeText(getApplicationContext(),"ProgressBar Updated"+values[0],Toast.LENGTH_SHORT).show();
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Toast.makeText(getApplicationContext(),"Execution Complete",Toast.LENGTH_SHORT).show();

            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            mImageView.setImageBitmap(bitmap);
            //super.onPostExecute(bitmap);
        }
    }
}
