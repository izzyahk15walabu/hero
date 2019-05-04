package com.walaabu.gadalaw;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapterforbook extends RecyclerView.Adapter<Adapterforbook.Viewholder>{

    List<Article> marticlelist;

    public Adapterforbook(List<Article>articlelist){
        marticlelist=articlelist;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_article,parent,false);
        return new Adapterforbook.Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Article marticle=marticlelist.get(position);
        String mnumber=  Integer.toString(marticle.getNumber());
        String mtitle=marticle.getTitle();


        holder.marticleTitle.setText(mtitle);
        holder.marticlenumber.setText(mnumber);

    }

    @Override
    public int getItemCount() {
        return marticlelist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
               TextView marticlenumber;
               TextView marticleTitle;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            marticlenumber=itemView.findViewById(R.id.tv_articlenumber);
            marticleTitle=itemView.findViewById(R.id.tv_articletitle);
        }
    }


}
