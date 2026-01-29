package controller;

import model.Exhibit;
import model.Painting;
import model.Sculpture;
import view.MuseumView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MuseumController {
    private List<Exhibit> exhibits;
    private MuseumView view;

    public MuseumController(MuseumView view) {
        this.exhibits = new ArrayList<>();
        this.view = view;
        initializeSampleData();
    }

    private void initializeSampleData() {
        exhibits.add(new Painting("P001", "Starry Night", "Vincent van Gogh", 
                LocalDate.of(2020, 1, 15), "Famous painting with swirling sky", 
                "Oil on canvas", "Post-Impressionism"));
        
        exhibits.add(new Sculpture("S001", "David", "Michelangelo", 
                LocalDate.of(2019, 3, 20), "Renaissance sculpture of biblical hero", 
                "Marble", 517.0, 5660.0));
        
        exhibits.add(new Painting("P002", "Mona Lisa", "Leonardo da Vinci", 
                LocalDate.of(2021, 6, 10), "Portrait with enigmatic smile", 
                "Oil on poplar panel", "Renaissance"));
        
        exhibits.add(new Sculpture("S002", "The Thinker", "Auguste Rodin", 
                LocalDate.of(2020, 11, 5), "Bronze sculpture of a man in deep thought", 
                "Bronze", 189.0, 750.0));
        
        exhibits.add(new Painting("P003", "The Persistence of Memory", "Salvador Dalí", 
                LocalDate.of(2022, 2, 28), "Surrealist painting with melting clocks", 
                "Oil on canvas", "Surrealism"));
    }

    public void displayAllExhibits() {
        view.displayExhibits(exhibits);
    }

    public void sortExhibitsByName() {
        exhibits.sort(Comparator.comparing(Exhibit::getName));
        view.displayMessage("Exhibits sorted by name.");
        view.displayExhibits(exhibits);
    }

    public void sortExhibitsByAcquisitionDate() {
        exhibits.sort(Comparator.comparing(Exhibit::getAcquisitionDate));
        view.displayMessage("Exhibits sorted by acquisition date.");
        view.displayExhibits(exhibits);
    }

    public void searchExhibitsByAuthor(String author) {
        List<Exhibit> results = new ArrayList<>();
        for (Exhibit exhibit : exhibits) {
            if (exhibit.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                results.add(exhibit);
            }
        }
        view.displaySearchResults(results, author);
    }

    public void addExhibit(String type, String id, String name, String author, 
                          LocalDate acquisitionDate, String description) {
        Exhibit exhibit = null;
        
        if (type.equalsIgnoreCase("painting")) {
            String technique = view.getInput("Enter technique: ");
            String style = view.getInput("Enter style: ");
            exhibit = new Painting(id, name, author, acquisitionDate, description, technique, style);
        } else if (type.equalsIgnoreCase("sculpture")) {
            String material = view.getInput("Enter material: ");
            double height = Double.parseDouble(view.getInput("Enter height (cm): "));
            double weight = Double.parseDouble(view.getInput("Enter weight (kg): "));
            exhibit = new Sculpture(id, name, author, acquisitionDate, description, material, height, weight);
        }
        
        if (exhibit != null) {
            exhibits.add(exhibit);
            view.displayMessage("Exhibit added successfully!");
            displayAllExhibits();
        }
    }

    public List<Exhibit> getExhibits() {
        return new ArrayList<>(exhibits);
    }
}
