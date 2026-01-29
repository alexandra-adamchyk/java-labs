public abstract class Mammal extends Animal {
    private String furColor;
    
    public Mammal(String name, int age, String furColor) {
        super(name, age);
        this.furColor = furColor;
    }
    
    public String getFurColor() {
        return furColor;
    }
    
    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }
    
    public abstract String feedMilk();
}
