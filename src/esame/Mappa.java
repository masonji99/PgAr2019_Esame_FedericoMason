package esame;

import java.util.ArrayList;

public class Mappa {
	private final static int INIZIALE = 0;
	int numCaselle;
	Casella iniziale;
	Casella attuale;
	Personaggio personaggio;
	ArrayList<Casella> percorso;
	
	public Mappa(Personaggio personaggio,ArrayList<Casella> percorso) {
		this.personaggio = personaggio;
		this.percorso = percorso;
		Casella memo = percorso.get(0);
		iniziale = new Casella(INIZIALE,memo.getDesc(),memo.getTipo(),memo.getSucc(),memo.getDanni(),memo.getMessaggi());
		attuale = iniziale;
		numCaselle = INIZIALE;
	}
	public void start() {
		System.out.println("Benvenuto in questo gioco di ruolo.\nIl tuo obbiettivo"
				+"sarà quello di arrivare in fondo al percorso con più vita possible.\nINIZIAMO!");
	}
	public Casella getAttuale() {
		return attuale;
	}
	public void setNumCaselle() {
		numCaselle++;
	}
	public ArrayList<Casella> getPercorso(){
		return percorso;
	}
}
