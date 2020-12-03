package ir.justdev.lab.myeshop;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import ir.justdev.lab.myeshop.Model.User;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText edtUsername, edtPassword, edtReplyPassword;
    private TextInputLayout edtUsernameL, edtPasswordL, edtReplyPasswordL;
    private Button btnRegister;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        final Database database = initDatabase();
        btnRegister.setOnClickListener((view) -> {
            if (database.get(edtUsername.getText().toString()) == null) {
                edtUsernameL.setError(null);
                if (edtPassword.getText().toString().equals(edtReplyPassword.getText().toString())) {
                    if (database.add(new User(edtUsername.getText().toString().toLowerCase(), edtPassword.getText().toString()))) {
                        utils.setSharedPreferences("isLogged", true);
                        utils.goTo(RegisterActivity.this, MainActivity.class);
                    } else {
                        Toast.makeText(getApplicationContext(), "مشکل فنی غیر قابل تصور به وجود امده!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    edtPasswordL.setError("پسورد با تکرار آن مطابق نیست!");
                    edtReplyPasswordL.setError("پسورد با تکرار آن مطابق نیست!");
                }
            } else {
                edtUsernameL.setError("این نام کاربری موجود می باشد!");
            }

        });
    }

    private void init() {
        edtUsername = findViewById(R.id.activity_register_edt1);
        edtPassword = findViewById(R.id.activity_register_edt2);
        edtReplyPassword = findViewById(R.id.activity_register_edt3);
        edtUsernameL = findViewById(R.id.activity_register_edtl1);
        edtPasswordL = findViewById(R.id.activity_register_edtl2);
        edtReplyPasswordL = findViewById(R.id.activity_register_edtl3);
        btnRegister = findViewById(R.id.activity_register_btn1);
//
        utils = new Utils(RegisterActivity.this);
    }

    private Database initDatabase() {
        return new Database(getApplicationContext());
    }
}