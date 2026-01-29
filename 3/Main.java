import controller.MuseumController;
import view.MuseumView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        MuseumView view = new MuseumView();
        MuseumController controller = new MuseumController(view);
        
        boolean running = true;
        
        while (running) {
            view.displayMenu();
            String choice = view.getInput("");
            
            switch (choice) {
                case "1":
                    controller.displayAllExhibits();
                    break;
                    
                case "2":
                    controller.sortExhibitsByName();
                    break;
                    
                case "3":
                    controller.sortExhibitsByAcquisitionDate();
                    break;
                    
                case "4":
                    String author = view.getInput("Enter author name to search: ");
                    controller.searchExhibitsByAuthor(author);
                    break;
                    
                case "5":
                    addNewExhibit(controller, view);
                    break;
                    
                case "6":
                    running = false;
                    view.displayMessage("Thank you for using Museum Exhibit Management System!");
                    break;
                    
                default:
                    view.displayMessage("Invalid option. Please try again.");
            }
        }
    }
    
    private static void addNewExhibit(MuseumController controller, MuseumView view) {
        view.displayAddExhibitMenu();
        String typeChoice = view.getInput("");
        
        String type = "";
        switch (typeChoice) {
            case "1":
                type = "painting";
                break;
            case "2":
                type = "sculpture";
                break;
            default:
                view.displayMessage("Invalid choice. Returning to main menu.");
                return;
        }
        
        String id = view.getInput("Enter exhibit ID: ");
        String name = view.getInput("Enter exhibit name: ");
        String author = view.getInput("Enter author name: ");
        
        LocalDate acquisitionDate = null;
        while (acquisitionDate == null) {
            String dateStr = view.getInput("Enter acquisition date (yyyy-MM-dd): ");
            try {
                acquisitionDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                view.displayMessage("Invalid date format. Please use yyyy-MM-dd format.");
            }
        }
        
        String description = view.getInput("Enter description: ");
        
        controller.addExhibit(type, id, name, author, acquisitionDate, description);
    }
}
