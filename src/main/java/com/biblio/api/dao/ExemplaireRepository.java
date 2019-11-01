package com.biblio.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblio.api.core.Exemplaire;


@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {

	public Iterable<Exemplaire> findByLivre_Id(long id);
}