package com.appservisi.aktalist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.webkit.WebSettings;
import android.webkit.WebView;



public class Webv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          String  value = extras.getString("key");
           // Toast.makeText(this, ""+value, Toast.LENGTH_SHORT).show();
            //The key argument here must match that used in the other activity

        WebView myWebView = (WebView) findViewById(R.id.webview);
       // setContentView(myWebView);
        // Create an unencoded HTML string, then convert the unencoded HTML string into
// bytes. Encode it with base64 and load the data.
        String unencodedHtml =
                "<html><body>'%23' is the percent code for ‘#‘ </body></html>";
        String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(),
                Base64.NO_PADDING);
       // myWebView.loadData(encodedHtml, "text/html", "base64");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(value);
        }

    }
}