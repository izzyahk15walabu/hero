package com.walaabu.gadalaw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MainActivity ";
    Button addpdfbtn;
    RecyclerView recyclerView;

    List<PDFfile>mpdFfileList;
    Adapterfordownloadpdfs madpterDownload;
//livedata and and viewmodel for firebase
    FirebaseViewmodel mviewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      //   mpdFfileList=new ArrayList<>();


        // Obtain a new or prior instance of FirebaseViewModel from the
        // ViewModelProviders utility class.
       // mviewModel = ViewModelProviders.of(this).get(FirebaseViewmodel.class);


      //  LiveData<List<PDFfile>> mLiveData = mviewModel.geeetPdfLiveData();
/**

        mpdFfileList= mLiveData.getValue();
        if (mpdFfileList!=null) {
            Log.d(LOG_TAG, "PUPULATED LIST BEFORE ONCHNGE IS CALLED..... ..... .... ... .. .. .. . . ");
        }else {
            Log.d(LOG_TAG, "  LIST is EMPETY BEFORE ONCHNGE IS CALLED..... ..... .... ... .. .. .. . . ");
        }
**/
        addpdfbtn=findViewById(R.id.btn_addbutton);
        recyclerView=findViewById(R.id.rcid_download);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

/**
        mLiveData.observe(this, new Observer<List<PDFfile>>() {
            @Override
            public void onChanged(List<PDFfile> pdFfileList) {

                if (pdFfileList!=null) {
                    Log.d(LOG_TAG, "istning to the changes ...... ..... .... ... .. .. .. . . ");
                    mpdFfileList = pdFfileList;

                    PDFfile a = mpdFfileList.get(0);
                    String b = a.getName();
                    Log.d(LOG_TAG, b + "--------------------------------------");
                    madpterDownload=new Adapterfordownloadpdfs(mpdFfileList);
                    recyclerView.setAdapter(madpterDownload);
                    madpterDownload.notifyDataSetChanged();

                }else {
                    Log.d(LOG_TAG, "the pdflist is empty  at Onchange ...... ..... .... ... .. .. .. . . ");
                }
            }
        });
**/


        final Intent i=new Intent(this,Uploadpdf.class);
        addpdfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);

            }
        });

 //DatabaseReference dtbrf= FirebaseDatabase.getInstance().getReference();
/**
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
**/



        BottomNavigationView bottomnavigation=findViewById(R.id.bottomnavigatin);
        bottomnavigation.setOnNavigationItemSelectedListener(navlistner);
        // this line forecessthr b ook fargment to be inflated frist with out clicking
       // getSupportFragmentManager().beginTransaction().replace(R.id.fargmentscontainer,new Fragment_books()).commit();



    }


   private BottomNavigationView.OnNavigationItemSelectedListener navlistner=
           new BottomNavigationView.OnNavigationItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                   Fragment selectedfragment=null;
                   switch(menuItem.getItemId()){

                       case R.id.navigation_book:
                       selectedfragment=new Fragment_books();
                       break;
                       case R.id.navigation_download:
                           selectedfragment=new Fragment_download();
                           break;
                       case R.id.navigation_saved:
                           selectedfragment=new Fragment_saved();
                           break;




                   }
                   getSupportFragmentManager().beginTransaction().replace(R.id.fargmentscontainer,selectedfragment).commit();


                   return true;
               }
           };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutMenu:
                Log.d(LOG_TAG, "Clicked on About!");
                // Code for About goes here
                return true;
            case R.id.addarticle:
                Log.d(LOG_TAG, "Clicked on Help!");
                Intent intent= new Intent(this,AddArticleactivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
