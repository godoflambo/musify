package mx.com.coffeedev.spring.boot.springboorcoffeedev.dbcontroller;

import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels.Artist;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.responses.ArtistByStyleRS;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.responses.ArtistListRS;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.responses.RelatedArtistsRS;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels.Style;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ObjectBuilder {

    public static ArtistListRS buildArtistList(ResultSet dbResults) throws SQLException {
        ArtistListRS artistsList = new ArtistListRS();
        artistsList.setArtistList(new ArrayList<>());
        long controlId;

        while(dbResults.next()){
            controlId = dbResults.getLong(1);

            Artist newArtist = new Artist(dbResults.getLong(1),
                    dbResults.getString(2),
                    dbResults.getInt(3),
                    new ArrayList<>());

            newArtist.getStyles().add(new Style(dbResults.getLong(4),
                    dbResults.getString(5)));

            while(dbResults.next()){
                if(dbResults.getLong(1) == controlId){
                    newArtist.getStyles().add(new Style(dbResults.getLong(4),
                            dbResults.getString(5)));
                }else{
                    dbResults.previous();
                    break;
                }
            }
            artistsList.getArtistList().add(newArtist);
        }
        return artistsList;
    }



    public static ArtistByStyleRS buildArtistByStyleList(ResultSet dbResults) throws SQLException {
        ArtistByStyleRS resultsList = new ArtistByStyleRS(new ArrayList<>());

        while(dbResults.next()){
            Artist newArtist = new Artist(dbResults.getLong(1),
                    dbResults.getString(2),
                    dbResults.getInt(3),
                    null);
            resultsList.getArtistsByStyle().add(newArtist);
        }
        return resultsList;
    }



    public static ArrayList<Style> buildStylesList(ResultSet dbResults) throws SQLException{
        ArrayList<Style> resultsList = new ArrayList<>();

        while(dbResults.next()){
            Style newStyle = new Style(dbResults.getLong(1), dbResults.getString(2));
            resultsList.add(newStyle);
        }
        return resultsList;
    }



    public static RelatedArtistsRS buildRelatedRS(ResultSet artistResult, ResultSet relatedArtistList) throws SQLException {
        RelatedArtistsRS relatedArtistsRS = new RelatedArtistsRS();

        while(artistResult.next()){
            Artist artistRequested = new Artist(artistResult.getLong(1),
                    artistResult.getString(2),
                    artistResult.getInt(3),
                    null);
            relatedArtistsRS.setArtist(artistRequested);
        }

        while(relatedArtistList.next()){
            Artist artistRelated = new Artist(relatedArtistList.getLong(1),
                    relatedArtistList.getString(2),
                    relatedArtistList.getInt(3),
                    null);
            relatedArtistsRS.getRelatedArtists().add(artistRelated);
        }
        return relatedArtistsRS;
    }
}