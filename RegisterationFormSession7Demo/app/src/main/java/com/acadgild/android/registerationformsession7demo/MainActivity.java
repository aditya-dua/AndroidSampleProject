package com.acadgild.android.registerationformsession7demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,phone;
    RadioGroup gender;
    CheckBox english,fluency,ielts,toefl;
    Button submit;
    Spinner age_grp;
    String [] ages={"10-15","16-18","19-22","23-30","31-40","41 & Above"};
    String selected_age_group="";
    String selected_gender="";
    final String CLASS_NAME="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String METHOD_NAME="onCreate()";
        // Method Starting & ending , hitting a DB connection
        // hitting a URL or any other scenereo where in you feel that the
        // Info about its execution needs to be posted.
        Log.i(CLASS_NAME+" "+METHOD_NAME,"Started Execution");
        name=(EditText)findViewById(R.id.editText);
        phone =(EditText)findViewById(R.id.phone);
        gender=(RadioGroup)findViewById(R.id.gender_group);
        english =(CheckBox)findViewById(R.id.english_chk);
        fluency =(CheckBox)findViewById(R.id.fluency_chk);
        ielts =(CheckBox)findViewById(R.id.ielts_chk);
        toefl =(CheckBox)findViewById(R.id.toefl_chk);
        Log.i(METHOD_NAME+" Name Field ",name.toString());
        age_grp =(Spinner)findViewById(R.id.age_grup);
        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String METHOD_NAME="onClick()";
                Log.i(METHOD_NAME+" "+CLASS_NAME,"Execution Started");
                String user="Name :"+name.getText().toString()+
                        " Phone :"+phone.getText().toString()+
                        " Gender:"+selected_gender+
                        " English :"+english.isChecked()+
                        " Fluency :"+fluency.isChecked()+
                        " IELTS :"+ielts.isChecked()+
                        " TOELF :"+toefl.isChecked()+
                        " Age Group ::"+selected_age_group;
                Log.d("User Value IS::",user);
                Toast.makeText(getApplicationContext(),user,Toast.LENGTH_LONG).show();
            }
        });

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,ages);
        age_grp.setAdapter(adapter);

        age_grp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String METHOD_NAME= "onItemSelected()";
                Log.i(METHOD_NAME,"Value of Row click (Posistion IS :"+position+")");
                selected_age_group=ages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.male){
                    selected_gender="Male";

                }else{
                    selected_gender="Female";

                }
            }
        });

    }
}
