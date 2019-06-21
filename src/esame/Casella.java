package esame;

import java.util.ArrayList;
import java.util.Scanner;

public class Casella {
	
	/*Ogni casella presenta in ordine
	   - il suo id
	   - successivi ovvero la lista degli id a cui è collegata quella casella
	   - l'eventuale danno da applicare
	   - i messaggi collegati ad ogni associazione
	   - la descrizione di quella casella
	   - il tipo (empty, end, branch)
	 * */
	private int id;
	private ArrayList<Integer> successivi;
	private ArrayList<Integer> danni;
	private ArrayList<String>messaggi;
	private String descrizione;
	private String tipo;
	
	public Casella(int id,String descrizione,String tipo,ArrayList<Integer> successivi,ArrayList<Integer> danni,ArrayList<String>messaggi) {
		this.id = id;
		this.successivi = successivi;
		this.danni = danni;
		this.messaggi = messaggi;
		this.descrizione = descrizione;
		this.tipo = tipo;
	}
	public int getNumSucc() {
		return successivi.size();
	}
	public String getTipo() {
		return tipo;
	}
	public int getEffetto(int scelta) {
		return danni.get(scelta);
	}
	
	public String getDesc() {
		return descrizione;
	}
	public ArrayList<Integer> getDanni() {
		return danni;
	}
	public ArrayList<Integer> getSucc() {
		return successivi;
	}
	public int getId() {
		return id;
	}
	public ArrayList<String> getMessaggi(){
		return messaggi;
	}
}
