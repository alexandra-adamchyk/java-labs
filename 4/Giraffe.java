public class Giraffe extends Mammal {
    private double neckLength;
    
    public Giraffe(String name, int age, String furColor, double neckLength) {
        super(name, age, furColor);
        this.neckLength = neckLength;
    }
    
    public double getNeckLength() {
        return neckLength;
    }
    
    public void setNeckLength(double neckLength) {
        this.neckLength = neckLength;
    }
    
    @Override
    public String makeSound() {
        return "Hum!";
    }
    
    @Override
    public String feedMilk() {
        return "Giraffe cow feeds milk to calf";
    }
    
    public String eatLeaves() {
        return "Giraffe stretches neck to eat leaves";
    }
}
