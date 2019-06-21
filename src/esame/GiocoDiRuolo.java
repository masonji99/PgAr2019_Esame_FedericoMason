package esame;

import java.util.Scanner;


public class GiocoDiRuolo {

	public static void main(String[] args) {
		Personaggio personaggio = new Personaggio("Federico");
		Mappa mappa = new Mappa(personaggio);
		start(mappa,personaggio);
		
		System.out.println("Il gioco è terminato");
	}
	public void creaPercorso() {
		
	}
	public static void start(Mappa mappa,Personaggio p) {
		Casella attuale = mappa.getAttuale();
		mappa.start();
		while(attuale.getNumSucc()!=0) {
			System.out.println("Sei entrato in una casella "+ attuale.getTipo());
			System.out.println("Ecco l'effetto di questa casella \n"+ attuale.getDesc());
			if(attuale.getTipo().equals("fine")) {
				System.out.println("HAI VINTO!!!, sei rimasto con una vita di "+p.getVita());
				break;
			}else if(attuale.getTipo().equals("scelta")) {
				System.out.println("Oh,devi affrontare una scelta. Seleziona in quale area andare: ");
				for(Casella c: attuale.getSucc()) {
					System.out.println("- Area numero: "+c.getId()+"\n");
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
			}
		}
	}
}
