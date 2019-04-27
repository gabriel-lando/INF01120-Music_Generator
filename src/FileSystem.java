import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSystem {
	
	// Lê Arquivo
	public String readFile(File file) {
		Scanner sc;
		StringBuilder content = new StringBuilder("");
		try {
			sc = new Scanner(file);
			sc.useDelimiter("\\Z"); 
		    content.append(sc.next());
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return content.toString();
	}
}
