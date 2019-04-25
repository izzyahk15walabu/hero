package com.walaabu.gadalaw;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_download extends Fragment {
    private static final String LOG_TAG = "MainActivity ";
RecyclerView recyclerView;

 private   List<PDFfile>mpdFfileList;

  private   Adapterfordownloadpdfs madpterDownload;

FirebaseViewmodel mviewModelfordownloadfrag;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_download, container, false);

        recyclerView=v.findViewById(R.id.rc_fordownloadfrgment);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        return v;}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mpdFfileList=new ArrayList<>();
        mviewModelfordownloadfrag=  ViewModelProviders.of(getActivity()).get(FirebaseViewmodel.class);
        LiveData<List<PDFfile>> mLiveData = mviewModelfordownloadfrag.geeetPdfLiveData();
        mLiveData.observe(getViewLifecycleOwner(), new Observer<List<PDFfile>>() {
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
    }
}
