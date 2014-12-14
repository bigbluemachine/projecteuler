package core;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class IOLib {
	// Scanner from filename.
	public static Scanner scanner(String filename) {
		try {
			return new Scanner(new File(filename));
		} catch (IOException e) {
			return null;
		}
	}

	// Exception-free close hack.
	public static void close(Closeable in) {
		try {
			in.close();
		} catch (IOException e) {
			// Do nothing
		}
	}
}
