package chapterSix;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class ConditionExcercises {

    public String bootcampAgeChecker(int leeftijd) {
        String result;

        if(leeftijd < 21) {
            result = "Deelnemer is te jong";
        } else if (leeftijd >= 65){
            result = "Deelnemer niet toegelaten, check bij de manager";
        } else {
            result = "Deelnemer toegelaten";
        }

        System.out.println(result);
        return result;
    }

    @Test
    public void bootCampAgeCheckerTest() {
        Assertions.assertThat(bootcampAgeChecker(19)).isEqualTo("Deelnemer is te jong");
        Assertions.assertThat(bootcampAgeChecker(21)).isEqualTo("Deelnemer toegelaten");
        Assertions.assertThat(bootcampAgeChecker(64)).isEqualTo("Deelnemer toegelaten");
        Assertions.assertThat(bootcampAgeChecker(65)).isEqualTo("Deelnemer niet toegelaten, check bij de manager");
    }
}
