package com.biblio.api.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.biblio.api.core.Livre;
import com.biblio.api.dao.CategorieRepository;
import com.biblio.api.dao.LivreRepository;

@Controller
public class VueLivreController  {
	LivreRepository livrerepo;
	CategorieRepository categorierepo;
	@Autowired
	public VueLivreController (LivreRepository livrerepo) {
		
		this.livrerepo = livrerepo;
		
	}
	
	@RequestMapping(value = "/livres", method = RequestMethod.GET)
	public String getLivres (Model model){
		model.addAttribute("livres", livrerepo.findAll());
		return "LivreListe";
	}
	
	@RequestMapping(value = "/livres/{id}", method = RequestMethod.GET)
	public String getLivre(Model model,@PathVariable("id") long id){
		Optional<Livre> livre = livrerepo.findById(id);
		if(livre.isPresent()) {
			model.addAttribute("livre", livre.get());
			return "LivreDetail";
		}
		return "LivreListe";
	}
	
	@RequestMapping(value = "/livre", method = RequestMethod.GET)
	public String ajoutLivre(Model model) {
		model.addAttribute("livre", new Livre());
		model.addAttribute("categories", categorierepo.findAll());
		return "LivreAjout";

	}

	@RequestMapping(value = "/livre", method = RequestMethod.POST)
	@Transactional
	public String saveEmprunt(Model model, Livre livre) {
		if (livre != null) {
			if (livre.getId() != null) {
				Livre livreToUpdate =livrerepo.getOne(livre.getId());
				livreToUpdate.setTitre(livre.getTitre());
				livreToUpdate.setSynopsis(livre.getSynopsis());
				livreToUpdate.setStatut(livre.getStatut());
				livreToUpdate.setNbExemplaire(livre.getNbExemplaire());
				livreToUpdate.setNbExemplaireDisponible(livre.getNbExemplaireDisponible());
				livreToUpdate.setAuteur(livre.getAuteur());
				livreToUpdate.setEditeur(livre.getEditeur());
				livrerepo.save(livreToUpdate);
			}else {
				livrerepo.save(livre);
			}	
		}
		return "redirect:/livres";
	}

}
