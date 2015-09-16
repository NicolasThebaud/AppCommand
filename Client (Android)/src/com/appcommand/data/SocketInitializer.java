package com.appcommand.data;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;

public class SocketInitializer extends AsyncTask<String, Void, Socket> {
	
	Socket socket;
	
	@Override
	protected Socket doInBackground(String... params) {
		String address = params[0];
		String port = params[1];
		try {
			socket = new Socket(address, Integer.parseInt(port));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socket;
	}
	
	@Override
	protected void onPostExecute(Socket result) {
	}
}