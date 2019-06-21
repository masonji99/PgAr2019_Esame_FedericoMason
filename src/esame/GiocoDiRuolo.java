package esame;

public class GiocoDiRuolo {

	public static void main(String[] args) {
		Personaggio personaggio = new Personaggio("Federico");
		Mappa mappa = new Mappa(personaggio);
		mappa.start();
		
	}

}
