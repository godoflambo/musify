package mx.com.coffeedev.spring.boot.springboorcoffeedev.dbcontroller;

public class DataBaseHelper {

    public static final String SIMPLE_ARTIST_QUERY = "select * from artist where id = ?;";
    public static final String ARTIST_LIST_QUERY = "select a.ID, a.Name, a.Year, s.Style, p.Name from Artist a" +
            " join style_artist s on a.id = s.Artist join Styles p on p.ID = s.Style order by a.id;";
    public static final String ARTIST_BY_STYLE_QUERY = "select a.ID, a.Name, a.Year, s.Style, p.Name from Artist a " +
            "join style_artist s on a.id = s.Artist join Styles p on p.ID = s.Style where s.Style = ? order by a.id;";
    public static final String STYLES_QUERY = "select * from Styles";
    public static final String RELATED_ARTIST_QUERY = "select * from artist where id = any " +
            "(select related from artists_related where artist = ?)";



    public static final String INSERT_ARTIST_BASIC_INFO = "INSERT INTO `artist` (`ID`, `Name`, `Year`) VALUES (?, ?, ?);";
    public static final String INSERT_STYLES = "INSERT INTO `styles` (`ID`, `Name`) VALUES (?, ?);";
    public static final String INSERT_ARTIST_STYLES_RELATIONS = "INSERT INTO `style_artist` (`Style`, `Artist`) VALUES (?, ?);";
    public static final String INSERT_PEOPLE = "INSERT INTO `people` (`ID`, `Name`, `Years`, `ID_Artist`) VALUES (?, ?, ?, ?);";
    public static final String ASSIGN_PEOPLE = "UPDATE `people` SET `ID_Artist` = ? WHERE `people`.`ID` = ?;";
    public static final String ASSIGN_ARTISTS  = "INSERT INTO `artists_related` (`Artist`, `Related`) VALUES (?, ?);";



    public static final String DELETE_RELATION  = "Delete from artists_related where Artist = ? and Related = ?;";



    public static final String DB_URL = "jdbc:mysql://localhost:3306/hiberus_musify";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "";



    public static final String STATUS_KO = "KO";
    public static final String STATUS_OK = "OK";



    public static final String MESSAGE_BAD_REQUEST = "Bad Request";
    public static final String MESSAGE_ARTIST_INSERT = "Artist inserted successful";
    public static final String MESSAGE_PEOPLE_INSERT = "People inserted successful";
    public static final String MESSAGE_PEOPLE_ASSIGNED = "People Assigned successful";
    public static final String MESSAGE_REL_DELETED = "Relation deleted successful";
    public static final String MESSAGE_QUERY_OK = "Query executed successful";
    public static final String MESSAGE_NO_RESULTS = "No reults found";
    public static final String MESSAGE_ARTIST_RELATED = "Artist related successful";


}
