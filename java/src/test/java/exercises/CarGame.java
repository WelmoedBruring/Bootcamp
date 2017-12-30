package exercises;

import app.AutoAdvanced;
import org.testng.annotations.Test;

public class CarGame {

    @Test
    public void checkCar() {

        Auto auto = new Auto();
        auto.brand("Volvo");
        auto.doors(4);
        auto.motorType("Diesel");
    }

    @Test
    public void checkBetterCar() {

        AutoAdvanced betterCar = new AutoAdvanced(
                "Tesla",
                4,
                "Super-duper fancy-schmancy",
                1000,
                2500);

        betterCar.printBrand();
        betterCar.printNumberOfDoors();
        betterCar.printMotorType();
        betterCar.printTorque();
    }
}
