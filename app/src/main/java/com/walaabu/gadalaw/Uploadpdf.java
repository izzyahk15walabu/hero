package com.walaabu.gadalaw;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Uploadpdf extends AppCompatActivity {


    private static final int RESULT_LOAD_PDF = 1;

  private StorageReference mStorageRef;
   StorageReference pdftoupload;
    Button btnSelectpdf;
    RecyclerView rc_uploadpdf;

    List<String>mpdffilenamelist;
    List<String>mpdfdonelist;


    Adpaterforselectedpdfs madpterforuploadlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpdf);
       // myapp= FirebaseApp.initializeApp(getApplicationContext());

mStorageRef=FirebaseStorage.getInstance().getReference();

        pdftoupload=mStorageRef.child("PDF");

        btnSelectpdf=findViewById(R.id.btn_uploadselected);


        mpdffilenamelist=new ArrayList<>();
        mpdfdonelist=new ArrayList<>();

        madpterforuploadlist=new Adpaterforselectedpdfs(mpdffilenamelist,mpdfdonelist);


        rc_uploadpdf=findViewById(R.id.rc_uploadpdf);
        rc_uploadpdf.setLayoutManager(new LinearLayoutManager(this));
        rc_uploadpdf.setHasFixedSize(true);
        rc_uploadpdf.setAdapter(madpterforuploadlist);













        btnSelectpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // the below lines sends an intent to outside  to select pdfs
                Intent intent=new Intent();
                intent.setType("application/pdf");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select pdfs"),RESULT_LOAD_PDF);
            }
        });
    }

    //the below line allows us to choose the  file we wont from the file explorer in android
//this methode proccess the intent by cheking our requestcode we sent to outside to get the pdfs files if we get a data
// it proceess it by getting its filename and adding it to the pdfnamelists

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_PDF &&resultCode == RESULT_OK){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                //this if satatement runs if we only selected multiple file
                if (data.getClipData()!=null){

                    // Toast.makeText(Upload_pdf.this,"multiple file selector",Toast.LENGTH_SHORT).show();

                    final int totalitemselected=data.getClipData().getItemCount();

                    for (int i=0; i < totalitemselected;i++){

                        Uri pdfuri=data.getClipData().getItemAt(i).getUri();
                        String mfilename = getFileName(pdfuri);
                        mpdffilenamelist.add(mfilename);
                        madpterforuploadlist.notifyDataSetChanged();


                        pdftoupload.child(mfilename).putFile(pdfuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                          @Override
                           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                               Toast.makeText(Uploadpdf.this,"done",Toast.LENGTH_SHORT).show();
                          }
                       });

                    }

                }
                else if (data.getData()!=null){
                   // Toast.makeText(Upload_pdf.this,"single file selector",Toast.LENGTH_SHORT).show();

                }
            }


        }
    }

    // this methode is for geting  filenamefrom any uri in this project we use it to get the filename of the pdf
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }


}
