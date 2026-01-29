import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Enclosure<T extends Animal> implements Serializable {
    private List<T> animals;
    private int capacity;
    private String enclosureType;
    
    public Enclosure(int capacity, String enclosureType) {
        this.capacity = capacity;
        this.enclosureType = enclosureType;
        this.animals = new ArrayList<>();
    }
    
    public boolean addAnimal(T animal) {
        if (animals.size() >= capacity) {
            System.out.println("Enclosure is full! Cannot add " + animal.getName());
            return false;
        }
        
        if (!isValidAnimal(animal)) {
            System.out.println("Invalid animal type for " + enclosureType + " enclosure: " + animal.getClass().getSimpleName());
            return false;
        }
        
        animals.add(animal);
        System.out.println(animal.getName() + " added to " + enclosureType + " enclosure");
        return true;
    }
    
    public boolean removeAnimal(T animal) {
        boolean removed = animals.remove(animal);
        if (removed) {
            System.out.println(animal.getName() + " removed from " + enclosureType + " enclosure");
        } else {
            System.out.println(animal.getName() + " not found in " + enclosureType + " enclosure");
        }
        return removed;
    }
    
    public boolean removeAnimalByName(String name) {
        for (T animal : animals) {
            if (animal.getName().equals(name)) {
                return removeAnimal(animal);
            }
        }
        System.out.println("Animal with name '" + name + "' not found in " + enclosureType + " enclosure");
        return false;
    }
    
    private boolean isValidAnimal(T animal) {
        switch (enclosureType.toLowerCase()) {
            case "lion":
                return animal instanceof Lion;
            case "ungulate":
                return animal instanceof Zebra || animal instanceof Giraffe;
            case "bird":
                return animal instanceof Eagle;
            case "mammal":
                return animal instanceof Mammal;
            case "general":
                return true;
            default:
                return false;
        }
    }
    
    public List<T> getAnimals() {
        return new ArrayList<>(animals);
    }
    
    public int getCurrentCount() {
        return animals.size();
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public String getEnclosureType() {
        return enclosureType;
    }
    
    public boolean isFull() {
        return animals.size() >= capacity;
    }
    
    public void displayAnimals() {
        System.out.println("\n=== " + enclosureType.toUpperCase() + " ENCLOSURE ===");
        System.out.println("Capacity: " + animals.size() + "/" + capacity);
        if (animals.isEmpty()) {
            System.out.println("No animals in this enclosure");
        } else {
            for (int i = 0; i < animals.size(); i++) {
                System.out.println((i + 1) + ". " + animals.get(i));
            }
        }
        System.out.println("========================\n");
    }
    
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Enclosure saved to " + filename);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Animal> Enclosure<T> loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Enclosure<T> enclosure = (Enclosure<T>) ois.readObject();
            System.out.println("Enclosure loaded from " + filename);
            return enclosure;
        }
    }
}
