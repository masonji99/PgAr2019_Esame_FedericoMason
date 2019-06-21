package esame;

import java.util.ArrayList;
import java.util.Scanner;

public class Casella {
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
