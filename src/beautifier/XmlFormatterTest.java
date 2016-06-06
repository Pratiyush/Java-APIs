package beautifier;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.stream.XMLStreamException;

import beautifier.t.PlainBeautifierFormatter;
import beautifier.t.XMLBeautifier;
import beautifier.t.XMLBeautifierFormatter;



public class XmlFormatterTest {

	static XMLBeautifier beautifier; 
	
	static Reader in;
	
	static Writer out;
	
	public static void main(String[] args) throws Exception{
		out = new FileWriter(new File("C:\\eig\\test_drain.xml"));
		
		in = new BufferedReader(new FileReader("C:\\eig\\test.xml"));
		XmlFormatter xmlFormatter = new XmlFormatter();
		xmlFormatter.format(in, out);
		out.close();
		in.close();
	}
}