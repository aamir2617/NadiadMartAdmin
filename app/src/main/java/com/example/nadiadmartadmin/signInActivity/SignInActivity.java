package com.example.nadiadmartadmin.signInActivity;

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
import com.example.nadiadmartadmin.signUpActivity.SignUpActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.nadiadmartadmin.additionalMethods.ShortMethods.GetString;
import static com.example.nadiadmartadmin.additionalMethods.ShortMethods.makeToast;

public class SignInActivity extends AppCompatActivity {

    private EditText edtUsername,edtPassword;
    private TextView txtSignUp;
    private Button btnSignIn;
    private String strUsername,strPassword;
    private Context context = SignInActivity.this;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initView();

        if(currentUser!=null)
        {
            startActivity(new Intent(context, HomeScreenActivity.class));
            finish();
        }




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



                if(GetString(edtUsername).isEmpty() && GetString(edtPassword).isEmpty())
                {
                    makeToast(context,"Enter the details");
                }
                else if(GetString(edtUsername).isEmpty() && !GetString(edtPassword).isEmpty())
                {
                    edtUsername.setError("Enter the Username");
                }
                else if(GetString(edtPassword).isEmpty() && !GetString(edtUsername).isEmpty())
                {
                    edtPassword.setError("Enter Password");
                }
                else
                {
                    authentication(GetString(edtUsername),GetString(edtPassword));
                }




            }
        });
    }

    private void authentication(String strUsername, String strPassword) {

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(strUsername,strPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                startActivity(new Intent(context, HomeScreenActivity.class));
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                    makeToast(context,e.getMessage().toString());
                    progressBar.setVisibility(View.GONE);
            }
        });
    }






    private void initView() {

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        txtSignUp = findViewById(R.id.txtSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }


}