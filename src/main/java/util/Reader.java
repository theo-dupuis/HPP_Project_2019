package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	private String fileType;
	private File file;
	
	public Reader(String fileType, String fileName) {
		this.fileType = fileType;
		this.file = new File(fileName);
		
		processFile();
	}

	private void processFile() {
		String data = "";
		
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((data = br.readLine()) != null)
				if(!data.isEmpty())
					Processor.process(fileType, data);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
