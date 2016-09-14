package com.grouprx.ui;

import java.io.File;
import java.util.ArrayList;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.MenuDrawer.OnDrawerStateChangeListener;
import net.simonvt.menudrawer.Position;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nationaldrugcard.fluffygrouprx.R;
import com.grouprx.adapters.MenuListAdapter;
import com.grouprx.sync.AppSettings;
import com.grouprx.sync.URLDownloadFile;
import com.grouprx.util.MyActivity;
import com.grouprx.util.MyFragment;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

public class MainActivity extends MyActivity {

	private MenuDrawer mDrawer;

	private TextView mTitleTextView;
	private RadioButton actionbar_home;
	private RadioGroup radiobutton_group;
	private RadioGroup ragiobutton_group_home;
	private RadioButton button_Use_Free_Card;
	private RadioButton button_Search_Prices;
	private ImageButton actionbar_back;
	private ImageButton actionbar_menu;
	private ImageView imageMenu;
	private SharedPreferences sharedPreferences;
	private boolean seenTutorial = false;

	private static MainActivity instance;

	private ActionBar mActionBar;

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		
		sharedPreferences = getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
		seenTutorial = sharedPreferences.getBoolean("seenTutorial", false);
		
		
		try {
			mActionBar = getActionBar();
			mActionBar.setDisplayShowHomeEnabled(false);
			mActionBar.setDisplayShowTitleEnabled(false);
			LayoutInflater mInflater = LayoutInflater.from(this);
			View mCustomView = mInflater.inflate(R.layout.custom_action_bar,
					null);
			mTitleTextView = (TextView) mCustomView
					.findViewById(R.id.activity_title_textView);
			actionbar_menu = (ImageButton) mCustomView
					.findViewById(R.id.actionbar_menu);
			actionbar_menu.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					mDrawer.toggleMenu();
					
				}
			});

			ragiobutton_group_home = (RadioGroup) mCustomView
					.findViewById(R.id.ragiobutton_group_home);
			actionbar_home = (RadioButton) mCustomView
					.findViewById(R.id.actionbar_home);

			actionbar_back = (ImageButton) mCustomView
					.findViewById(R.id.actionbar_back);

			actionbar_back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					getSupportFragmentManager().popBackStack();
				}
			});

			actionbar_home
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {
								radiobutton_group.clearCheck();
								new freeRxCardTask().execute();
							}
						}
					});

			mActionBar.setCustomView(mCustomView);
			mActionBar.setDisplayShowCustomEnabled(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ExceptionException = " + e);
		}

		mDrawer = MenuDrawer.attach(MainActivity.this, MenuDrawer.Type.BEHIND,
				Position.LEFT, MenuDrawer.MENU_DRAG_WINDOW);
		mDrawer.setMenuSize(getGridWidht());
		mDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_NONE);

		mDrawer.setContentView(R.layout.activity_main);
		mDrawer.setMenuView(R.layout.slide_menu_tools);

		View view = View.inflate(this, R.layout.list_header_menu, null);

		imageMenu = (ImageView) view.findViewById(R.id.image_menu);

		ListView lv1 = (ListView) findViewById(R.id.menulistview);
		lv1.addHeaderView(view, null, false);

		MenuListAdapter mListAdap = new MenuListAdapter(this);
		mListAdap.setListValues(get_menu_item_left());
		lv1.setAdapter(mListAdap);
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				if (position == 1) {
					new howToUseAppTask().execute();
				} else if (position == 2) {
					action_share();
				} else if (position == 3) {
					new faqsTask().execute();
				} else if (position == 4) {
					if (ragiobutton_group_home != null) {
						ragiobutton_group_home.clearCheck();
					}
					closeMenu();
					actionbar_menu.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
					openFragment(new AboutNDCFragment());
				} else if (position == 5) {
					if (ragiobutton_group_home != null) {
						ragiobutton_group_home.clearCheck();
					}
					closeMenu();
					actionbar_menu.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
					openFragment(new AboutNDCRXFragment());
				}

			}
		});

		radiobutton_group = (RadioGroup) findViewById(R.id.radiobutton_group);
		button_Use_Free_Card = (RadioButton) findViewById(R.id.button_Use_Free_Card);
		button_Search_Prices = (RadioButton) findViewById(R.id.button_Search_Prices);
		
		mDrawer.setOnDrawerStateChangeListener(new OnDrawerStateChangeListener() {
			
			@Override
			public void onDrawerStateChange(int oldState, int newState) {
				if (oldState == 1) {
					actionbar_menu.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
				} else {
					actionbar_menu.setColorFilter(getResources().getColor(R.color.purple), PorterDuff.Mode.SRC_ATOP);
				}
				
			}
			
			@Override
			public void onDrawerSlide(float openRatio, int offsetPixels) {
				// TODO Auto-generated method stub
				
			}
		});

		button_Use_Free_Card
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							if (ragiobutton_group_home != null) {
								ragiobutton_group_home.clearCheck();
							}

							new howToUseDigitalRxCardTask().execute();
						}
					}
				});
		button_Search_Prices
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							if (ragiobutton_group_home != null) {
								ragiobutton_group_home.clearCheck();
							}
							new searchPriceTask().execute();
						}
					}
				});

		if (actionbar_home != null) {
			actionbar_home.setChecked(false);
		}
		hideBackButton();
		refresh();
		
		if (!seenTutorial) {
			new howToUseAppTask().execute();
		} else {
			showFreeCard();
		}
	}

	public void refresh() {

		File fil = URLDownloadFile.getInstance().getFilePath_sidebar_image();
		if (fil != null && fil.exists()) {
			try {
				imageMenu.setImageBitmap(BitmapFactory.decodeFile(fil
						.getAbsolutePath()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.onResume();
	}

	public void showBackButton() {
		if (actionbar_back != null) {
			actionbar_back.setVisibility(View.VISIBLE);
			actionbar_home.setVisibility(View.GONE);
			actionbar_menu.setVisibility(View.GONE);
		}
	}

	public void hideBackButton() {
		if (actionbar_back != null) {
			actionbar_back.setVisibility(View.GONE);
			actionbar_home.setVisibility(View.VISIBLE);
			actionbar_menu.setVisibility(View.VISIBLE);
		}
	}

	public static MainActivity getInstance() {
		return instance;
	}

	public void setMainTitle(int res) {
		if (mTitleTextView != null) {
			mTitleTextView.setText(res);
		}
	}

	private class howToUseDigitalRxCardTask extends
			AsyncTask<String, Void, Void> {

		@Override
		public void onPreExecute() {

		}

		@Override
		protected Void doInBackground(String... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// openFragment(new HowToUseDigitalRxCardFragment());
			openFragment(new UseGroupRxCardOptionsFragment());
		}

	}

	private class howToUseAppTask extends AsyncTask<String, Void, Void> {

		@Override
		public void onPreExecute() {
			mDrawer.closeMenu();
			actionbar_menu.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
		}

		@Override
		protected Void doInBackground(String... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			openFragmentHelp(HowToUseAppFragment.getInstance(),
					(MyFragment) getVisibleFragment());
		}

	}

	private class faqsTask extends AsyncTask<String, Void, Void> {

		@Override
		public void onPreExecute() {
			mDrawer.closeMenu();
			actionbar_menu.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
		}

		@Override
		protected Void doInBackground(String... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (ragiobutton_group_home != null) {
				ragiobutton_group_home.clearCheck();
			}
			openFragment(new FaqsFragment());
		}

	}

	private class searchPriceTask extends AsyncTask<String, Void, Void> {

		@Override
		public void onPreExecute() {
			mDrawer.closeMenu();
			actionbar_menu.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
		}

		@Override
		protected Void doInBackground(String... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			openFragment(new SearchPricesFragment());
		}

	}

	private class freeRxCardTask extends AsyncTask<String, Void, Void> {

		@Override
		public void onPreExecute() {
			mDrawer.closeMenu();
			actionbar_menu.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
		}

		@Override
		protected Void doInBackground(String... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			openFragment(new FreeRxCardHomeFragment());
		}
	}

	public void action_share() {
		mDrawer.post(new Runnable() {
			@Override
			public void run() {
				mDrawer.closeMenu();
				actionbar_menu.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
			}
		});
		setMainTitle(R.string.Share_Application);
		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
		sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		sharingIntent.setType("text/plain");
//		sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, ""+AppSettings.getInstance().get_group_app_id_OnlyID());
		String ss = getResources().getString(R.string.share_message);
		ss = ss.replace("(group ID goes here)", AppSettings.getInstance().get_group_app_id_OnlyID());
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,ss);
		startActivity(Intent.createChooser(sharingIntent, "Share via"));
	}

	@Override
	public void closeMenu() {
		mDrawer.closeMenu();
		actionbar_menu.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
	}

	@Override
	public boolean isMenuVisible() {
		return mDrawer.isMenuVisible();
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save the user's current game state
		super.onSaveInstanceState(savedInstanceState);
	}
	
	public void showFreeCard() {
		radiobutton_group.clearCheck();
		button_Use_Free_Card.setChecked(true);
		SharedPreferences.Editor editor = getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE).edit();
		editor.putBoolean("seenTutorial", true);
		editor.commit();
		new howToUseDigitalRxCardTask().execute();
	}

	// menu_item_left
	private ArrayList<String> get_menu_item_left() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(getResources().getString(R.string.how_to_use_app));
		list.add(getResources().getString(R.string.Share_Application));
		list.add(getResources().getString(R.string.faq));
		if (!AppSettings.getInstance().get_hide_about_ndc_savings()) {
			list.add(getResources().getString(
					R.string.About_Healthcare_Saving_Card));
		}
		if (!AppSettings.getInstance().get_hide_about_ndc()) {
			list.add(getResources()
					.getString(R.string.About_National_Drug_Card));
		}
		return list;
	}
}
