package com.example.nadiadmartadmin.signUpActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nadiadmartadmin.R;
import com.example.nadiadmartadmin.signInActivity.SignInActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtMobileNumber, edtSecurityCode;
    private TextView txtSignIn;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();


        onClickListener();
    }

    private void onClickListener() {

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                finish();
            }
        });



    }

    private void initView() {

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtMobileNumber = findViewById(R.id.edtMobileNumber);
        edtSecurityCode = findViewById(R.id.edtSecurityCode);
        txtSignIn = findViewById(R.id.txtSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);

    }
}