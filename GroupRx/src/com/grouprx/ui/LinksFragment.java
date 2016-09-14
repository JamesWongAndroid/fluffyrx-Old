package com.grouprx.ui;


import java.io.File;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.adapters.LinksListAdapter;
import com.grouprx.objects.objLink;
import com.grouprx.sync.AppSettings;
import com.grouprx.sync.URLDownloadFile;
import com.grouprx.util.MyFragment;

public class LinksFragment extends MyFragment {
	private LinksListAdapter adp;
	private ImageView image;
	private TextView textView_groupName;
	private TextView textView_tag_Line;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_links, null);
		isMainLevel = false;
		View viewheader = View.inflate(getActivity(),
				R.layout.list_header_links, null);

		textView_groupName = (TextView) viewheader
				.findViewById(R.id.textView_groupName);
		textView_tag_Line = (TextView) viewheader
				.findViewById(R.id.textView_tag_Line);

		image = (ImageView) viewheader.findViewById(R.id.image);
		
		File fil = URLDownloadFile.getInstance().getFilePath_group_banner();
		if (fil != null && fil.exists()) {
			try {
				Bitmap bmp = BitmapFactory.decodeFile(fil.getAbsolutePath());
				image.setImageBitmap(bmp);
			} catch (Exception e) {

			}
		}

//		try {
//			Bitmap bmp = BitmapFactory.decodeFile(URLDownloadFile.getInstance()
//					.getFilePath_group_banner().getAbsolutePath());
//			image.setImageBitmap(bmp);
//		} catch (Exception e) {
//
//		}

		ListView lv1 = (ListView) view.findViewById(R.id.listview_links);
		lv1.addHeaderView(viewheader, null, false);
		lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				objLink obj = adp.getItem(position - 1);
				LinkItemFragment fr = new LinkItemFragment();
				Bundle bundle = new Bundle();
				bundle.putString("LinkURL", obj.getUrl());
				fr.setArguments(bundle);
				MainActivity.getInstance().openFragment(fr);
			}
		});
		adp = new LinksListAdapter(getActivity());
		lv1.setAdapter(adp);
		MainActivity.getInstance().showBackButton();
		edit();
		return view;
	}

	private void edit() {
		AppSettings seting = AppSettings.getInstance();
		adp.setListValues(seting.get_links());
		textView_groupName.setText(seting.get_group_name());
		textView_tag_Line.setText(seting.get_tag_line());
	}

	@Override
	public int getTitle() {
		return R.string.links;
	}

	@Override
	public void onDetach() {
		MainActivity.getInstance().hideBackButton();
		super.onDetach();
	}

}
