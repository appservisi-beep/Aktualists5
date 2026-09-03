package com.appservisi.aktalist;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.appservisi.aktalist.Crops.Crobum;
import com.appservisi.aktalist.adapterler.ImageAdapter;
import com.appservisi.aktalist.ads.Odullu;
import com.appservisi.aktalist.araclar.CropActivity;
import com.appservisi.aktalist.araclar.Screenshot;
import com.appservisi.aktalist.araclar.SharedPref;
import com.appservisi.aktalist.araclar.SomeView;
import com.appservisi.aktalist.database.DataBase;
import com.appservisi.aktalist.model.ModelKayit;
import com.appservisi.aktalist.volley.CallbackViewpager;
import com.appservisi.aktalist.volley.IResult;
import com.appservisi.aktalist.volley.VolleyService;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Detay extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    List<ResimList> resimlistem = new ArrayList<>();
    JSONObject jsonObject;
    JSONObject sendObj;
    CallbackViewpager callbackViewpager;
    JSONArray array = null;
    ImageView img1, img2, img3;
    private String TAG = "Anasinif";
    IResult mResultCallback = null;
    VolleyService mVolleyService;
    String gelenid, gelenbaslik, baslik, markagonder;
    ViewPager goster;
    String resimler[] = new String[5];
    int islem = 0, gelenresimsirasi = -1, errortespit;
    TextView tv1;
    Spinner spinner;
    Context context = this;
    int spinnerdurum = 0;
    String resimurl, resimid, resimsirasi = "0";


    AdView adv1;
    Odullu odullu;
    public static final int WRITE_REQUEST_CODE = 101;
    List<String> asayfalar;
    ArrayAdapter<String> arrayAdapter;
    Screenshot screenshot;
    LinearLayout linearLayout, ls;
    SharedPref sharedPref;
    int gezilensayfasayisi;
    DataBase dataBase;
    String favoriler[];


    ArrayList<ModelKayit> modelKayits;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detaylayout);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        tv1 = findViewById(R.id.tv1);
        spinner = findViewById(R.id.spinner);
        linearLayout = findViewById(R.id.ly);
        ls = findViewById(R.id.ls);

        dataBase = new DataBase(this);


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }


        gelenid = getIntent().getExtras().getString("veri");
        baslik = getIntent().getExtras().getString("gelenbaslik");
        markagonder = getIntent().getExtras().getString("markagonder");

        String string = getIntent().getExtras().getString("resimsirasi");

        if (string != null) {

            gelenresimsirasi = Integer.parseInt(string);
        }
        //


        // Toast.makeText(context, ""+string, Toast.LENGTH_SHORT).show();

        try {
            modelKayits = dataBase.getRowTek(gelenid);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        MobileAds.initialize(this, getString(R.string.reklamid));

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner));

        adv1 = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adv1.loadAd(adRequest);


        odullu = new Odullu(this);


        sharedPref = new SharedPref();
        int gezinme = sharedPref.intgetir(this, "gezinme");
        if (gezinme == 99) {
            //Toast.makeText(getContext(), "fggf", Toast.LENGTH_SHORT).show();
            sharedPref.intkaydet(this, "gezinme", 1);
            gezilensayfasayisi = 1;
        } else {

            gezilensayfasayisi = sharedPref.intgetir(this, "gezinme");
        }


        Toast.makeText(Detay.this, "Resimi büyükmek için bir kere tıkla!", Toast.LENGTH_LONG).show();


        initVolleyCallback();
        volleypost();

        screenshot = new Screenshot(this);
        goster = findViewById(R.id.viewpager);

        callbackViewpager = new CallbackViewpager() {
            @Override
            public void viewpagerim(int toplam, int pozisyon, String resim) {


                if (spinnerdurum == 0) {
                    asayfalar = new ArrayList<>();
                    for (int i = 0; i < toplam; i++) {
                        asayfalar.add(Integer.toString(i + 1));
                    }
                    arrayAdapter = new ArrayAdapter<String>(Detay.this, android.R.layout.simple_spinner_dropdown_item, asayfalar);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(arrayAdapter);
                    tv1.setText("/ " + toplam);
                    spinnerdurum = 1;
                }


            }
        };


        spinner.setOnItemSelectedListener(Detay.this);
        img1.setOnClickListener(Detay.this);
        img2.setOnClickListener(Detay.this);
        img3.setOnClickListener(Detay.this);
        goster.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//spinner.setSelection(i);
                img2.setImageResource(R.drawable.heart);

                for (ModelKayit m : modelKayits) {
                    if (m.getResimsirasi() == i) {


                        img2.setImageResource(R.drawable.heartfovori);
                        img2.setEnabled(false);
                        break;
                    } else {
                        img2.setImageResource(R.drawable.heart);
                        img2.setEnabled(true);
                    }

                }

            }

            @Override
            public void onPageSelected(int i) {
                //   resimid= resimlistem.get(i).getResimid();


                errortespit = i;


                // img1.setOnClickListener(Detay.this);
                gezilensayfasayisi = sharedPref.intgetir(context, "gezinme");
                // Toast.makeText(Detay.this, ""+gezilensayfasayisi, Toast.LENGTH_SHORT).show();
                if (gezilensayfasayisi % 10 == 0) {
                    odullu.goster();
                    sharedPref.intkaydet(context, "gezinme", 1);
                } else {
                    sharedPref.intkaydet(context, "gezinme", gezilensayfasayisi + 1);
                }
                spinner.setSelection(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });






    }


    void viewcalistir() {

        ImageAdapter res = new ImageAdapter(resimlistem, Detay.this, callbackViewpager);
        goster.setAdapter(res);


    }


    public void initVolleyCallback() {
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, "Volley JSON post" + response);


                try {
                    //   array = new JSONArray(response);

                    JSONArray jsonObj = response.getJSONArray("kayit");
                    JSONObject objem = null;

                    for (int i = 0; i < jsonObj.length(); i++) {
                        objem = jsonObj.getJSONObject(i);

                        String resimkonum = objem.getString("resimKonum");
                        String resimid = objem.getString("resimID");


                        resimlistem.add(new ResimList(resimkonum, resimid));


                    }


                    viewcalistir();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, "Volley JSON post" + "That didn't work!");

            }


        };


    }//init volleycallback


    public void volleypost() {
        mVolleyService = new VolleyService(mResultCallback, this);


        jsonObject = new JSONObject();
        try {
            jsonObject.put("insortid", gelenid);

            sendObj = jsonObject;
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


        mVolleyService.postDataVolley("POSTCALL", "http://31.223.111.119/aktuel/Aktuelci/insortler", sendObj);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1:


               String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
              if (Build.VERSION.SDK_INT >= 33) {
                  screenshot.ekrancek(ls);
                } else {
                  requestPermissions(permissions, WRITE_REQUEST_CODE);



                }















                break;

            case R.id.img2:

                // Toast.makeText(Detay.this, ""+errortespit, Toast.LENGTH_SHORT).show();
                resimurl = resimlistem.get(errortespit).getResimkonum();
                resimsirasi = Integer.toString(errortespit);
                Toast.makeText(context, "İnsört Favorilere eklendi!", Toast.LENGTH_SHORT).show();
                img2.setImageResource(R.drawable.heartfovori);
                dataBase.insertEntry(baslik, markagonder, resimurl, gelenid, resimsirasi);
                break;

            case R.id.img3:
                resimurl = resimlistem.get(errortespit).getResimkonum();


                sharedPref.stringkaydet(Detay.this, "resimurl", resimurl);
                Intent crop = new Intent(Detay.this, Crobum.class);
                startActivity(crop);
                break;
        }
    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // goster.getCurrentItem(position);
        // Toast.makeText(context, ""+gelenresimsirasi, Toast.LENGTH_SHORT).show();
        if (gelenresimsirasi == -1) {
            goster.setCurrentItem(position);

        } else {

            goster.setCurrentItem(gelenresimsirasi);
            gelenresimsirasi = -1;
        }
        //goster.setCurrentItem(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case WRITE_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission granted.
                    //Continue with writing files...
                    screenshot.ekrancek(ls);
                    //Toast.makeText(this, "izin verildi.", Toast.LENGTH_SHORT).show();

                } else {
                    //Permission denied.

                    Toast.makeText(this, "izin verilmedi.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}