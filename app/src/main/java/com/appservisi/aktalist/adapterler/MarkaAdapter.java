package com.appservisi.aktalist.adapterler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appservisi.aktalist.AnaSinif;
import com.appservisi.aktalist.MarkayaGore;
import com.appservisi.aktalist.R;
import com.appservisi.aktalist.data.Markalar;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MarkaAdapter extends RecyclerView.Adapter<MarkaAdapter.ViewHolder> {
ArrayList<Markalar> markalarArrayList=new ArrayList<>();
LayoutInflater layoutInflater;
Context context;


    public MarkaAdapter(ArrayList<Markalar> markalarArrayList, Context context) {
        this.markalarArrayList = markalarArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.row_list,viewGroup,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
          viewHolder.tv1.setText(markalarArrayList.get(i).getMarkaID());
          viewHolder.tv2.setText(markalarArrayList.get(i).getMarkaAdi());
         //viewHolder.img1.setImageResource(markalarArrayList.get(i).imgSrc);


        Glide.with(context)
                .load(markalarArrayList.get(i).getResim())
                .into(viewHolder.img1);
        viewHolder.linear1.setTag("lineartag");
        viewHolder.linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String id=markalarArrayList.get(i).getMarkaID();
                Intent gecis=new Intent(context,MarkayaGore.class);
                gecis.putExtra("id",id);
                context.startActivity(gecis);


              //  Toast.makeText(context, Marka, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return markalarArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
     TextView tv1,tv2;
     ImageView img1;
     LinearLayout linear1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
           img1=itemView.findViewById(R.id.img1);
            linear1=itemView.findViewById(R.id.linear1);


        }
    }
}
