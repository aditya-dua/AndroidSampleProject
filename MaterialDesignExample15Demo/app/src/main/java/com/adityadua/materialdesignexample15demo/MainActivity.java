package com.adityadua.materialdesignexample15demo;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnSignUp;
    TextInputLayout inputLayoutName,inputLayoutEmail,inputLayoutPassword;
    EditText inputName,inputEmail,inputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLayoutName = (TextInputLayout)findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout)findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout)findViewById(R.id.input_layout_password);


        inputName = (EditText)findViewById(R.id.input_name);
        inputPassword = (EditText)findViewById(R.id.input_password);
        inputEmail = (EditText)findViewById(R.id.input_email);

        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));



        btnSignUp = (Button)findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });


    }

    private void submitForm(){

        if(!validateName()){
            return;
        }
        if(!validateEmail()){
            return;
        }
        if(!validatePassword()){
            return;
        }
        Toast.makeText(getApplicationContext(),"Thanks for registering",Toast.LENGTH_LONG).show();

    }

    private boolean validateName(){
        if(inputName.getText().toString().trim().isEmpty()){
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return  false;

        }else
        {inputLayoutName.setErrorEnabled(false);
        return true;}


    }


    private boolean validateEmail(){

        String mail = inputEmail.getText().toString();
        if(checkEmail(mail)){
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return  false;

        }else
        {inputLayoutEmail.setErrorEnabled(false);
            return true;}


    }

    private boolean checkEmail(String mail){
        return !TextUtils.isEmpty(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }


    private boolean validatePassword(){
        if(inputPassword.getText().toString().trim().isEmpty()){
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return  false;

        }else
        {inputLayoutPassword.setErrorEnabled(false);
            return true;}


    }

    private void requestFocus(View v){

        if(v.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher{
        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (view.getId()){
                case R.id.input_email : validateEmail();break;
                case R.id.input_name : validateName(); break;
                case R.id.input_password : validatePassword(); break;
            }
        }
    }
}
