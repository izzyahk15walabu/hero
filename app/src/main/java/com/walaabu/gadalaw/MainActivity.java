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

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MainActivity ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        BottomNavigationView bottomnavigation=findViewById(R.id.bottomnavigatin);
        bottomnavigation.setOnNavigationItemSelectedListener(navlistner);
        // this line forecessthr b ook fargment to be inflated frist with out clicking
       getSupportFragmentManager().beginTransaction().replace(R.id.fargmentscontainer,new Fragment_books()).commit();



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
                final Intent i=new Intent(this,Uploadpdf.class);
                startActivity(i);
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
