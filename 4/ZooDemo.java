import java.io.IOException;

public class ZooDemo {
    public static void main(String[] args) {
        try {
            // Create different types of enclosures
            Enclosure<Lion> lionEnclosure = new Enclosure<>(3, "lion");
            Enclosure<Zebra> zebraEnclosure = new Enclosure<>(4, "ungulate");
            Enclosure<Giraffe> giraffeEnclosure = new Enclosure<>(2, "ungulate");
            Enclosure<Eagle> eagleEnclosure = new Enclosure<>(5, "bird");
            
            // Create animals
            Lion simba = new Lion("Simba", 5, "golden", true);
            Lion nala = new Lion("Nala", 4, "golden", false);
            Lion mufasa = new Lion("Mufasa", 8, "golden", true);
            
            Zebra marty = new Zebra("Marty", 3, "black and white", 52);
            Zebra stripes = new Zebra("Stripes", 2, "black and white", 48);
            
            Giraffe melman = new Giraffe("Melman", 6, "brown patches", 2.5);
            Giraffe longneck = new Giraffe("Longneck", 4, "brown patches", 2.8);
            
            Eagle sam = new Eagle("Sam", 7, 2.1, 3000);
            Eagle sky = new Eagle("Sky", 3, 1.8, 2500);
            
            // Add animals to enclosures
            System.out.println("=== ADDING ANIMALS TO ENCLOSURES ===");
            lionEnclosure.addAnimal(simba);
            lionEnclosure.addAnimal(nala);
            lionEnclosure.addAnimal(mufasa);
            
            zebraEnclosure.addAnimal(marty);
            zebraEnclosure.addAnimal(stripes);
            
            giraffeEnclosure.addAnimal(melman);
            giraffeEnclosure.addAnimal(longneck);
            
            eagleEnclosure.addAnimal(sam);
            eagleEnclosure.addAnimal(sky);
            
            // Try to add invalid animal (should fail)
            System.out.println("\n=== TESTING INVALID ANIMAL ADDITION ===");
            // This will fail at compile time due to generics type safety
            // lionEnclosure.addAnimal(marty); // Compile error - zebra in lion enclosure
            
            // Create a general enclosure to test runtime validation
            Enclosure<Animal> generalEnclosure = new Enclosure<>(5, "lion");
            generalEnclosure.addAnimal(marty); // Should fail - zebra in lion enclosure
            
            // Display all enclosures
            System.out.println("\n=== DISPLAYING ALL ENCLOSURES ===");
            lionEnclosure.displayAnimals();
            zebraEnclosure.displayAnimals();
            giraffeEnclosure.displayAnimals();
            eagleEnclosure.displayAnimals();
            
            // Test animal removal
            System.out.println("=== TESTING ANIMAL REMOVAL ===");
            lionEnclosure.removeAnimalByName("Nala");
            zebraEnclosure.removeAnimalByName("Stripes");
            
            // Display after removal
            lionEnclosure.displayAnimals();
            zebraEnclosure.displayAnimals();
            
            // Save enclosures to files
            System.out.println("\n=== SAVING ENCLOSURES TO FILES ===");
            lionEnclosure.saveToFile("lion_enclosure.dat");
            zebraEnclosure.saveToFile("zebra_enclosure.dat");
            giraffeEnclosure.saveToFile("giraffe_enclosure.dat");
            eagleEnclosure.saveToFile("eagle_enclosure.dat");
            
            // Load enclosures from files
            System.out.println("\n=== LOADING ENCLOSURES FROM FILES ===");
            Enclosure<Lion> loadedLionEnclosure = Enclosure.loadFromFile("lion_enclosure.dat");
            Enclosure<Zebra> loadedZebraEnclosure = Enclosure.loadFromFile("zebra_enclosure.dat");
            
            // Display loaded enclosures
            System.out.println("\n=== DISPLAYING LOADED ENCLOSURES ===");
            loadedLionEnclosure.displayAnimals();
            loadedZebraEnclosure.displayAnimals();
            
            // Test animal sounds
            System.out.println("=== ANIMAL SOUNDS ===");
            for (Lion lion : loadedLionEnclosure.getAnimals()) {
                System.out.println(lion.getName() + " says: " + lion.makeSound());
            }
            
            for (Zebra zebra : loadedZebraEnclosure.getAnimals()) {
                System.out.println(zebra.getName() + " says: " + zebra.makeSound());
            }
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
