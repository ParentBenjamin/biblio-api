package com.biblio.api.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.biblio.api.core.Categorie;
import com.biblio.api.dao.CategorieRepository;

@Controller
public class VueCategorieController {
	CategorieRepository categorierepo;

	@Autowired
	public VueCategorieController(CategorieRepository categorierepo) {

		this.categorierepo = categorierepo;

	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String getCategories(Model model) {
		model.addAttribute("categories", categorierepo.findAll());
		return "CategorieListe";
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public String getCategorie(Model model, @PathVariable("id") long id) {
		Optional<Categorie> categorie = categorierepo.findById(id);
		if (categorie.isPresent()) {
			model.addAttribute("categorie", categorie.get());
			return "CategorieDetail";
		}
		return "CategorieListe";
	}

	@RequestMapping(value = "/categorie", method = RequestMethod.GET)
	public String ajoutCategorie(Model model) {
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("categories", categorierepo.findAll());
		return "CategorieAjout";

	}

	@RequestMapping(value = "/categorie", method = RequestMethod.POST)
	@Transactional
	public String saveCategorie(Model model, Categorie categorie) {
		if (categorie != null) {
			if (categorie.getId() != null) {
				Categorie categorieToUpdate =categorierepo.getOne(categorie.getId());
				categorieToUpdate.setNom(categorie.getNom());
				categorieToUpdate.setDescription(categorie.getDescription());
				categorieToUpdate.setParent(categorie.getParent());
				categorierepo.save(categorieToUpdate);
			}else {
				categorierepo.save(categorie);
			}	
		}

		return "redirect:/categories";
	}
	
}
