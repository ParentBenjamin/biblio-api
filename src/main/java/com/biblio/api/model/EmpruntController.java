package com.biblio.api.model;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.biblio.api.core.Emprunt;
import com.biblio.api.dao.EmpruntRepository;



@RestController
public class EmpruntController {

    @Autowired
    private EmpruntRepository empruntDao;

    //Récupérer la liste des Emprunts
    @RequestMapping(value = "/Emprunts", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Emprunt> listeEmprunts() {
        
        return empruntDao.findAll();
    }
    
    //Récupérer un Emprunt donnée
    @RequestMapping(value = "/Emprunts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Emprunt> getEmprunt(@PathVariable long id) {
        
        return empruntDao.findById(id);
    }
    
    //Récupérer la liste sous catégorie d'une Categorie donnée
    @RequestMapping(value = "/Emprunts/Utilisateur/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Emprunt> listeEmpruntUtilisateurId(@PathVariable long id) {
        
        return empruntDao.findByUtilisateur_Id(id);
    }
    
    //Supprimer un emprunt donné
    @RequestMapping(value = "/emprunts/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteEmpruntId(@PathVariable long id) {
        
        empruntDao.deleteById(id);
    }


    @PostMapping(value = "/Emprunts")
    public ResponseEntity<Void> ajouterEmprunt(@RequestBody Emprunt emprunt) {
        Emprunt empruntAdded =  empruntDao.save(emprunt);
        if (empruntAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(empruntAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    
}