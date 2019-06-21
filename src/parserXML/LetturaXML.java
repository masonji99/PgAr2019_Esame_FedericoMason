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
	private XMLInputFactory xmlif = null;
	private XMLStreamReader xmlr = null; //stream per la lettura del file XML
	private String nomeFile = "";
	
	public LetturaXML(String nomeFile) throws FileNotFoundException, XMLStreamException {
			this.nomeFile ="fileXML/".concat(nomeFile).trim(); /*concatena il nome della cartella dove dovrebbero
			essere i file XML e il nome dato dall'utente ed effettua il caricamento da quel file*/
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
		ArrayList<Casella> caselle = new ArrayList<Casella>(); //prepara la lista di caselle che compongono
		//la mappa
		//---------------------------------
		//TUTTI QUESTI PARAMETRI SONO SPIEGATI NELLA CLASSE Casella
		int id = 0;
		String tipo = "",descrizione = "";
		ArrayList<Integer> successivi = new ArrayList<Integer>();
		ArrayList<Integer> danni = new ArrayList<Integer>();
		ArrayList<String> messaggi = new ArrayList<String>();
		
		//---------------------------------
		while(xmlr.hasNext()) {
			switch  (xmlr.getEventType()) {
				case XMLStreamConstants.START_ELEMENT	:  // inizio di un elemento: stampa il nome del tag e i suoi attributi
					if(xmlr.getLocalName().equals("cell")) { //se questo elemento è una caselle allora devo prepararmi
						//a memorizzare i dati,eliminando eventualmente quelli di altre caselle
						successivi = new ArrayList<Integer>();
						danni = new ArrayList<Integer>();
						for(int i=0;i<xmlr.getAttributeCount();i++) {
							
							//se trova un id lo memorizza nella variabile dedicata all'id di quella casella
							//mentre se è il tipo viene salvato nella variabile per il tipo della casella(end,empty ...)
							if(xmlr.getAttributeLocalName(i).equals("id")) id = Integer.parseInt(xmlr.getAttributeValue(i));
							else if(xmlr.getAttributeLocalName(i).equals("type")) tipo = xmlr.getAttributeValue(i);
						}
					}else if(xmlr.getLocalName().equals("description")) {
						//se è la descrizione allora salva tutto quello che segue ovvero la descrizione stessa
						if(xmlr.hasNext())xmlr.next();
						descrizione = xmlr.getText().trim();
					}else if(xmlr.getLocalName().equals("option")) {
						//altrimenti può essere un opzione
						for(int i=0;i<xmlr.getAttributeCount();i++) {
							//e quindi dovrà ricordare sia l'id della casella collegata, che la vita da aggiungere/rimuovere
							if(xmlr.getAttributeLocalName(i).equals("destination")) successivi.add(Integer.parseInt(xmlr.getAttributeValue(i)));
							else if(xmlr.getAttributeLocalName(i).equals("lifepoints")) danni.add(Integer.parseInt(xmlr.getAttributeValue(i)));
						}
						if(xmlr.hasNext())xmlr.next();
						messaggi.add(xmlr.getText().trim());
					}
				break ;
				case XMLStreamConstants.END_ELEMENT:
					//una volta arrivato alla fine della cella salva il nuovo oggetto con gli elementi appena raccolti
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
