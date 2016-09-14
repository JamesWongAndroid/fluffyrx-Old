package com.grouprx.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.util.MyFragment;

public class FaqsFragment extends MyFragment {

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_faqs, null);
		
		isMainLevel = false;
		
ListView listView1 = (ListView) view.findViewById(R.id.list_faqs);
        
final String[] array_faqs = getResources().getStringArray(R.array.array_string_faqs_questions);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_row_faqs,array_faqs);
        
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				FaqsItemFragment fr = new FaqsItemFragment();
				Bundle bundle = new Bundle();
			    bundle.putInt("faq_item", position);
			    fr.setArguments(bundle);
			    MainActivity.getInstance().openFragment(fr);

			}
		});
		
		return view;
	}
	
	@Override
	public int getTitle(){
		return R.string.faq;
	}

	
}
