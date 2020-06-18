package com.example.nadiadmartadmin.addProductActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.nadiadmartadmin.R;

public class CategoryDialog extends DialogFragment {

    static  int position=-1;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String[] categories = getActivity().getResources().getStringArray(R.array.product_categories);

        builder.setTitle("Select Category").setSingleChoiceItems(categories, position, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                position=which;
                AddProductActivity.edtCategory.setText(categories[position]);
                dialog.dismiss();



            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();

    }


}
