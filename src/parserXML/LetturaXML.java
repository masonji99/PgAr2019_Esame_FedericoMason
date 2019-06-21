package parserXML;
import esame.Casella;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class LetturaXML {
	XMLInputFactory xmlif = null;
	XMLStreamReader xmlr = null;
	String nomeFile = "";
	public LetturaXML(String nomeFile) throws FileNotFoundException, XMLStreamException {
			this.nomeFile ="fileXML/".concat(nomeFile).trim();
			System.out.println(this.nomeFile);
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(this.nomeFile,new FileInputStream(this.nomeFile));
	}
	
	public XMLInputFactory getXmlif() {
		return xmlif;
	}
	
	public XMLStreamReader getXmlr() {
		return xmlr;
	}
	
	public ArrayList<Casella> leggiXML() throws XMLStreamException {
		ArrayList<Casella> caselle = new ArrayList<Casella>();
		int id = 0;
		String tipo = "",descrizione = "";
		ArrayList<Integer> successivi = new ArrayList<Integer>();
		ArrayList<Integer> danni = new ArrayList<Integer>();
		ArrayList<String> messaggi = new ArrayList<String>();
		while(xmlr.hasNext()) {
			switch  (xmlr.getEventType()) {
				case XMLStreamConstants.START_ELEMENT	:  // inizio di un elemento: stampa il nome del tag e i suoi attributi
					if(xmlr.getLocalName().equals("cell")) {
						successivi = new ArrayList<Integer>();
						danni = new ArrayList<Integer>();
						for(int i=0;i<xmlr.getAttributeCount();i++) {
							if(xmlr.getAttributeLocalName(i).equals("id")) id = Integer.parseInt(xmlr.getAttributeValue(i));
							else if(xmlr.getAttributeLocalName(i).equals("type")) tipo = xmlr.getAttributeValue(i);
						}
					}else if(xmlr.getLocalName().equals("description")) {
						if(xmlr.hasNext())xmlr.next();
						descrizione = xmlr.getText().trim();
					}else if(xmlr.getLocalName().equals("option")) {
						for(int i=0;i<xmlr.getAttributeCount();i++) {
							if(xmlr.getAttributeLocalName(i).equals("destination")) successivi.add(Integer.parseInt(xmlr.getAttributeValue(i)));
							else if(xmlr.getAttributeLocalName(i).equals("lifepoints")) danni.add(Integer.parseInt(xmlr.getAttributeValue(i)));
						}
						if(xmlr.hasNext())xmlr.next();
						messaggi.add(xmlr.getText().trim());
					}
				break ;
				case XMLStreamConstants.END_ELEMENT:
					if(xmlr.getLocalName().equals("cell")) {
						caselle.add(new Casella(id,descrizione,tipo,successivi,danni,messaggi));
					}
				break;
				
			}
			xmlr.next();
		}
		return caselle;
	}
}
