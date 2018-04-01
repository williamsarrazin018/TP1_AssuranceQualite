package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Main.Facture;

public class TestFormat {

	//William
	@SuppressWarnings("deprecation")
	@Test
	public void testLignesFactureAvecErreurFormat() {
		
		
		String fichierTest = ".\\testErreurFormat.txt";
		
		Facture facture4 = new Facture();
		
		facture4.gererCommandes(fichierTest);
		
		facture4.lignesFacture();
		
		String[] tabLignesExpected = new String[20];
		tabLignesExpected[0] = "Erreur de format : Roger Poutine 1";
		tabLignesExpected[1] = "Erreur de format : Céline t Frites 2";
		tabLignesExpected[2] = "Erreur de format : Céline Repas_Poulet 1";
	
		assertEquals(tabLignesExpected, facture4.getLignesFactures());
		
		
	}

	
	
}
