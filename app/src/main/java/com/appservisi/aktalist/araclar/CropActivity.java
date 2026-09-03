package com.appservisi.aktalist.araclar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.Toast;

import com.appservisi.aktalist.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CropActivity extends AppCompatActivity {

    ImageView compositeImageView;
    boolean crop;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cropview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            crop = extras.getBoolean("crop");
        }
        int widthOfscreen = 0;
        int heightOfScreen = 0;

        DisplayMetrics dm = new DisplayMetrics();
        try {
            getWindowManager().getDefaultDisplay().getMetrics(dm);
        } catch (Exception ex) {
        }
        widthOfscreen = dm.widthPixels;
        heightOfScreen = dm.heightPixels;


        sharedPref=new SharedPref();
        String resimurl=sharedPref.stringgetir(CropActivity.this,"resimurl");

        compositeImageView = (ImageView)findViewById(R.id.our_imageview);
        Bitmap bitmap2 = null;
        try {
            bitmap2 = BitmapFactory.decodeStream((InputStream)new URL(resimurl).getContent());
            bitmap2=getResizedBitmap(bitmap2,widthOfscreen,heightOfScreen);

        } catch (IOException e) {
            e.printStackTrace();
        }
        compositeImageView.setImageBitmap(bitmap2);


     /*   compositeImageView = (ImageView) findViewById(R.id.our_imageview);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.gallery_12);*/

        Bitmap resultingImage = Bitmap.createBitmap(widthOfscreen,
                heightOfScreen, bitmap2.getConfig());

        Canvas canvas = new Canvas(resultingImage);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Path path = new Path();
        for (int i = 0; i < SomeView.points.size(); i++) {
            path.lineTo(SomeView.points.get(i).x, SomeView.points.get(i).y);
        }

        canvas.drawPath(path, paint);
        if (crop) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        } else {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        }
        canvas.drawBitmap(bitmap2, 0, 0, paint);
        int gen=canvas.getWidth();
        Toast.makeText(this, ""+SomeView.points.size(), Toast.LENGTH_SHORT).show();
        compositeImageView.setImageBitmap(resultingImage);
    }


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}