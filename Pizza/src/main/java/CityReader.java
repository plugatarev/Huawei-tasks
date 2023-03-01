import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for reading city data.
 */
public class CityReader {
    private final Scanner input;

    public CityReader(InputStream stream) {
        input = new Scanner(stream);
    }

    /**
     * @return city if exists city block and otherwise null.
     */
    public City readCity() {
        int cityHeight = input.nextInt();
        if (cityHeight == 0) return null;

        int cityWidth = input.nextInt();
        int pizzeriasCount = input.nextInt();

        List<Pizzeria> pizzerias = new ArrayList<>(pizzeriasCount);
        for (int i = 0; i < pizzeriasCount; i++) {
            Coordinate pizzeriaLocation = new Coordinate(input.nextInt() - 1, input.nextInt() - 1);
            int pizzeriaCapacity = input.nextInt();
            pizzerias.add(new Pizzeria(pizzeriaLocation, pizzeriaCapacity));
        }

        return new City(cityHeight, cityWidth, pizzeriasCount, pizzerias);
    }
}
