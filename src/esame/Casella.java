package esame;

import java.util.ArrayList;
import java.util.Scanner;

public class Casella {
	private int id;
	private ArrayList<Casella> successivi;
	private ArrayList<Integer> danno;
	private String descrizione;
	private String tipo;
	
	public Casella(int id,String descrizione,String tipo,int dannoA,int dannoB) {
		this.id = id;
		this.successivi = new ArrayList<Casella>();
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
		return danno.get(scelta);
	}
	
	public String getDesc() {
		return descrizione;
	}
	
	public ArrayList<Casella> getSucc() {
		return successivi;
	}
	public int getId() {
		return id;
	}
}
