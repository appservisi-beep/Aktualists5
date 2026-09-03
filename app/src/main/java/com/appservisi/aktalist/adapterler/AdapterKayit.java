package com.appservisi.aktalist.adapterler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appservisi.aktalist.Detay;
import com.appservisi.aktalist.MarkayaGore;
import com.appservisi.aktalist.R;
import com.appservisi.aktalist.data.Markalar;
import com.appservisi.aktalist.model.ModelKayit;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterKayit extends RecyclerView.Adapter<AdapterKayit.ViewHolder> {
ArrayList<ModelKayit> modelKayits=new ArrayList<>();
LayoutInflater layoutInflater;
Context context;


    public AdapterKayit(ArrayList<ModelKayit> modelKayits, Context context) {
        this.modelKayits = modelKayits;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.row_kayit,viewGroup,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
          viewHolder.tv1.setText(modelKayits.get(i).getMarka());
          viewHolder.tv2.setText(modelKayits.get(i).getResimbaslik());
          viewHolder.tv3.setText(Integer.toString(modelKayits.get(i).getResimsirasi()+1)+". Sayfa");
         //viewHolder.img1.setImageResource(markalarArrayList.get(i).imgSrc);


        Glide.with(context)
                .load(modelKayits.get(i).getResim())
                .into(viewHolder.img1);
        viewHolder.linear1.setTag("lineartag");
        viewHolder.linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String id=modelKayits.get(i).getKayitid();
               String gelenid=modelKayits.get(i).getGelenid();
               String resimsirasi=String.valueOf(modelKayits.get(i).getResimsirasi());
                Log.e("kontrolum",resimsirasi+"gelen id: "+gelenid);

                Intent gecis=new Intent(context,Detay.class);
                gecis.putExtra("veri",gelenid);
                gecis.putExtra("resimsirasi",resimsirasi);
                context.startActivity(gecis);



              //  Toast.makeText(context, Marka, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelKayits.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
     TextView tv1,tv2,tv3;
     ImageView img1;
     LinearLayout linear1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
            tv3=itemView.findViewById(R.id.tv3);
           img1=itemView.findViewById(R.id.img1);
            linear1=itemView.findViewById(R.id.linear1);


        }
    }
}
