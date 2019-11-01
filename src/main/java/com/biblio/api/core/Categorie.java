package com.biblio.api.core;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;




@Entity
@Table(name = "categorie")
public class Categorie extends AbstractDomainObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5226338547116133089L;

	@Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;
    
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Livre> livres = new HashSet<>();
       
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Categorie parent;
    
	public Categorie ( String nom, String description){

        this.nom = nom;
        this.description = description;
    }

    public Categorie() {

    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<Livre> getLivres() {
		return livres;
	}

	public void setLivres(Set<Livre> livres) {
		if (livres == null) {
			livres = new HashSet<>();
		}
		this.livres = livres;
	}
	
	public Categorie getParent() {
		return parent;
	}

	public void setParent(Categorie parent) {
		this.parent = parent;
	}
	
	public void addLivre (Livre livre) {
		
		if (livres != null) {
			this.livres.add(livre);
			livre.setCategorie(this);
		}
	}
	
	public void deleteLivre (Livre livre) {
		
		if (livres != null) {
			this.livres.remove(livre);
			livre.setCategorie(null);
		}
	}
	
	public boolean hasParent() {
	
		return this.parent != null?true:false;
	}
	
	public int nombreLivre() {
		return this.livres.size();
	}
	
	public String getParentName() {
		return this.hasParent() ? this.parent.getNom() : "-";
	}
	public int getNbSousCategorie() {
		return 0;
	}

}
