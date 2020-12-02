package ir.justdev.lab.myeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import ir.justdev.lab.myeshop.Model.User;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText edtUsername, edtPassword;
    private TextInputLayout edtUsernameL, edtPasswordL;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        final Database database = initDatabase();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = database.get(edtUsername.getText().toString());
                if (user != null) {
                    if (user.Password.equals(edtPassword.getText().toString())) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                } else {
                    edtUsernameL.setError("نام کاربری یا رمز عبور اشتباه است!");
                    edtPassword.setError("نام کاربری یا رمز عبور اشتباه است!");
                }
            }
        });
    }

    private void init() {
        edtUsername = findViewById(R.id.activity_login_edt1);
        edtPassword = findViewById(R.id.activity_login_edt2);
        edtUsernameL = findViewById(R.id.activity_login_edtl1);
        edtPasswordL = findViewById(R.id.activity_login_edtl2);
        btnLogin = findViewById(R.id.activity_login_btn1);
    }

    private Database initDatabase() {
        return new Database(getApplicationContext());
    }
}