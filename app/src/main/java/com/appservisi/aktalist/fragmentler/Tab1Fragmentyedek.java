package com.appservisi.aktalist.fragmentler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.appservisi.aktalist.CustomListView;
import com.appservisi.aktalist.Detay;
import com.appservisi.aktalist.R;
import com.appservisi.aktalist.volley.IResult;
import com.appservisi.aktalist.volley.VolleyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by suraj on 23/6/17.
 */

public class Tab1Fragmentyedek  {/*
    extends Fragment
    JSONObject jsonObject;
    JSONObject sendObj;
    JSONArray array = null;
    Context context;
    private String TAG = "Tab1fragment";
    IResult mResultCallback = null;
    public VolleyService mVolleyService;
    ImageView img1;
    ListView lv1;
    public String[] name;
    String[] id;
    public String[] imagepath;
    TextView tv1;
    Tab1Fragmentyedek tab1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        lv1 = view.findViewById(R.id.lv1);
        tv1 = view.findViewById(R.id.tv1);




      // tab1.initVolleyCallback();
      //  tab1.volleypost();



        return view;
    }
    public void ali() {

        tv1.setText("fsdfsdf");

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // this.ali();

        this.initVolleyCallback();
        this.volleypost();
    }

    public void initVolleyCallback() {
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, "Volley JSON post" + response);

            //  Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();

                try {
                    //   array = new JSONArray(response);

                    JSONArray jsonObj = response.getJSONArray("kayit");
                    JSONObject objem = null;
                    // name=new String[jsonObj.length()];
                    //  imagepath=new String[jsonObj.length()];
                    name = new String[jsonObj.length()];
                    imagepath = new String[jsonObj.length()];
                    id = new String[jsonObj.length()];
                    for (int i = 0; i < jsonObj.length(); i++) {
                        objem = jsonObj.getJSONObject(i);

                        name[i] = objem.getString("aktuelBaslik");
                        imagepath[i] = objem.getString("aktuelResim");
                        //  imagepath[i]=objem.getString("aktuelResim");
                        id[i] = objem.getString("aktuelID");

                    }


                    StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
                    final CustomListView customListView = new CustomListView( getActivity(),markaAdi, name, imagepath, id);
                    lv1.setAdapter(customListView);


                    lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String[] as = customListView.getId();
                            String idim = as[position];

                            Intent i = new Intent(getActivity(), Detay.class);
                            i.putExtra("veri", idim);
                            startActivity(i);


                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, "Volley JSON post" + error.getMessage());

            }


        };


    }//init volleycallback


    public void volleypost() {
        mVolleyService = new VolleyService(mResultCallback, getActivity());


        jsonObject = new JSONObject();
        try {
            jsonObject.put("sorusirasi", "sorusirasi");

            jsonObject.put("testid", "testsecimi");
            sendObj = jsonObject;
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


        mVolleyService.postDataVolley("POSTCALL", "http://aktuel.appservisi.com/Aktuelci/aktueller", sendObj);

    }



*/
}
