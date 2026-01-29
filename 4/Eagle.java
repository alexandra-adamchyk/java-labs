public class Eagle extends Bird {
    private double flightAltitude;
    
    public Eagle(String name, int age, double wingspan, double flightAltitude) {
        super(name, age, wingspan);
        this.flightAltitude = flightAltitude;
    }
    
    public double getFlightAltitude() {
        return flightAltitude;
    }
    
    public void setFlightAltitude(double flightAltitude) {
        this.flightAltitude = flightAltitude;
    }
    
    @Override
    public String makeSound() {
        return "Screech!";
    }
    
    @Override
    public String fly() {
        return "Eagle soars at " + flightAltitude + " meters";
    }
    
    public String hunt() {
        return "Eagle dives to catch prey";
    }
}
