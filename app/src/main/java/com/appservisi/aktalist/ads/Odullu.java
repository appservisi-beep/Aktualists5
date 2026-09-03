package com.appservisi.aktalist.ads;

import android.content.Context;

import android.widget.Button;

import android.widget.Toast;


import com.appservisi.aktalist.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class Odullu implements RewardedVideoAdListener {


   Context context;

    RewardedVideoAd mRewardedVideoAd;


    public Odullu(Context context) {
        this.context = context;

        MobileAds.initialize(context, context.getString(R.string.reklamid));
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

    }

    public void goster(){

        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }

    private void loadRewardedVideoAd() {

        mRewardedVideoAd.loadAd(context.getString(R.string.videoreklam),
                new AdRequest.Builder().build());
    }
    @Override
    public void onRewardedVideoAdLoaded() {

        //Toast.makeText(context, "Video hazır!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        //callTek.gelen("odul");

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(context, "Fal Hakkı için Videoyu Tamamen izlemelisiniz!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
       // Toast.makeText(context, ""+i, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {

       // callTek.gelen("fsd");

        Toast.makeText(context, "Teşekkürler!", Toast.LENGTH_SHORT).show();

    }

}
