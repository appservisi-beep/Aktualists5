package com.appservisi.aktalist.ads;

import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class Odulluyedek  {
 /* implements RewardedVideoAdListener

   Context context;
    CallTek callTek;
    CallCihazRegister callCihazRegister;
    Odul odul;
    String  cihazid;
    RewardedVideoAd mRewardedVideoAd;
    TextView tv11;
    Button btn1;
    public Odullu(Context context, Button btn1, TextView tv11) {
        this.context = context;
        this.btn1=btn1;
        this.tv11=tv11;
        MobileAds.initialize(context, context.getString(R.string.reklamid));
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

    }

    public void goster(CallTek callTek,String cihazid){
        this.callTek = callTek;
this.cihazid=cihazid;

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
        btn1.setVisibility(View.VISIBLE);
        Toast.makeText(context, "Video hazır!", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(context, ""+i, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {

       // callTek.gelen("fsd");

        callCihazRegister=new CallCihazRegister() {
            @Override
            public void gelen(String mesaj, String puan) {
                tv11.setText(puan);
                callTek.gelen(mesaj);
                //Toast.makeText(context, ""+mesaj+puan, Toast.LENGTH_SHORT).show();
            }
        };
        odul=new Odul(callCihazRegister,context);
        odul.initvolley();
        odul.volleypost(cihazid);

    }
    */
}
