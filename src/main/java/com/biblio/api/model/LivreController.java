package com.biblio.api.model;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.biblio.api.core.Livre;
import com.biblio.api.dao.LivreRepository;

@RestController
public class LivreController {

    @Autowired
    private LivreRepository livreDao;

  //Récupérer la liste des Categories
    @RequestMapping(value = "/Livres", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Livre> listeLivres() {
        
        return livreDao.findAll();
    }
    
    //Récupérer la liste des Categories
    @RequestMapping(value = "/Livres/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Livre> getLivre(@PathVariable long id) {
        
        return livreDao.findById(id);
    }
    
    //Récupérer la liste sous catégorie d'une Categorie donnée
    @RequestMapping(value = "/Livres/Categorie/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Livre> listeCategorieId(@PathVariable long id) {
        
        return livreDao.findByCategorie_Id(id);
    }
    
    //Supprimer un Livre donné
    @RequestMapping(value = "/livres/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteLivreId(@PathVariable long id) {
        
        livreDao.deleteById(id);
    }


    @PostMapping(value = "/Livres")
    public ResponseEntity<Void> ajouterLivre(@RequestBody Livre livre) {
    	Livre livreAdded =  livreDao.save(livre);
        if (livreAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livreAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    
}