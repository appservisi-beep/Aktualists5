package com.appservisi.aktalist.fragmentler;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.appservisi.aktalist.R;
import com.appservisi.aktalist.adapterler.MarkaAdapter;
import com.appservisi.aktalist.data.Markalar;
import com.appservisi.aktalist.volley.Callbacktwo;
import com.appservisi.aktalist.volley.IResult;
import com.appservisi.aktalist.volley.MarkaGetir;
import com.appservisi.aktalist.volley.VolleyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by suraj on 23/6/17.
 */

public class Tab2Fragment extends Fragment {
        RecyclerView recyclerView;
        ArrayList<Markalar> markalar;
        ProgressDialog pDialog;
        IResult mResultCallback = null;
        VolleyService mVolleyService;
        Callbacktwo callbacktwo=null;
        JSONObject jsonObject;
        JSONObject sendObj;
        JSONArray array = null;
        private String TAG = "Tab2Fragment";
        MarkaAdapter customAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab2, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
/*
       LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);*/
       GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);//performansı artırmak için zorunlu değil

       /* markalar.add(new Markalar("ios","2001"));
        markalar.add(new Markalar("android","2003"));
        markalar.add(new Markalar("linux","2005"));
        markalar.add(new Markalar("apple","2007"));*/

      // Toast.makeText(getActivity(), dondu.toString(), Toast.LENGTH_SHORT).show();

        callbacktwo=new Callbacktwo() {
            @Override
            public void gelentwo(ArrayList<Markalar> markalar) {
                customAdapter=new MarkaAdapter(markalar,getActivity());
                recyclerView.setAdapter(customAdapter);
            }
        };

 MarkaGetir markaGetir=new MarkaGetir(callbacktwo,getActivity());
markaGetir.initvolley();
 markaGetir.volleypost();






/*        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


    }
},3000);*/


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);//performansı artırmak için zorunlu değil
        MarkaAdapter customAdapter=new MarkaAdapter(markalar,getActivity());
        recyclerView.setAdapter(customAdapter);
*/



    }





}
