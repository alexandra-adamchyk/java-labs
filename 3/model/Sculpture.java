package model;

public class Sculpture extends Exhibit {
    private String material;
    private double height;
    private double weight;

    public Sculpture(String id, String name, String author, java.time.LocalDate acquisitionDate,
                    String description, String material, double height, double weight) {
        super(id, name, author, acquisitionDate, description);
        this.material = material;
        this.height = height;
        this.weight = weight;
    }

    public String getMaterial() {
        return material;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String getType() {
        return "Sculpture";
    }

    @Override
    public String toString() {
        return String.format("%s, Material: %s, Height: %.2f cm, Weight: %.2f kg", 
                super.toString(), material, height, weight);
    }
}
