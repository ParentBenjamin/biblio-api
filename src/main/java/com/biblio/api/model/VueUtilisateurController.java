package com.biblio.api.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.biblio.api.core.Utilisateur;
import com.biblio.api.dao.UtilisateurRepository;

@Controller
public class VueUtilisateurController  {
	UtilisateurRepository utilisateurrepo;
	@Autowired
	public VueUtilisateurController (UtilisateurRepository utilisateurrepo) {
		
		this.utilisateurrepo = utilisateurrepo;
		
	}
	
	@RequestMapping(value = "/utilisateurs", method = RequestMethod.GET)
	public String getCategories(Model model) {
		model.addAttribute("utilisateurs", utilisateurrepo.findAll());
		return "UtilisateurListe";
	}

	@RequestMapping(value = "/utilisateurs/{id}", method = RequestMethod.GET)
	public String getCategorie(Model model, @PathVariable("id") long id) {
		Optional<Utilisateur> utilisateur = utilisateurrepo.findById(id);
		if (utilisateur.isPresent()) {
			model.addAttribute("utilisateur", utilisateur.get());
			return "UtilisateurDetail";
		}
		return "UtilisateurListe";
	}
	
	@RequestMapping(value = "/utilisateur", method = RequestMethod.GET)
	public String ajoutCategorie(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "UtilisateurAjout";

	}

	@RequestMapping(value = "/utilisateur", method = RequestMethod.POST)
	@Transactional
	public String saveUtilisateur(Model model, Utilisateur utilisateur) {
		if (utilisateur != null) {
			if (utilisateur.getId() != null) {
				Utilisateur utilisateurToUpdate =utilisateurrepo.getOne(utilisateur.getId());
				utilisateurToUpdate.setNom(utilisateur.getNom());
				utilisateurToUpdate.setPrenom(utilisateur.getPrenom());
				utilisateurToUpdate.setMail(utilisateur.getMail());
				utilisateurToUpdate.setPass(utilisateur.getPass());
				utilisateurToUpdate.setPseudo(utilisateur.getPseudo());
				utilisateurToUpdate.setStatut(utilisateur.isStatut());
				utilisateurrepo.save(utilisateurToUpdate);
			}else {
				utilisateurrepo.save(utilisateur);
			}	
		}
		return "redirect:/utilisateurs";
	}

}
