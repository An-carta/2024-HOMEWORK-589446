package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class stanzaTest {

	private static final int MAX_ATTREZZI = 10;
	private static final String ATTREZZO = "AttrezzoDiTest";
	private static final String STANZA = "StanzaTest";
	private static final String STANZA_ADIACENTE = "StanzaAdiacente";
	private static final String NORD = "nord";

	protected Stanza stanza;

	@BeforeEach
	public void setUp() {
		this.stanza = new Stanza(STANZA);
	}

	@Test
	public void testImpostaStanzaAdiacenteSingola() {
		Stanza adiacente = creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, NORD);
		assertEquals(adiacente, this.stanza.getStanzaAdiacente(NORD));
	}

	@Test
	public void testCambiaStanzaAdiacente() {
		creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, NORD);
		Stanza nuovaAdiacente = creaStanzaEImpostaAdiacente(this.stanza, "nuovaAdiacente", NORD);
		assertEquals(nuovaAdiacente, this.stanza.getStanzaAdiacente(NORD));
	}

	@Test
	public void testImpostaMassimo4Stanze() {
		Stanza adiacente = new Stanza(STANZA_ADIACENTE);
		String[] direzioni = {"nord", "sud", "est", "ovest"};
		String nordEst = "nord-est";
		for(String direzione : direzioni) 
			this.stanza.impostaStanzaAdiacente(direzione, adiacente);
		Stanza stanzaDaNonInsere = new Stanza("stanzaDaNonInsere");
		this.stanza.impostaStanzaAdiacente(nordEst, stanzaDaNonInsere);
		assertNotContains(this.stanza.getDirezioni(), nordEst);
	}

	private void assertNotContains(String[] array, String ricercato) {
		boolean contiene = false;
		for(String elemento : array) {
			if(elemento != null && elemento.equals(ricercato))
				contiene = true;
		}
		assertFalse(contiene);
	}

	@Test
	public void testGetStanzaAdiacenteNonEsistente() {
		assertNull(this.stanza.getStanzaAdiacente(NORD));
	}

	@Test
	public void testGetStanzaAdiacenteEsistente() {
		Stanza adiacente = creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, NORD);
		assertEquals(this.stanza.getStanzaAdiacente(NORD),adiacente);
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneNonValida() {
		creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, NORD);
		assertNull(this.stanza.getStanzaAdiacente("nonValida"));
	}

	@Test
	public void testGetDirezioniVuoto() {
		assertArrayEquals(new String[0], this.stanza.getDirezioni());
	}

	@Test
	public void testGetDirezioniSingleton() {
		creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, NORD);
		String[] direzioni = new String[1];
		direzioni[0] = NORD;
		assertArrayEquals(direzioni, this.stanza.getDirezioni());
	}


	@Test
	public void testAddAttrezzoSingolo() {
		Attrezzo attrezzoSemplice = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(attrezzoSemplice);
		assertEquals(attrezzoSemplice, this.stanza.getAttrezzo(ATTREZZO));
	}

	@Test
	public void testAddAttrezziOltreMassimo() {
		for (int i = 0; i < MAX_ATTREZZI; i++) {
			Attrezzo attrezzoSemplice = new Attrezzo(ATTREZZO+i, 1);
			assertTrue(this.stanza.addAttrezzo(attrezzoSemplice));
		}
		Attrezzo attrezzoDiTroppo =  new Attrezzo(ATTREZZO+MAX_ATTREZZI, 1);
		assertFalse(this.stanza.addAttrezzo(attrezzoDiTroppo));
	}

	@Test
	public void testHasAttrezzoSingolo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.hasAttrezzo(ATTREZZO));
	}

	@Test
	public void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanza.hasAttrezzo(ATTREZZO));
	}

	@Test
	public void testHasAttrezzoInesistente() {
		assertFalse(this.stanza.hasAttrezzo("inesistente"));
	}

	private Stanza creaStanzaEImpostaAdiacente(Stanza stanzaDiPartenza, String nomeStanzaAdiacente, String direzione) {
		Stanza stanzaAdiacente = new Stanza(nomeStanzaAdiacente);
		stanzaDiPartenza.impostaStanzaAdiacente(direzione, stanzaAdiacente);
		return stanzaAdiacente;
	}
}