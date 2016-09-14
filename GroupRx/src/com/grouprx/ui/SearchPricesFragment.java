package com.grouprx.ui;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.util.MyFragment;

public class SearchPricesFragment extends MyFragment {

	@SuppressLint({ "InflateParams", "SetJavaScriptEnabled" })
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		isMainLevel = true;
		View view = inflater.inflate(R.layout.fragment_search_price, null);
		
		WebView myWebView = (WebView) view.findViewById(R.id.webview);
		
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);
		
		final ProgressBar pB1 = (ProgressBar) view.findViewById(R.id.pB1);
		myWebView.setWebViewClient(new WebViewClient() {

	        @Override
	        public void onPageStarted(WebView view, String url, Bitmap favicon) {
	        	pB1.setVisibility(View.VISIBLE);
	        	System.out.println("Progress Started");
	        	super.onPageStarted(view, url, favicon);
	            
	        }

	        @Override
	        public void onPageFinished(WebView myWebView, String url) {
	        	pB1.setVisibility(View.GONE);
	        	System.out.println("Progress Finished");
	        	super.onPageFinished(myWebView, url);
	        }
	 });
		
		
		
		
		myWebView.loadUrl("https://www.nationaldrugcard.com/price-search/index.html");
		
		return view;
	}

	@Override
	public int getTitle() {
		return R.string.Prices_And_Locations;
	}
	
//	private class MyWebViewClient extends WebViewClient {
//	    @Override
//	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//	        if (Uri.parse(url).getHost().equals("www.example.com")) {
//	            // This is my web site, so do not override; let my WebView load the page
//	            return false;
//	        }
//	        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
//	        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//	        startActivity(intent);
//	        return true;
//	    }
//	}

}
