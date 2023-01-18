import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    public static Courier getDefault() {
        return new Courier("katekatekatekate", "qwerty", "Kate");
    }

    public static Courier randomData() {
        return new Courier(RandomStringUtils.randomAlphanumeric(6), "qwerty", "MonaLiza");
    }

    public static Courier loginData() {
        return new Courier("katekatekate", "qwerty");
    }

    public static Courier notFullData() {
        return new Courier("katekatekate", "");
    }
}
