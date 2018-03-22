package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Main.Commande;
import Main.Facture;
import Main.Plat;

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
		
		assertEquals(true, clientCorrect);
		// fin test pour true
		
		
		// test pour false
		Facture.tabClients[0] = "allo5aulo";
		
		for (int i = 0; i < Facture.tabClients.length; i++) {
			if (client == Facture.tabClients[i]){
				clientCorrect = true;
			} else {
				clientCorrect = false;
			}
		}
		
		assertEquals(false, clientCorrect);
	}
	
	//Agustin
			@Test
				public void verifierPlatTest() {
					Plat plat = new Plat(12,"platTest22");
					String nomTest= "platTest22";
					plat.setNom(nomTest);
					Plat plat1 = new Plat(12,"platTEst");
					Plat plat2 = new Plat(12,"platTest22");
					
					plat2.setNom("platTest22");
				
					Facture.tabPlats[0] = plat1;
					Facture.tabPlats[1] = plat2;
					boolean nomPlat = false;
					Plat test = new Plat(12,"platTest 22");
					
					for (int i = 0; i < 2 ; i++) {

						if ( plat.getNom() == Facture.tabPlats[i].getNom() ){
							nomPlat = true;	
							test.setNom("platTest22");
							
						}
						
						if(nomPlat == true){
							String[]tabPlat = plat.getNom().split(" ");
							if (tabPlat.length > 2){
								System.out.println("Probleme avec espace");//pour test
								nomPlat = false;
							}
							
						}
						
					}
					
					assertEquals(Facture.tabPlats[1].getNom(), test.getNom());
				
				
				}
			
			
			//Agustin
			@Test
				public void verifierPlatTestFalse() {
					Plat plat = new Plat(12,"platTest22");
					String nomTest= "platTest22";
					plat.setNom(nomTest);
					Plat plat1 = new Plat(12,"platT Est");
					Plat plat2 = new Plat(12,"platTest2 2");
					
					
				
					Facture.tabPlats[0] = plat1;
					Facture.tabPlats[1] = plat2;
					boolean nomPlat = false;
					Plat test = new Plat(12,"platTest 22");
					
					for (int i = 0; i < 2 ; i++) {

						if ( plat.getNom() == Facture.tabPlats[i].getNom() ){
							nomPlat = true;	
							test.setNom("platTest22");
							
						}
						
						if(nomPlat == true){
							String[]tabPlat = plat.getNom().split(" ");
							if (tabPlat.length > 2){
								System.out.println("Probleme avec espace");//pour test
								nomPlat = false;
							}
							
						}
						
					}
					
					assertEquals(false, nomPlat);
				
				
				}
	
		//Agustin
				@Test
				public void verifierCommandeTest() {
					Commande com = new Commande("Roger","Poutine",2);
					int qte = 2;
					String nom = "Roger";
					
					assertEquals(nom,com.getNomClient());
					assertEquals(qte,com.getQte());
				}
	
	

	
	//William
	@SuppressWarnings("deprecation")
	@Test
	public void testLignesFactureAvecTotalDe0() {
		
		String fichierTest = ".\\testFacture0.txt";
		
		Facture facture1 = new Facture();
		
		facture1.gererCommandes(fichierTest);
		
		facture1.lignesFacture();
		
		String[] tabLignesExpected = new String[20];
		tabLignesExpected[0] = "Céline 18.07$";
		
		assertEquals(tabLignesExpected, facture1.getLignesFactures());
		
		
	}
	
	//William
	@SuppressWarnings("deprecation")
	@Test
	public void testLignesFactureFicSansErreur() {
		
		String fichierTest = ".\\testFactureSansErreur.txt";
		
		Facture facture2 = new Facture();
		
		facture2.gererCommandes(fichierTest);
		
		facture2.lignesFacture();
		
		String[] tabLignesExpected = new String[20];
		tabLignesExpected[0] = "Céline 18.07$";
		tabLignesExpected[1] = "Steeve 5.74$";
		tabLignesExpected[2] = "William 14.92$";
		

		assertEquals(tabLignesExpected, facture2.getLignesFactures());
		
		
	}

	
	//William
	@SuppressWarnings("deprecation")
	@Test
	public void testLignesFactureQteInvalide() {
		
		String fichierTest = ".\\testErreurQte.txt";
		
		Facture facture5 = new Facture();
		
		facture5.gererCommandes(fichierTest);
		
		facture5.lignesFacture();
		
		
		String[] tabLignesExpected = new String[20];
		tabLignesExpected[0] = "Erreur quantité invalide : Steeve Frites -1";
		tabLignesExpected[1] = "Céline 23.81$";
		tabLignesExpected[2] = "Steeve 2.87$";
		
		assertEquals(tabLignesExpected, facture5.getLignesFactures());
		
		
	}
	
}
