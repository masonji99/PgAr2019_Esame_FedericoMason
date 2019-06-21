package esame;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.stream.XMLStreamException;

import parserXML.LetturaXML;


public class GiocoDiRuolo {

	public static void main(String[] args) {
		ArrayList<Casella>c = new ArrayList<Casella>();
		try {
			LetturaXML lettura = new LetturaXML();
			c = lettura.leggiXML();
			
		}catch(Exception e ) {
			e.getStackTrace();
		}
		Personaggio personaggio = new Personaggio("Federico");
		Mappa mappa = new Mappa(personaggio,c);
		start(mappa,personaggio);
		
		System.out.println("Il gioco è terminato");
	}
	public void creaPercorso() {
		
	}
	public static void start(Mappa mappa,Personaggio p) {
		Casella attuale = mappa.getAttuale();
		mappa.start();
		while(attuale.getNumSucc()!=0) {
			if(attuale.getTipo().equals("end")) {
				System.out.println("HAI VINTO!!!, sei rimasto con una vita di "+p.getVita());
				break;
			}else if(attuale.getTipo().equals("branch")) {
				System.out.println("Oh,devi affrontare una scelta. Seleziona in quale area andare: ");
				for(Integer c: attuale.getSucc()) {
					System.out.println("- Area numero: "+c+"\n");
				}
				Scanner sc = new Scanner(System.in);
				int scelta = attuale.getSucc().size();
				do {
					System.out.println("Inserisci il numero corrispondente alla strada da percorrere ");
					try {
						scelta = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Devi inserire un numero intero");
					}
				}while(scelta>=attuale.getSucc().size());
				int danno = attuale.getEffetto(scelta);
				if(danno != 0) {
					System.out.println("\n La tua scelta ha portato a ricevere un danno di "+ danno+"\n");
				}
				p.modificaVita(danno);
				
				if(p.getVita()<=0) {
					System.out.println("HAI PERSO. LA TUA VITA E' SCESA SOTTO LO 0");
					break;
				}				
			}else if(attuale.getTipo().equals("empty")){
				attuale = mappa.getPercorso().get(attuale.getSucc().get(0));
			}
		}
	}
}
