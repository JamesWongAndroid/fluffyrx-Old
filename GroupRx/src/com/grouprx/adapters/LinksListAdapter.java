package com.grouprx.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.objects.objLink;

public class LinksListAdapter extends BaseAdapter
{

	private List<objLink> list_values;
	private LayoutInflater inflater;
	
	public LinksListAdapter(Activity activity) {
		inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		list_values = new ArrayList<objLink>();
	}
	
	public void setListValues(List<objLink> val) {
		this.list_values = val;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount()
	{
		return list_values.size();
	}

	@Override
	public objLink getItem(int position)
	{
		return list_values.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_row_links, parent,
					false);
			holder = new ViewHolder();
			holder.txt_label = (TextView) convertView
					.findViewById(R.id.list_item_label);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		objLink ss = getItem(position);
		holder.txt_label.setText(ss.getTitle());
		
		
		return convertView;
	}
	
	private class ViewHolder {
		public TextView txt_label;
	}

}
