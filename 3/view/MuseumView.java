package view;

import model.Exhibit;

import java.util.List;

public class MuseumView {
    
    public void displayMenu() {
        System.out.println("\n=== Museum Exhibit Management System ===");
        System.out.println("1. Display all exhibits");
        System.out.println("2. Sort exhibits by name");
        System.out.println("3. Sort exhibits by acquisition date");
        System.out.println("4. Search exhibits by author");
        System.out.println("5. Add new exhibit");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }
    
    public void displayExhibits(List<Exhibit> exhibits) {
        if (exhibits.isEmpty()) {
            System.out.println("No exhibits found.");
            return;
        }
        
        System.out.println("\n=== Exhibits ===");
        for (int i = 0; i < exhibits.size(); i++) {
            System.out.println((i + 1) + ". " + exhibits.get(i));
        }
        System.out.println();
    }
    
    public void displaySearchResults(List<Exhibit> results, String author) {
        System.out.println("\n=== Search Results for Author: " + author + " ===");
        if (results.isEmpty()) {
            System.out.println("No exhibits found for author: " + author);
        } else {
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
        System.out.println();
    }
    
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public String getInput(String prompt) {
        System.out.print(prompt);
        return new java.util.Scanner(System.in).nextLine();
    }
    
    public void displayAddExhibitMenu() {
        System.out.println("\n=== Add New Exhibit ===");
        System.out.println("1. Add Painting");
        System.out.println("2. Add Sculpture");
        System.out.print("Choose exhibit type: ");
    }
}
