package exercises;

public class EnumExample {

    public enum BootCampDays {
        DAYONE,
        DAYTWO;
    }

    // BootCampDays is een zelf aangemaakt datatype
    public static String checkBootCampDaysInfo(BootCampDays bootCampDays) {
        switch(bootCampDays) {
            case DAYONE: default:
                return "Kerst was erg pittig";
            case DAYTWO:
                return "Hotelovernachting was erg pittig";
        }
    }
}
