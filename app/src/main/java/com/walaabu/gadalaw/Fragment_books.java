package com.walaabu.gadalaw;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_books extends Fragment {
    RecyclerView recyclerView;
    Adapterforbook madpterbook;
    AppDatabase mdb;
    List<Article>articleList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_books, container, false);
        recyclerView=v.findViewById(R.id.rc_articles);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));




        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        articleList=new ArrayList<>();
        mdb=AppDatabase.getInstance(getActivity());
        articleList= mdb.ArticleDao().LoasdallArticle();


        madpterbook=new Adapterforbook(articleList);
        recyclerView.setAdapter(madpterbook);
        madpterbook.notifyDataSetChanged();

    }
}


