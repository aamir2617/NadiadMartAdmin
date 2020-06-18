package com.example.nadiadmartadmin.additionalMethods;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class ShortMethods {

    public static void makeToast(Context context,String msg)
    {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static String GetString(EditText editText)
    {
        return editText.getText().toString();
    }
}
