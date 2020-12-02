package ir.justdev.lab.myeshop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import ir.justdev.lab.myeshop.Model.User;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText edtUsername, edtPassword, edtReplyPassword;
    private TextInputLayout edtUsernameL, edtPasswordL, edtReplyPasswordL;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        final Database database = initDatabase();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtPassword.getText().toString().equals(edtReplyPassword.getText().toString()))
                    database.add(new User(edtUsername.getText().toString(), edtPassword.getText().toString()));
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
    }

    private Database initDatabase() {
        return new Database(getApplicationContext());
    }
}