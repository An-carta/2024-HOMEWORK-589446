package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Stanza;



import java.io.Console;
import java.util.Scanner;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole io;
	
	public DiaDia(IOConsole io) {
		this.partita = new Partita();			// costruttore che inizializza oggetto partita
		this.io = io;
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		// legge input istruzione
				
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);			// inizializza oggetto comando con l'istruzione ricevuta

		if (comandoDaEseguire.getNome().equals("fine")) {				// comando di fine partita
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))			// comando vai
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))			// comando aiuto
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		}
		else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		}
		else
			this.io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {										// vittoria
			this.io.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.io.mostraMessaggio(elencoComandi[i]+" ");			// stampa elenco comandi
		this.io.mostraMessaggio();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)																	// direzione mancante
			this.io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;														// dichiara oggetto stanza per la prossima stanza
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);	// inizializza prossima stanza seguendo la direzione
		if (prossimaStanza == null)															// direzione inesistente
			this.io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);									// aggiorna stanza corrente
			int cfu = this.partita.getCfu();												// inizializza cfu
			this.partita.getGiocatore().setCfu(cfu--);														// aggiorna cfu
		}
		this.io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());					// stampa descrizione stanza corrente
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);		// inizializza oggetto diadia
		
		gioco.gioca();				// invoco metodo gioca
	}
	
	public void prendi(String nomeAttrezzo) {
		Stanza stanzaCorrente = this.partita.getLabirinto().getStanzaCorrente();
		Borsa borsaCorrente = this.partita.getGiocatore().getBorsa();
		
		if (stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzo = null;
			attrezzo = stanzaCorrente.getAttrezzo(nomeAttrezzo);
			stanzaCorrente.removeAttrezzo(attrezzo);
			borsaCorrente.addAttrezzo(attrezzo);
		}
	}
	
	public void posa(String nomeAttrezzo) {
		Stanza stanzaCorrente = this.partita.getLabirinto().getStanzaCorrente();
		Borsa borsaCorrente = this.partita.getGiocatore().getBorsa();
		
		if (borsaCorrente.hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzo = null;
			attrezzo = borsaCorrente.getAttrezzo(nomeAttrezzo);
			stanzaCorrente.addAttrezzo(attrezzo);
			borsaCorrente.removeAttrezzo(nomeAttrezzo);
		}
	}
}