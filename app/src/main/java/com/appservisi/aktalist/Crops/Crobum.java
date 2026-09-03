package com.appservisi.aktalist.Crops;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.appservisi.aktalist.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



public class Crobum extends AppCompatActivity {
    String link="https://www.katiaandbony.com/sirt-dekolteli-spor-kadin-bikini-seti-siyah-plaj-koleksiyonu-4301-15-O.jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cropgithub);

        Uri resim=getImageUri(this,urltobitmap(link));

        File yol=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

  /*      UCrop.of(resim, Uri.fromFile(yol))
                .withAspectRatio(16, 9)
                .withMaxResultSize(100, 100)
                .start(Crobum.this);*/

    }


    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    
    public Bitmap urltobitmap(String resimurl){
     Bitmap bitmap1 = null;
        try {
            bitmap1 = BitmapFactory.decodeStream((InputStream)new URL(resimurl).getContent());
           
        } catch (IOException e) {
            e.printStackTrace();
        }  
        return bitmap1;
    }
     

    /*   Bitmap bitmap;
    PhotoEditorView mPhotoEditorView;
    PhotoEditor mPhotoEditor;
    SharedPref sharedPref;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cropgithub);

        mPhotoEditorView=findViewById(R.id.photoEditorView);
        sharedPref=new SharedPref();

        String resimurl=sharedPref.stringgetir(this,"resimurl");

bitmap=getBitmapFromURL(resimurl);
    *//*    try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL("https://icdn.ensonhaber.com/resimler/galeri/48_180.jpg").get);
        } catch (IOException e) {
            e.printStackTrace();
        }*//*
        //mInstaCropper.setImageUri(Uri.parse("https://icdn.ensonhaber.com/resimler/galeri/48_180.jpg"));
        mPhotoEditorView.getSource().setImageBitmap(bitmap);



//Use custom font using latest support library
        Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.roboto_medium);

//loading font from assest
        Typeface mEmojiTypeFace = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");

        mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(true)
                .setDefaultTextTypeface(mTextRobotoTf)
                .setDefaultEmojiTypeface(mEmojiTypeFace)
                .build();


        mPhotoEditor.setBrushDrawingMode(true);
     //   mPhotoEditor.setBrushColor("#fff");

        mPhotoEditor.addEmoji(mEmojiTypeFace);

    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/




}
