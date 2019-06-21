package esame;

public class Personaggio {
	private String nome;
	private int vita;
	
	
	public Personaggio(String nome) {
		this.nome = nome;
		this.vita = 100;
	}
	
	public void modificaVita(int vita) {
		this.vita -= vita;
	}
	
	public int getVita() {
		return vita;
	}
	
	public String getNome() {
		return nome;
	}
}
