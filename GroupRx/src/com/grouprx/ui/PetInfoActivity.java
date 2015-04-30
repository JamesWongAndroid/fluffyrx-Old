package com.grouprx.ui;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.grouprx.R;

public class PetInfoActivity extends ActionBarActivity {
	private ActionBar mActionBar;
	private TextView mTitleTextView;
	private ImageButton actionbar_back;
	private TextView textview_info_text; 
	
	
	@SuppressLint("InflateParams") 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pet_info);

		
		try {
			mActionBar = getActionBar();
			mActionBar.setDisplayShowHomeEnabled(false);
			mActionBar.setDisplayShowTitleEnabled(false);
			LayoutInflater mInflater = LayoutInflater.from(this);
			View mCustomView = mInflater.inflate(R.layout.custom_action_bar_back,
					null);
			mTitleTextView = (TextView) mCustomView
					.findViewById(R.id.activity_title_textView);
			actionbar_back = (ImageButton) mCustomView
					.findViewById(R.id.actionbar_back);
			actionbar_back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					finish();
					overridePendingTransition(R.anim.anim_obj_slide_in_left, R.anim.anim_obj_slide_out_right);
				}
			});
			mTitleTextView.setText(R.string.about_free_pet_drug_card);
			
			
			
			mActionBar.setCustomView(mCustomView);
			mActionBar.setDisplayShowCustomEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ExceptionException = "+e);
		}
		
		textview_info_text = (TextView) findViewById(R.id.textview_info_text);
		textview_info_text.setText(R.string.pet_card_info);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

}
