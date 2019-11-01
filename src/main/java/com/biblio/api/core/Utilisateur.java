package com.biblio.api.core;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "utilisateur", schema= "public")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utilisateur extends AbstractDomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1195178778687795005L;

	@Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "mail")
    private String mail;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "pass")
    private String pass;

    @Column(name = "statut")
    private boolean statut;
    
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private Set<Emprunt> emprunts = new HashSet<>();


    public Utilisateur ( String nom, String prenom){

        this.nom = nom;
        this.prenom = prenom;
    }

    public Utilisateur() {

    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }
    
	public void ajoutermprunt (Emprunt emprunt) {
		if (emprunt != null) {
			this.emprunts.add(emprunt);
			emprunt.setUtilisateur(this);
		}
	}
	
	public void retirerEmprunt (Emprunt emprunt) {
		if (emprunt != null) {
			this.emprunts.remove(emprunt);
			emprunt.setUtilisateur(null);
		}
	}
	
	public String getStatutName() {
		return this.statut ? "Accepté" : "Refusé";
	}

}
