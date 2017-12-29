package exercises;

public class Auto {


    public void brand(String brand) {
        System.out.println("Brand name is: " + brand);
    }

    public void doors(int numberOfDoors) {
        System.out.println("This car has " + numberOfDoors + " doors");
    }

    public void motorType(String motorType) {
        System.out.println("This car has motor type: " + motorType);
    }

    public void calculateTorque(int force, int rpm) {
        int torque = (force * 5252)/rpm;
        System.out.println("This car's torque: " + torque);
    }

}
