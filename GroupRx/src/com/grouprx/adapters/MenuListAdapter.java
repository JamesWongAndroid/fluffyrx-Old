package com.grouprx.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.grouprx.R;

public class MenuListAdapter extends BaseAdapter {

	private List<String> list_values;
	private LayoutInflater inflater;

	public MenuListAdapter(Activity activity) {
		inflater = (LayoutInflater) activity
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		list_values = new ArrayList<String>();
	}

	public void setListValues(List<String> val) {
		this.list_values = val;
		notifyDataSetChanged();
	}

	public void setListValues(String[] val) {
		this.list_values = new ArrayList<String>();
		for (String string : val) {
			this.list_values.add(string);
		}
		notifyDataSetChanged();
	}

	public void setListValues(ArrayList<String> val) {
		this.list_values = new ArrayList<String>(val);
		// for (String string : val) {
		// this.list_values.add(string);
		// }
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list_values.size();
	}

	@Override
	public String getItem(int position) {
		return list_values.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_row_menu, parent,
					false);
			holder = new ViewHolder();
			holder.txt_label = (TextView) convertView
					.findViewById(R.id.list_item_label);

			holder.imageView_menu = (ImageView) convertView
					.findViewById(R.id.imageView_menu);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String ss = getItem(position);
		holder.txt_label.setText(ss);

		int image_id = -1;
		switch (position) {
		case 0:
			image_id = R.drawable.ic_info;
			break;
		case 1:
			image_id = R.drawable.ic_mail;
			break;
		case 2:
			image_id = R.drawable.ic_question;
			break;
		case 3:
			image_id = R.drawable.ic_about_n;
			break;
		case 4:
			image_id = R.drawable.ic_about_h;
			break;
		default:
			break;
		}
		if (image_id > -1) {
			holder.imageView_menu.setImageResource(image_id);
		}
		return convertView;
	}

	private class ViewHolder {
		public TextView txt_label;
		public ImageView imageView_menu;
	}

}
