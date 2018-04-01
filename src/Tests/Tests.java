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
				public void verifierCommandeTest() {
					Commande com = new Commande("Roger","Poutine",2, 2);
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
		tabLignesExpected[0] = "Table 1";
		tabLignesExpected[1] = "C�line 15.75$";
		tabLignesExpected[2] = "Total : 18.07$";
		
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
		tabLignesExpected[0] = "Table 1";
		tabLignesExpected[1] = "C�line 15.75$";
		tabLignesExpected[2] = "William 13.0$";
		tabLignesExpected[3] = "Total : 37.3$";
		tabLignesExpected[4] = "Table 2";
		tabLignesExpected[5] = "Steeve 5.0$";
		tabLignesExpected[6] = "Total : 5.74$";
		

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
		tabLignesExpected[0] = "Erreur quantit� invalide : Steeve Frites -1 2";
		tabLignesExpected[1] = "Table 2";
		tabLignesExpected[2] = "C�line 20.75$";
		tabLignesExpected[3] = "Total : 23.81$";
		
		
		assertEquals(tabLignesExpected, facture5.getLignesFactures());
		
		
	}
	
}
