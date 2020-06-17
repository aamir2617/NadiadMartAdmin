package com.example.nadiadmartadmin.signInActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nadiadmartadmin.R;
import com.example.nadiadmartadmin.signUpActivity.SignUpActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText edtUsername,edtPassword;
    private TextView txtSignUp;
    private Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initView();

        onClickListeners();

        emptyViews();




    }

    private void emptyViews() {


    }

    private void onClickListeners() {

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initView() {

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        txtSignUp = findViewById(R.id.txtSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
    }


}