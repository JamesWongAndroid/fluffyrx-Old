package com.grouprx.sync;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.grouprx.ui.LaunchActivity;

public class URLDownloadFile {
	public static URLDownloadFile instance;
	
	public static URLDownloadFile getInstance(){
		if (instance == null) {
			instance = new URLDownloadFile();
		}
		return instance;
	}
	
	public String getFileName(String str){
		String ss = "";
		try {
		int y = str.lastIndexOf("/")+1;
		int yy = str.indexOf("?");
		ss = str.substring(y,yy);
		} catch (Exception e) {
			
		}
		System.out.println("Aloooooooooooooooo yyyyyyyyyyyyyy = "+ss);
		return ss;
	}
	
	public File getFilePath_group_banner() {
//		System.out.println("Aloooooooooooooooo : "
//				+ getFileName(AppSettings.getInstance().get_group_banner()));
		// File sa = new
		// File(getFolder(),"image_group_banner"+AppSettings.getInstance().get_extention(AppSettings.KEY_group_banner));
		String ss = getFileName(AppSettings.getInstance().get_group_banner());
		File sa = null;
		if (!ss.isEmpty()) {
			sa = new File(getFolder(), ss);
		}
		return sa;
	}
	
	public File getFilePath_sidebar_image(){
//		File sa = new File(getFolder(),"image_sidebar"+AppSettings.getInstance().get_extention(AppSettings.KEY_sidebar_image));
		String ss = getFileName(AppSettings.getInstance().get_sidebar_image());
		File sa = null;
		if (!ss.isEmpty()) {
			sa = new File(getFolder(), ss);
		}
		return sa;
	}
	
	public File getFilePath_drug_card_option_image(){
//		File sa = new File(getFolder(),"image_drag_card_options"+AppSettings.getInstance().get_extention(AppSettings.KEY_drug_card_option_image));
		String ss = getFileName(AppSettings.getInstance().get_drug_card_option_image());
		File sa = null;
		if (!ss.isEmpty()) {
			sa = new File(getFolder(), ss);
		}
		return sa;
	}
	
	public File getFilePath_pet_drug_card_option_image(){
//		File sa = new File(getFolder(),"image_pet_drag_card_options"+AppSettings.getInstance().get_extention(AppSettings.KEY_pet_drug_card_option_image));
		String ss = getFileName(AppSettings.getInstance().get_pet_drug_card_option_image());
		File sa = null;
		if (!ss.isEmpty()) {
			sa = new File(getFolder(), ss);
		}
		return sa;
	}
	
	public File getFilePath_drug_card_image(){
//		File sa = new File(getFolder(),"image_drag_card"+AppSettings.getInstance().get_extention(AppSettings.KEY_drug_card_image));
		String ss = getFileName(AppSettings.getInstance().get_drug_card_image());
		File sa = null;
		if (!ss.isEmpty()) {
			sa = new File(getFolder(), ss);
		}
		return sa;
	}
	
	public File getFilePath_pet_drug_card_image(){
//		File sa = new File(getFolder(),"image_pet_drag_card"+AppSettings.getInstance().get_extention(AppSettings.KEY_pet_drug_card_image));
		String ss = getFileName(AppSettings.getInstance().get_pet_drug_card_image());
		File sa = null;
		if (!ss.isEmpty()) {
			sa = new File(getFolder(), ss);
		}
		return sa;
	}
	
	public File getFolder(){
		File sd = Environment.getExternalStorageDirectory();
		File folder = new File(sd, "GroupRX/");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return folder;
	}
	
	
	public synchronized void downloadFile(final String uRl,final File fil) {
	    File sd = Environment.getExternalStorageDirectory();
		File direct = new File(sd, "GroupRX/");
	    if (!direct.exists()) {
	    	direct.mkdirs();
	    }
	    DownloadManager mgr = (DownloadManager) LaunchActivity.getInstance().getSystemService(Context.DOWNLOAD_SERVICE);
	    Uri downloadUri = Uri.parse(uRl);
	    DownloadManager.Request request = new DownloadManager.Request(downloadUri);
	    System.out.println("Alo1 = "+fil.getName() + " direct : "+direct.getPath());
	    request.setAllowedNetworkTypes(
	            DownloadManager.Request.NETWORK_WIFI
	                    | DownloadManager.Request.NETWORK_MOBILE)
	            .setAllowedOverRoaming(false).setTitle("Download")
	            .setDescription("Download From Server")
	            .setDestinationInExternalPublicDir("/GroupRx", fil.getName());
	    
	    		mgr.enqueue(request);
	    		
	    		try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public synchronized void downloadSaveFile(final URL url,final File fileTarget) throws IOException{
//		final long startTime = System.currentTimeMillis();
//		try {
//			URLConnection ucon = url.openConnection();
//				InputStream is = ucon.getInputStream();
//				System.out.println("URl = "+url+" File : "+fileTarget.getPath());
//				System.out.println("availableavailable " + is.available());
//				if (is.available() > 0) {
//					BufferedInputStream bis = new BufferedInputStream(
//							is);
//					ByteArrayBuffer baf = new ByteArrayBuffer(bis
//							.available());
//					int current = 0;
//					while ((current = bis.read()) != -1) {
//						baf.append((byte) current);
//					}
//					FileOutputStream fos = new FileOutputStream(
//							fileTarget);
//					fos.write(baf.toByteArray());
//					fos.close();
//					bis.close();
//					System.out.println("Download Completed in"
//							+ ((System.currentTimeMillis() - startTime) / 1000)
//							+ " sec File : " + fileTarget.getPath());
//				}
//				is.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		
		downloadFile(url.toExternalForm(), fileTarget);
		
	}
}
