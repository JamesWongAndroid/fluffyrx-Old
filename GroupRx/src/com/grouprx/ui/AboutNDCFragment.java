package com.grouprx.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.util.MyFragment;

public class AboutNDCFragment extends MyFragment {

	@SuppressLint({ "InflateParams", "SetJavaScriptEnabled" })
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		isMainLevel = true;
		View view = inflater.inflate(R.layout.fragment_about_ndc, null);
		
		View imageview_ndc = (View) view.findViewById(R.id.imageview_ndc);
		imageview_ndc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(),PlayVideoActivity.class);
				String fileName = "android.resource://"+  getActivity().getPackageName() + "/raw/ndc_savings_club";
				i.putExtra("url", fileName);
				startActivity(i);
			}
		});
		 
		return view;
	}

	@Override
	public int getTitle(){
		return R.string.About_Healthcare_Saving_Card;
	}
	

}
