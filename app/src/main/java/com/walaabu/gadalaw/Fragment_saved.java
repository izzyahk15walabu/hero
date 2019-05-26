package com.walaabu.gadalaw;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_saved extends Fragment {

    private static final String LOG_TAG = "MainActivity ";
    private RecyclerView recyclerView1;

    private List<PDFfile> mpdFfileList;

    private    Adapterforsaved madpterDownload;

    FirebaseViewmodelRES mviewModelfordownloadfrag;
    LiveData<List<PDFfile>> mLiveData;

    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_saved, container, false);
        progressBar=v.findViewById(R.id.progresbar);
        recyclerView1=v.findViewById(R.id.testrec);
        recyclerView1.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView1.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);


        return v;



    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mpdFfileList=new ArrayList<>();
        mviewModelfordownloadfrag=  ViewModelProviders.of(getActivity()).get(FirebaseViewmodelRES.class);

        mLiveData = mviewModelfordownloadfrag.geeetPdfLiveData();
        mLiveData.observe(getViewLifecycleOwner(), new Observer<List<PDFfile>>() {
            @Override
            public void onChanged(List<PDFfile> pdFfileList) {

                if (pdFfileList!=null) {
                    progressBar.setVisibility(View.INVISIBLE);
                    recyclerView1.setVisibility(View.VISIBLE);
                    Log.d(LOG_TAG, "istning to the changes ...... ..... .... ... .. .. .. . . ");
                    mpdFfileList = pdFfileList;

                    PDFfile a = mpdFfileList.get(0);
                    String b = a.getName();
                    Log.d(LOG_TAG, b + "--------------------------------------");
                    madpterDownload=new Adapterforsaved(mpdFfileList);
                    recyclerView1.setAdapter(madpterDownload);
                    madpterDownload.notifyDataSetChanged();

                }else {
                    Log.d(LOG_TAG, "the pdflist is empty  at Onchange ...... ..... .... ... .. .. .. . . ");
                }

            }
        });
    }
}
