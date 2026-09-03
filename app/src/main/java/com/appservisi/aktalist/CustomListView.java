package com.appservisi.aktalist;

import android.graphics.Bitmap;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by jaiso on 13-02-2018.
 */

public class CustomListView extends ArrayAdapter<String>{

    private String[] profilename;
    private String[] markaAdi;
    private String[] id;
    private String[] imagepath;
    private String[] infom;
    private AppCompatActivity context;
    Bitmap bitmap;

    public CustomListView(FragmentActivity context, String [] markaAdi, String[] profilename, String[] imagepath, String[] id, String[] infom) {
        super(context, R.layout.insort_listesi,markaAdi);
        this.context= (AppCompatActivity) context;
        this.profilename=profilename;
        this.markaAdi=markaAdi;
        this.id=id;
        this.imagepath=imagepath;
        this.infom=infom;
    }

    public String[] getId() {
        return id;
    }

    public String[] getProfilename() {
        return profilename;
    }
    public String[] getmarkaAdi() {
        return markaAdi;
    }







    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.insort_listesi,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)r.getTag();

        }

        viewHolder.tvw1.setText(profilename[position]);
       viewHolder.tvw2.setText(id[position]);
       viewHolder.markaAdi.setText(markaAdi[position]);
       viewHolder.bilgi.setText(infom[position]);


        Picasso.get().load(imagepath[position]).into(viewHolder.ivw);


        return r;
    }

    class ViewHolder{

        TextView tvw1;
        TextView tvw2;
        TextView markaAdi;
        TextView bilgi;
        ImageView ivw;

        ViewHolder(View v){
            tvw1=(TextView)v.findViewById(R.id.tvprofilename);
          tvw2=(TextView)v.findViewById(R.id.idim);
            ivw=(ImageView)v.findViewById(R.id.imageView);
            markaAdi=(TextView) v.findViewById(R.id.markaAdi);
            bilgi=(TextView) v.findViewById(R.id.bilgi);
        }

    }





}
