package exercises;

import org.testng.annotations.Test;

public class CallEnumExample {

    @Test
    public void printBootCampDays() {
        System.out.println(EnumExample.checkBootCampDaysInfo(EnumExample.BootCampDays.DAYONE));
        System.out.println(EnumExample.checkBootCampDaysInfo(EnumExample.BootCampDays.DAYTWO));
    }
}
