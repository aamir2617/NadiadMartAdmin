package com.example.nadiadmartadmin.homeScreenActivity.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nadiadmartadmin.homeScreenActivity.OrdersFragment;
import com.example.nadiadmartadmin.homeScreenActivity.StockFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:return new OrdersFragment();

            case 1 : return new StockFragment();

            default:return null;
        }


    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position)
        {
            case 0:return "Orders";
            case 1:return "Stock";

            default:return null;

        }
    }

    @Override
    public int getCount() {

        return 2;
    }
}