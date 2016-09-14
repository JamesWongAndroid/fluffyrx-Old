package com.grouprx.ui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.util.MyFragment;

public class HowToUseDigitalRxCardFragment extends MyFragment {
	public static HowToUseDigitalRxCardFragment instance;
	
	
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_digital_rx_card_use, null);
		isMainLevel = false;
		instance = this;
		view.setOnTouchListener(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
//				getActivity().getSupportFragmentManager().popBackStack();
				new DigitalRxCardOptionsTask().execute();
//				MainActivity.getInstance().closeFragmentHelp(HowToUseDigitalRxCardFragment.this);
				return false;
			}
		});
		return view;
	}

	public static HowToUseDigitalRxCardFragment getInstance(){
		return instance;
	}
	
	@Override
	public int getTitle() {
		return R.string.how_to_use_digital_rx_card;
	}
	
	private class DigitalRxCardOptionsTask extends AsyncTask<String, Void, Void> {
		
		@Override
		public void onPreExecute(){
			
		}

		@Override
		protected Void doInBackground(String... params) {
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			MainActivity.getInstance().openFragment(new UseGroupRxCardOptionsFragment());
	     }
	}
	
}
