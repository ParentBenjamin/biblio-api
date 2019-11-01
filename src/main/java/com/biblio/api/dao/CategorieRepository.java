package com.biblio.api.dao;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.biblio.api.core.Categorie;


@Repository
@Transactional
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	
	 public Iterable<Categorie> findByParent_Id(long id);
	 
	 @Transactional
	 @Modifying
	 @Query("update Categorie c set c.parent.id = :idParent where c.id = :id")
	 void updateParentById(@Param("idParent") long idParent, @Param("id") long id);
	 
	 @Transactional
	 @Modifying
	 @Query("update Categorie c set c.nom = :nom, c.description = :description, c.parent.id = :idParent where c.id = :id")
	 void updateDataById(@Param("nom") String nom,@Param("description") String description, @Param("idParent") long idParent,@Param("id") long id);
	 


}