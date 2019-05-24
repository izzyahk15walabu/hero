package com.walaabu.gadalaw;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_books extends Fragment {
    RecyclerView recyclerView;
    Adapterforbook madpterbook;
    AppDatabase mdb;
    List<String>chapterList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_books, container, false);
        recyclerView=v.findViewById(R.id.rc_articles);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        recyclerView.addItemDecoration(HeaderDecoration.with(recyclerView)
               .inflate(R.layout.header)
              .parallax(0.2f)
               .dropShadowDp(4)
               .build());



        //madpterbook.notifyDataSetChanged();



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

        chapterList=new ArrayList<>();
        chapterList.add(Constants.boqona2);
        chapterList.add(Constants.boqona3);
        //mdb=AppDatabase.getInstance(getActivity());
        //articleList= mdb.ArticleDao().LoasdallArticle();
        madpterbook=new Adapterforbook(getContext(), chapterList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                Intent intent=new Intent(getContext(),BookActivity.class);
                intent.putExtra("boqona",chapterList.get(position));

                startActivity(intent);

                Toast.makeText(getContext(),"clicked"+chapterList.get(position),Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(madpterbook);
    }
}


