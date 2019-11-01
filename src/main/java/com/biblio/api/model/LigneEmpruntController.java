package com.biblio.api.model;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.biblio.api.core.LigneEmprunt;
import com.biblio.api.dao.LigneEmpruntRepository;


@RestController
public class LigneEmpruntController {

    @Autowired
    private LigneEmpruntRepository ligneEmpruntDao;

    //Récupérer la liste des ligne d'emprunt
    @RequestMapping(value = "/LigneEmprunt", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<LigneEmprunt> listeCategories() {
        
        return ligneEmpruntDao.findAll();
    }
    
    //Récupérer une ligne d'emprunt donnée
    @RequestMapping(value = "/LigneEmprunts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<LigneEmprunt> getCategorie(@PathVariable long id) {
        
        return ligneEmpruntDao.findById(id);
    }
    
    //Récupérer la liste des ligne d'emprunt d'un emprunt donné
    @RequestMapping(value = "/LigneEmprunts/Emprunt/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<LigneEmprunt> listeLigneEmpruntEmpruntId(@PathVariable long id) {
        
        return ligneEmpruntDao.findByEmprunt_Id(id);
    }
    
    //Récupérer la liste des ligne d'emprunt d'un Exemplaire donné
    @RequestMapping(value = "/LigneEmprunts/Exemplaire/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<LigneEmprunt> listeLigneEmpruntExemplaireId(@PathVariable long id) {
        
        return ligneEmpruntDao.findByExemplaire_Id(id);
    }
    
    //Supprimer une catégore donnée
    @RequestMapping(value = "/ligneEmprunts/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteLigneEmpruntId(@PathVariable long id) {
        
        ligneEmpruntDao.deleteById(id);
    }


    @PostMapping(value = "/LigneEmprunts")
    public ResponseEntity<Void> ajouterLigneEmprunt(@RequestBody LigneEmprunt ligneEmprunt) {
    	LigneEmprunt ligneEmpruntAdded =  ligneEmpruntDao.save(ligneEmprunt);
        if (ligneEmpruntAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ligneEmpruntAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    
}