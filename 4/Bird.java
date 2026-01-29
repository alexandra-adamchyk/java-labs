public abstract class Bird extends Animal {
    private double wingspan;
    
    public Bird(String name, int age, double wingspan) {
        super(name, age);
        this.wingspan = wingspan;
    }
    
    public double getWingspan() {
        return wingspan;
    }
    
    public void setWingspan(double wingspan) {
        this.wingspan = wingspan;
    }
    
    public abstract String fly();
}
