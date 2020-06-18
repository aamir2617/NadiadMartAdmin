package com.example.nadiadmartadmin.homeScreenActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.nadiadmartadmin.R;
import com.example.nadiadmartadmin.addProductActivity.AddProductActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import static com.example.nadiadmartadmin.additionalMethods.ShortMethods.makeToast;


public class StockFragment extends Fragment {

    private FloatingActionButton btnAddProduct;
    private Context context;

    public StockFragment() {
    }

    public StockFragment(Context context) {

        this.context=context;

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stock, container, false);

        initView(view);

        onClickListeners();

        return view;
    }

    private void onClickListeners() {

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                takePersmission();


            }
        });
    }

    private void takePersmission() {

        PermissionListener listener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                startActivity(new Intent(getContext(), AddProductActivity.class));
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                makeToast(getContext(),"You have to grant permission to enter");
            }
        };

        TedPermission.with(getContext()).setPermissionListener(listener).setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).check();

    }

    private void initView(View view) {

        btnAddProduct = view.findViewById(R.id.btnAddProduct);
    }
}