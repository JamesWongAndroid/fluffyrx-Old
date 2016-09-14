package com.grouprx.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.sync.AppSettings;
import com.grouprx.sync.URLRequest;

public class GroupIDActivity extends Activity {

	private Button button_activiate;
	private Button button_dont_have_id;
	private EditText editText_group_id;

	private static GroupIDActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		setContentView(R.layout.activity_group_id);
		
		AppSettings.getInstance().set_group_app_id("FluffyRx");

		if (!AppSettings.getInstance().get_group_app_id().isEmpty()) {
			openLoadingActivity();
			return;
		}

		editText_group_id = (EditText) findViewById(R.id.editText_group_id);

		button_activiate = (Button) findViewById(R.id.button_activiate);
		button_activiate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (editText_group_id.getText().toString().isEmpty()) {
					Toast.makeText(getApplicationContext(),
							R.string.PLS_SELECT_GROUPID, Toast.LENGTH_LONG)
							.show();
					return;
				}

				hideKeyboard();

				new checkURLTask().execute();
			}
		});
		
		button_dont_have_id = (Button) findViewById(R.id.button_dont_have_id);
		button_dont_have_id.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AppSettings.getInstance().set_group_app_id("grouprx");
				openLoadingActivity();
			}
		});

	}

	private void hideKeyboard() {
		View view = this.getCurrentFocus();
		if (view != null) {
			InputMethodManager inputManager = (InputMethodManager) this
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(view.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	private void openLoadingActivity() {
		startActivity(new Intent(GroupIDActivity.this, LaunchActivity.class));
		finish();
	}

	public static GroupIDActivity getInstance() {
		return instance;
	}
	
	
	class checkURLTask extends AsyncTask<Void, Integer, String> {
		boolean bb = false;
		
	    protected void onPreExecute (){
	        Log.d("PreExceute","On pre Exceute......");
	    }

	    protected String doInBackground(Void...arg0) {
			try {
				bb = URLRequest.getInstance().getDataTest(editText_group_id.getText().toString().trim());
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("qwerbb = " + bb);
	        return "You are at PostExecute";
	    }

	    protected void onProgressUpdate(Integer...a){
	    }

	    protected void onPostExecute(String result) {
	    	runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					if (bb) {
						AppSettings.getInstance().set_group_app_id(
								editText_group_id.getText().toString().trim());
						openLoadingActivity();
					} else {
						AlertDialog alertDialog = new AlertDialog.Builder(
								GroupIDActivity.this).create();
						alertDialog.setTitle("Group ID Not Found");
						alertDialog
								.setMessage("Please confirm that the Group ID"
										+ " provided by your organization is"
										+ " correct and that your internet"
										+ " connection is active.");
						alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								});
						alertDialog.show();
					}
					
				}
			});
	    	
	    	
	    }
	}

}
