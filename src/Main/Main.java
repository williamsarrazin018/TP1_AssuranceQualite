package Main;


public class Main {
	
	public static void main(String[] args) {

		//Partie 1
		
//		CV cv1 = new CV("Sarrazin", "William", "Collégiale", 1, "Programmation", "Apprendre de nouvelles choses");
//		
//		CV cv2 = new CV("Torres", "Agustin", "Collégiale", 2, "Prog", "Apprendre");
		
		
		//cv1.afficher();
		
		//cv2.afficher();
		
		System.out.println("Bienvenue chez Barette!\n");
		
		String fichier = ".\\commande.txt";
		
		Facture facture = new Facture();
		facture.gererCommandes(fichier);
		//facture.lignesFacture();
		facture.ecrireFacture();
		facture.factureTable();
	}
		
		
}


