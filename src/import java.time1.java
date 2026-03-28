import java.time.LocalDateTime;
import java.util.ArrayList;

public class BikeDatabase {
    public static ArrayList<Bike> bikes = new ArrayList<>();

    static {
        bikes.add(new Bike("B001", true, 85, null, "Downtown"));
        bikes.add(new Bike("B002", true, 90, null, "Campus"));
        bikes.add(new Bike("B003", false, 50, LocalDateTime.now(), "Station"));
    }
}