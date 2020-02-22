package com.example.charpreferences;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button btnLogin;
    private Switch remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buildUi();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(email.getText().toString(), password.getText().toString());
            }
        });

    }

    private void buildUi(){
        email = findViewById(R.id.editTextMail);
        password = findViewById(R.id.editTextPass);
        btnLogin = findViewById(R.id.buttonLogin);
        remember = findViewById(R.id.switchRemember);
    }


    private void login (String emai, String pass){

        if(!isValidEmail(emai)){
            Toast.makeText(Login.this, "Email incorrecto", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!isValidPassword(pass)){
            Toast.makeText(Login.this, "Password incorrecto", Toast.LENGTH_SHORT).show();
            return;
        }

        gotoMain();
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
