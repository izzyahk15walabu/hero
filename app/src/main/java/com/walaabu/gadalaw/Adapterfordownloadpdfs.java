package com.walaabu.gadalaw;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapterfordownloadpdfs extends RecyclerView.Adapter<Adapterfordownloadpdfs.Viewholder> {
   List<PDFfile>mpdffilelist;

    public Adapterfordownloadpdfs(List<PDFfile>pdFfileList) {
        mpdffilelist=pdFfileList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.iteeeem,parent,false);
        return new Adapterfordownloadpdfs.Viewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        PDFfile mpdffile=mpdffilelist.get(position);
        String mpdffilename=mpdffile.getName();
        final String uri=mpdffile.getUri();

        holder.mtvpdfname.setText(mpdffilename);
holder.mdownloadbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Context context= v.getContext();
        Intent intent=new Intent();
        intent.setType(Intent.ACTION_VIEW);
         intent.setData(Uri.parse(uri));
        context.startActivity(intent);
    }
});
    }



    @Override
    public int getItemCount() {
        return mpdffilelist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView mtvpdfname;
        ImageView mdownloadbutton;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            mtvpdfname=(TextView)itemView.findViewById(R.id.tvid_bookmame);
            mdownloadbutton=itemView.findViewById(R.id.ibid_downloadpdf);
        }

    }
}