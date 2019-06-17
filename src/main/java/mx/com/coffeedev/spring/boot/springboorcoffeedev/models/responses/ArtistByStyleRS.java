package mx.com.coffeedev.spring.boot.springboorcoffeedev.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels.Artist;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.general.GeneralRS;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistByStyleRS extends GeneralRS {

    List<Artist> artistsByStyle;

    public ArtistByStyleRS(List<Artist> artistsByStyle) {
        super(null, null);
        this.artistsByStyle = artistsByStyle;
    }

    public ArtistByStyleRS(List<Artist> artistsByStyle, String status, String message) {
        super(status, message);
        this.artistsByStyle = artistsByStyle;
    }

    public ArtistByStyleRS() {
        super(null, null);
    }

    public List<Artist> getArtistsByStyle() {
        return artistsByStyle;
    }

    public void setArtistsByStyle(List<Artist> artistsByStyle) {
        this.artistsByStyle = artistsByStyle;
    }
}
