package com.appservisi.aktalist.ads;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Gecisyedek {
  /*  CallTek callTek;
    Context context;
InterstitialAd mInterstitialAd;
    public Gecisyedek(Context context) {
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

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {

            }

            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                callTek.gelen("tmm");

            }
        });

    }

    public void goster(CallTek callTek){
        this.callTek=callTek;
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

    }*/
}
