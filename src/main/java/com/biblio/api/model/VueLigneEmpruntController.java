package com.biblio.api.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.biblio.api.core.LigneEmprunt;
import com.biblio.api.dao.LigneEmpruntRepository;

@Controller
public class VueLigneEmpruntController  {
	LigneEmpruntRepository ligneEmpruntrepo;
	@Autowired
	public VueLigneEmpruntController (LigneEmpruntRepository LigneEmpruntrepo) {
		
		this.ligneEmpruntrepo = LigneEmpruntrepo;
		
	}
	
	@RequestMapping(value = "/ligneEmprunts", method = RequestMethod.GET)
	public String getLivre (Model model){
		model.addAttribute("ligneEmprunts", ligneEmpruntrepo.findAll());
		return "LigneEmpruntListe";
	}
	
	@RequestMapping(value = "/ligneEmprunt", method = RequestMethod.POST)
	@Transactional
	public String saveEmprunt(Model model, LigneEmprunt ligneEmprunt) {
		if (ligneEmprunt != null) {
			if (ligneEmprunt.getId() != null) {
				LigneEmprunt ligneEmpruntToUpdate =ligneEmpruntrepo.getOne(ligneEmprunt.getId());
				ligneEmpruntToUpdate.setDateFin(ligneEmprunt.getDateFin());
				ligneEmpruntToUpdate.setDateRetour(ligneEmprunt.getDateRetour());
				ligneEmpruntToUpdate.setStatut(ligneEmprunt.getStatut());
				ligneEmpruntToUpdate.setRelance(ligneEmprunt.getRelance());
				ligneEmpruntToUpdate.setEmprunt(ligneEmprunt.getEmprunt());
				ligneEmpruntrepo.save(ligneEmpruntToUpdate);
			}else {
				ligneEmpruntrepo.save(ligneEmprunt);
			}	
		}
		return "redirect:/emprunts";
	}

}