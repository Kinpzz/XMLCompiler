package scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 16th Oct, 2016
 * @author x1057057 Yan Pengxiang
 */
public class FileUtil {
	// output file name
	private String output;
	// instance of FileUnit singleton
	private static FileUtil _instance;
	
	private FileUtil() {}
	//singleton
	public static FileUtil getInstance() {
		if (_instance == null) {
			_instance = new FileUtil();
		}
		_instance.setOutput("./src/output.txt");
		return _instance;
	}
	public void setOutput(String file) {
		output = file;
	}
	/**
	 * 
	 * @param buffer - store buffer read from file
	 * @param fileSrc - the name of the file to read from
	 * @return true if succeed to read, false otherwise
	 */
	public boolean readFile(StringBuffer buffer, String fileSrc) {
		try {
			FileReader fileReader = new FileReader(fileSrc);
			BufferedReader br = new BufferedReader(fileReader);
			int temp;
			while ((temp = br.read()) != -1) {
				buffer.append((char)temp);
			}
			br.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @param args - the file to write
	 * @return true if succeed to write, false otherwise
	 */
	public boolean writeFile(String args) {
		try {
			File file = new File(output);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(args);
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * clear the exist output file, if not exist then create one.
	 * @return true if succeed to write, false otherwise
	 */
	public boolean clearFile() {
		try {
			File file = new File(output);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("");
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
