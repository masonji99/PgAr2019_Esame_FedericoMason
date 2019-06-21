package esame;

public class Mappa {
	private final static int INIZIALE = 0;
	int numCaselle;
	Casella iniziale;
	Casella attuale;
	Personaggio personaggio;
	
	public Mappa(Personaggio personaggio) {
		iniziale = new Casella(INIZIALE,"ciao","danno");
		attuale = new Casella(INIZIALE,"ciao","danno");
		numCaselle = INIZIALE;
		this.personaggio = personaggio;
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
}
