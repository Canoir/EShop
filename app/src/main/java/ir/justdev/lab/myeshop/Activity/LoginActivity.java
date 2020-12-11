package ir.justdev.lab.myeshop.Activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import ir.justdev.lab.myeshop.Models.Database;
import ir.justdev.lab.myeshop.Models.Model.User;
import ir.justdev.lab.myeshop.R;
import ir.justdev.lab.myeshop.Engine.Utils;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText edtUsername, edtPassword;
    private TextInputLayout edtUsernameL, edtPasswordL;
    private Button btnLogin;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        final Database database = initDatabase();
        btnLogin.setOnClickListener(view -> {
            User user = database.get(edtUsername.getText().toString().toLowerCase());
            if (user != null) {
                if (user.Password.equals(edtPassword.getText().toString())) {
                    utils.setSharedPreferences("isLogged", true);
                    utils.goTo(MainActivity.class);
                }
            } else {
                edtUsernameL.setError(getString(R.string.LoginError));
                edtPasswordL.setError(getString(R.string.LoginError));
            }
        });
    }

    private void init() {
        edtUsername = findViewById(R.id.activity_login_edt1);
        edtPassword = findViewById(R.id.activity_login_edt2);
        edtUsernameL = findViewById(R.id.activity_login_edtl1);
        edtPasswordL = findViewById(R.id.activity_login_edtl2);
        btnLogin = findViewById(R.id.activity_login_btn1);
//
        utils = new Utils(getApplicationContext(),LoginActivity.this);
    }

    private Database initDatabase() {
        return new Database(getApplicationContext());
    }
}