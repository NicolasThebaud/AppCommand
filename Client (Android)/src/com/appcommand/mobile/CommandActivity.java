package com.appcommand.mobile;

import java.net.Socket;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.appcommand.data.Command;
import com.appcommand.data.SocketInitializer;

public class CommandActivity extends Activity {
	
	final Context ctx = this;
	final CommandActivity instance = this;
	private Socket socket;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               
        Bundle extras = this.getIntent().getExtras();
        String address = extras.getString("ip");
        String port = extras.getString("port");
        
        try {
			socket = (new SocketInitializer()).execute(address, port).get();
		} catch (InterruptedException e) { e.printStackTrace();	} 
		  catch (ExecutionException e) { e.printStackTrace(); }
		finally {
			if(socket == null) {
				Log.w("tamer", "socket null, aborting...");
			}
		}
		
		setContentView(R.layout.activity_command);
		
        Button button = (Button) findViewById(R.id.cmd1);
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Log.w("tamer", "click!");
				//(new Command()).execute(socket, new String(".lel"));
				findViewById(R.id.cmd1).setBackgroundColor(Color.BLUE);
			}
        });
        
        button.setOnTouchListener(new OnTouchListener() {
        	@Override
        	public boolean onTouch(View view, MotionEvent event) {
        		if (event.getAction() == MotionEvent.ACTION_DOWN){
        			Log.w("tamer", "x:"+event.getX()+"; y:"+event.getY());
        			(new Command()).execute(socket, new String(".coord;"+event.getX()+";"+event.getY()));
        			view.performClick();
        		}
        		return true;
        	}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
