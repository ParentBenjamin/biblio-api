package com.biblio.api.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.biblio.api.core.Exemplaire;
import com.biblio.api.dao.ExemplaireRepository;
import com.biblio.api.dao.LivreRepository;

@Controller
public class VueExemplaireController  {
	ExemplaireRepository exemplairerepo;
	LivreRepository livrerepo;
	@Autowired
	public VueExemplaireController (ExemplaireRepository exemplairerepo) {
		
		this.exemplairerepo = exemplairerepo;
		
	}
	
	@RequestMapping(value = "/exemplaires", method = RequestMethod.GET)
	public String getExemplaires (Model model){
		model.addAttribute("exemplaires", exemplairerepo.findAll());
		return "ExemplaireListe";
	}
	
	@RequestMapping(value = "/exemplaires/{id}", method = RequestMethod.GET)
	public String getExemplaire(Model model,@PathVariable("id") long id){
		Optional<Exemplaire> exemplaire = exemplairerepo.findById(id);
		if(exemplaire.isPresent()) {
			model.addAttribute("exemplaire", exemplaire.get());
			return "ExemplaireDetail";
		}
		return "ExemplaireListe";
	}
	
	@RequestMapping(value = "/exemplaire", method = RequestMethod.GET)
	public String ajoutExemplaire(Model model) {
		model.addAttribute("exemplaire", new Exemplaire());
		model.addAttribute("livres", livrerepo.findAll());
		return "ExemplaireAjout";

	}

	@RequestMapping(value = "/exemplaire", method = RequestMethod.POST)
	@Transactional
	public String saveEmprunt(Model model, Exemplaire exemplaire) {
		if (exemplaire != null) {
			if (exemplaire.getId() != null) {
				Exemplaire exemplaireToUpdate =exemplairerepo.getOne(exemplaire.getId());
				exemplaireToUpdate.setIsbn(exemplaire.getIsbn());
				exemplaireToUpdate.setDateParution(exemplaire.getDateParution());
				exemplaireToUpdate.setNumInventaire(exemplaire.getNumInventaire());
				exemplaireToUpdate.setLivre(exemplaire.getLivre());
				exemplairerepo.save(exemplaireToUpdate);
			}else {
				exemplairerepo.save(exemplaire);
			}	
		}
		return "redirect:/exemplaires";
	}

}