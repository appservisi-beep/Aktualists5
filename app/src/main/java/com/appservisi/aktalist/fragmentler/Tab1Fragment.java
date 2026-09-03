package com.appservisi.aktalist.fragmentler;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.appservisi.aktalist.CustomListView;
import com.appservisi.aktalist.ads.Gecis;
import com.appservisi.aktalist.araclar.SharedPref;
import com.appservisi.aktalist.guncelle.UpdateApp;
import com.appservisi.aktalist.volley.IResult;
import com.appservisi.aktalist.R;
import com.appservisi.aktalist.volley.VolleyService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by suraj on 23/6/17.
 */

public class Tab1Fragment extends Fragment {
    ProgressDialog pDialog;
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
    public String[] markalar;
    String[] id;
    public String[] imagepath;
    public String[] infom;
    TextView tv1;
    Tab1Fragment tab1;
    String cihazkodu, cihaztoken;
    Gecis gecis;
    SharedPref sharedPref;

    int appversion;
    private static final int PERMISSION_REQUEST_CODE = 105;

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

        Toast.makeText(context, "ssss", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // this.ali();


        displayLoader();
        cihazkodu = Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        PackageInfo pInfo = null;
        try {
            pInfo = getActivity().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        appversion = pInfo.versionCode;

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "getInstanceId failed", task.getException());
                    return;
                }
                gecis = new Gecis(getContext());
                // Get new Instance ID token
                cihaztoken = task.getResult().getToken();

                // Log and toast
                // String msg = getString(R.string.msg_token_fmt, token);
                Log.d(TAG, "teken:  " + cihaztoken);
                Log.d(TAG, "cihazid:  " + cihazkodu);
                //Toast.makeText(getContext(), "cihazid :"+cihazkodu+cihaztoken, Toast.LENGTH_SHORT).show();
                // Toast.makeText(getContext(), cihaztoken, Toast.LENGTH_SHORT).show();

                initVolleyCallback();
                volleypost(cihazkodu, cihaztoken);

            }
        });


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void adaptercalistir() {

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        final CustomListView customListView = new CustomListView(getActivity(), markalar, name, imagepath, id, infom);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] as = customListView.getId();
                String idim = as[position];
                String gittibaslik = name[position];
                String markagonder = markalar[position];
                //  Toast.makeText(getContext(), ""+gittibaslik, Toast.LENGTH_SHORT).show();
                gecis.goster(idim, gittibaslik, markagonder);
              /*  Intent i = new Intent(getActivity(), Detay.class);
                i.putExtra("veri", idim);
                startActivity(i);*/


            }
        });


        lv1.setAdapter(customListView);
        pDialog.dismiss();
    }

    public void initVolleyCallback() {
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, "Volley JSON post" + response);


                int version = 0;
                try {
                     version=response.getInt("version");
                    Log.e("kontrol","burası"+version);
                    if (version >= appversion) {


                        AlertDialog.Builder alerdialog = new AlertDialog.Builder(getContext(),R.style.MyDialogTheme);
                        alerdialog.setMessage("Yeni Sürüm Geldi! Uygulamayı Güncelemek ister misin?").setPositiveButton("Evet", dialoglistener)
                                .setNegativeButton("Hayır ", dialoglistener).show();


                    }


                   JSONArray jsonObj = response.getJSONArray("kayit");

                    JSONObject objem = null;

                    name = new String[jsonObj.length()];
                    markalar = new String[jsonObj.length()];
                    imagepath = new String[jsonObj.length()];
                    id = new String[jsonObj.length()];
                    infom = new String[jsonObj.length()];
                    for (int i = 0; i < jsonObj.length(); i++) {
                        objem = jsonObj.getJSONObject(i);
                        // markam = jsonmarka.getJSONObject(i);

                        name[i] = objem.getString("aktuelBaslik");
                        markalar[i] = objem.getString("markaAdi");
                        imagepath[i] = objem.getString("aktuelResim");
                        //  imagepath[i]=objem.getString("aktuelResim");
                        id[i] = objem.getString("aktuelID");
                        infom[i] = zamanbul(objem.getString("ilktarih"), objem.getString("sontarih"));

                    }

                    adaptercalistir();


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


    DialogInterface.OnClickListener dialoglistener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:

                    if (checkPermission()) {
                        UpdateApp atualizaApp = new UpdateApp();
                        atualizaApp.setContext(getActivity());
                        atualizaApp.execute("http://31.223.111.119:8000/app.apk");
                    } else {
                        requestPermission();
                    }

                    break;
                case DialogInterface.BUTTON_NEGATIVE:


                    break;
            }
        }
    };

    /* updateapp ugyulamasını yaparken ekledik */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }


    public void volleypost(String cihazkodu, String cihaztoken) {
        mVolleyService = new VolleyService(mResultCallback, getActivity());


        jsonObject = new JSONObject();
        try {
            jsonObject.put("cihazkodu", cihazkodu);

            jsonObject.put("cihaztoken", cihaztoken);
            sendObj = jsonObject;
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


        //  mVolleyService.postDataVolley("POSTCALL", "http://aktuel.appservisi.com/Aktuelci/aktueller", sendObj);
        mVolleyService.postDataVolley("POSTCALL", "http://31.223.111.119/aktuel/Aktuelapp/aktueller", sendObj);

    }


    private void displayLoader() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("İnsörtler Yükleniyor... Lütfen Bekleyin...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

    }

    public String zamanbul(String ilktarih, String sontarih) {
        Date date = new Date();
        SimpleDateFormat tarihFormat = new SimpleDateFormat("dd.M.yyyy");
        String simdikitarih = tarihFormat.format(date);

        String zaman = "boş";
        if (ilktarih != "" || sontarih != "") {

            try {
                Date simdikizaman = tarihFormat.parse(simdikitarih);
                Date ilkzaman = tarihFormat.parse(ilktarih);
                Date sonzaman = tarihFormat.parse(sontarih);
                long longsimdikiZaman = simdikizaman.getTime();
                long longilkZaman = ilkzaman.getTime();
                long longsonZaman = sonzaman.getTime();

                if (longilkZaman > longsimdikiZaman) {

                    int gun = (int) ((longilkZaman - longsimdikiZaman) / (1000 * 60 * 60 * 24));
                    zaman = "Başlamaya " + gun + " gün kaldı!";

                } else if (longilkZaman == longsimdikiZaman) {

                    zaman = "Bugün Başladı";
                } else if (longsonZaman > longsimdikiZaman) {

                    int gun = (int) ((longsonZaman - longsimdikiZaman) / (1000 * 60 * 60 * 24));
                    zaman = gun + " gün kaldı!";
                } else {

                    zaman = "Süresi Doldu";
                }


            } catch (ParseException e) {
                e.printStackTrace();
            }


        }


        return zaman;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (locationAccepted && cameraAccepted) {

                    UpdateApp updateApp = new UpdateApp();
                    updateApp.setContext(getActivity());
                    updateApp.execute("http://31.223.111.119:8000/app.apk");
                }
            }
        }


    }
}
