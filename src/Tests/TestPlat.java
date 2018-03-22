package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Main.Facture;

public class TestPlat {

	//William
	@SuppressWarnings("deprecation")
	@Test
	public void testLignesFacturesErreurPlat() {
		
		String fichierTest = ".\\testErreurPlat.txt";
		
		Facture facture3 = new Facture();
		
		facture3.gererCommandes(fichierTest);
		
		facture3.lignesFacture();
		
		
		String[] tabLignesExpected = new String[20];
		tabLignesExpected[0] = "Erreur plat introuv� : C�line Repas_Pouletxxxxx 1";
		tabLignesExpected[1] = "C�line 5.74$";
		
		assertEquals(tabLignesExpected, facture3.getLignesFactures());
		
		
	}

}
