package esame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import parserXML.LetturaXML;


public class GiocoDiRuolo {
	private static Mappa mappa;
	private final static String[]listaMappe = {"file.xml","base.xml"}; //lista da fare a mano dei file presenti nella cartella apposita
	public static void main(String[] args) {
		System.out.println("Ecco tutti i file dispondibili: \nNon tutti esistono solo i primi 2, solo per provare (Daniele ha confermato che va bene)");
		//visualizzazione dei vari file che si presume siano nella cartella, ordinati alfabeticamente (SPECIFICA 1,3 DEL MODULO A1)
		System.out.println("---------------------");
		Arrays.sort(listaMappe);
		for(int i = 0;i<listaMappe.length;i++) {
			System.out.println(i +")"+ listaMappe[i]);
		}
		System.out.println("---------------------");
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Casella>c = new ArrayList<Casella>(); //oggetto per la memorizzazione dei dati da XML
		LetturaXML lettura = null;
		int x = -1;
			//ricerca del file (SPECIFICA 2 DEL MODULO A1)
			System.out.print("vuoi cercare il file? (si per confermare) ");
			String risposta = sc.nextLine().toLowerCase().trim();
			if(risposta.equals("si")|| risposta.equals("sì")) {
				ricerca();
			}
		
		do {
			//selezione dell'utente (SPECIFICA 3 DEL MODULO A1) 
			try {
				int length = listaMappe.length-1;
				System.out.print("Selezionane il numero associato alla mappa: (0-"+length+") ");
				x = Integer.parseInt(sc.nextLine());
				String theMap = listaMappe[x];
				 lettura = new LetturaXML(theMap); //se non da errore allora il file esiste e lo carica
			}catch( NumberFormatException e) {
				//nel caso in cui metta lettere o numeri decimali
				System.out.println("Devi inserire un numero intero ");
			}
			catch(Exception e) {
				
				//in tutti gli altri casi (numeri maggiori di quelli validi o altre cose non gestite dal primo
				//catch) 
				System.out.println("Devi inserire un numero valido");
				x = -1;
			}
		}while(x<0 || x>= listaMappe.length); //fintanto che non è un numero valido 
		
		
		try {
			c = lettura.leggiXML(); //salva i dati letti dal XML in una variabile
		}catch(Exception e ) {
		}
		//creazione del personaggio e della mappa
		Personaggio personaggio = new Personaggio("Federico");
		mappa = new Mappa(personaggio,c);
		start(mappa,personaggio);
		
		System.out.println("Il gioco è terminato");
	}
	//metodo principale che esegue tutta la visualizzazione
	public static void start(Mappa mappa,Personaggio p) {
		//setta la casella attuale(dov'è il giocatore) a quella di partenza
		Casella attuale = mappa.getAttuale();
		mappa.start(); //solo una visualizzazione grafica 
		while(true) { //ciclo infinito perché termina con un break sia nel caso muoia che se arriva alla fine
			int scelta = 0;
			if(attuale.getTipo().equals("end")) { //se è arrivato alla fine ha vinto
				System.out.println("HAI VINTO!!!, sei rimasto con una vita di "+p.getVita());
				break;//esce dal ciclo infinito
			}else if (attuale.getTipo().equals("branch") || attuale.getTipo().equals("effect")) {
				//se è un branch o un effect avrà una scelta da fare 
				System.out.println("Ora devi affrontare una scelta. Seleziona in quale area andare: ");
				for(Integer c: attuale.getSucc()) {
					System.out.println("- Area numero: "+c);
				}
				Scanner sc = new Scanner(System.in);
				scelta = attuale.getSucc().size();
				do {
					System.out.print("Inserisci il numero corrispondente alla strada da percorrere ");
					//visualizza l'id delle caselle collegate e chiede all'utente di sceglierne una
					//verificandone la validità
					try {
						scelta = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Devi inserire un numero intero");
					}
				}while(!accettabile(attuale,scelta));
				
			}
			if(checkDanni(scelta)) {//se è un effect allora dovrà applicare i danni e visualizza la vita rimanente
				for(int i = 0;i<attuale.getSucc().size();i++) {
					if(attuale.getSucc().get(i)==scelta) {
						p.modificaVita(attuale.getDanni().get(i));
						System.out.println("-------------------------------------------------");
						String vita = "Ti sono rimasti "+p.getVita()+ " punti vita";
						System.out.println(vita.toUpperCase());
						System.out.println("-------------------------------------------------");
						System.out.println(attuale.getTipo());
					}
				}
				
			}
			//una volta calcolati eventuali danni aggiorna la nuova posizione del giocatore (attuale)
			if(attuale.getTipo().equals("branch")||attuale.getTipo().equals("effect")) {
				attuale = mappa.getPercorso().get(scelta);
			}else if(attuale.getTipo().equals("empty")){
				attuale = mappa.getPercorso().get(attuale.getSucc().get(0));
			}
		}
	}
	//metodo per capire se ha scelto la strada corretta ad un bivio (se deve scegliere tra 1 e 2 e mette 8 => return false
	public static boolean accettabile(Casella attuale,int val) {
		for(Integer x:attuale.getSucc()) {
			if(x==val) return true;
		}
		return false;
	}
	//calcola i danni da applicare dopo un effect
	public static boolean checkDanni(int scelta ) {
		System.out.println(scelta);
		if(mappa.getPercorso().get(scelta).getTipo().equals("effect")) {
			return true;
		}return false;
	}
	//metodo per cercare se esiste un file (ricerca dell'utente SPECIFICA 2 MODULO A1) 
	public static void ricerca(){
		System.out.print("Inserisci una parte del nome del file da cercare: \n  ");
		Scanner sc = new Scanner(System.in);
		String sub = sc.nextLine();
		boolean x = false;
		for(int i = 0;i<listaMappe.length;i++) {
			if(listaMappe[i].toLowerCase().contains(sub.toLowerCase())) {
				System.out.println(i+")"+listaMappe[i]);
				x = true;
			}
		}
		if(!x) System.out.println("Non esiste nessun file con quella parte di nome");
	}
}
