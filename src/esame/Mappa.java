package esame;

public class Mappa {
	private final static int INIZIALE = 0;
	int numCaselle;
	Casella iniziale;
	Casella attuale;
	Personaggio personaggio;
	
	public Mappa(Personaggio personaggio) {
		iniziale = new Casella(INIZIALE);
		attuale = new Casella(INIZIALE);
		numCaselle = INIZIALE;
		this.personaggio = personaggio;
	}
	public void start() {
		
	}
	
}
