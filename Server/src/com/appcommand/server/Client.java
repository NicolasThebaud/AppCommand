package com.appcommand.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.appcommand.data.Command;
import com.appcommand.data.ExecutableCommand;

public class Client extends Thread {

	private BufferedReader bufferedReader;
	private HashMap<String, Method> commands;

	public Client(Socket socket) {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) { e.printStackTrace(); }
		commands = new HashMap<String, Method>();
		retreiveMethods();
	}
	
	private void retreiveMethods() {
		List<Method> methods = Arrays.asList(Command.class.getMethods());
		for(Method m : methods) {
			commands.put(m.getName(), m);
		}
	}

	public void run() {
		while(true) {
			try {
				String message = bufferedReader.readLine();
				if(message == null) {
					continue;
				} else {
					/**DEBUG*/
					//System.out.println(message);
					checkCommand(message);
					
					if(commands.containsKey(message.substring(1))) {
						Method method = commands.get(message.substring(1));
						try {
							System.out.println("[info]SERVER: command - "+message);
							if(method.isAnnotationPresent(ExecutableCommand.class))
								method.invoke(Command.class, (Object[])null);
							else
								System.err.println("invalid command");
						} catch (IllegalAccessException | IllegalArgumentException
								| InvocationTargetException e) {
							e.printStackTrace();
						}				
					} else
						System.err.println("invalid command");
				}
			} catch(Exception e) {}
		}
	}
	
	private void checkCommand(String message) {
		if(message.contains(";")) {
			String[] data = message.split(";");
			String cmd = data[0];
		}
	}
}