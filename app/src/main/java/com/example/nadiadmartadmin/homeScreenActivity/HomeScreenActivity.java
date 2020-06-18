package com.example.nadiadmartadmin.homeScreenActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nadiadmartadmin.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeScreenActivity extends AppCompatActivity {

    FirebaseAuth mAuth=FirebaseAuth.getInstance();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);








    }


}