public class Lion extends Mammal {
    private boolean isAlpha;
    
    public Lion(String name, int age, String furColor, boolean isAlpha) {
        super(name, age, furColor);
        this.isAlpha = isAlpha;
    }
    
    public boolean isAlpha() {
        return isAlpha;
    }
    
    public void setAlpha(boolean alpha) {
        isAlpha = alpha;
    }
    
    @Override
    public String makeSound() {
        return "Roar!";
    }
    
    @Override
    public String feedMilk() {
        return "Lioness feeds milk to cubs";
    }
    
    public String hunt() {
        return isAlpha ? "Alpha lion leads the hunt" : "Lion follows the pride";
    }
}
