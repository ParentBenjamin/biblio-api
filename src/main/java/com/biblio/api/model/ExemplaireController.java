package com.biblio.api.model;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.biblio.api.core.Exemplaire;
import com.biblio.api.dao.ExemplaireRepository;



@RestController
public class ExemplaireController {

    @Autowired
    private ExemplaireRepository exemplaireDao;

    //Récupérer la liste des Exemplaires
    @RequestMapping(value = "/Exemplaires", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Exemplaire> listeExemplaires() {
        
        return exemplaireDao.findAll();
    }
    
    //Récupérer un exemplaire selon son id
    @RequestMapping(value = "/Exemplaires/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Exemplaire> getExemplaire(@PathVariable long id) {
        
        return exemplaireDao.findById(id);
    }
    
    //Récupérer la liste d'exemplaire d'un livre donné
    @RequestMapping(value = "/Exemplaires/Livre/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Exemplaire> listeExemplaireLivreId(@PathVariable long id) {
        
        return exemplaireDao.findByLivre_Id(id);
    }
    
    //Supprimer un Exemplaire donné
    @RequestMapping(value = "/exemplaires/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteExemplaireId(@PathVariable long id) {
        
        exemplaireDao.deleteById(id);
    }



    @PostMapping(value = "/Exemplaires")
    public ResponseEntity<Void> ajouterExemplaire(@RequestBody Exemplaire emprunt) {
        Exemplaire empruntAdded =  exemplaireDao.save(emprunt);
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