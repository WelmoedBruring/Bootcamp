package app;

public class AutoAdvanced {

    // Private zodat de waarde één keer gezet kan worden maar niet kan worden veranderd.
    // Kan alleen waarden krijgen toegekend door de constructor (want alleen methoden in deze class mogen deze
    // variabelen benaderen
    private String brand;
    private int numberOfDoors;
    private String motorType;
    private int torque;

    // Constructor
    public AutoAdvanced(String brand, int numberOfDoors, String motorType, int force, int rpm ) {
        this.brand = brand;
        this.numberOfDoors = numberOfDoors;
        this.motorType = motorType;
        this.torque = calculateTorque(force, rpm);
    }

    private int calculateTorque(int force, int rpm) {
        if(rpm > 0) {
            return (force * 5252)/rpm;
        } else {
            throw new ArithmeticException("Cannot divide by zero!");
        }
    }

    public void printBrand() {
        System.out.println("Car brand: " + brand);
    }

    public void printNumberOfDoors() {
        System.out.println("Number of doors: " + numberOfDoors);
    }

    public void printMotorType() {
        System.out.println("Motor type: " + motorType);
    }

    public void printTorque() {
        System.out.println("Torque: " + torque);
    }
}
