package com.appservisi.aktalist.ads;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.appservisi.aktalist.Detay;
import com.appservisi.aktalist.R;
import com.appservisi.aktalist.araclar.SharedPref;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Gecis {

    Context context;
InterstitialAd mInterstitialAd;
String idim,gidenbaslik,markagonder;
SharedPref sharedPref;
int gezilensayfasayisi;
    public Gecis(final Context context) {
        this.context = context;


        MobileAds.initialize(context, context.getString(R.string.reklamid));
        //Geçiş Reklamı
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getString(R.string.gecis));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {


            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
              /*  mInterstitialAd.loadAd(new AdRequest.Builder().build());
                Intent i = new Intent(context, Detay.class);
                i.putExtra("veri", idim);
                context.startActivity(i);*/

               // Toast.makeText(context, "yüklenmedi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                insorteGit();
            }

            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                insorteGit();

            }
        });

    }

    public void goster(String idim,String gidenbaslik,String markagonder){
        this.idim=idim;
        this.gidenbaslik=gidenbaslik;
        this.markagonder=markagonder;


        sharedPref=new SharedPref();
        int gezinme= sharedPref.intgetir(context,"gezinme");
        if(gezinme==99){
            //Toast.makeText(getContext(), "fggf", Toast.LENGTH_SHORT).show();
            sharedPref.intkaydet(context,"gezinme",1);
            gezilensayfasayisi=1;
        }else{

            gezilensayfasayisi=sharedPref.intgetir(context,"gezinme");
        }
if(gezilensayfasayisi%5==0){

    if (mInterstitialAd.isLoaded()) {
        mInterstitialAd.show();
        sharedPref.intkaydet(context,"gezinme",1);
    } else {
        Log.d("TAG", "The interstitial wasn't loaded yet.");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        insorteGit();
    }



}else{
    sharedPref.intkaydet(context,"gezinme",gezilensayfasayisi+1);
    //Toast.makeText(context, "henuz on tane sayfa gezmedin"+gezilensayfasayisi, Toast.LENGTH_SHORT).show();

    Log.d("TAG", "The interstitial wasn't loaded yet.");
    mInterstitialAd.loadAd(new AdRequest.Builder().build());

    insorteGit();
}


    }

    public void insorteGit(){
        Intent i = new Intent(context, Detay.class);
        i.putExtra("veri", idim);
        i.putExtra("gelenbaslik", gidenbaslik);
        i.putExtra("markagonder", markagonder);
        context.startActivity(i);

    }


}
