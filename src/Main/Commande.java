package Main;

public class Commande {
	
	private String nomClient;
	private String nomPlat;
	private int qte;
	
	public Commande (String nomClient,String nomPlat,int qte){
		this.nomClient = nomClient;
		this.nomPlat = nomPlat;
		this.qte = qte;
		
	}
	
	public void setNomclient(String nom){
		this.nomClient = nom;
	}
	
	public String getNomClient(){
		return this.nomClient;
	}
	
	public void setNomPlat(String nomPlat){
		this.nomPlat = nomPlat;
	}
	
	public String getNomPlat(){
		return this.nomPlat;
	}
	
	public void setQte(int qte){
		this.qte = qte;
	}
	
	public int getQte(){
		return this.qte;
	}
}
