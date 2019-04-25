package com.walaabu.gadalaw;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class FirebaseViewmodel extends ViewModel {
    private static final String LOG_TAG = "FirebaseViewmodel";

    private static DatabaseReference dtbrf= FirebaseDatabase.getInstance().getReference("PDF");

    FirebaseLiveData livedta=new FirebaseLiveData(dtbrf);

/**
    private final LiveData<PDFfile >PdfLiveData =
            Transformations.map(livedta, new Deserializer());
**/

   //- deserialize a DataSnapshot into a Pdf.calss type
    private class Deserializer implements Function<DataSnapshot,PDFfile> {
        @Override
        public PDFfile apply(DataSnapshot dataSnapshot) {
            return dataSnapshot.getValue(PDFfile.class);
        }
    }


/**
//this returns the  livedata tye of datasnapshot
//    @NonNull
    public LiveData<DataSnapshot>getDataSnapshotLiveData(){
        return livedta;
    }
    **/
// this returnes the actual pdf class type object
    @NonNull
    public LiveData<List<PDFfile>> geeetPdfLiveData() {
        Log.d(LOG_TAG, "Sendimg data to main activity from viewmodel ...... ..... .... ... .. .. .. . . ");
        return livedta;

    }

}
