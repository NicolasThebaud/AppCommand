package com.appcommand.data;

import java.io.IOException;

public class Command {

	@ExecutableCommand
	public static void cmd_example() throws IOException {
		Runtime.getRuntime().exec("cmd /c " + "start " + "commands\\" + "cmd.bat");
	}
}
