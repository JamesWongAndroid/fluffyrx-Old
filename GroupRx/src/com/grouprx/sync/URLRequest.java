package com.grouprx.sync;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;


public class URLRequest extends CommonUtilsManagerImplHTTPS{
	private static URLRequest inistance;
	
	public static URLRequest getInstance(){
		if (inistance == null) {
			inistance = new URLRequest();
		}
		return inistance;
	}
	
	public boolean getData() throws InterruptedException, ExecutionException{
		try {
        	HashMap<String, String> params = new HashMap<String, String>();
        	String test = AppSettings.getInstance().get_group_app_id();
    		JSONObject js = doPost(AppSettings.getInstance().get_group_app_id(),params);
    		if (js != null) {
    			AppSettings.getInstance().setValues(js);
    			return true;
    		}
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return false;
	}
	
	public boolean getDataTest(String ss) throws InterruptedException, ExecutionException{
		try {
        	HashMap<String, String> params = new HashMap<String, String>();
    		JSONObject js = doPost("/groups/" + ss + ".json",params);
    		if (js != null) {
    			return true;
    		}
        } catch (Exception e) {
        }
		return false;
	}
	
	public void download_group_banner_image() throws IOException{
		URL url = new URL(getServerURLStarter()+AppSettings.getInstance().get_group_banner());
		URLDownloadFile.getInstance().downloadSaveFile(url, URLDownloadFile.getInstance().getFilePath_group_banner());
	}
	
	public void download_sidebar_image() throws IOException{
		String uu = getServerURLStarter()+AppSettings.getInstance().get_sidebar_image();
		URL url = new URL(uu);
		URLDownloadFile.getInstance().downloadSaveFile(url, URLDownloadFile.getInstance().getFilePath_sidebar_image());
	}
	
	public void download_drug_card_option_image() throws IOException{
		URL url = new URL(getServerURLStarter()+AppSettings.getInstance().get_drug_card_option_image());
		URLDownloadFile.getInstance().downloadSaveFile(url, URLDownloadFile.getInstance().getFilePath_drug_card_option_image());
	}
	
	public void download_pet_drug_card_option_image() throws IOException{
		URL url = new URL(getServerURLStarter()+AppSettings.getInstance().get_pet_drug_card_option_image());
		URLDownloadFile.getInstance().downloadSaveFile(url, URLDownloadFile.getInstance().getFilePath_pet_drug_card_option_image());
	}
	
	public void download_drug_card_image() throws IOException{
		URL url = new URL(getServerURLStarter()+AppSettings.getInstance().get_drug_card_image());
		URLDownloadFile.getInstance().downloadSaveFile(url, URLDownloadFile.getInstance().getFilePath_drug_card_image());
	}
	
	public void download_pet_drug_card_image() throws IOException{
		URL url = new URL(getServerURLStarter()+AppSettings.getInstance().get_pet_drug_card_image());
		URLDownloadFile.getInstance().downloadSaveFile(url, URLDownloadFile.getInstance().getFilePath_pet_drug_card_image());
	}
}
