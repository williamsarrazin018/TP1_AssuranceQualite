package Main;

public class Commande {
	
	private String nomClient;
	private String nomPlat;
	private int qte;
	private int noTable;
	
	public Commande (String nomClient,String nomPlat,int qte, int noTable){
		this.nomClient = nomClient;
		this.nomPlat = nomPlat;
		this.qte = qte;
		this.noTable = noTable;
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

	public int getNoTable() {
		return noTable;
	}

	public void setNoTable(int noTable) {
		this.noTable = noTable;
	}
}
