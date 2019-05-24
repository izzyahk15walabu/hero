package com.walaabu.gadalaw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    Bundle extras;
    Context mcontext;
    WebView webView;




    private static final String LOG_TAG = BookActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
               mcontext=BookActivity.this;

               webView=findViewById(R.id.webview);


              extras= getIntent().getExtras();

              if(!extras.equals(null) ){

                  String data=extras.getString("boqona");

                  String uri="file:///android_asset/"+data+".html";
                  webView.loadUrl(uri);

                  WebSettings websettings;
                  websettings=webView.getSettings();
                  websettings.setBuiltInZoomControls(true);
                  websettings.setDisplayZoomControls(false);
                  websettings.setJavaScriptEnabled(true);




              }




    }
}
