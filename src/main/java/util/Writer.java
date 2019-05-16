package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

	private File file;
	
	public Writer(File file) {
		this.file = file;
	}
	public void write(String data) {
        	BufferedWriter bw;
        	if(file.exists()) {
				try {
					bw = new BufferedWriter(new FileWriter(file, true));
		        	bw.append(data);
					bw.append("\r\n");
		            bw.close();
				} catch (IOException e) {
					System.out.println("Error while writing");
				}
        	}
	}
}
