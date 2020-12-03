package ir.justdev.lab.myeshop;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Utils utils = new Utils(MainActivity.this);
        findViewById(R.id.activity_main_btn1).setOnClickListener(view -> {
            utils.setSharedPreferences("isLogged", false);
            utils.goTo(MainActivity.this, SplashActivity.class);
        });
    }
}