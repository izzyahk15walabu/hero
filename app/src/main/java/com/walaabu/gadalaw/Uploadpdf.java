package com.walaabu.gadalaw;


import androidx.annotation.NonNull;
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
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Uploadpdf extends AppCompatActivity {


    private static final int RESULT_LOAD_PDF = 1;

  private StorageReference mStorageRef;
  FirebaseDatabase mdatabase;
   StorageReference pdftoupload;

    Button btnSelectpdf;
    RecyclerView rc_uploadpdf;

    //List<String>mpdffilenamelist;
   // List<String>mpdfdonelist;

    List<PDFfile>mpdFfileList;

    ProgressBar progerassbar;


    Adpaterforselectedpdfs madpterforuploadlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpdf);
       // myapp= FirebaseApp.initializeApp(getApplicationContext());

mStorageRef=FirebaseStorage.getInstance().getReference();
mdatabase=FirebaseDatabase.getInstance();

        pdftoupload=mStorageRef.child("PDF");

        btnSelectpdf=findViewById(R.id.btn_uploadselected);


       // mpdffilenamelist=new ArrayList<>();
       // mpdfdonelist=new ArrayList<>();
        mpdFfileList=new ArrayList<>();

        madpterforuploadlist=new Adpaterforselectedpdfs(mpdFfileList);


        rc_uploadpdf=findViewById(R.id.rc_uploadpdf);
        rc_uploadpdf.setLayoutManager(new LinearLayoutManager(this));
        rc_uploadpdf.setHasFixedSize(true);
        rc_uploadpdf.setAdapter(madpterforuploadlist);













        btnSelectpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//the below lines allows us to choose the  file we wont from the file explorer in android
                // the below lines sends an intent to outside  to select pdfs
                Intent intent=new Intent();
                intent.setType("application/pdf");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select pdfs"),RESULT_LOAD_PDF);
            }
        });
    }


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

                        final Uri pdfuri=data.getClipData().getItemAt(i).getUri();
                       final String  mfilename = getFileName(pdfuri);
                        String fileUri="";
                        PDFfile mpdfile= new PDFfile(mfilename,fileUri);
                        mpdFfileList.add(mpdfile);
                        madpterforuploadlist.notifyDataSetChanged();


                        //this line uploads data to firebase storage
                        pdftoupload.child(mfilename).putFile(pdfuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                          @Override
                           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                               //Toast.makeText(Uploadpdf.this,"done",Toast.LENGTH_SHORT).show();
                              final String rlstring=mfilename.substring(0,mfilename.length()-4);



                               //the blew uoloads the filename and uri to realtimedatabase and sends toast when completed
                                  pdftoupload.child(mfilename).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                      @Override
                                      public void onSuccess(Uri uri) {
                                          String downloadurl=uri.toString();


                                          DatabaseReference databaseref= mdatabase.getReference();
                                          databaseref.child(rlstring ).
                                                  setValue(downloadurl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                              @Override
                                              public void onComplete(@NonNull Task<Void> task) {

                                                  if (task.isSuccessful()) {
                                                      Toast.makeText(Uploadpdf.this, "pdf uploaded ", Toast.LENGTH_SHORT).show();
                                                  }else{
                                                      Toast.makeText(Uploadpdf.this, "pdf notuploaded sucessfulyy ", Toast.LENGTH_SHORT).show();
                                                  }
                                              }
                                          });
                                      }
                                  });



                          }
                       }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //do something here if it fails to upload the file

                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {



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
