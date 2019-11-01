package com.biblio.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblio.api.core.Emprunt;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

	public Iterable<Emprunt> findByUtilisateur_Id(long id);
}