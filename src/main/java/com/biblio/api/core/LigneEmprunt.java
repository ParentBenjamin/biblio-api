package com.biblio.api.core;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "ligneEmprunt", schema= "public")
public class LigneEmprunt extends AbstractDomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5381760553406265295L;

	@Column(name = "dateFin")
    private Date dateFin;
	
	@Column(name = "dateRetour")
    private Date dateRetour;
	
	@Column(name = "statut")
    private boolean statut = false;
	
	@Column(name = "relance")
    private int relance = 0;
	
	@ManyToOne
    @JoinColumn
    private Exemplaire exemplaire;
	
	@ManyToOne
    @JoinColumn
    private Emprunt emprunt;
	
	public LigneEmprunt (){
    }
	
	public LigneEmprunt ( Date dateFin, boolean statut, int relance){

        this.dateFin = dateFin;
        this.statut = statut;
        this.relance = relance;
    }
	
	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public boolean getStatut() {
		return statut;
	}

	public void setStatut(boolean statut) {
		this.statut = statut;
	}

	public int getRelance() {
		return relance;
	}

	public void setRelance(int relance) {
		this.relance = relance;
	}
	
	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public Emprunt getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(Emprunt emprunt) {
		this.emprunt = emprunt;
	}
	
	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	
	public void relancer () {
		if(this.relance == 0) {
			this.dateFin = dateFinQuatreSemaine(); 
			this.relance++;
		}
		
	}

	private Date dateFinQuatreSemaine() {
		// TODO Auto-generated method stub
		return null;
	}


}
