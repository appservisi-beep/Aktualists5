package com.appservisi.aktalist.fragmentler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appservisi.aktalist.R;
import com.appservisi.aktalist.adapterler.AdapterKayit;
import com.appservisi.aktalist.database.DataBase;
import com.appservisi.aktalist.model.ModelKayit;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by suraj on 23/6/17.
 */

public class Tab3Fragment extends Fragment {

RecyclerView recyclerView;
ArrayList<ModelKayit>modelKayits=new ArrayList<>();
DataBase dataBase;
LinearLayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab3 , container, false);

        recyclerView=view.findViewById(R.id.recycles);
dataBase=new DataBase(getContext());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        try {
            modelKayits=dataBase.getRows();
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3,LinearLayoutManager.HORIZONTAL,false);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);//performansı artırmak için zorunlu değil

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        AdapterKayit adapterKayit=new AdapterKayit(modelKayits,getContext());
        recyclerView.setAdapter(adapterKayit);
    }
}
