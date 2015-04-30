package com.grouprx.sync;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Environment;

import com.grouprx.objects.objLink;
import com.grouprx.util.NoExceptionJsonObject;
import com.grouprx.util.SharedPreferencesHelper;

public class AppSettings {
	// app_settings
	public static String KEY_sidebar_image = "sidebar_image";
	public static String KEY_pet_drug_card_option_image = "pet_drug_card_option_image";
	public static String KEY_drug_card_image = "drug_card_image";
	public static String KEY_group_banner = "group_banner";
	public static String KEY_pet_drug_card_image = "pet_drug_card_image";
	public static String KEY_drug_card_option_image = "drug_card_option_image";
	public static String KEY_hide_about_ndc = "hide_about_ndc";
	public static String KEY_hide_about_ndc_savings = "hide_about_ndc_savings";

	// group_info
	public static String KEY_group_app_id = "group_app_id";
	public static String KEY_group_name = "group_name";
	public static String KEY_group_description = "group_description";
	public static String KEY_tag_line = "tag_line";
	public static String KEY_donation_link = "donation_link";
	public static String KEY_custom_group_fields = "custom_group_fields";

	// URl_str
	// private String KEY_URl_str = "URL_str";

	// links
	public static String KEY_links_titles = "titles";
	public static String KEY_links_urls = "urls";

	// network_info
	public static String KEY_network_info = "network_info";

	// ndc_card
	public static String KEY_ndc_card = "ndc_card";
	public static String KEY_help_phones_ndc = "ndc";

	// pet
	public static String KEY_pet_card = "pet_card";
	public static String KEY_help_phones_pet = "pet";

	// help Phones
	public static String KEY_help_phones = "help_phones";

	// fields
	public static String KEY_fields = "fields";

	// values
	public static String KEY_values = "values";

	public static AppSettings instance;

	public AppSettings() {

	}

	public synchronized void setValues(JSONObject js) {
		try {
			// boolean toDownloadFileBannar = true;
			// boolean toDownloadFileSideBarImage = true;
			// boolean toDownloadFileDrugCardOptionsImage = true;
			// boolean toDownloadFilePetDrugCardOptionsImage = true;
			// boolean toDownloadFileDrugCardImage = true;
			// boolean toDownloadFilePetDrugCardImage = true;

			// app_settings
			JSONObject app_settings = new JSONObject(
					js.getString("app_settings"));
			NoExceptionJsonObject app_settingsx = new NoExceptionJsonObject(
					app_settings);

			// Slide Menu Image
			String slideURL = app_settingsx.getString(KEY_sidebar_image);
			// String slide = SharedPreferencesHelper.getStringSetting(
			// KEY_sidebar_image, "");
			// if (slide.trim().equalsIgnoreCase(slideURL)) {
			// toDownloadFileSideBarImage = false;
			// }
			SharedPreferencesHelper.putStringSetting(KEY_sidebar_image,
					slideURL);

			// baner Image
			String bannarURL = app_settingsx.getString(KEY_group_banner);
			// String bannar = SharedPreferencesHelper.getStringSetting(
			// KEY_group_banner, "");
			// if (bannar.trim().equalsIgnoreCase(bannarURL)) {
			// toDownloadFileBannar = false;
			// }
			SharedPreferencesHelper.putStringSetting(KEY_group_banner,
					bannarURL);

			// pet drug card options
			String petdragCardOptionURL = app_settingsx
					.getString(KEY_pet_drug_card_option_image);
			// String petdragCardOption = SharedPreferencesHelper
			// .getStringSetting(KEY_pet_drug_card_option_image, "");
			// if
			// (petdragCardOption.trim().equalsIgnoreCase(petdragCardOptionURL))
			// {
			// toDownloadFilePetDrugCardOptionsImage = false;
			// }
			SharedPreferencesHelper.putStringSetting(
					KEY_pet_drug_card_option_image, petdragCardOptionURL);

			// Drug Card Options Image
			String dragCardOptionURL = app_settingsx
					.getString(KEY_drug_card_option_image);
			// String dragCardOption = SharedPreferencesHelper.getStringSetting(
			// KEY_drug_card_option_image, "");
			// if (dragCardOption.trim().equalsIgnoreCase(dragCardOptionURL)) {
			// toDownloadFileDrugCardOptionsImage = false;
			// }
			SharedPreferencesHelper.putStringSetting(
					KEY_drug_card_option_image, dragCardOptionURL);

			// Drug Card Image
			String drugcardimageURL = app_settingsx
					.getString(KEY_drug_card_image);
			// String drugcardimage = SharedPreferencesHelper.getStringSetting(
			// KEY_drug_card_image, "");
			// if (drugcardimage.trim().equalsIgnoreCase(drugcardimageURL)) {
			// toDownloadFileDrugCardImage = false;
			// }
			SharedPreferencesHelper.putStringSetting(KEY_drug_card_image,
					drugcardimageURL);

			// Pet Drug Card Image
			String petdrugcardimageURL = app_settingsx
					.getString(KEY_pet_drug_card_image);
			// String petdrugcardimage =
			// SharedPreferencesHelper.getStringSetting(
			// KEY_pet_drug_card_image, "");
			// if
			// (petdrugcardimage.trim().equalsIgnoreCase(petdrugcardimageURL)) {
			// toDownloadFilePetDrugCardImage = false;
			// }
			SharedPreferencesHelper.putStringSetting(KEY_pet_drug_card_image,
					petdrugcardimageURL);

			SharedPreferencesHelper.putBooleanSetting(KEY_hide_about_ndc,
					app_settingsx.getBoolean(KEY_hide_about_ndc));
			SharedPreferencesHelper.putBooleanSetting(
					KEY_hide_about_ndc_savings,
					app_settingsx.getBoolean(KEY_hide_about_ndc_savings));

			// group_info
			JSONObject group_info = new JSONObject(js.getString("group_info"));
			NoExceptionJsonObject group_infox = new NoExceptionJsonObject(
					group_info);
			SharedPreferencesHelper.putStringSetting(KEY_group_name,
					group_infox.getString(KEY_group_name));
			SharedPreferencesHelper.putStringSetting(KEY_group_description,
					group_infox.getString(KEY_group_description));
			SharedPreferencesHelper.putStringSetting(KEY_tag_line,
					group_infox.getString(KEY_tag_line));
			SharedPreferencesHelper.putStringSetting(KEY_donation_link,
					group_infox.getString(KEY_donation_link));

			JSONObject custom_group = new JSONObject(
					group_infox.getString(KEY_custom_group_fields));
			JSONArray custom_group_fields = new JSONArray(
					custom_group.getString(KEY_fields));
			JSONArray custom_group_values = new JSONArray(
					custom_group.getString(KEY_values));
			SharedPreferencesHelper.putStringSetting(KEY_custom_group_fields
					+ "_" + KEY_fields, custom_group_fields.toString());
			SharedPreferencesHelper.putStringSetting(KEY_custom_group_fields
					+ "_" + KEY_values, custom_group_values.toString());

			// links
			JSONObject links = new JSONObject(js.getString("links"));
			NoExceptionJsonObject linksx = new NoExceptionJsonObject(links);
			SharedPreferencesHelper.putStringSetting(KEY_links_titles,
					linksx.getString(KEY_links_titles));
			SharedPreferencesHelper.putStringSetting(KEY_links_urls,
					linksx.getString(KEY_links_urls));

			// network_info
			JSONObject network_info = new JSONObject(
					js.getString(KEY_network_info));
			JSONObject network_info_ndc = new JSONObject(
					network_info.getString(KEY_ndc_card));
			JSONArray network_info_ndc_fields = new JSONArray(
					network_info_ndc.getString(KEY_fields));
			JSONArray network_info_ndc_values = new JSONArray(
					network_info_ndc.getString(KEY_values));
			SharedPreferencesHelper.putStringSetting(KEY_ndc_card + "_"
					+ KEY_fields, network_info_ndc_fields.toString());
			SharedPreferencesHelper.putStringSetting(KEY_ndc_card + "_"
					+ KEY_values, network_info_ndc_values.toString());

			JSONObject network_info_pet = new JSONObject(
					network_info.getString(KEY_pet_card));
			JSONArray network_info_pet_fields = new JSONArray(
					network_info_pet.getString(KEY_fields));
			JSONArray network_info_pet_values = new JSONArray(
					network_info_pet.getString(KEY_values));
			SharedPreferencesHelper.putStringSetting(KEY_pet_card + "_"
					+ KEY_fields, network_info_pet_fields.toString());
			SharedPreferencesHelper.putStringSetting(KEY_pet_card + "_"
					+ KEY_values, network_info_pet_values.toString());

			JSONObject network_info_phones = new JSONObject(
					network_info.getString(KEY_help_phones));
			SharedPreferencesHelper.putStringSetting(KEY_ndc_card + "_"
					+ KEY_help_phones,
					network_info_phones.getString(KEY_help_phones_ndc));
			SharedPreferencesHelper.putStringSetting(KEY_pet_card + "_"
					+ KEY_help_phones,
					network_info_phones.getString(KEY_help_phones_pet));

			
//			File sd = Environment.getExternalStorageDirectory();
//			File direct = new File(sd, "GroupRX/");
//			System.out.println("directdirect = "+direct.getPath()+" Exist = "+direct.exists());
//		    deleteDirectory(direct);
			
			// image group bannar
			try {
				File f = new File(URLDownloadFile.getInstance()
						.getFilePath_group_banner().getAbsolutePath());
				System.out.println("f = group_banner : " + f.getPath()
						+ " exists = " + f.exists());
				if (!f.exists()) {
					URLRequest.getInstance().download_group_banner_image();
				}
				System.out.println("f =B " + f.getPath() + " exists = "
						+ f.exists());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// sidebar_image
			try {
				File ff = new File(URLDownloadFile.getInstance()
						.getFilePath_sidebar_image().getAbsolutePath());
				System.out.println("ff = sidebar_image : " + ff.getPath()
						+ " exists = " + ff.exists());
				if (! ff.exists()) {
				URLRequest.getInstance().download_sidebar_image();
				}
				System.out.println("ff =B " + ff.getPath() + " exists = "
						+ ff.exists());
			} catch (Exception e) {

			}

			// drug_card_option_image
			try {
				File fff = new File(URLDownloadFile.getInstance()
						.getFilePath_drug_card_option_image().getAbsolutePath());
				System.out.println("fff = drug_card_option : " + fff.getPath()
						+ " exists = " + fff.exists());
				if (! fff.exists()) {
				URLRequest.getInstance()
				.download_drug_card_option_image();
				}
				System.out.println("fff =B " + fff.getPath() + " exists = "
						+ fff.exists());
			} catch (Exception e) {

			}

			// pet_drug_card_option_image
			try {
				File ffff = new File(URLDownloadFile.getInstance()
						.getFilePath_pet_drug_card_option_image()
						.getAbsolutePath());
				System.out.println("ffff = pet_drug_card_option : "
						+ ffff.getPath() + " exists = " + ffff.exists());
				if (! ffff.exists()) {
				URLRequest.getInstance()
				.download_pet_drug_card_option_image();
				}
				System.out.println("ffff =B " + ffff.getPath() + " exists = "
						+ ffff.exists());
			} catch (Exception e) {

			}

			// drug_card_image
			try {
				File fffff = new File(URLDownloadFile.getInstance()
						.getFilePath_drug_card_image().getAbsolutePath());
				System.out.println("fffff = drug_card : " + fffff.getPath()
						+ " exists = " + fffff.exists());
				if (! fffff.exists()) {
				URLRequest.getInstance().download_drug_card_image();
				}
				System.out.println("fffff =B " + fffff.getPath() + " exists = "
						+ fffff.exists());
			} catch (Exception e) {

			}

			// pet_drug_card_image
			try {
				File ffffff = new File(URLDownloadFile.getInstance()
						.getFilePath_pet_drug_card_image().getAbsolutePath());
				System.out.println("ffffff = pet_drug_card : "
						+ ffffff.getPath() + " exists = " + ffffff.exists());
				if (! ffffff.exists()) {
				URLRequest.getInstance().download_pet_drug_card_image();
				}
				System.out.println("ffffff =B " + ffffff.getPath()
						+ " exists = " + ffffff.exists());
			} catch (Exception e) {

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteDirectory(File path) {
	    if( path.exists() ) {
	      File[] files = path.listFiles();
	      if (files == null) {
	          return true;
	      }
	      for(int i=0; i<files.length; i++) {
	         if(files[i].isDirectory()) {
	           deleteDirectory(files[i]);
	         }
	         else {
	           files[i].delete();
	         }
	      }
	    }
	    return( path.delete() );
	  }

	public static AppSettings getInstance() {
		if (instance == null) {
			instance = new AppSettings();
		}
		return instance;
	}

	public JSONArray get_NDCCardValues() {
		try {
			JSONArray jsA = new JSONArray(
					SharedPreferencesHelper.getStringSetting(KEY_ndc_card + "_"
							+ KEY_values, ""));
			return jsA;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new JSONArray();
	}

	public JSONArray get_PetCardValues() {
		try {
			JSONArray jsA = new JSONArray(
					SharedPreferencesHelper.getStringSetting(KEY_pet_card + "_"
							+ KEY_values, ""));
			return jsA;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new JSONArray();
	}

	public JSONArray get_CustomGroupFields() {
		try {
			JSONArray jsA = new JSONArray(
					SharedPreferencesHelper.getStringSetting(
							KEY_custom_group_fields + "_" + KEY_fields, ""));
			return jsA;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new JSONArray();
	}

	public JSONArray get_CustomGroupValues() {
		try {
			JSONArray jsA = new JSONArray(
					SharedPreferencesHelper.getStringSetting(
							KEY_custom_group_fields + "_" + KEY_values, ""));
			return jsA;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new JSONArray();
	}

	public String get_NDCCardPhone() {
		return SharedPreferencesHelper.getStringSetting(KEY_ndc_card + "_"
				+ KEY_help_phones, "");
	}

	public String get_PETCardPhone() {
		return SharedPreferencesHelper.getStringSetting(KEY_pet_card + "_"
				+ KEY_help_phones, "");
	}

	public JSONArray get_NDCCardFields() {
		try {
			JSONArray jsA = new JSONArray(
					SharedPreferencesHelper.getStringSetting(KEY_ndc_card + "_"
							+ KEY_fields, ""));
			return jsA;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JSONArray();
	}

	public String get_sidebar_image() {
		return SharedPreferencesHelper.getStringSetting(KEY_sidebar_image, "");
	}

	public String get_pet_drug_card_option_image() {
		return SharedPreferencesHelper.getStringSetting(
				KEY_pet_drug_card_option_image, "");
	}

	public String get_drug_card_image() {
		return SharedPreferencesHelper
				.getStringSetting(KEY_drug_card_image, "");
	}

	public String get_group_banner() {
		return SharedPreferencesHelper.getStringSetting(KEY_group_banner, "");
	}

	// public String get_group_banner_extention() {
	// String ss = SharedPreferencesHelper.getStringSetting(KEY_group_banner,
	// "");
	// int y = ss.lastIndexOf(".");
	// int yy = ss.lastIndexOf("?");
	// String ds = ss.substring(y, yy);
	// System.out.println("DSssssssssssssssss get_group_banner_extention : " +
	// ds);
	// return ds;
	// }
	//
	// public String get_group_slideImage_extention() {
	// String ss = SharedPreferencesHelper.getStringSetting(KEY_sidebar_image,
	// "");
	// int y = ss.lastIndexOf(".");
	// int yy = ss.lastIndexOf("?");
	// String ds = ss.substring(y, yy);
	// System.out.println("DSssssssssssssssss get_group_slideImage_extention : "
	// + ds);
	// return ds;
	// }

	public String get_extention(String key) {
		String ss = SharedPreferencesHelper.getStringSetting(key, "");
		int y = ss.lastIndexOf(".");
		int yy = ss.lastIndexOf("?");
		String ds = ss.substring(y, yy);
		System.out.println("DSssssssssssssssss " + key + " : " + ds);
		return ds;
	}

	public String get_pet_drug_card_image() {
		return SharedPreferencesHelper.getStringSetting(
				KEY_pet_drug_card_image, "");
	}

	public String get_drug_card_option_image() {
		return SharedPreferencesHelper.getStringSetting(
				KEY_drug_card_option_image, "");
	}

	public boolean get_hide_about_ndc() {
		return SharedPreferencesHelper.getBooleanSetting(KEY_hide_about_ndc,
				false);
	}

	public boolean get_hide_about_ndc_savings() {
		return SharedPreferencesHelper.getBooleanSetting(
				KEY_hide_about_ndc_savings, false);
	}

	public String get_group_name() {
		return SharedPreferencesHelper.getStringSetting(KEY_group_name, "");
	}

	public void set_group_app_id(String str) {
		SharedPreferencesHelper.putStringSetting(KEY_group_app_id, str);
		clearImages();
	}
	
	private void clearImages(){
		File sd = Environment.getExternalStorageDirectory();
		File direct = new File(sd, "GroupRX/");
		System.out.println("directdirect = "+direct.getPath()+" Exist = "+direct.exists());
	    AppSettings.getInstance().deleteDirectory(direct);
	}

	public String get_group_app_id() {
		String ss = SharedPreferencesHelper.getStringSetting(KEY_group_app_id,"");
		if (ss == null || ss.isEmpty()) {
			return "";
		}
		return "/groups/" + ss + ".json";
	}
	
	public String get_group_app_id_OnlyID() {
		return SharedPreferencesHelper.getStringSetting(KEY_group_app_id,"");
	}

	public String get_group_description() {
		return SharedPreferencesHelper.getStringSetting(KEY_group_description,
				"");
	}

	public String get_tag_line() {
		return SharedPreferencesHelper.getStringSetting(KEY_tag_line, "");
	}

	public boolean get_is_donation_link() {
		boolean bb = false;
		String ss = SharedPreferencesHelper.getStringSetting(KEY_donation_link,
				"");
		if (ss != null && !ss.isEmpty()) {
			bb = true;
		}
		return bb;
	}

	public String get_donation_link() {
		return SharedPreferencesHelper.getStringSetting(KEY_donation_link, "");
	}

	public List<objLink> get_links() {
		List<objLink> list = new ArrayList<objLink>();
		try {
			JSONArray arrTit = new JSONArray(
					SharedPreferencesHelper.getStringSetting(KEY_links_titles,
							""));

			JSONArray arrUrl = new JSONArray(
					SharedPreferencesHelper
							.getStringSetting(KEY_links_urls, ""));
			for (int i = 0; i < arrTit.length(); i++) {
				try {
					String title = arrTit.getString(i);
					String url = arrUrl.getString(i);
					objLink obj = new objLink(title, url);
					list.add(obj);
				} catch (Exception e) {

				}
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
	}

}
