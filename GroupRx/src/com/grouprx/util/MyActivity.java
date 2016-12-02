package com.grouprx.util;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.ui.HowToUseAppFragment;

public abstract class MyActivity extends ActionBarActivity {
	
	
	public abstract void setMainTitle(int tit);
	
	public void openFragment(MyFragment fr){
		
		closeFragmentHelp(HowToUseAppFragment.getInstance());
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		if (! fr.isMainLevel) {
			transaction.setCustomAnimations(R.anim.anim_obj_slide_in_right,
					R.anim.anim_obj_slide_out_left,
					R.anim.anim_obj_slide_in_left,
					R.anim.anim_obj_slide_out_right);
			transaction.addToBackStack(null);
		} 
		transaction.replace(R.id.main_container, fr);
		setMainTitle(fr.getTitle());
		transaction.commitAllowingStateLoss();
		System.out.println("myFragment.isMainLevel = "+fr.isMainLevel+" FR Name : "+fr.getClass().getSimpleName());
	}
	
	public void openFragmentHelp(MyFragment fr,MyFragment parent){
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out,R.anim.fade_in,R.anim.fade_out);
		if (!fr.isAdded()) {
			transaction.add(R.id.main_container, fr);
			if (! fr.isMainLevel) {
				transaction.addToBackStack(null);
			}
			fr.setTargetFragment(parent, 1);
			setMainTitle(fr.getTitle());
			transaction.commitAllowingStateLoss();
			System.out.println("myFragment.isMainLevel = "+fr.isMainLevel+" FR Help Name : "+fr.getClass().getSimpleName());
		}
	}
	
	public void closeFragmentHelp(MyFragment fr) {
		if (fr != null && fr.isAdded()) {
		android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
				.beginTransaction();
		
		ft.setCustomAnimations(R.anim.fade_in,
				R.anim.fade_out);
//		ft.detach(fr);
		ft.remove(fr);
//			ft.hide(fr);
		ft.commitAllowingStateLoss();
		}
	}
	
	public Fragment getVisibleFragment() {
		List<Fragment> fragments = getSupportFragmentManager()
				.getFragments();
		Fragment fr = null;
		if (fragments != null) {
			for (Fragment fragment : fragments) {
				if (fragment != null && fragment.isVisible())
					fr = fragment;
			}
		}
		return fr;
	}

	public abstract void closeMenu();
	
	public abstract boolean isMenuVisible();
	
	@Override
	public void onBackPressed() {
		if (isMenuVisible()) {
			closeMenu();
		} else {
			MyFragment myFragment = (MyFragment) getVisibleFragment();
			if (myFragment != null) {
				System.out.println("myFragment.isMainLevel = "+myFragment.isMainLevel+" Name : "+myFragment.getClass().getSimpleName());
				if (myFragment.isMainLevel) {
					finish();
				} else {
					super.onBackPressed();
				}
			} else {
				super.onBackPressed();
			}
		}
	}
	
	public int getGridWidht() {
		int min_right_margen = 70;
		float max_grid_margen = getResources().getDimension(R.dimen.max_menu_widht);
		DisplayMetrics metrics = this.getResources().getDisplayMetrics();
		int screen_width = metrics.widthPixels;
		
//		System.out.println("DDDDD Scale = "+metrics.xdpi);
		
		int result = 0;
		if (((screen_width - max_grid_margen) >= min_right_margen)) {
			result = (int) max_grid_margen;
		} else {
			result = (screen_width - min_right_margen);
		}
		return result;
	}
	
	
}
