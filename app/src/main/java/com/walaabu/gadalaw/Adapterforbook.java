package com.walaabu.gadalaw;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapterforbook extends RecyclerView.Adapter<Adapterforbook.Viewholder>{
    private static final String LOG_TAG = Adapterforbook.class.getSimpleName();
    CustomItemClickListener clickListener;
    Context context;
    List<String> mchpterlist;

    public Adapterforbook(Context context,List<String>mchpterlist,CustomItemClickListener clickListener){
        this.context=context;
       this.mchpterlist=mchpterlist;
       this.clickListener=clickListener;

        Log.d(LOG_TAG, "setting the texts completed-----------------Adaper-----------------");
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_article,parent,false);

        final Viewholder viewholder= new Adapterforbook.Viewholder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(v,viewholder.getPosition());
            }
        });


        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {


        holder.marticleTitle.setText(mchpterlist.get(position).replace("_"," "));

        Log.d(LOG_TAG, "setting the texts completed-----------------Adaper-----------------");

    }

    @Override
    public int getItemCount() {
        return mchpterlist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
               TextView marticlenumber;
               TextView marticleTitle;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

           // marticlenumber=itemView.findViewById(R.id.tv_articlenumber);
            marticleTitle=itemView.findViewById(R.id.tv_articletitle);
        }
    }


}
