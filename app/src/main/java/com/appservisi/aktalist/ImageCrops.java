package com.appservisi.aktalist;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Display;


import com.appservisi.aktalist.araclar.SomeView;

public class ImageCrops extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.imagecrops);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = (int) size.x;
        int height = (int) size.y;

        SomeView someView=new SomeView(this,width,height);
        LinearLayoutCompat linearLayoutCompat=findViewById(R.id.ln);
        linearLayoutCompat.addView(someView);
    }
}
