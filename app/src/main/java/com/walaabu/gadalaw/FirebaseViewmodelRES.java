package com.walaabu.gadalaw;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class FirebaseViewmodelRES extends ViewModel {
    private static final String LOG_TAG = "FirebaseViewmodel";



    private DatabaseReference dtbrf= FirebaseDatabase.getInstance().getReference("RES");

    FirebaseLiveData livedta=new FirebaseLiveData(dtbrf);



    //- deserialize a DataSnapshot into a Pdf.calss type
    private class Deserializer implements Function<DataSnapshot,PDFfile> {
        @Override
        public PDFfile apply(DataSnapshot dataSnapshot) {
            return dataSnapshot.getValue(PDFfile.class);
        }
    }



    // this returnes the actual pdf class type object
    @NonNull
    public LiveData<List<PDFfile>> geeetPdfLiveData() {
        Log.d(LOG_TAG, "Sendimg data to main activity from viewmodel ...... ..... .... ... .. .. .. . . ");
        return livedta;

    }



}
