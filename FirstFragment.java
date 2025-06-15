package com.example.navigationdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
//Fragment open করলে অনেক লেখা আসে । এখানে শুরুতে onCreateView রেখে বাকী সব রিমুব করে দিলাম



public class FirstFragment extends Fragment {

    public static String WEB_url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  myView =inflater.inflate(R.layout.fragment_first, container, false);

        WebView webView=myView.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(WEB_url);
//WEB_url ধরলাম সেটা ও public static String এর মধ্যে রাখলাম যাতে MainActivity তে Access করা যায় ।
// চাইলো এখানে "" ব্যবহার করে মাঝে link দিলে ও হইতো ।

//best practice 


        return myView ;
    }
}
