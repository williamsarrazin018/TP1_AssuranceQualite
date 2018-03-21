package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Main.Facture;

public class Tests {

	//Agustin
	@Test
	public void verifierClientTest() {
		Facture.tabClients[0] = "allo5 aulo";
		String client = "allo5 aulo";
		
		boolean clientCorrect = false;
		
		
		for (int i = 0; i < Facture.tabClients.length; i++) {
			if (client == Facture.tabClients[i]){
				clientCorrect = true;
			}
		}
		if(clientCorrect){
			System.out.println("Nom existant");
			String[]nom = client.split(" ");
			if (nom.length > 1){
				System.out.println("Probleme avec espace");
			}
		} else {
			System.out.println("Nom inexistant");
		}
	}
	
//	//Agustin
//		@Test
//		public void verifierPlatTest() {
//			String
//			boolean nomPlat =true;
//		}
	
	//William
	@SuppressWarnings("deprecation")
	@Test
	public void testLignesFactureAvecTotalDe0() {
		
		String fichierTest = ".\\testFacture0.txt";
		
		Facture facture = new Facture();
		
		facture.gererCommandes(fichierTest);
		
		facture.lignesFacture();
		
		String[] tabLignesExpected = new String[20];
		tabLignesExpected[0] = "C�line 15.75$";
		
		assertEquals(tabLignesExpected, facture.getLignesFactures());
		
		
	}
	
	//William
	@SuppressWarnings("deprecation")
	@Test
	public void testLignesFactureFicSansErreur() {
		
		String fichierTest = ".\\testFactureSansErreur.txt";
		
		Facture facture = new Facture();
		
		facture.gererCommandes(fichierTest);
		
		facture.lignesFacture();
		
		String[] tabLignesExpected = new String[20];
		tabLignesExpected[0] = "C�line 15.75$";
		tabLignesExpected[1] = "Steeve 5.0$";
		tabLignesExpected[2] = "William 13.0$";
		

		assertEquals(tabLignesExpected, facture.getLignesFactures());
		
		
	}
	
	//William
	@SuppressWarnings("deprecation")
	@Test
	public void testLignesFacturesErreurPlat() {
		
		String fichierTest = ".\\testErreurPlat.txt";
		
		Facture facture = new Facture();
		
		facture.gererCommandes(fichierTest);
		
		facture.lignesFacture();
		
		
		String[] tabLignesExpected = new String[20];
		tabLignesExpected[0] = "Erreur plat introuv� : C�line Repas_Pouletxxxxx 1";
		tabLignesExpected[2] = "C�line 5.0$";
		
		assertEquals(tabLignesExpected, facture.getLignesFactures());
		
		
	}
	
	//William
	@SuppressWarnings("deprecation")
	@Test
	public void testLignesFactureAvecErreurFormatNom() {
		
		String fichierTest = ".\\testErreurFormat.txt";
		
		Facture facture = new Facture();
		
		facture.gererCommandes(fichierTest);
		
		facture.lignesFacture();
		
		
		String[] tabLignesExpected = new String[20];
		tabLignesExpected[0] = "Erreur de format : C�line t Frites 2";
		tabLignesExpected[1] = "Erreur de format : C�line t Repas_Poulet 1";
		tabLignesExpected[2] = "Roger 10.5$";
		
		assertEquals(tabLignesExpected, facture.getLignesFactures());
		
		
	}
	
	//William
	@SuppressWarnings("deprecation")
	@Test
	public void testLignesFactureQteInvalide() {
		
		String fichierTest = ".\\testErreurQte.txt";
		
		Facture facture = new Facture();
		
		facture.gererCommandes(fichierTest);
		
		facture.lignesFacture();
		
		
		String[] tabLignesExpected = new String[20];
		tabLignesExpected[0] = "Erreur quantit� invalide : Steeve Frites -1";
		tabLignesExpected[1] = "C�line 20.75$";
		tabLignesExpected[2] = "Steeve 2.5$";
		
		assertEquals(tabLignesExpected, facture.getLignesFactures());
		
		
	}
	
}
