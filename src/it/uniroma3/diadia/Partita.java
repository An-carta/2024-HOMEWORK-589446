package it.uniroma3.diadia;
import Stanza;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	//static final private int CFU_INIZIALI = 20;

	private boolean finita;
	//private int cfu;
	private Labirinto labirinto;
	private Giocatore giocatore;
	
	public Partita(){
		this.labirinto = new Labirinto();			// costruttore che oltre a inizializzare le variabili di istanza invoca il metodo crea stanze
		this.finita = false;
		//this.cfu = CFU_INIZIALI;
		this.giocatore = new Giocatore();
	}
    
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}


	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.labirinto.getStanzaCorrente()== this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu == 0);
	}
	
	
	// getter e setter per partita finita
	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.giocatore.getCfu();
	}


	public Giocatore getGiocatore() {
		return giocatore;
	}


	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}
	
	

	/*
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	*/	
}
