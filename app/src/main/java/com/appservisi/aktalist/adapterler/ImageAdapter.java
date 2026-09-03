package com.appservisi.aktalist.adapterler;

import android.app.Activity;
import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;


import com.ablanco.zoomy.Zoomy;
import com.appservisi.aktalist.R;
import com.appservisi.aktalist.ResimList;
import com.appservisi.aktalist.database.DataBase;
import com.appservisi.aktalist.model.ModelKayit;
import com.appservisi.aktalist.volley.CallbackViewpager;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.squareup.picasso.Picasso;




import java.util.ArrayList;
import java.util.List;


public class ImageAdapter extends PagerAdapter {
    // private int [] resim={R.drawable.ab,R.drawable.ac,R.drawable.ad,R.drawable.ae};
    List<ResimList> resimlistem = new ArrayList<>();
    Zoomy.Builder builder;
CallbackViewpager callbackViewpager;


    Context ctx;
    LayoutInflater layoutInflater;

    public ImageAdapter(List resimlistem,Context ctx,CallbackViewpager callbackViewpager) {
        this.resimlistem= resimlistem;
        this.ctx=ctx;
this.callbackViewpager=callbackViewpager;


    }


    @Override
    public int getCount() {
        //return resimler.length;

        return resimlistem.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==((LinearLayout)o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
     final ResimList resimList=resimlistem.get(position);
        layoutInflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.layoutadapter,container,false);
        ImageView imageview=(ImageView) item_view.findViewById(R.id.img1);
        LinearLayout linearLayout=item_view.findViewById(R.id.layout);

        imageview.setOnTouchListener(new ImageMatrixTouchHandler(item_view.getContext()));

//        imageview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                builder = new Zoomy.Builder((Activity) ctx).target(v);
//                builder.register();
//            }
//        });


callbackViewpager.viewpagerim(getCount(),position,resimList.getResimkonum());




/*
Button btn1=item_view.findViewById(R.id.btn1);

int toplam=getCount();
        Toast.makeText(ctx, toplam+"", Toast.LENGTH_SHORT).show();
btn1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // Toast.makeText(ctx, , Toast.LENGTH_SHORT).show();
        String adres=resimList.getResimkonum();
        Uri uri = Uri.parse(adres);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);

        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");

        ctx.startActivity(Intent.createChooser(sendIntent, "deneme"));

    }



});
*/
//resimList.getResimkonum(position);


     //  Picasso.get().load(resimler[position]).into(imageview);
      Picasso.get().load(resimList.getResimkonum()).into(imageview);
       //Toast.makeText(ctx,resimler[position], Toast.LENGTH_SHORT).show();

        container.addView(item_view);



        return item_view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }











}
