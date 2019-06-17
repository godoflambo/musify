package mx.com.coffeedev.spring.boot.springboorcoffeedev.controller;


import mx.com.coffeedev.spring.boot.springboorcoffeedev.dbcontroller.DataBaseConnector;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.dbcontroller.DataBaseHelper;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels.Artist;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.general.GeneralRS;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.general.NMRelation;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels.People;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.responses.ArtistByStyleRS;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.responses.ArtistListRS;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/Musify", produces = "application/json")
public class Controller {

    DataBaseConnector db = new DataBaseConnector(DataBaseHelper.DB_URL,
            DataBaseHelper.DB_USER,
            DataBaseHelper.DB_PASS);

    @GetMapping(value = "/Artists")
    @ResponseBody
    public ArtistListRS getArtistList(){ return db.getArtistList(); }

    @GetMapping(value = "/Artists/Style/{id}")
    @ResponseBody
    public ArtistByStyleRS getArtistByStyle(@PathVariable long id){
        return db.getArtistByStyle(id);
    }

    @GetMapping(value = "/Artists/Related")
    @ResponseBody
    public GeneralRS getRelatedArtistsById(@RequestParam long id){ return db.getRelatedArtists(id); }

    @PostMapping(value = "/Artists/Related/Delete")
    @ResponseBody
    public GeneralRS deleteArtistRelation(@RequestBody NMRelation relation){ return db.deleteArtistRelation(relation); }

    @PostMapping(value = "/Artists/Related/New")
    @ResponseBody
    public GeneralRS relateArtists(@RequestBody NMRelation relation){
        return db.relateArtists(relation);
    }

    @PostMapping(value = "/Artists/New")
    @ResponseBody
    public GeneralRS insertArtist(@RequestBody  Artist artist){
        return db.insertArtist(artist);
    }

    @PostMapping(value = "/People/New")
    @ResponseBody
    public GeneralRS insertPeople(@RequestBody People people){
        return db.insertPeople(people);
    }

    @PostMapping(value = "/People/Asign")
    @ResponseBody
    public GeneralRS asignPeople(@RequestBody NMRelation relation){
        return db.assignPeople(relation);
    }
}