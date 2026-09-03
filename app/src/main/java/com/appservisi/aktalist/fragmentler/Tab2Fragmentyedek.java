package com.appservisi.aktalist.fragmentler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.appservisi.aktalist.R;
import com.appservisi.aktalist.adapterler.MarkaAdapter;
import com.appservisi.aktalist.data.Markalar;
import com.appservisi.aktalist.volley.IResult;
import com.appservisi.aktalist.volley.VolleyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by suraj on 23/6/17.
 */

public class Tab2Fragmentyedek extends Fragment {
RecyclerView recyclerView;
ArrayList<Markalar> markalar=new ArrayList<>();
    IResult mResultCallback = null;
    VolleyService mVolleyService;
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
       /* LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);*/
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);//performansı artırmak için zorunlu değil
       customAdapter=new MarkaAdapter(markalar,getActivity());
       /* markalar.add(new Markalar("ios","2001"));
        markalar.add(new Markalar("android","2003"));
        markalar.add(new Markalar("linux","2005"));
        markalar.add(new Markalar("apple","2007"));*/

      // Toast.makeText(getActivity(), dondu.toString(), Toast.LENGTH_SHORT).show();


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, "Volley JSON tost" + response);

                //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonObj = response.getJSONArray("kayit");
                    JSONObject objem = null;
                    int toplam=jsonObj.length();
                    Log.d(TAG, "toplam" + toplam);
                    Toast.makeText(getActivity(), Integer.toString(toplam), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < jsonObj.length(); i++) {
                        objem = jsonObj.getJSONObject(i);
                        String id= objem.getString("markaID");
                        String markaAdi= objem.getString("markaAdi");
                        String resim= objem.getString("resim");

                        markalar.add(new Markalar(id,markaAdi,resim));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



                recyclerView.setAdapter(customAdapter);







            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, "Volley JSON tost" + "That didn't work!");

            }


        };

        mVolleyService = new VolleyService(mResultCallback,getActivity());


        jsonObject = new JSONObject();
        try {
            jsonObject.put("sorusirasi", "sorusirasi");

            jsonObject.put("testid", "testsecimi");
            sendObj=jsonObject;
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


        mVolleyService.postDataVolley("POSTCALL", "http://aktuel.appservisi.com/Aktuelci/markalar", sendObj);



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
