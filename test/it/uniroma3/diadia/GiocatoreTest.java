package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import it.uniroma3.diadia.giocatore.Giocatore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class GiocatoreTest {
	
	private Giocatore giocatore;
	
	@BeforeEach
	public void setUp() {
		this.giocatore = new Giocatore();
	}
	
	@Test
	public void testCfuNonFinitiInizioPartita() {
		assertNotEquals(0,this.giocatore.getCfu());
	}
	
	@Test
	public void testCfuIniziali() {
		assertEquals(Giocatore.CFU_INIZIALI,this.giocatore.getCfu());
	}
}
