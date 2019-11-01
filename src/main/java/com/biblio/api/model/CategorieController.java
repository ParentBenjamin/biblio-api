package com.biblio.api.model;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.biblio.api.core.Categorie;
import com.biblio.api.dao.CategorieRepository;


@RestController
public class CategorieController {

    @Autowired
    private CategorieRepository categorieDao;

    //Récupérer la liste des Categories
    @RequestMapping(value = "/Categories", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Categorie> listeCategories() {
        
        return categorieDao.findAll();
    }
    
    //Récupérer la liste des Categories
    @RequestMapping(value = "/Categories/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Categorie> getCategorie(@PathVariable long id) {
        
        return categorieDao.findById(id);
    }
    
    //Récupérer la liste sous catégorie d'une Categorie donnée
    @RequestMapping(value = "/categories/Parent/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Categorie> listeCategorieId(@PathVariable long id) {
        
        return categorieDao.findByParent_Id(id);
    }
    
    //Supprimer une catégore donnée
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCategorieId(@PathVariable long id) {
        
        categorieDao.deleteById(id);
    }


    @PostMapping(value = "/categories")
    public ResponseEntity<Void> ajouterCategorie(@RequestBody Categorie categorie) {
        Categorie categorieAdded =  categorieDao.save(categorie);
        if (categorieAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categorieAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    
    @PostMapping(value = "/categories/Parent/{id}")
    public void modifierParent(@RequestBody Categorie categorie,@PathVariable long id) {
        categorieDao.updateParentById(categorie.getId(),id);
        
    }
    
    @PostMapping(value = "/categories/u/{id}")
    public void modifierData(@RequestBody Categorie categorie,@PathVariable long id) {
        categorieDao.updateDataById(categorie.getNom(),categorie.getDescription(), categorie.getParent().getId() ,id);
        
    }
    
}