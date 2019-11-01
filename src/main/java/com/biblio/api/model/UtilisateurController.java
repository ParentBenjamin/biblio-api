package com.biblio.api.model;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.biblio.api.core.Utilisateur;
import com.biblio.api.dao.UtilisateurRepository;



@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurDao;

    //Récupérer la liste des Utilisateurs
    @RequestMapping(value = "/Utilisateurs", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Utilisateur> listeUtilisateurs() {
        
        return utilisateurDao.findAll();
    }
    
    //Récupérer recupere un utilisateur selon l'id
    @RequestMapping(value = "/Utilisateurs/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Utilisateur> getUtilisateur(@PathVariable long id) {
        
        return utilisateurDao.findById(id);
    }
    
  //Récupérer recupere un utilisateur selon l'id
    @RequestMapping(value = "/Utilisateurs/Connexion/{id}", method = RequestMethod.GET)
    @ResponseBody
    public boolean connectUtilisateur(@PathVariable String pseudo,@PathVariable String pass ) {
        
        Utilisateur user = utilisateurDao.findByPseudoAndPass(pseudo,pass);
        
        if (user != null) {
        	return true;
        }
        return false;
    }
    
    //Supprimer un utilisateur donné
    @RequestMapping(value = "/utilisateurs/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUtilisateurId(@PathVariable long id) {
        
        utilisateurDao.deleteById(id);
    }


    @PostMapping(value = "/Utilisateurs")
    public ResponseEntity<Void> ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
    	Utilisateur utilisateurAdded =  utilisateurDao.save(utilisateur);
        if (utilisateurAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(utilisateurAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    
}