package ir.justdev.lab.myeshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import ir.justdev.lab.myeshop.Engine.Utils;
import ir.justdev.lab.myeshop.R;

public class SplashActivity extends AppCompatActivity {
    private Button btnSignUp, btnSignIn;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new Utils(getApplicationContext(), SplashActivity.this);
        //
        utils.GET("https://jsonplaceholder.typicode.com/posts", new FutureCallback<JsonArray>() {
            @Override
            public void onCompleted(Exception e, JsonArray result) {
                if (e == null)
                    Log.i("Result", result.toString());
                else
                    Log.i("ResultError", e.getLocalizedMessage());
            }
        },true);
        utils.GET("https://jsonplaceholder.typicode.com/posts/1",(e,result)->{
            if (e == null)
                Log.i("Object", result.toString());
        });
        //
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