package model;

import java.time.LocalDate;

public abstract class Exhibit {
    protected String id;
    protected String name;
    protected String author;
    protected LocalDate acquisitionDate;
    protected String description;

    public Exhibit(String id, String name, String author, LocalDate acquisitionDate, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.acquisitionDate = acquisitionDate;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Author: %s, Type: %s, Acquisition Date: %s, Description: %s",
                id, name, author, getType(), acquisitionDate, description);
    }
}
