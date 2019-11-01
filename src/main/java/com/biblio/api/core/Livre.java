package com.biblio.api.core;

import java.util.Set;
import javax.persistence.*;
import com.biblio.api.core.Categorie;


@Entity
@Table(name = "livre", schema= "public")
public class Livre extends AbstractDomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5550702597139515388L;

	@Column(name = "titre")
    private String titre;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "nbExemplaireDisponible")
    private int nbExemplaireDisponible;

    @Column(name = "nbExemplaire")
    private int nbExemplaire;

    @Column(name = "Editeur")
    private String editeur;

    @Column(name = "Auteur")
    private String auteur;

    @Column(name = "statut")
    private Boolean statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Categorie categorie;
    
    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Exemplaire> exemplaires;


    public Livre ( String titre, String synopsis, String auteur){

        
        this.titre = titre;
        this.synopsis = synopsis;
        this.auteur = auteur;
        this.statut = false;
    }
    
    public Livre ( String titre, String synopsis, int nbExemplaire){

        
        this.titre = titre;
        this.synopsis = synopsis;
        if (this.nbExemplaire > 0) {
        	this.nbExemplaire= nbExemplaire;
            this.nbExemplaireDisponible= nbExemplaire;
        	this.statut = true;
        }
        else {
        	this.nbExemplaire= 0;
            this.nbExemplaireDisponible= 0;
            this.statut = false;
        }
        	
    }

    public Livre() {

    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getNbExemplaireDisponible() {
        return nbExemplaireDisponible;
    }

    public int getNbExemplaire() {
        return nbExemplaire;
    }

    public void setNbExemplaire(int nbExemplaire) {
        this.nbExemplaire = nbExemplaire;
    }
    
    public void setNbExemplaireDisponible(int nbExemplaire) {
        this.nbExemplaire = nbExemplaire;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    public void ajoutExemplaire (Exemplaire exemplaire) {
    	
    	if(exemplaire != null) {
    		this.exemplaires.add(exemplaire);
    		exemplaire.setLivre(this);
    		this.nbExemplaire++;
    		this.nbExemplaireDisponible++;
    	}
    }
    
    public void retirerExemplaire (Exemplaire exemplaire) {
    	
    	if(exemplaire != null) {
    		this.exemplaires.remove(exemplaire);
    		exemplaire.setLivre(null);
    		this.nbExemplaire--;
    		this.nbExemplaireDisponible--;
    	}
    }
    
    public boolean emprunterExemplaire (Exemplaire exemplaire) {
    	if(this.exemplaires.contains(exemplaire) && this.statut) {   	
    			this.nbExemplaireDisponible--;
    		if( this.nbExemplaireDisponible == 0) {
    			this.statut =false;
    		}
    		return true;
    	}
    	return false;
    }
    
    public boolean retournerExemplaire (Exemplaire exemplaire) {
    	if(this.exemplaires.contains(exemplaire) && this.nbExemplaireDisponible < this.nbExemplaire) {
    		this.nbExemplaireDisponible++;
    	if( this.nbExemplaireDisponible > 0) {
    		this.statut =true;
    	}
    	return true;
    	}
    	return false;
    }
    
    public String getCategorieName() {
    	return this.categorie.getNom();
    }
    
    public String getStatutName() {
    	return this.getStatut() ? "disponible" : "indisponible";
    }
    

}
