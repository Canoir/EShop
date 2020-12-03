package ir.justdev.lab.myeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private Button btnSignUp, btnSignIn;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new Utils(SplashActivity.this);
        if ((Boolean) utils.getSharedPreferences("isLogged", false))
            utils.goTo(SplashActivity.this, MainActivity.class);
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