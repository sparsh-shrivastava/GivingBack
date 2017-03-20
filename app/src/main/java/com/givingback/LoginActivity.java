package com.givingback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static customs.MethodsClass.isValidEmail;

public class LoginActivity extends AppCompatActivity {
    EditText emailAddress,password;
    Button login;
    TextView createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        windWidgets();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmailAndPassword(emailAddress.getText().toString(),password.getText().toString()))
                    startActivity(new Intent(LoginActivity.this,DevelopmentActivity.class));

            }
        });
    }

    private void windWidgets() {
        emailAddress = (EditText)findViewById(R.id.login_email_address);
        password = (EditText)findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.login_button);
        createAccount = (TextView) findViewById(R.id.login_create_account);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
    }
    public boolean checkEmailAndPassword(String emailText,String passwordText){


        if(emailText.isEmpty()){
            Toast.makeText(LoginActivity.this,"Email cannot be empty.",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (passwordText.isEmpty()){
            Toast.makeText(LoginActivity.this,"Password cannot be empty.",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!isValidEmail(emailText)){
            Toast.makeText(LoginActivity.this,"Enter Valid Email.",Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;


    }

}
