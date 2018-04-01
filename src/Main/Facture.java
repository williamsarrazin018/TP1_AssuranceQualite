package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.File;



public class Facture {

	public static String[] tabClients = new String[20];
	public static Plat[] tabPlats = new Plat[20];
	public static Commande[] tabCommandes = new Commande[20];
	public static String[] tabErreurs = new String[20];
	
	private static final double TPS = 0.05;
	private static final double TVQ = 0.0975;
	private int cptLignes = 0;
	private int cptErreurs = 0;
	private int cptClient = 0;
	private int cptPlat = 0;
	private int cptCommande = 0;
	private int cptTable = 0;
	private int[] tabTables = new int[20];
	private String[] lignesFactures = new String[20];

	public Facture() {
		
		
	}
	
	
	
	public void lignesFacture() {
		
		cptLignes = 0;

		for (int j = 0; j < tabErreurs.length; j++) {
			if (tabErreurs[j] != null) {
				lignesFactures[cptLignes] = tabErreurs[j];
				cptLignes++;
			}
		}
		
		
		for (int h = 0; h < tabTables.length; h++) {
			if (tabTables[h] != 0) {
				double total = 0;
				int nbClientsTable = 0;
				lignesFactures[cptLignes] = "Table " + tabTables[h];
				cptLignes++;
				for (int i = 0; i < cptClient; i++) {
					
					double prix = 0;
					double prixTotalClient = 0;
					
					for (int j = 0; j < cptCommande; j++) {

						if (tabCommandes[j].getNoTable() == tabTables[h]) {
							if (tabClients[i].equals(tabCommandes[j].getNomClient())) {
								nbClientsTable++;


								prix = trouverPrixPlat(tabCommandes[j].getNomPlat())
										* tabCommandes[j].getQte();
								prixTotalClient += trouverPrixPlat(tabCommandes[j].getNomPlat())
										* tabCommandes[j].getQte();
								total += prix;

							}
						}
						
						

					}

					
					if (prix > 0) {
						
						getLignesFactures()[cptLignes] = tabClients[i] + " "
								+ (Math.round(prixTotalClient * 100.0) / 100.0) + "$";
						
						cptLignes++;
						

					}

				}
				
				if (total > 100 || nbClientsTable >= 3) {
					total += (total * 0.15) + (total * TPS) + (total * TVQ);
					lignesFactures[cptLignes] = "Total : " + (Math.round(total * 100.0) / 100.0) + "$";
					cptLignes++;

				} else {
					
					total += (total * TPS) + (total * TVQ);
					lignesFactures[cptLignes] = "Total : " + (Math.round(total * 100.0) / 100.0) + "$";
					cptLignes++;
					
				}
			}
			
		}

	}

	public void gererCommandes(String fichier) {

		BufferedReader bReader = null;
		FileReader fReader = null;

		try {

			boolean clients = false;
			boolean plats = false;
			boolean commandes = false;

			cptClient = 0;
			cptPlat = 0;
			cptCommande = 0;

			// Lire le contenu du fichier et le mettre dans les tableaux
			fReader = new FileReader(fichier);
			bReader = new BufferedReader(fReader);

			String ligneCourrante;

			while ((ligneCourrante = bReader.readLine()) != null) {
				if (ligneCourrante.contains("Clients")) {

					clients = true;

				} else if (ligneCourrante.contains("Plats")) {

					clients = false;
					plats = true;

				} else if (ligneCourrante.contains("Commandes")) {

					plats = false;
					commandes = true;

				}

				if (clients) {
					
					tabClients[cptClient] = ligneCourrante;

					cptClient++;

				}

				if (plats && !ligneCourrante.contains("Plats")) {

					String[] plat = ligneCourrante.split(" ");
					Plat platTmp = new Plat(Double.parseDouble(plat[1]),
							plat[0]);
					tabPlats[cptPlat] = platTmp;
					cptPlat++;
				}

				if (commandes && !ligneCourrante.contains("Commandes")
						&& !ligneCourrante.contains("Fin")) {

					try {

						boolean nomTrouve = false;
						boolean platTrouve = false;
						boolean qteOK = true;
						
						String[] commande = ligneCourrante.split(" ");
						if (commande.length != 4) {
							throw new Exception("Format de commande invalide");
						} else {

							Commande commandeTmp = new Commande(commande[0],
									commande[1], Integer.parseInt(commande[2]), Integer.parseInt(commande[3]));
							
							//Nom valide
							for (int i = 0; i < cptClient  && !nomTrouve; i++) {
								if (tabClients[i] != null) {
									if (tabClients[i].equals(commandeTmp.getNomClient())) {
										nomTrouve = true;
									}
								}
							}
							
							//Plat valide
							for (int i = 0; i < cptPlat && !platTrouve; i++) {
								if (tabPlats[i] != null) {
									if (tabPlats[i].getNom().equals(commandeTmp.getNomPlat())) {
										platTrouve = true;
									}
								}
							}
							
							//qte valide
							if (commandeTmp.getQte() <= 0) {
								qteOK = false;
							}
							
							if (nomTrouve && platTrouve && qteOK) {
								tabCommandes[cptCommande] = commandeTmp;
								
								boolean tableExistante = false;
								
								for (int i = 0; i < tabTables.length; i++) {
									if (commandeTmp.getNoTable() == tabTables[i]) {
										tableExistante = true;
									}
								}
								
								if (!tableExistante) {
									tabTables[cptTable] = commandeTmp.getNoTable();
									cptTable++;
								}
								
								
								cptCommande++;
							} else if (!nomTrouve){
								tabErreurs[cptErreurs] = "Erreur nom introuv� : " + ligneCourrante;
								cptErreurs++;
							} else if (!platTrouve) {
								tabErreurs[cptErreurs] = "Erreur plat introuv� : " + ligneCourrante;
								cptErreurs++;
							} else if (!qteOK) {
								tabErreurs[cptErreurs] = "Erreur quantit� invalide : " + ligneCourrante;
								cptErreurs++;
							}

							
						}
						
						

					} catch (Exception formatInvalide) {

						tabErreurs[cptErreurs] = "Erreur de format : " + ligneCourrante;
						cptErreurs++;

					}

				}

			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bReader != null)

					bReader.close();

				if (fReader != null)

					fReader.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}

	public boolean verifierClient(String client) {
		boolean correct = false;
		boolean clientCorrect = false;
		

		for (int i = 0; i < Facture.tabClients.length; i++) {
			if (client == Facture.tabClients[i]) {
				clientCorrect = true;
				correct = true;
			}
		}
		if (clientCorrect) {
			String[] nom = client.split(" ");
			if (nom.length > 1) {
				correct = false;
			}
		} else {
			System.out.println("Nom inexistant");
		}

		return correct;
	}
	
	public boolean verifierPlat(String nomPlat){
		boolean platCorrect = false;
		
		for (int i = 0; i <tabPlats.length ; i++) {
			if (tabPlats[i].getNom() == nomPlat){
				platCorrect = true;	
			}
			String[]plat = nomPlat.split(" ");
			if (plat.length > 1){
				System.out.println("Probleme avec espace");//pour test
				platCorrect = false;
			}
		}
		return platCorrect;
	}

	public String[] getLignesFactures() {
		return lignesFactures;
	}

	public void setLignesFactures(String[] lignesFactures) {
		this.lignesFactures = lignesFactures;
	}
		
	public void ecrireFacture(){
		
        BufferedWriter writer = null;
        try {
            String timeLog = new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());
            File facture = new File("Facture-du-" + timeLog + ".txt");

            writer = new BufferedWriter(new FileWriter(facture));
            System.out.println("Affichage des factures:");
            for (int i = 0; i < lignesFactures.length; i++) {
				if (lignesFactures[i] != null) {
					writer.write(lignesFactures[i] + "\n");
					writer.newLine();
					System.out.println(lignesFactures[i]);
				}
			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
		
	}
	
	public double trouverPrixPlat( String nomPlat){
		double prix = 0;
		for (int i = 0; i < tabPlats.length; i++) {
			if (tabPlats[i] != null) {
				if (tabPlats[i].getNom().equals(nomPlat) ) {
					prix = tabPlats[i].getPrix();
				}
			}
		}
		
		return prix;
		
	}
	
}
