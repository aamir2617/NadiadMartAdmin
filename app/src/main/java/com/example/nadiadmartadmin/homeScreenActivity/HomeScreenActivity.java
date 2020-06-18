package com.example.nadiadmartadmin.homeScreenActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.nadiadmartadmin.R;
import com.example.nadiadmartadmin.homeScreenActivity.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeScreenActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        initView();

        setTabs();






    }

    private void setTabs() {

        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {

        sectionsPagerAdapter = new SectionsPagerAdapter(this,getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);

    }
}