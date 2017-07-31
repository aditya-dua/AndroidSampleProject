package com.adityadua.sendsmsdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText phone,message;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        phone = (EditText)findViewById(R.id.editText);
        message = (EditText)findViewById(R.id.editText2);
        tv=(TextView)findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone.getText().toString(),null,
                        message.getText().toString(),null,null);
                Toast.makeText(getApplicationContext(),"SMS Sent",Toast.LENGTH_LONG).show();
                //tv.setText("SMS send Sucess ! ");
            }
        });


        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(),"Permission Not Granted,Requesting..",Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,new String []{Manifest.permission.SEND_SMS},123);
            button.setEnabled(true);
        }else{
            Toast.makeText(getApplicationContext(),"Permission Granted !",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 123){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
               // tv.setText("Permission Granted , Please click on Send Button");
                button.setEnabled(true);
            }else{
                //tv.setText("Permission Not Granted");
                button.setEnabled(false);
            }
        }
    }
}
