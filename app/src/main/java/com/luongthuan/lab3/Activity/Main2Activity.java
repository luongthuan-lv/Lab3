package com.luongthuan.lab3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.luongthuan.lab3.R;

public class Main2Activity extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webView=findViewById(R.id.webview);
        Intent intent=getIntent();
        webView.loadUrl(intent.getStringExtra("link"));
    }
}