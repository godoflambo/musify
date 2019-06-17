package mx.com.coffeedev.spring.boot.springboorcoffeedev.models.basicModels;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Artist{

    private long id;

    private String name;
    private int year;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Style> styles;


    public Artist(long id, String name, int year, List<Style> styles) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.styles = styles;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
