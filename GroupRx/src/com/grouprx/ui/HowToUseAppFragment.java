package com.grouprx.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebView.FindListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.util.MyFragment;

public class HowToUseAppFragment extends MyFragment {
	private static HowToUseAppFragment instance;
	private SharedPreferences sharedPreferences;
	private boolean seenTutorial;
	private boolean isShowingFirst = true;
	private ImageView instructionImage;
	
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
		instructionImage = (ImageView) view.findViewById(R.id.image);
		isMainLevel = false;
		getActivity();
		sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
		seenTutorial = sharedPreferences.getBoolean("seenTutorial", false);
		view.requestFocus();
		view.setFocusableInTouchMode(true);
		view.setFocusable(true);
		view.setOnTouchListener(new OnTouchListener() {
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
//				getActivity().getSupportFragmentManager().popBackStack();
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (isShowingFirst) {
						instructionImage.setImageResource(R.drawable.fluffy_instructions);
						isShowingFirst = false;
					} else {
						if (!seenTutorial) {
							MainActivity.getInstance().closeFragmentHelp(HowToUseAppFragment.this);
							MainActivity.getInstance().showFreeCard();
							isShowingFirst = true;
						} else {
							MainActivity.getInstance().closeFragmentHelp(HowToUseAppFragment.this);
							isShowingFirst = true;
						}
					}
				}
				
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
