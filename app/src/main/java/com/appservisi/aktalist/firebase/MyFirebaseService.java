package com.appservisi.aktalist.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.appservisi.aktalist.AnaSinif;
import com.appservisi.aktalist.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseService extends FirebaseMessagingService {
    private String TAG = "MyFirebaseService";
    Context context;
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "Refreshed: " + s);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e(TAG, "From: " + remoteMessage.getData().get("tur"));
        Log.e(TAG, "From: " + remoteMessage.getData().get("body"));
        String tur=remoteMessage.getData().get("tur");
        if(tur.equals("1")){
            bildirim(remoteMessage.getData().get("title"),remoteMessage.getData().get("body"));
        }else if(tur.equals("2")){

            bildirimyonlendir(remoteMessage.getData().get("title"),remoteMessage.getData().get("body"),remoteMessage.getData().get("url"));
        }


    }

    public void bildirim(String baslik, String mesaj){

        NotificationManager notificationManager=(NotificationManager)getSystemService(context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID="com.appservisi.aktalist";

        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);

        //Create the intent that’ll fire when the user taps the notification//

        // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.androidauthority.com/"));

        Intent intent=new Intent(MyFirebaseService.this,AnaSinif.class);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.drawable.ic_menu_camera);

        mBuilder.setContentTitle(baslik);
        mBuilder.setContentText(mesaj);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);
        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(001, mBuilder.build());

    }


    public void bildirimyonlendir(String baslik,String mesaj,String url){

        NotificationManager notificationManager=(NotificationManager)getSystemService(context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID="com.appservisi.aktalist";

        NotificationCompat.Builder cBuilder =new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);

        //Create the intent that’ll fire when the user taps the notification//

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Intent intent=new Intent(MyFirebaseMessage.this,FalListesi.class);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        cBuilder.setContentIntent(pendingIntent);

        cBuilder.setSmallIcon(R.drawable.ic_menu_share);

        cBuilder.setContentTitle(baslik);
        cBuilder.setContentText(mesaj);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        cBuilder.setSound(alarmSound);
        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(002, cBuilder.build());

    }









}
