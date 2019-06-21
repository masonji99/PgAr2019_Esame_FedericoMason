package esame;

import java.util.ArrayList;
import java.util.Scanner;

public class Casella {
	int id;
	ArrayList<Casella> successivi;
	String descrizione;
	String tipo;
	int dannoA, dannoB;
	
	public Casella(int id,String descrizione,String tipo,int dannoA,int dannoB) {
		this.id = id;
		this.successivi = new ArrayList<Casella>();
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.dannoA = dannoA;
		this.dannoB = dannoB;
	}
	public int getNumSucc() {
		return successivi.size();
	}
	public String getTipo() {
		return tipo;
	}
	public int getEffetto() {
		
		getEffetto();
		return 0;
	}
	
	public String getDesc() {
		return descrizione;
	}
}
