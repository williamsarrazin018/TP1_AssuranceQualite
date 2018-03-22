package Main;

public class Plat {
	private double prix;
	private String nom;
	
	public Plat(double prix, String nom){
		
		this.nom = nom;
		
		this.prix = prix;
		
	}
	
	public void setNom(String nom){
		
		this.nom = nom;
		
	}
	
	public void setPrix(double prix){
		
		this.prix = prix;
		
	}
	
	public String getNom(){
		
		return this.nom;
		
	}
	
	public double getPrix(){
		
		return this.prix;
		
	}
	
}
