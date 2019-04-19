package com.walaabu.gadalaw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button addpdfbtn;
    RecyclerView recyclerView;
    List<PDFfile>mpdFfileList;
    Adapterfordownloadpdfs madpterDownload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
mpdFfileList=new ArrayList<>();
        addpdfbtn=findViewById(R.id.btn_addbutton);
        final Intent i=new Intent(this,Uploadpdf.class);
        addpdfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);

            }
        });

 DatabaseReference dtbrf= FirebaseDatabase.getInstance().getReference();

        dtbrf.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String pdfname =dataSnapshot.getKey();
                String Pdfurl=dataSnapshot.getValue(String.class);
                PDFfile p=new PDFfile(pdfname,Pdfurl);
                mpdFfileList.add(p);
              madpterDownload.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

           madpterDownload=new Adapterfordownloadpdfs(mpdFfileList);
        recyclerView=findViewById(R.id.rcid_download);
recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
 recyclerView.setAdapter(madpterDownload);




    }
}
