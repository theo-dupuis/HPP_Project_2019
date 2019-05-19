package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	private File file;
	private BufferedReader br;
	
	public Reader(String fileName) {
		this.file = new File(fileName);
		try {
			this.br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println(e);
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
	
	public void releaseReader() {
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
