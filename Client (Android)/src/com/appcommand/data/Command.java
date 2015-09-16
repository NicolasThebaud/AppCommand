package com.appcommand.data;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import android.os.AsyncTask;
import android.util.Log;

public class Command extends AsyncTask<Object, Void, Void> {

	private PrintStream printStream;
    
	@Override
	protected Void doInBackground(Object... params) {
		Object socket = params[0];
		Object command = params[1];
		
		Log.w("tamer", "starting command...");
		Log.w("tamer", socket==null ? "null" : "not null");
		if(socket instanceof Socket) {
			Log.w("tamer", "sending command...");
			try {
				printStream = new PrintStream(((Socket) socket).getOutputStream(), true);
	            printStream.println(command);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		return null;
	}    
}