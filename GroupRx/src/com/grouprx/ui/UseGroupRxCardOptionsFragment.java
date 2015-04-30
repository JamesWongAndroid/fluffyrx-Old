package com.grouprx.ui;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.grouprx.R;
import com.grouprx.sync.URLDownloadFile;
import com.grouprx.util.MyFragment;

public class UseGroupRxCardOptionsFragment extends MyFragment {

	@Override
	@SuppressLint("InflateParams")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		isMainLevel = true;
		View view = inflater.inflate(R.layout.fragment_group_rx_card_options,
				null);

		ImageView image_1 = (ImageView) view.findViewById(R.id.image_1);
		
		try {
			image_1.setImageBitmap(BitmapFactory.decodeStream(getActivity().getAssets().open("ic_image_free_rx_card.png")));
		} catch (Exception e) {
			
		}

		File fil = URLDownloadFile.getInstance()
				.getFilePath_drug_card_option_image();
		if (fil != null && fil.exists()) {
			try {
				image_1.setImageBitmap(BitmapFactory.decodeFile(fil
						.getAbsolutePath()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		image_1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new freeGroupRxCardTask().execute();
			}
		});

		ImageView image_2 = (ImageView) view.findViewById(R.id.image_2);
		
		try {
			image_2.setImageBitmap(BitmapFactory.decodeStream(getActivity().getAssets().open("ic_image_free_pet_rx_card.png")));
		} catch (Exception e) {
			
		}
		
		File fil2 = URLDownloadFile.getInstance()
				.getFilePath_pet_drug_card_option_image();
		if (fil2 != null && fil2.exists()) {
			try {
				image_2.setImageBitmap(BitmapFactory.decodeFile(fil2
						.getAbsolutePath()));
			} catch (Exception e) {
				
			}
		}
		
		image_2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new freeGroupRxCardPetTask().execute();
			}
		});
		return view;
	}

	@Override
	public int getTitle() {
		return R.string.use_rx_card;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private class freeGroupRxCardTask extends AsyncTask<String, Void, Void> {

		@Override
		public void onPreExecute() {
		}

		@Override
		protected Void doInBackground(String... params) {

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Intent intent = new Intent(MainActivity.getInstance(),
					FreeGroupRxCardTempletActivity.class);
			intent.putExtra("isPet", false);
			getActivity().startActivity(intent);
		}

	}

	private class freeGroupRxCardPetTask extends AsyncTask<String, Void, Void> {

		@Override
		public void onPreExecute() {
		}

		@Override
		protected Void doInBackground(String... params) {

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Intent intent = new Intent(MainActivity.getInstance(),
					FreeGroupRxCardTempletActivity.class);
			intent.putExtra("isPet", true);
			getActivity().startActivity(intent);

		}

	}

}
