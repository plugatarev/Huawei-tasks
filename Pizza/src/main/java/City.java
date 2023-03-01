import java.util.List;

/**
 * Class represents city with the height and width of the city and pizzerias.
 */
public record City(int height, int width, int pizzeriasCount, List<Pizzeria> pizzerias) {
}
