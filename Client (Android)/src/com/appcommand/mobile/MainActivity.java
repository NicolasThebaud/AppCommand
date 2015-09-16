package com.appcommand.mobile;

import java.net.Socket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	final Context ctx = this;
	final MainActivity instance = this;
	private Socket socket;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.connect);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String address = ((EditText)findViewById(R.id.editText_ip)).getText().toString();
				String port = ((EditText)findViewById(R.id.editText_port)).getText().toString();
				Intent command = new Intent(ctx, CommandActivity.class);
				command.putExtra("ip", address);
				command.putExtra("port", port);
				((Activity) instance).startActivity(command);
			}
		});
	}
	
	public Socket getSocket() {
		return socket;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
