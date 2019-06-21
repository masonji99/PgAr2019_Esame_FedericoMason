package parserXML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class LetturaXML {
	XMLInputFactory xmlif = null;
	XMLStreamReader xmlr = null;
	String nomeFile;
	public LetturaXML(String nomeFile) throws FileNotFoundException, XMLStreamException{
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(nomeFile,new FileInputStream(nomeFile));
			this.nomeFile = nomeFile;
	}
	
	public XMLInputFactory getXmlif() {
		return xmlif;
	}
	
	public XMLStreamReader getXmlr() {
		return xmlr;
	}
	
	public void lettura() {
		
	}
}
