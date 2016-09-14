package com.grouprx.ui;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.sync.AppSettings;
import com.grouprx.sync.URLDownloadFile;
import com.grouprx.util.MyFragment;

public class FreeRxCardHomeFragment extends MyFragment {
	private TextView textView_groupName;
	private TextView textView_tag_Line;
	private TextView textView_group_description;
	private ImageView image;
	private LinearLayout layout_custom_fields;
	private View buttonLinks;
	private View buttonDonate;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		
		textView_groupName = (TextView) view.findViewById(R.id.textView_groupName);
		textView_tag_Line = (TextView) view.findViewById(R.id.textView_tag_Line);
		textView_group_description = (TextView) view.findViewById(R.id.textView_group_description);
		
		layout_custom_fields = (LinearLayout) view.findViewById(R.id.layout_custom_fields);
		
		image = (ImageView) view.findViewById(R.id.image);
//		@SuppressWarnings("deprecation")
//		BitmapDrawable bitmapDrawable = new BitmapDrawable(BitmapFactory.decodeFile(URLDownloadFile.getInstance().getFilePath_group_banner().getAbsolutePath()));
//		image.setBackground(bitmapDrawable);
		
//		Bitmap bmp=BitmapFactory.decodeFile(BitmapFactory.decodeFile(URLDownloadFile.getInstance().getFilePath_group_banner().getAbsolutePath()));
		
		
		File fil = URLDownloadFile.getInstance().getFilePath_group_banner();
		if (fil != null && fil.exists()) {
			try {
				Bitmap bmp = BitmapFactory.decodeFile(fil.getAbsolutePath());
				image.setImageBitmap(bmp);
			} catch (Exception e) {

			}
		}
		
//		image.setImageBitmap(BitmapFactory.decodeFile(URLDownloadFile.getInstance().getFilePath_group_banner().getAbsolutePath()));
		
		buttonLinks = (View) view.findViewById(R.id.buttonLinks);
		buttonLinks.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LinksFragment fr = new LinksFragment();
				MainActivity.getInstance().openFragment(fr);
			}
		});
		
		buttonDonate = (View) view.findViewById(R.id.buttonDonate);
		if (! AppSettings.getInstance().get_is_donation_link()) {
			buttonDonate.setVisibility(View.GONE);
		}
		buttonDonate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(AppSettings.getInstance().get_donation_link()));
				startActivity(i);
			}
		});
		
		isMainLevel = true;
		
		edit();
		
		return view;
	}
	
	private void edit(){
		AppSettings seting = AppSettings.getInstance();
		textView_groupName.setText(seting.get_group_name());
		textView_tag_Line.setText(seting.get_tag_line());
		textView_group_description.setText(seting.get_group_description());
		
		JSONArray listFields = seting.get_CustomGroupFields();
		JSONArray listValues = seting.get_CustomGroupValues();
		for (int i = 0; i < listFields.length(); i++) {
			try {
				String filed = (String) listFields.get(i);
				String val = (String) listValues.get(i);
				if (filed.isEmpty() && val.isEmpty()) {
					continue;
				}
				TextView vv = new TextView(getActivity());
				vv.setText(filed+"  :  "+val);
				vv.setTextSize(16);
				layout_custom_fields.addView(vv);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	public int getTitle(){
		return R.string.Free_RX_Card;
	}

	
}
