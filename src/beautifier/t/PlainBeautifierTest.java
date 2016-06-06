package beautifier.t;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

public class PlainBeautifierTest {

	static XMLBeautifier beautifier; 
	
	static InputStream inputStream;
	
	static Writer writer;
	
	public void testFile() throws Exception {
		
	}
	
	public static void main(String[] args) throws Exception{
		inputStream = new FileInputStream(new File("C:\\eig\\test.xml"));
		writer = new FileWriter(new File("C:\\eig\\test_drain1.xml"));
	    XMLBeautifierFormatter formatter = new PlainBeautifierFormatter(writer, 4);
		beautifier = new XMLBeautifier(formatter);
		beautifier.parse(new InputStreamReader(inputStream));
		writer.close();
		inputStream.close();
	}
}