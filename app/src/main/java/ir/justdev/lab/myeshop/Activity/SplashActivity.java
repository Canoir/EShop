package ir.justdev.lab.myeshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ir.justdev.lab.myeshop.Engine.Utils;
import ir.justdev.lab.myeshop.R;

public class SplashActivity extends AppCompatActivity {
    private Button btnSignUp, btnSignIn;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new Utils(getApplicationContext(), SplashActivity.this);
        if ((Boolean) utils.getSharedPreferences("isLogged", false))
            utils.goTo(MainActivity.class);
        setContentView(R.layout.activity_splash);
        init();
        btnSignIn.setOnClickListener(view -> startActivity(new Intent(SplashActivity.this, LoginActivity.class)));
        btnSignUp.setOnClickListener(view -> startActivity(new Intent(SplashActivity.this, RegisterActivity.class)));
    }

    private void init() {
        btnSignIn = findViewById(R.id.activity_splash_btn1);
        btnSignUp = findViewById(R.id.activity_splash_btn2);
    }
}