package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

	private String filePath;
	public Writer(String filePath) {
		this.filePath = filePath;
	}
	public void write(String data) {
        File file = new File(filePath);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
