package com.biblio.api.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblio.api.core.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

	public Iterable<Livre> findByCategorie_Id(long id);
}