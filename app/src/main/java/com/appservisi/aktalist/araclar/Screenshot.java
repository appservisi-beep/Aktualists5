package com.appservisi.aktalist.araclar;

import static android.content.ContentValues.TAG;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {


    Context context;
    public Screenshot(Context context) {
        this.context = context;
    }











    public void ekrancek(View a) {
        try {
            // Ekran görüntüsü al
            Bitmap bitmap = Bitmap.createBitmap(a.getWidth(), a.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            a.draw(canvas);


            shareScreenshot(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Ekran "+e, Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap getScreenShot(View v) {
        //burası paylaşma kısmına dahil değil. aktif değil
       //View rootView =  context.getWindow().getDecorView().getRootView();
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }



    private void shareScreenshot(Bitmap bitmap) {



            Uri contentUri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                contentUri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
            } else {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            }

            ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
            ContentValues newImageDetails = new ContentValues();
            newImageDetails.put(MediaStore.Images.Media.DISPLAY_NAME, "filename.png");
            Uri imageContentUri = contentResolver.insert(contentUri, newImageDetails);

            try (ParcelFileDescriptor fileDescriptor =
                         contentResolver.openFileDescriptor(imageContentUri, "w", null)) {
                FileDescriptor fd = fileDescriptor.getFileDescriptor();
                OutputStream outputStream = new FileOutputStream(fd);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bufferedOutputStream);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "Error saving bitmap", e);
            }

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_STREAM, imageContentUri);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "some text here");
            sendIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            sendIntent.setType("image/*");
            Intent shareIntent = Intent.createChooser(sendIntent, "Share with");
            context.startActivity(shareIntent);

    }




}
