package com.biblio.api.core;

import java.util.Date;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;

@Entity
@Table(name = "exemplaire", schema= "public")
public class Exemplaire extends AbstractDomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3954850869211208912L;

	@Column(name = "numInventaire")
    private String numInventaire;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "dateParution")
    private Date dateParution;
    
    @ManyToOne
    @JoinColumn
    private Livre livre;

    @OneToMany(mappedBy = "exemplaire", cascade = CascadeType.ALL)
    private Set<LigneEmprunt> ligneEmprunts = new HashSet<>();


    public Exemplaire ( String isbn, Date dateParution){
        this.isbn = isbn;
        this.dateParution = dateParution;
    }

    public Exemplaire (){
    }


    public String getNumInventaire() {
        return numInventaire;
    }

    public void setNumInventaire(String numInventaire) {
        this.numInventaire = numInventaire;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getDateParution() {
        return dateParution;
    }

    public void setDateParution(Date dateParution) {
        this.dateParution = dateParution;
    }
    
    public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public Collection<LigneEmprunt> getLigneEmprunts() {
		return ligneEmprunts;
	}

	public void setLigneEmprunts(Set<LigneEmprunt> ligneEmprunts) {
		this.ligneEmprunts = ligneEmprunts;
	}
	
	public void emprunter () {
		boolean emprunt = this.livre.emprunterExemplaire(this);
		if(emprunt) {
			LigneEmprunt ligne = new LigneEmprunt();
			ligne.setExemplaire(this);
			ligne.setDateFin(dateDansQuatreSemaine());
		}
	}
	
	public void retourner(LigneEmprunt ligne) {
		boolean emprunt = this.livre.retournerExemplaire(this);
		if(emprunt) {

			ligne.setDateRetour(new Date());
			ligne.setStatut(true);
		}
	}


	private Date dateDansQuatreSemaine() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public String getLivreTitre () {
    	return this.livre.getTitre();
    }

}
