package com.biblio.api.core;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "emprunt", schema= "public")
public class Emprunt extends AbstractDomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1921509676528880803L;

	@Column(name = "dateDebut")
    private Date dateDebut = new Date();
	
	@Column(name = "description")
    private String description;
	
	@ManyToOne
    @JoinColumn
    private Utilisateur utilisateur;
	
	@OneToMany(mappedBy = "emprunt", cascade = CascadeType.ALL)
    private Set<LigneEmprunt> ligneEmprunts;
	
	public Emprunt (){
    }
	
	public Emprunt ( String description){

        
        this.description = description;
    }

	public Date getDateDebut() {
		return dateDebut;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Set<LigneEmprunt> getLigneEmprunts() {
		return ligneEmprunts;
	}

	public void setLigneEmprunts(Set<LigneEmprunt> ligneEmprunts) {
		this.ligneEmprunts = ligneEmprunts;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public void ajouterLigneEmprunt (LigneEmprunt ligne) {
		if (ligne != null) {
			this.ligneEmprunts.add(ligne);
			ligne.setEmprunt(this);
		}
	}
	
	public void retirerLigneEmprunt (LigneEmprunt ligne) {
		if (ligne != null) {
			this.ligneEmprunts.remove(ligne);
			ligne.setEmprunt(null);
		}
	}
	
	public String getUtilisateurName() {
		return this.utilisateur.getNom()+" "+this.utilisateur.getPrenom();
	}


}
