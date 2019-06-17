package mx.com.coffeedev.spring.boot.springboorcoffeedev.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels.Artist;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.general.GeneralRS;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedArtistsRS extends GeneralRS {

    private Artist artist;
    private List<Artist> relatedArtists;

    public RelatedArtistsRS(Artist artist, List<Artist> relatedArtists) {
        super(null, null);
        this.artist = artist;
        this.relatedArtists = relatedArtists;
    }


    public RelatedArtistsRS(Artist artist, List<Artist> relatedArtists, String status, String message) {
        super(status, message);
        this.artist = artist;
        this.relatedArtists = relatedArtists;
    }

    public RelatedArtistsRS(){
        super(null, null);
        this.relatedArtists = new ArrayList<>();
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Artist> getRelatedArtists() {
        return relatedArtists;
    }

    public void setRelatedArtists(List<Artist> relatedArtists) {
        this.relatedArtists = relatedArtists;
    }
}
