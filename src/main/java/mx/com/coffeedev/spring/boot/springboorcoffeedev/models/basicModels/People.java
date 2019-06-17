package mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels;

public class People {

    private long id;
    private String name;
    private int years;
    private long id_artist;

    public People(long id, String name, int years, long id_artist) {
        this.id = id;
        this.name = name;
        this.years = years;
        this.id_artist = id_artist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public long getId_artist() {
        return id_artist;
    }

    public void setId_artist(long id_artist) {
        this.id_artist = id_artist;
    }
}
