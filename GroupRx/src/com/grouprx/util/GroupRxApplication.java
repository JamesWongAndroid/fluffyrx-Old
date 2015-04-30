package com.grouprx.util;

import android.app.Application;

public class GroupRxApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		SharedPreferencesHelper.init(this);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	@Override
	public void onLowMemory() {
		System.gc();
		super.onLowMemory();
	}

}
