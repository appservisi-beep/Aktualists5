package com.appservisi.aktalist;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.appservisi.aktalist.adapterler.MarkayagoreAdapter;
import com.appservisi.aktalist.model.Markayagoremodel;
import com.appservisi.aktalist.volley.MarkayaGoreGetir;

import java.util.ArrayList;

public class MarkayaGore extends AppCompatActivity {
    ListView lv1;
    Context context;
    ProgressDialog pDialog;
ArrayList<Markayagoremodel>markayagoremodel=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displayLoader();


        setContentView(R.layout.markayagorelistele);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

            String id = (String) bd.get("id");


        MarkayaGoreGetir markayaGoreGetir=new MarkayaGoreGetir(this);
        markayagoremodel=markayaGoreGetir.initvolley();
markayaGoreGetir.volleypost(id);
        lv1=findViewById(R.id.lv1);

     final    MarkayagoreAdapter markayagoreAdapter=new MarkayagoreAdapter(markayagoremodel,MarkayaGore.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            lv1.setAdapter(markayagoreAdapter);
           pDialog.dismiss();
            }
        },3000);


        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idim=markayagoremodel.get(position).getAktuelid();
                //String[] as = markayagoreAdapter.getItemId(position));
               // String idim = as[position];

                Intent i = new Intent(MarkayaGore.this, Detay.class);
                i.putExtra("veri", idim);
                startActivity(i);


            }
        });












    }


    private void displayLoader() {
        pDialog = new ProgressDialog(MarkayaGore.this);
        pDialog.setMessage("İnsörtler Yükleniyor... Lütfen Bekleyin...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
}
