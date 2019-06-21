package esame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


import parserXML.LetturaXML;


public class GiocoDiRuolo {
	static Mappa mappa;
	final static String[]listaMappe = {"file.xml","fileA.xml","fileB.xml","fileC.xml","base.xml"};
	public static void main(String[] args) {
		System.out.println("Ecco tutti i file dispondibili: ");
		
		System.out.println("---------------------");
		String[] memo = listaMappe.clone();
		Arrays.sort(listaMappe);
		for(int i = 0;i<listaMappe.length;i++) {
			System.out.println(i +")"+ listaMappe[i]);
		}
		System.out.println("---------------------");
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Casella>c = new ArrayList<Casella>();
		LetturaXML lettura = null;
		int x = -1;
		do {
			try {
				int length = listaMappe.length-1;
				System.out.println("Selezionane il numero associato alla mappa: (0-"+length+")");
				x = Integer.parseInt(sc.nextLine());
				String theMap = listaMappe[x];
				 lettura = new LetturaXML(theMap);
			}catch( NumberFormatException e) {
				
				System.out.println("Devi inserire un numero intero ");
			}
			catch(Exception e) {
				System.out.println(e);
				x = -1;
			}
		}while(x<0 || x>= listaMappe.length);
		
		
		try {
			
			
			
			c = lettura.leggiXML();
		}catch(Exception e ) {
			System.out.println("Prova");
		}
		Personaggio personaggio = new Personaggio("Federico");
		mappa = new Mappa(personaggio,c);
		start(mappa,personaggio);
		
		System.out.println("Il gioco è terminato");
	}
	public void creaPercorso() {
		
	}
	public static void start(Mappa mappa,Personaggio p) {
		Casella attuale = mappa.getAttuale();
		mappa.start();
		while(attuale.getNumSucc()!=0) {
			int scelta = 0;
			System.out.println(attuale.getDesc());
			if(attuale.getTipo().equals("end")) {
				System.out.println("HAI VINTO!!!, sei rimasto con una vita di "+p.getVita());
				break;
			}else if (attuale.getTipo().equals("branch") || attuale.getTipo().equals("effect")) {
				System.out.println("Ora devi affrontare una scelta. Seleziona in quale area andare: ");
				for(Integer c: attuale.getSucc()) {
					System.out.println("- Area numero: "+c);
				}
				Scanner sc = new Scanner(System.in);
				scelta = attuale.getSucc().size();
				do {
					System.out.print("Inserisci il numero corrispondente alla strada da percorrere ");
					try {
						scelta = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Devi inserire un numero intero");
					}
				}while(!accettabile(attuale,scelta));
				attuale = mappa.getPercorso().get(scelta);
			}
			if(checkDanni(attuale,scelta)) {
				p.modificaVita(attuale.getDanni().get(scelta));
			}
			if(attuale.getTipo().equals("branch")) {
				attuale = mappa.getPercorso().get(scelta);
			}else if(attuale.getTipo().equals("empty")){
				attuale = mappa.getPercorso().get(attuale.getSucc().get(0));
			}
		}
	}
	public static boolean accettabile(Casella attuale,int val) {
		for(Integer x:attuale.getSucc()) {
			if(x==val) return true;
		}
		return false;
	}
	
	public static boolean checkDanni(Casella prossima,int scelta ) {
		if(mappa.getPercorso().get(scelta).getTipo().equals("effect")) {
			return true;
		}return false;
	}
	
	public void ricerca(){
		System.out.print("Inserisci una parte del nome del file da cercare: \n  ");
		Scanner sc = new Scanner(System.in);
		String sub = sc.nextLine();
		for(int i = 0;i<listaMappe.length;i++) {
			if(listaMappe[i].toLowerCase().contains(sub.toLowerCase())) {
				System.out.println(listaMappe[i]);
			}
		}
	}
}
