package com.example.nadiadmartadmin.signUpActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nadiadmartadmin.R;
import com.example.nadiadmartadmin.homeScreenActivity.HomeScreenActivity;
import com.example.nadiadmartadmin.signInActivity.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.nadiadmartadmin.additionalMethods.ShortMethods.GetString;
import static com.example.nadiadmartadmin.additionalMethods.ShortMethods.makeToast;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtMobileNumber, edtSecurityCode;
    private String strUsername, strPassword, strMobileNumber, strSecurityCode;
    private TextView txtSignIn;
    private Button btnSignUp;
    private ProgressBar progressBar;

    private Context context = SignUpActivity.this;
    private FirebaseAuth mAuth;
    private DatabaseReference adminReference;



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

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkDetails();

            }
        });


    }

    private void checkDetails() {

        if (GetString(edtUsername).isEmpty() || GetString(edtPassword).isEmpty() || GetString(edtMobileNumber).isEmpty() || GetString(edtSecurityCode).isEmpty()) {
            makeToast(context, "Enter all the Details");
        } else {

            if (!GetString(edtSecurityCode).equals("Royal@2020")) {
                makeToast(context, "You are not authorized to this application");
            } else if (GetString(edtPassword).length() < 4) {
                makeToast(context, "Enter password with minimum 4 digit");
            } else if (GetString(edtMobileNumber).length() < 10) {
                makeToast(context, "Enter proper Mobile Number");
            } else {
                signUp();
            }
        }
    }

    private void signUp() {

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(GetString(edtUsername),GetString(edtPassword)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    mAuth.signInWithEmailAndPassword(GetString(edtUsername),GetString(edtPassword)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                adminReference.child(GetString(edtMobileNumber)).setValue(GetString(edtMobileNumber)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        startActivity(new Intent(context, HomeScreenActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        makeToast(context,e.getMessage().toString());
                                    }
                                });

                            }

                        }
                    });
                }

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
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        adminReference = FirebaseDatabase.getInstance().getReference("Admin");




    }
}