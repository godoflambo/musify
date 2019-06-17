package mx.com.coffeedev.spring.boot.springboorcoffeedev.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels.Artist;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.general.GeneralRS;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistListRS extends GeneralRS {

    private List<Artist> artistList;

    public ArtistListRS(List<Artist> artistList) {
        super(null, null);
        this.artistList = artistList;
    }

    public ArtistListRS() {
        super(null, null);
    }

    public ArtistListRS(List<Artist> artistList, String status, String message) {
        super(status, message);
        this.artistList = artistList;
    }

    public List<Artist> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }
}
