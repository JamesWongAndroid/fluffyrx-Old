package com.grouprx.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.grouprx.R;
import com.grouprx.sync.URLRequest;

public class LaunchActivity extends Activity {
	private static LaunchActivity instance;
	
//	private long enqueue;
//	private DownloadManager dm;
	
	public static LaunchActivity getInstance(){
		return instance;
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        instance = this;
        
        
        
        new loadingTask().execute();
        
        
        
        
        
        
//        BroadcastReceiver receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String action = intent.getAction();
//                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
//                    long downloadId = intent.getLongExtra(
//                            DownloadManager.EXTRA_DOWNLOAD_ID, 0);
//                    Query query = new Query();
//                    query.setFilterById(enqueue);
//                    Cursor c = dm.query(query);
//                    if (c.moveToFirst()) {
//                        int columnIndex = c
//                                .getColumnIndex(DownloadManager.COLUMN_STATUS);
//                        if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
// 
//                            ImageView view = (ImageView) findViewById(R.id.fullscreen_content);
//                            String uriString = c
//                                    .getString(c
//                                            .getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
//                            view.setImageURI(Uri.parse(uriString));
//                        }
//                    }
//                }
//            }
//        };
// 
//        registerReceiver(receiver, new IntentFilter(
//                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
    
    private class loadingTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPostExecute(String result) {
      		startActivity(new Intent(LaunchActivity.this, MainActivity.class));
        	finish();
        }

		@Override
		protected String doInBackground(Void... params) {
			try {
				URLRequest.getInstance().getData();
			} catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
      }

    
}
