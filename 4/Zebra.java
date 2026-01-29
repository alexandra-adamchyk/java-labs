public class Zebra extends Mammal {
    private int stripeCount;
    
    public Zebra(String name, int age, String furColor, int stripeCount) {
        super(name, age, furColor);
        this.stripeCount = stripeCount;
    }
    
    public int getStripeCount() {
        return stripeCount;
    }
    
    public void setStripeCount(int stripeCount) {
        this.stripeCount = stripeCount;
    }
    
    @Override
    public String makeSound() {
        return "Bray!";
    }
    
    @Override
    public String feedMilk() {
        return "Zebra mare feeds milk to foal";
    }
    
    public String run() {
        return "Zebra gallops at high speed";
    }
}
