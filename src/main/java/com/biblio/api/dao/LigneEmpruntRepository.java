package com.biblio.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblio.api.core.LigneEmprunt;

@Repository
public interface LigneEmpruntRepository extends JpaRepository<LigneEmprunt, Long> {

	public Iterable<LigneEmprunt> findByEmprunt_Id(long id);
	public Iterable<LigneEmprunt> findByExemplaire_Id(long id);
}