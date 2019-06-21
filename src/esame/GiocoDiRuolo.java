package esame;

import java.util.Scanner;

public class GiocoDiRuolo {

	public static void main(String[] args) {
		Personaggio personaggio = new Personaggio("Federico");
		Mappa mappa = new Mappa(personaggio);
		start(mappa);
		
	}
	
	public static void start(Mappa mappa) {
		Casella attuale = mappa.getAttuale();
		mappa.start();
		while(attuale.getNumSucc()!=0) {
			System.out.println("Sei finito su una casella di tipo: "+ attuale.getTipo());
			System.out.println("Ecco l'effetto di questa casella: \n"+ attuale.getDesc());
			if(attuale.getTipo().equals("Effetto")) {
				System.out.println("Sei arrivato ad un bivio.\nScegli una delle due strade (a,b): ");
				Scanner sc = new Scanner(System.in);
				
			}
		}
	}
	
}
