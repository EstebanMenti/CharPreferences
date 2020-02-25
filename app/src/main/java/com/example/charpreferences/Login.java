package com.example.charpreferences;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button btnLogin;
    private Switch switchRemember;
    private Preferences preferences;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = new Preferences(this);

        buildUi();

        email.setText(preferences.getPref(Preferences.MAIL));
        password.setText(preferences.getPref(Preferences.PASSWORD));


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValidLogin(email.getText().toString(), password.getText().toString()) ){
                    Toast.makeText(Login.this, "Email or pass incorrect", Toast.LENGTH_SHORT).show();
                }

                if(switchRemember.isChecked()){
                    saveOnPreferences(email.getText().toString(), password.getText().toString());
                }

                gotoMain();
            }
        });

    }

    private void buildUi(){
        email = findViewById(R.id.editTextMail);
        password = findViewById(R.id.editTextPass);
        btnLogin = findViewById(R.id.buttonLogin);
        switchRemember = findViewById(R.id.switchRemember);
    }


    private boolean isValidLogin(String emai, String pass){
        if(!isValidEmail(emai)){
            return false;
        }
        if(!isValidPassword(pass)){
            return false;
        }
        return true;
    }

    private void saveOnPreferences(String email, String pass){
        if(isValidEmail(email)){
            preferences.setPref(Preferences.MAIL,email);
        }
        if(isValidPassword(pass)){
            preferences.setPref(Preferences.PASSWORD,pass);
        }
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String pass){
        if(pass.length()> 3){
            return true;
        }
        return false;
    }
    private void  gotoMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
