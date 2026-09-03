package com.appservisi.aktalist.volley;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.appservisi.aktalist.data.Markalar;
import com.appservisi.aktalist.model.Markayagoremodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MarkayaGoreGetir {
ArrayList<Markayagoremodel>markayagoremodel=new ArrayList<>();
    JSONObject jsonObject;
    JSONObject sendObj;
    JSONArray array = null;
Context context;
    private String TAG = "MarkayaGoreGetir";
    IResult mResultCallback = null;
    VolleyService mVolleyService;
JSONObject donen;

    public MarkayaGoreGetir(Context context) {
        this.context = context;
    }


public ArrayList<Markayagoremodel> initvolley(){

    mResultCallback = new IResult() {
        @Override
        public void notifySuccess(String requestType, JSONObject response) {
            Log.d(TAG, "Volley requester " + requestType);
            Log.d(TAG, "markayagore" + response);

            //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
         try {
                JSONArray jsonObj = response.getJSONArray("kayit");
                JSONObject objem = null;
                int toplam=jsonObj.length();
                Log.d(TAG, "toplam" + toplam);
              //  Toast.makeText(context, Integer.toString(toplam), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < jsonObj.length(); i++) {
                    objem = jsonObj.getJSONObject(i);
                    String id= objem.getString("aktuelID");
                    String baslik= objem.getString("aktuelBaslik");
                    String resim= objem.getString("aktuelResim");


                    markayagoremodel.add(new Markayagoremodel(id,baslik,resim));
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }











        }


        @Override
        public void notifyError(String requestType, VolleyError error) {
            Log.d(TAG, "Volley requester " + requestType);
            Log.d(TAG, "markayagore" + "That didn't work!");

        }


    };

    return markayagoremodel;
}



















    public void volleypost(String markaid){
        mVolleyService = new VolleyService(mResultCallback,context);
        jsonObject = new JSONObject();
        try {
            jsonObject.put("markaid", markaid);


            sendObj=jsonObject;
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


      //  mVolleyService.postDataVolley("POSTCALL", "http://aktuel.appservisi.com/Aktuelci/markayagore", sendObj);
        mVolleyService.postDataVolley("POSTCALL", "http://31.223.111.119:8000/aktuel/Aktuelci/markayagore", sendObj);

    }



}
