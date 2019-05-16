package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	private String fileType;
	private File file;
	private BufferedReader br;
	
	public Reader(String fileType, String fileName) {
		this.fileType = fileType;
		this.file = new File(fileName);
		try {
			this.br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		
		processLine();
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
	
	public String processLine() {
		String data = "";
		
		try {
			if((data = br.readLine()) != null)
				if(!data.isEmpty())
					return data;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}
}
