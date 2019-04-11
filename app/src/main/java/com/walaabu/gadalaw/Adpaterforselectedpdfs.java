package com.walaabu.gadalaw;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adpaterforselectedpdfs extends RecyclerView.Adapter<Adpaterforselectedpdfs.Viewholder> {

    List<String >mpdfilenamelist;
    List<String>mpdfdonelist;

    public Adpaterforselectedpdfs(List<String>filename,List<String>filedone){


        mpdfdonelist=filedone;
        mpdfilenamelist=filename;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_selectedpdf,parent,false);
        return new Viewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        String mpdffilename=mpdfilenamelist.get(position);
        holder.mpdffilenameview.setText(mpdffilename);

    }

    @Override
    public int getItemCount() {
        return mpdfilenamelist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        View mview;
        TextView mpdffilenameview;
        ImageView mpdfdoneview;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
            mpdffilenameview=(TextView)mview.findViewById(R.id.txpdffilename);
            mpdfdoneview=(ImageView)mview.findViewById(R.id.upload_loading);
        }
    }
}
