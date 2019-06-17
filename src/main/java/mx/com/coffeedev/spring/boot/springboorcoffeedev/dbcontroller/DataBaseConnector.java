package mx.com.coffeedev.spring.boot.springboorcoffeedev.dbcontroller;

import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels.Artist;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels.People;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels.Style;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.general.GeneralRS;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.general.NMRelation;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.responses.ArtistByStyleRS;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.responses.ArtistListRS;
import mx.com.coffeedev.spring.boot.springboorcoffeedev.models.responses.RelatedArtistsRS;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnector {

    private String url;
    private String user;
    private String pass;


    public DataBaseConnector(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }


    public ArtistListRS getArtistList(){
        ArtistListRS artistList = null;

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement st = con.prepareStatement(DataBaseHelper.ARTIST_LIST_QUERY);
             ResultSet rs = st.executeQuery()){

             artistList = ObjectBuilder.buildArtistList(rs);
             artistList.setStatusInfo(DataBaseHelper.STATUS_OK, DataBaseHelper.MESSAGE_QUERY_OK);

             if(artistList.getArtistList().size() == 0){
                artistList.setStatusInfo(DataBaseHelper.STATUS_OK, DataBaseHelper.MESSAGE_NO_RESULTS);
                return artistList;
             }
        } catch (SQLException e) {
            return new ArtistListRS(null, DataBaseHelper.STATUS_KO, DataBaseHelper.MESSAGE_BAD_REQUEST);
        }
        return artistList;
    }



    public ArrayList<Style> getStyles()throws SQLException {
        ArrayList<Style> stylesList = null;

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement st = con.prepareStatement(DataBaseHelper.STYLES_QUERY)){

            ResultSet rs = st.executeQuery();

            stylesList = ObjectBuilder.buildStylesList(rs);
        }
        return stylesList;
    }



    public ArtistByStyleRS getArtistByStyle(long id){
        ArtistByStyleRS artistList = null;

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement st = con.prepareStatement(DataBaseHelper.ARTIST_BY_STYLE_QUERY)){

            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            artistList = ObjectBuilder.buildArtistByStyleList(rs);

            if(artistList.getArtistsByStyle().size() == 0){
                artistList.setStatusInfo(DataBaseHelper.STATUS_OK, DataBaseHelper.MESSAGE_NO_RESULTS);
                return  artistList;
            }
            artistList.setStatusInfo(DataBaseHelper.STATUS_OK, DataBaseHelper.MESSAGE_QUERY_OK);
        } catch (SQLException e) {
            return new ArtistByStyleRS(null, DataBaseHelper.STATUS_KO, DataBaseHelper.MESSAGE_BAD_REQUEST);
        }

        return artistList;
    }



    public RelatedArtistsRS getRelatedArtists(long id){
        RelatedArtistsRS relatedArtistList = null;

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement relatedArtistQuery = con.prepareStatement(DataBaseHelper.RELATED_ARTIST_QUERY);
             PreparedStatement simpleArtistQuery = con.prepareStatement(DataBaseHelper.SIMPLE_ARTIST_QUERY)){

            simpleArtistQuery.setLong(1, id);
            relatedArtistQuery.setLong(1, id);

            ResultSet artistResultSet = simpleArtistQuery.executeQuery();
            ResultSet relatedResultSet = relatedArtistQuery.executeQuery();

            relatedArtistList = ObjectBuilder.buildRelatedRS(artistResultSet, relatedResultSet);
            if (relatedArtistList.getRelatedArtists().size() == 0){
                relatedArtistList.setStatusInfo(DataBaseHelper.STATUS_OK, DataBaseHelper.MESSAGE_NO_RESULTS);
                return relatedArtistList;
            }
            relatedArtistList.setStatusInfo(DataBaseHelper.STATUS_OK, DataBaseHelper.MESSAGE_QUERY_OK);

        } catch (SQLException e) {
            return new RelatedArtistsRS(null, null, DataBaseHelper.STATUS_KO, DataBaseHelper.MESSAGE_BAD_REQUEST);
        }
        return relatedArtistList;
    }


    public GeneralRS insertPeople(People people){
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement st = con.prepareStatement(DataBaseHelper.INSERT_PEOPLE)){

            st.setLong(1, people.getId());
            st.setString(2, people.getName());
            st.setInt(3, people.getYears());

            if (people.getId_artist() == 0)st.setNull(4, Types.BIGINT);
            else st.setLong(4, people.getId_artist());

            st.executeUpdate();

            return new GeneralRS(DataBaseHelper.STATUS_OK, DataBaseHelper.MESSAGE_PEOPLE_INSERT);
        } catch (SQLException e) {
            return new GeneralRS(DataBaseHelper.STATUS_KO, DataBaseHelper.MESSAGE_BAD_REQUEST);
        }
    }


    public GeneralRS assignPeople(NMRelation relation){
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement st = con.prepareStatement(DataBaseHelper.ASSIGN_PEOPLE)){

            st.setLong(1, relation.getId2());
            st.setLong(2, relation.getId1());

            st.executeUpdate();

            return new GeneralRS(DataBaseHelper.STATUS_OK, DataBaseHelper.MESSAGE_PEOPLE_ASSIGNED);
        } catch (SQLException e) {
            return new GeneralRS(DataBaseHelper.STATUS_KO, DataBaseHelper.MESSAGE_BAD_REQUEST);
        }
    }

    public GeneralRS relateArtists(NMRelation relation){
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement st = con.prepareStatement(DataBaseHelper.ASSIGN_ARTISTS)){

            st.setLong(1, relation.getId1());
            st.setLong(2, relation.getId2());

            st.executeUpdate();

            return new GeneralRS(DataBaseHelper.STATUS_OK, DataBaseHelper.MESSAGE_PEOPLE_ASSIGNED);
        } catch (SQLException e) {
            return new GeneralRS(DataBaseHelper.STATUS_KO, DataBaseHelper.MESSAGE_BAD_REQUEST);
        }
    }


    public GeneralRS insertArtist(Artist artist){
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement st = con.prepareStatement(DataBaseHelper.INSERT_ARTIST_BASIC_INFO)){

            st.setLong(1, artist.getId());
            st.setString(2, artist.getName());
            st.setInt(3, artist.getYear());

            st.executeUpdate();

            if(artist.getStyles() != null && artist.getStyles().size() > 0){
                insertStyles((ArrayList<Style>) artist.getStyles(), artist.getId());
            }
            return new GeneralRS(DataBaseHelper.STATUS_OK, DataBaseHelper.MESSAGE_ARTIST_INSERT);
        } catch (SQLException e) {
            return new GeneralRS(DataBaseHelper.STATUS_KO, DataBaseHelper.MESSAGE_BAD_REQUEST);
        }
    }


    public void insertStyles(ArrayList<Style> styles, long artistId) throws SQLException{
        ArrayList<Style> knownStyles = getStyles();

        for (Style style : styles){
            insertInfo(style, knownStyles);
            createRelations(style, artistId);
        }
    }


    private void createRelations(Style style, long artistId) throws SQLException{
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement st = con.prepareStatement(DataBaseHelper.INSERT_ARTIST_STYLES_RELATIONS)) {

            st.setLong(1, style.getId());
            st.setLong(2, artistId);

            st.executeUpdate();
        }
    }


    private void insertInfo(Style style, ArrayList<Style> knownStyles) throws SQLException {
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement st = con.prepareStatement(DataBaseHelper.INSERT_STYLES)) {

            for(Style knownStyle : knownStyles){
                if(style.getId() == knownStyle.getId()){
                    return;
                }
            }
            st.setLong(1, style.getId());
            st.setString(2, style.getName());

            st.executeUpdate();
        }
    }


    public GeneralRS deleteArtistRelation(NMRelation relation) {
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement st = con.prepareStatement(DataBaseHelper.DELETE_RELATION)){

            st.setLong(1, relation.getId1());
            st.setLong(2, relation.getId2());

            st.executeUpdate();

            return new GeneralRS(DataBaseHelper.STATUS_OK, DataBaseHelper.MESSAGE_REL_DELETED);
        } catch (SQLException e) {
            return new GeneralRS(DataBaseHelper.STATUS_KO, DataBaseHelper.MESSAGE_BAD_REQUEST);
        }
    }
}
