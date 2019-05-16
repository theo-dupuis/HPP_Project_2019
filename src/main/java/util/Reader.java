package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	private String fileType;
	
	public Reader(String fileType) {
		this.fileType = fileType;
	}

	public void processFile(String fileName) {
		String data = "";
		
		File file = new File(fileName);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((data = br.readLine()) != null)
				if(!data.isEmpty())
					Processor.process(fileType, data);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
