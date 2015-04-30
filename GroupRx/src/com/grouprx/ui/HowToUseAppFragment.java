package com.grouprx.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

import com.grouprx.R;
import com.grouprx.util.MyFragment;

public class HowToUseAppFragment extends MyFragment {
	private static HowToUseAppFragment instance;
	
	public static HowToUseAppFragment getInstance(){
		if (instance == null) {
			instance = new HowToUseAppFragment();
		}
		return instance;
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_how_to_use, null);
		isMainLevel = false;
		view.requestFocus();
		view.setFocusableInTouchMode(true);
		view.setFocusable(true);
		view.setOnTouchListener(new OnTouchListener() {
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
//				getActivity().getSupportFragmentManager().popBackStack();
				MainActivity.getInstance().closeFragmentHelp(HowToUseAppFragment.this);
				return false;
			}
		});
		
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				return;
			}
		});
		return view;
	}

	@Override
	public int getTitle() {
		return R.string.how_to_use_app;
	}
	
	
}
