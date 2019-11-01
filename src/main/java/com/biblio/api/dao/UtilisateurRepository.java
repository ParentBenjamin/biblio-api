package com.biblio.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblio.api.core.Utilisateur;


@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	
	public Utilisateur findByPseudoAndPass(String pseudo, String pass);

    
}