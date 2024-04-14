package it.uniroma3.diadia.giocatore;

/*
 *  Giocatore ha la responsabilità di gestire i CFU del giocatore e
 di memorizzare gli attrezzi in un oggetto istanza della classe
Borsa >>(vedi codice a seguire) 
*/

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public Borsa getBorsa() {
		return borsa;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
}
