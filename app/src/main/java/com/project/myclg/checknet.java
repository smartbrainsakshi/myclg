package com.project.myclg;



import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class checknet extends AppCompatActivity {
    WebView web;
  @Override
   public void onCreate(Bundle savedInstanceState)
   {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.ateende);
            String url="14.139.233.57/mmmut/studentlogin.aspx";

            web=(WebView)findViewById(R.id.web);
             web.loadUrl(url);



   }
}