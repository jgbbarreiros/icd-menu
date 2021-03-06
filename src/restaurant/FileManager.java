package restaurant;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class FileManager {
	
	private DocumentBuilder documentBuilder = null;
	private Document document = null;
	private Transformer transformer = null;
	private String currentDocName = null;
	
	public FileManager() {
		
		try {
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		} catch (javax.xml.parsers.ParserConfigurationException e) {
			System.err.println(e.getMessage());
		} catch (TransformerConfigurationException e) {
			System.err.println(e.getMessage());
		} catch (TransformerFactoryConfigurationError e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Document blank() {
		document = documentBuilder.newDocument();
		return this.document;
	}
	
	public boolean load(String docName) {
		try {
			File file = new File(docName + ".xml");
			document = documentBuilder.parse(file);
			return true;
		} catch (SAXException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean save() {
		if (currentDocName.isEmpty())
			return false;
		saveAs(currentDocName);
		return true;
	}
	
	public boolean saveAs(String docName) {
		try {
			// TODO document has root element ? go on : create and append root element  
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(docName + ".xml"));
			transformer.transform(source, result);
			return true;
		} catch (TransformerException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
}
