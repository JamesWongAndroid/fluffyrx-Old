package com.grouprx.ui;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.sync.AppSettings;
import com.grouprx.sync.URLDownloadFile;

public class FreeGroupRxCardTempletActivity extends ActionBarActivity {
	private ActionBar mActionBar;
	private TextView mTitleTextView;
	private ImageButton actionbar_back;
	private ImageButton actionbar_info_sold;
	
	
	@SuppressLint("InflateParams") 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_free_group_rx_card_templet);
		try {
			mActionBar = getActionBar();
			mActionBar.setDisplayShowHomeEnabled(false);
			mActionBar.setDisplayShowTitleEnabled(false);
			LayoutInflater mInflater = LayoutInflater.from(this);
			View mCustomView = mInflater.inflate(R.layout.custom_action_bar_back_info,
					null);
			mTitleTextView = (TextView) mCustomView
					.findViewById(R.id.activity_title_textView);
			actionbar_back = (ImageButton) mCustomView
					.findViewById(R.id.actionbar_back);
			actionbar_back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					finish();
				}
			});
			actionbar_info_sold = (ImageButton) mCustomView
					.findViewById(R.id.actionbar_info_sold);
			actionbar_info_sold.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(FreeGroupRxCardTempletActivity.this, PetInfoActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.anim_obj_slide_in_right, R.anim.anim_obj_slide_out_left);
				}
			});
			
			mTitleTextView.setText(R.string.use_rx_card_free_10_85_1);
			
			mActionBar.setCustomView(mCustomView);
			mActionBar.setDisplayShowCustomEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ExceptionException = "+e);
		}
		
		TextView textview_groupID = (TextView) findViewById(R.id.textview_groupID);
		TextView textview_memberID = (TextView) findViewById(R.id.textview_memberID);
		TextView textview_bin = (TextView) findViewById(R.id.textview_bin);
		TextView textview_pcn = (TextView) findViewById(R.id.textview_pcn);
		TextView textview_help = (TextView) findViewById(R.id.textview_help);
		ImageView image_1 = (ImageView) findViewById(R.id.image_1);
		
		boolean isPet = getIntent().getExtras().getBoolean("isPet");
		System.out.println("isPet bool = "+isPet);
		JSONArray values = AppSettings.getInstance().get_NDCCardValues();
		File fil = URLDownloadFile.getInstance().getFilePath_drug_card_image();
		String help = AppSettings.getInstance().get_NDCCardPhone();
		actionbar_info_sold.setVisibility(View.INVISIBLE);
		int imageNotFoundID = R.drawable.fluffy_card_template;
		if (isPet) {
			fil = URLDownloadFile.getInstance().getFilePath_pet_drug_card_image();
			values = AppSettings.getInstance().get_PetCardValues();
			help = AppSettings.getInstance().get_PETCardPhone();
			actionbar_info_sold.setVisibility(View.VISIBLE);
			imageNotFoundID = R.drawable.fluffy_human_template;
		}
		try {
			textview_groupID.setText(""+values.get(0));
			textview_memberID.setText(""+values.get(1));
			textview_bin.setText(""+values.get(2));
			textview_pcn.setText(""+values.get(3));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		textview_help.setText(help);
		if (fil != null && fil.exists()) {
			image_1.setBackground(Drawable.createFromPath(fil.getAbsolutePath()));
		} else {
			image_1.setBackgroundResource(imageNotFoundID);
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

}
