package com.grouprx.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.util.MyFragment;

public class FaqsItemFragment extends MyFragment {

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_item_faqs, null);
		
		isMainLevel = false;
		
		Bundle bb = getArguments();
		int index = bb.getInt("faq_item");
		
		final String[] array_faqs = getResources().getStringArray(R.array.array_string_faqs_questions);
		final String[] array_faqs_items = getResources().getStringArray(R.array.array_string_faqs_questions_items);
		
		TextView textview_faqs_question = (TextView) view.findViewById(R.id.textview_faqs_question);
		TextView textview_faqs_items = (TextView) view.findViewById(R.id.textview_faqs_items);
		
		textview_faqs_question.setText(array_faqs[index]);
		textview_faqs_items.setText(array_faqs_items[index]);
		
		MainActivity.getInstance().showBackButton();
		
		return view;
	}
	
	@Override
	public int getTitle(){
		return R.string.faq;
	}

	@Override
	public void onDetach() {
		MainActivity.getInstance().hideBackButton();
		super.onDetach();
	}
	
}
