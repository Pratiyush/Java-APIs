package beautifier;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.Arrays;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * <p>This class realizes an XML beautifier: it reads a provided XML file using StAX API and indents it.</p>
 * 
 * <p>Moreover, if a <code>CDATA</code> section is encountered and its content is recognized as being
 * an XML, it's rendered in a formatted style too.</p> 
 * 
 * @author Giorgio Ferrara
 * @see <a href="http://docs.oracle.com/cd/E13222_01/wls/docs90/xml/stax.html">http://docs.oracle.com/cd/E13222_01/wls/docs90/xml/stax.html</a>
 *
 */
public class XmlFormatter {
	private static final String EMPTY = "";
	private static final String SPACE = " ";
	
	private static final String XML = "xml";
	
	private static final String VERSION_TEMPLATE = "version=\"%1\"";
	private static final String ENCODING_TEMPLATE = "encoding=\"%1\"";
	private static final String ATTRIBUTE_ASSIGNMENT_TEMPLATE = "%1=\"%2\"";
	private static final String NAMESPACE_ASSIGNMENT_TEMPLATE = "xmlns%1=\"%2\"";
	
	private static final String PAR_1 = "%1";
	private static final String PAR_2 = "%2";
	
	private static final String PREFIX_SEPARATOR = ":";
	
	private static final String COMMENT_TAG_START = "<!--";
	private static final String COMMENT_TAG_END = "-->";
	
	private static final String PROCESSING_INSTRUCTION_TAG_START = "<?";
	private static final String PROCESSING_INSTRUCTION_TAG_END = "?>";
	
	private static final String OPENING_TAG_START = "<";
	private static final String CLOSING_TAG_START = "</";
	private static final String TAG_END = ">";
	
	private static final String CDATA_TAG_START = "<![CDATA[";
	private static final String CDATA_TAG_END = "]]>";
	
	private final int indentation;
	private static final int MIN_INDENTATION = 0;
	private static final int MAX_INDENTATION = 10;
	private static final int DEFAULT_INDENTATION = 4;
	
	private final int startingTreeLevel;
	private static final int MIN_TREE_LEVEL = 0;
	private static final int DEFAULT_STARTING_TREE_LEVEL = 0;
	
	private final XMLInputFactory xmlInputFactory;
	
	/**
	 * <p>Class constructor.</p>
	 * 
	 * @param indentation defines how many single space chars to use to do indentation. Min value is {@value #MIN_INDENTATION}, max value {@value #MAX_INDENTATION}.
	 * @param startingTreeLevel defines the initial level of the XML root. Min value is {@value #MIN_TREE_LEVEL}.
	 */
	public XmlFormatter(final int indentation, final int startingTreeLevel) {
		if (indentation < MIN_INDENTATION && indentation > MAX_INDENTATION) throw new IllegalArgumentException("indentSpaces must be between %1 and %2".replace(PAR_1, String.valueOf(MIN_INDENTATION)).replace(PAR_2, String.valueOf(MAX_INDENTATION)));
		if (indentation < MIN_TREE_LEVEL) throw new IllegalArgumentException("treeLevel must be greater than or equal to %1".replace(PAR_1, String.valueOf(MIN_TREE_LEVEL)));

		this.indentation = indentation;
		this.startingTreeLevel = startingTreeLevel;
		
		this.xmlInputFactory = XMLInputFactory.newFactory();
		xmlInputFactory.setProperty("http://java.sun.com/xml/stream/properties/report-cdata-event", Boolean.TRUE);
	}
	
	/**
	 * <p>Class constructor.</p>
	 * <p>Same as {@link #XmlFormatter(int, int) XmlFormatter} with <code>startingTreeLevel = {@value #DEFAULT_STARTING_TREE_LEVEL}</code>
	 * 
	 * @param indentation defines how many single space chars to use to do indentation. Min value is 1, max value 10.
	 */
	public XmlFormatter(final int indentation) {
		this (indentation, DEFAULT_STARTING_TREE_LEVEL);
	}
	
	/**
	 * <p>Class constructor.</p>
	 * <p>Same as {@link #XmlFormatter(int, int) XmlFormatter} with <code>indentation = {@value #DEFAULT_INDENTATION}</code>
	 * and <code>startingTreeLevel = {@value #DEFAULT_STARTING_TREE_LEVEL}</code>
	 */
	public XmlFormatter() {
		this (DEFAULT_INDENTATION, DEFAULT_STARTING_TREE_LEVEL);
	}
	
	/**
	 * <p>Reads an XML provided as input and generates a formatted version of it as output.</p> 
	 * 
	 * @param in {@link Reader} that contains XML
	 * @param out {@link Writer} that will contain formatted XML
	 * @throws XMLStreamException if some error occurs during XML interpretation
	 * @throws IOException if some error occurs during writing
	 */
	public void format(final Reader in, final Writer out) throws XMLStreamException, IOException {
		if (in == null) throw new NullPointerException("in");
		if (out == null) throw new NullPointerException("out");
		XMLStreamReader streamReader = xmlInputFactory.createXMLStreamReader(in);

		boolean isFirstLine = true;
		String lastReadTag = EMPTY;	
		OutputWriter outWriter = new OutputWriter(out, indentation, startingTreeLevel);
		
		try {
			while(streamReader.hasNext()) {
				switch (streamReader.getEventType()) {
				case XMLStreamReader.PROCESSING_INSTRUCTION:
					if (streamReader.hasText()) {
						// only output PI with text
						if (isFirstLine) isFirstLine = false; else outWriter.writeNewLine();
						outWriter.writeString(PROCESSING_INSTRUCTION_TAG_START + streamReader.getText() + PROCESSING_INSTRUCTION_TAG_END);
					}
					break;
				case XMLStreamReader.START_DOCUMENT:
					String version = streamReader.getVersion();
					String characterEncodingScheme = streamReader.getCharacterEncodingScheme();
					if (version != null) {
						// only output XML declarations with specified version
						if (isFirstLine) isFirstLine = false; else outWriter.writeNewLine();
						outWriter.writeString(PROCESSING_INSTRUCTION_TAG_START + XML + SPACE + VERSION_TEMPLATE.replace(PAR_1, version) + (isNotSpecified(characterEncodingScheme) ? EMPTY : SPACE + ENCODING_TEMPLATE.replace(PAR_1, characterEncodingScheme)) + PROCESSING_INSTRUCTION_TAG_END);
					}
					break;
				case XMLStreamReader.START_ELEMENT:
					if (isFirstLine) isFirstLine = false; else outWriter.writeNewLine();
					outWriter.incTreeLevel();
					
					// tag name
					String tagPrefix = streamReader.getPrefix();
			        String tagLocalName = streamReader.getLocalName();
			        String namespaces = EMPTY;
			        for (int j=0; j<streamReader.getNamespaceCount(); j++) {
			        	String namespacePrefix = streamReader.getNamespacePrefix(j);
			        	String namespaceUri = streamReader.getNamespaceURI(j);
			        	namespaces += (j>0 ? SPACE : EMPTY) + NAMESPACE_ASSIGNMENT_TEMPLATE.replace(PAR_1, (isNotSpecified(namespacePrefix) ? EMPTY : PREFIX_SEPARATOR + namespacePrefix)).replace(PAR_2, namespaceUri);
			        }
			        String tagName = lastReadTag = (isNotSpecified(tagPrefix) ? EMPTY : tagPrefix + PREFIX_SEPARATOR) + tagLocalName + (namespaces == EMPTY ? EMPTY : SPACE + namespaces);
			        
			        // attributes
			        String attributes = EMPTY;
			        for (int j=0; j<streamReader.getAttributeCount(); j++) {
			        	String attributePrefix = streamReader.getAttributePrefix(j);
			        	String attributeLocalName = streamReader.getAttributeLocalName(j);
			        	
			        	String attributeName = (isNotSpecified(attributePrefix) ? EMPTY : attributePrefix + PREFIX_SEPARATOR) + attributeLocalName;
			        	String attributeValue = streamReader.getAttributeValue(j);
			        	
			        	attributes += (j>0 ? SPACE : EMPTY) + ATTRIBUTE_ASSIGNMENT_TEMPLATE.replace(PAR_1, attributeName).replace(PAR_2, attributeValue);
			        }
			        
					outWriter.writeString(OPENING_TAG_START + tagName + (attributes == EMPTY ? EMPTY : SPACE + attributes) + TAG_END);			
					break;
				case XMLStreamReader.CHARACTERS:
					int start = streamReader.getTextStart();
			        int length = streamReader.getTextLength();
					String text = new String (streamReader.getTextCharacters(), start, length);
					if (!text.trim().isEmpty()) /* consider non formatting data only */ outWriter.writeString(text);
					break;
				case XMLStreamReader.CDATA:	
					start = streamReader.getTextStart();
					length = streamReader.getTextLength();
					String cdataText = new String (streamReader.getTextCharacters(), start, length);
					if (cdataText.trim().startsWith("<?xml") /* we assume inner XML is starting with XML declaration */) {
						// CDATA section contains XML data, so format it too 
						outWriter.writeString(CDATA_TAG_START);
						outWriter.incTreeLevel();
						XmlFormatter innerXmlFormatter = new XmlFormatter(this.indentation, outWriter.treeLevel);
						innerXmlFormatter.format(new StringReader(cdataText), out);
						outWriter.decTreeLevel();
						outWriter.writeString(CDATA_TAG_END);
						// Add a new line for more readability
						outWriter.writeNewLine();
					} else {
						outWriter.writeString(CDATA_TAG_START + cdataText + CDATA_TAG_END);
					}
					break;
				case XMLStreamReader.END_ELEMENT:
					outWriter.decTreeLevel();
					
					tagPrefix = streamReader.getPrefix();
			        tagLocalName = streamReader.getLocalName();
			        tagName = (isNotSpecified(tagPrefix) ? EMPTY : tagPrefix + PREFIX_SEPARATOR) + tagLocalName;
					
			        if (!lastReadTag.equals(tagName)) /* container tag */ outWriter.writeNewLine();
			        outWriter.writeString(CLOSING_TAG_START + tagName + TAG_END);
			        
			        lastReadTag = EMPTY;
					break;
				case XMLStreamReader.COMMENT:
					if (streamReader.hasText()) {
						// only output comments with text
						if (isFirstLine) isFirstLine = false; else outWriter.writeNewLine();
						outWriter.writeString(COMMENT_TAG_START + streamReader.getText() + COMMENT_TAG_END);
					}
					break;
				}
				streamReader.next();
			}
		}
		finally {
			try {streamReader.close();} catch (Exception e) {/* Hide this exception, intentionally: it doesn't matter if we cannot close the XML stream reader, really. It rather does matter if something else has gone wrong before */}
		}
	}
	
	/**
	 * <p>Returns true if a string is either null or empty.</p>
	 * 
	 * @param string string to be checked
	 * @return true if string is null or empty
	 */
	private static boolean isNotSpecified(final String string) {
		return string == null || EMPTY.equals(string);
	}
	
	/**
	 * <p>Internal facility for managing output writing.</p> 
	 */
	private static final class OutputWriter {
		private static final String NEW_LINE = System.getProperty("line.separator");
		
		private final Writer out;
		private final char[] indentSpaces;
		private int treeLevel;
		
		private OutputWriter(final Writer out, final int indentation, final int treeLevel) {
			this.out = out;
			this.indentSpaces = new char[indentation]; 
			Arrays.fill(indentSpaces, ' ');
			this.treeLevel = treeLevel;
		}
		
		private void incTreeLevel() {
			treeLevel++;
		}
		
		private void decTreeLevel() {
			treeLevel--;
		}
		
		private void writeNewLine() throws IOException {
			out.write(NEW_LINE);
			for (int i=0; i<treeLevel; i++) {
				out.write(indentSpaces);
			}
		}
		
		private void writeString(final String string) throws IOException {
			out.write(string);
		}
	}
	

}