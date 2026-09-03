package com.appservisi.aktalist;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import com.appservisi.aktalist.araclar.Paylas;
import com.appservisi.aktalist.volley.IResult;
import com.appservisi.aktalist.volley.VolleyService;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.appservisi.aktalist.Detay.WRITE_REQUEST_CODE;

public class AnaSinif extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    JSONObject jsonObject;
    JSONObject sendObj;
    JSONArray array = null;

    private String TAG = "Anasinif";
    private static final int PERMISSION_REQUEST_CODE = 102;
    IResult mResultCallback = null;
    VolleyService mVolleyService;
    ImageView img1;
    ListView lv1;
    public String[] name;


    String[] id;
    public String[] imagepath;
    Pager pager;
    AdView adv1;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analayout);


        MobileAds.initialize(this, getString(R.string.reklamid));

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner));

        adv1 = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adv1.loadAd(adRequest);



      /*  try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            int version = pInfo.versionCode;
            Toast.makeText(this, ""+version, Toast.LENGTH_SHORT).show();
            if(version>1.9){
                Toast.makeText(this, "büyük", Toast.LENGTH_SHORT).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        if (checkPermission()) {
            UpdateApp atualizaApp = new UpdateApp();
            atualizaApp.setContext(AnaSinif.this);
            atualizaApp.execute("http://31.223.111.119:8000/app.apk");
        } else {
            requestPermission();
        }*/


        pager = new Pager(getSupportFragmentManager(), 3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        //tab id
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        //add the tabs
        mTabLayout.addTab(mTabLayout.newTab().setText("İNSÖRTLER"));
        mTabLayout.addTab(mTabLayout.newTab().setText("MARKLALAR"));
        mTabLayout.addTab(mTabLayout.newTab().setText("FAVORİLER"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        Pager adapter = new Pager(getSupportFragmentManager(), mTabLayout.getTabCount());

        mViewPager.setAdapter(adapter);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setScrollPosition(position, 0, true);
                mTabLayout.setSelected(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Uri linkimiz = Uri.parse("http://appservisi.com/duyuru");
            Intent intentimiz = new Intent(Intent.ACTION_VIEW, linkimiz);
            startActivity(intentimiz);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            String value5 = "http://31.223.111.119/Mobil/Duyuru/";
            Intent i3 = new Intent(this, Webv.class);
            i3.putExtra("key", value5);
            startActivity(i3);
        } else if (id == R.id.nav_slideshow) {

            String value4 = "https://play.google.com/store/apps/developer?id=appservisi";
            Intent i4 = new Intent(this, Webv.class);
            i4.putExtra("key", value4);
            startActivity(i4);


        } else if (id == R.id.nav_share) {


            // Drawable drawable = this.getResources().getDrawable(R.drawable.appservisilogo);
            String[] permissions = {READ_EXTERNAL_STORAGE};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, WRITE_REQUEST_CODE);
            }


        } else if (id == R.id.nav_send) {
           /* Uri linkimiz = Uri.parse("http://31.223.111.119/Mobil/iletisim");
            Intent intentimiz = new Intent(Intent.ACTION_VIEW, linkimiz);
            startActivity(intentimiz);*/

            String value2 = "http://31.223.111.119/Mobil/iletisim";
            Intent i2 = new Intent(this, Webv.class);
            i2.putExtra("key", value2);
            startActivity(i2);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /* updateapp ugyulamasını yaparken ekledik */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == WRITE_REQUEST_CODE) {

            Paylas paylas=new Paylas(this);
            paylas.yap();


        }


    }
}
