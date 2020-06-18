package com.example.nadiadmartadmin.addProductActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nadiadmartadmin.R;
import com.example.nadiadmartadmin.homeScreenActivity.HomeScreenActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iceteck.silicompressorr.FileUtils;
import com.iceteck.silicompressorr.SiliCompressor;
import com.squareup.picasso.Picasso;

import java.io.File;

import static com.example.nadiadmartadmin.additionalMethods.ShortMethods.GetString;
import static com.example.nadiadmartadmin.additionalMethods.ShortMethods.makeToast;

public class AddProductActivity extends AppCompatActivity {

    private EditText edtProductName,edtOfferPrice,edtOriginalPrice,edtCode;
    public static EditText edtCategory;
    private ImageView imgProduct;
    private Button btnUpload;
    private ProgressBar progressBar;

    private CategoryDialog categoryDialog;
    private File imageFile,compressFile;
    private StorageReference storageReference;
    private DatabaseReference productReference;




    private Context context=AddProductActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        initView();

        createStorageFile();

        onClickListeners();

        getImageFromCode();






    }


    private void getImageFromCode() {


            edtCode.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(edtCode.length()==6)
                    {
                        getFile();

                    }

                }
            });



    }

    public void getFile() {


        imageFile = new File(Environment.getExternalStorageDirectory(),"/Android/ProductData/"+GetString(edtCategory)+"/"+GetString(edtCode)+".png");

        if(imageFile!=null)
        {
            Picasso.with(getApplicationContext()).load(imageFile).into(imgProduct);
            makeToast(context,Uri.fromFile(imageFile).toString());
        }
        else
        {
            makeToast(context,"File is null");
        }
    }



    private void onClickListeners() {

        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        edtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryDialog = new CategoryDialog();
                categoryDialog.setCancelable(false);
                categoryDialog.show(getSupportFragmentManager(),"Category Dialog");
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!GetString(edtCode).isEmpty() || !GetString(edtProductName).isEmpty() || !GetString(edtOfferPrice).isEmpty() || !GetString(edtOriginalPrice).isEmpty() || !GetString(edtCategory).isEmpty())
                {
                    uploadProduct();
                }
                else
                {
                    makeToast(context,"Enter all the details");
                }
            }
        });


    }



    private void uploadProduct() {

        progressBar.setVisibility(View.VISIBLE);
        try {
            compressFile = new File(SiliCompressor.with(this).compress(FileUtils.getPath(this,Uri.fromFile(imageFile)),new File(this.getCacheDir(),"temp")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Uri uri = Uri.fromFile(compressFile.getAbsoluteFile());

        storageReference.child(GetString(edtCode)).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while(!uri.isComplete());
                Uri downloadUrl = uri.getResult();

                ProductDetails productDetails = new ProductDetails(GetString(edtProductName),GetString(edtOfferPrice),GetString(edtOriginalPrice),GetString(edtCode),GetString(edtCategory),downloadUrl.toString());

                productReference.child(GetString(edtCode)).setValue(productDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        makeToast(context,"Item Added Successfully");
                        startActivity(new Intent(context, HomeScreenActivity.class));
                        finish();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        makeToast(context,e.getMessage());
                        progressBar.setVisibility(View.GONE);
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                makeToast(context,e.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });





    }

    private void createStorageFile() {

        File file = new File(Environment.getExternalStorageDirectory(),"/Android/ProductData");
        if(!file.exists())
        {
            file.mkdir();
        }
        else
        {
            makeToast(context,"File with this name already exist");
        }


    }

    private void initView() {

        edtProductName = findViewById(R.id.edtProductName);
        edtOfferPrice = findViewById(R.id.edtOfferPrice);
        edtOriginalPrice = findViewById(R.id.edtOriginalPrice);
        edtCode = findViewById(R.id.edtCode);
        edtCategory = findViewById(R.id.edtCategory);
        btnUpload = findViewById(R.id.btnUpload);
        imgProduct = findViewById(R.id.imgProduct);
        progressBar = findViewById(R.id.progressBar);

        storageReference = FirebaseStorage.getInstance().getReference("Products");
        productReference = FirebaseDatabase.getInstance().getReference("Products");


    }
}