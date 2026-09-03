package com.appservisi.aktalist.volley;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.appservisi.aktalist.data.Markalar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MarkaGetir {
        ArrayList<Markalar>markalar=new ArrayList<>();
        JSONObject jsonObject;
        Callbacktwo callbacktwo=null;
        JSONObject sendObj;
        JSONArray array = null;
        Context context;
        private String TAG = "Webservis";
        IResult mResultCallback = null;
        VolleyService mVolleyService;
        JSONObject donen;

    public MarkaGetir(Callbacktwo callbacktwo,Context context) {
        this.context = context;
        this.callbacktwo=callbacktwo;
    }


public void initvolley(){

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
             //   Toast.makeText(context, Integer.toString(toplam), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < jsonObj.length(); i++) {
                    objem = jsonObj.getJSONObject(i);
                    String id= objem.getString("markaID");
                    String markaAdi= objem.getString("markaAdi");
                    String resim= objem.getString("resim");

                    markalar.add(new Markalar(id,markaAdi,resim));
                }
                callbacktwo.gelentwo(markalar);

            } catch (JSONException e) {
                e.printStackTrace();
            }











        }


        @Override
        public void notifyError(String requestType, VolleyError error) {
            Log.d(TAG, "Volley requester " + requestType);
            Log.d(TAG, "Volley JSON tost" + "That didn't work!");

        }


    };


}



















    public void volleypost(){
        mVolleyService = new VolleyService(mResultCallback,context);
        jsonObject = new JSONObject();
        try {
            jsonObject.put("sorusirasi", "sorusirasi");

            jsonObject.put("testid", "testsecimi");
            sendObj=jsonObject;
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


      //  mVolleyService.postDataVolley("POSTCALL", "http://aktuel.appservisi.com/Aktuelci/markalar", sendObj);
        mVolleyService.postDataVolley("POSTCALL", "http://31.223.111.119/aktuel/Aktuelci/markalar", sendObj);

    }



}
