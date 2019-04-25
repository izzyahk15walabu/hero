package com.walaabu.gadalaw;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class FirebaseLiveData extends LiveData<List<PDFfile>>{
    private static final String LOG_TAG = "FirebaseLiveData";
    List<PDFfile> mpdFfileList;


    private final Query query;
    private final MyValueEventListener listener = new MyValueEventListener();

    public FirebaseLiveData(Query query) {
        this.query = query;
    }

    public FirebaseLiveData(DatabaseReference ref) {
        this.query = ref;
    }

    @Override
    protected void onActive() {
        mpdFfileList=new ArrayList<>();
        Log.d(LOG_TAG, "onActive");
        query.addChildEventListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(LOG_TAG, "onInactive");
       query.removeEventListener(listener);
    }



    private class MyValueEventListener implements ChildEventListener{


        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            String pdfname =dataSnapshot.getKey();
            String Pdfurl=dataSnapshot.getValue(String.class);
            PDFfile p=new PDFfile(pdfname,Pdfurl);
            mpdFfileList.add(p);
            setValue(mpdFfileList);
            Log.d(LOG_TAG, "onCHILDadded copleted geting data  from firebase.....................");
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
        public void onCancelled(DatabaseError  databaseError) {
            Log.e(LOG_TAG, "Can't listen to query " + query, databaseError.toException());
        }

    }
}
