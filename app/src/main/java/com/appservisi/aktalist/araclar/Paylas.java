package com.appservisi.aktalist.araclar;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.appservisi.aktalist.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Permission;

public class Paylas {
    Context context;
    public Paylas(Context context) {
        this.context=context;
    }

    public void yap(){

        Resources resources = context.getResources();
        BitmapDrawable bitmapDrawable = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            bitmapDrawable = (BitmapDrawable) resources.getDrawable(R.drawable.duzlogo, null);
        }
        Bitmap bitmap = bitmapDrawable.getBitmap();

        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Image Description", null);
        Uri uri = Uri.parse(path);

        // Metin eklemek için Intent oluştur
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "A101,Şok,Bim ve Daha bir sürü Market Aktüel Broşürleri uygulaması:https://play.google.com/store/apps/details?id=com.appservisi.aktalist"); // Metin eklemek için bu satırı ekleyin
        context.startActivity(Intent.createChooser(shareIntent, "Resmi Paylaş"));
    }
}
