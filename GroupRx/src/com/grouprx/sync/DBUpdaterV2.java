package com.grouprx.sync;


public class DBUpdaterV2 {

	private static DBUpdaterV2 instance;

	public static DBUpdaterV2 getInstance() {
		if (instance == null) {
			instance = new DBUpdaterV2();
		}
		return instance;
	}

	public boolean sendData() {
		try {

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean syncAllFromServer(final OnUpdateCompletedCallback callback) {

		new Thread(new Runnable() {

			public void run() {
				try {
					if (callback != null) {
						callback.onSuccess();
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (callback != null) {
						callback.onError("Exeption On syncAllFromServer : " + e);
					}
				}
			}
		}).start();
		return true;
	}

	public interface OnUpdateCompletedCallback {
		public void onSuccess();

		public void onError(String error);

	}

	public interface UpdateMessageSender {
		public void sendMessage(String message);
	}

}
