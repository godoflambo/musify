package mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels;

public class Style {

    private long id;
    private String name;

    public Style(long id, String name) {
        this.id = id;
        this.name = name;
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
}
