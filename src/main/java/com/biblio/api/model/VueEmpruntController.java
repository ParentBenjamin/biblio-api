package com.biblio.api.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.biblio.api.core.Emprunt;
import com.biblio.api.dao.EmpruntRepository;
import com.biblio.api.dao.UtilisateurRepository;

@Controller
public class VueEmpruntController  {
	EmpruntRepository empruntrepo;
	UtilisateurRepository utilisateurrepo;
	@Autowired
	public VueEmpruntController (EmpruntRepository empruntrepo) {
		
		this.empruntrepo = empruntrepo;
		
	}
	
	@RequestMapping(value = "/emprunts", method = RequestMethod.GET)
	public String getEmprunts (Model model){
		model.addAttribute("emprunts", empruntrepo.findAll());
		return "EmpruntListe";
	}
	
	@RequestMapping(value = "/emprunts/{id}", method = RequestMethod.GET)
	public String getEmprunt(Model model,@PathVariable("id") long id){
		Optional<Emprunt> emprunt = empruntrepo.findById(id);
		if(emprunt.isPresent()) {
			model.addAttribute("emprunt", emprunt.get());
			return "EmpruntDetail";
		}
		return "EmpruntListe";
	}
	
	@RequestMapping(value = "/emprunt", method = RequestMethod.GET)
	public String ajoutEmprunt(Model model) {
		model.addAttribute("emprunt", new Emprunt());
		model.addAttribute("users", utilisateurrepo.findAll());
		return "EmpruntAjout";

	}

	@RequestMapping(value = "/emprunt", method = RequestMethod.POST)
	@Transactional
	public String saveEmprunt(Model model, Emprunt emprunt) {
		if (emprunt != null) {
			if (emprunt.getId() != null) {
				Emprunt empruntToUpdate =empruntrepo.getOne(emprunt.getId());
				empruntToUpdate.setDateDebut(emprunt.getDateDebut());
				empruntToUpdate.setUtilisateur(emprunt.getUtilisateur());
				empruntToUpdate.setDescription(emprunt.getDescription());
				empruntrepo.save(empruntToUpdate);
			}else {
				empruntrepo.save(emprunt);
			}	
		}

		return "redirect:/emprunts";
	}
}