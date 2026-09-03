package com.appservisi.aktalist.adapterler;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appservisi.aktalist.R;
import com.appservisi.aktalist.model.Markayagoremodel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MarkayagoreAdapter extends BaseAdapter {
    ArrayList<Markayagoremodel> markayagoremodel;
Context context;

    public MarkayagoreAdapter(ArrayList<Markayagoremodel> markayagoremodel, Context context) {
        this.markayagoremodel = markayagoremodel;
        this.context = context;
    }

    @Override
    public int getCount() {
        return markayagoremodel.size();
    }

    @Override
    public Object getItem(int position) {
        return markayagoremodel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
LayoutInflater layoutInflater;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 /*
        View satir=layoutInflater.inflate(R.layout.markayagorelistem,null);
        TextView tv1=satir.findViewById(R.id.baslik);
        TextView tv2=satir.findViewById(R.id.aktuelid);
        Markayagoremodel modelim= (Markayagoremodel) getItem(position);
        tv1.setText(modelim.getAktueladi());
        tv2.setText(modelim.getAktuelid());

        return satir;
*/
         if(convertView==null){

           convertView= layoutInflater.from(context).inflate(R.layout.markayagorelistem,parent,false);
        }


        Markayagoremodel modelim= (Markayagoremodel) getItem(position);

        TextView tv1=convertView.findViewById(R.id.baslik);
        TextView tv2=convertView.findViewById(R.id.aktuelid);
        ImageView img=convertView.findViewById(R.id.imageView);


tv1.setText(modelim.getAktueladi());
tv2.setText(modelim.getAktuelid());
        Glide.with(context)
                .load(modelim.getResim())
                .into(img);

        return convertView;


    }
}
