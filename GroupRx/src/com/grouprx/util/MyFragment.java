package com.grouprx.util;

import android.support.v4.app.Fragment;

import com.grouprx.ui.MainActivity;

public abstract class MyFragment extends Fragment {
	public boolean isMainLevel = false;
	
	
	public abstract int getTitle();

	public void onResume() {
		MainActivity.getInstance().setMainTitle(getTitle());
		super.onResume();
	}
	
	@Override
	public void onStop(){
		MyFragment fr = (MyFragment) getTargetFragment();
		if (fr != null) {
			MainActivity.getInstance().setMainTitle(fr.getTitle());
		}
		super.onStop();
		
	}
	
	public void OnPause(){
		
		super.onPause();
	}
	

}
