import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DistributeTest {
    @Test
    public void oneBlockInCity() {
        List<Pizzeria> pizzerias = List.of(new Pizzeria(new Coordinate(0, 0), 1));
        Distributer distributer = new Distributer(1, 1, pizzerias);

        List<Result> results = distributer.distribute();

        Assert.assertEquals(results.size(), 1);
        Assert.assertEquals(results.get(0), new Result(0, 0, 0, 0));
    }

    @Test
    public void severalVariantForPizzerias() {
        List<Pizzeria> pizzerias = List.of(
                new Pizzeria(new Coordinate(0, 0), 1),
                new Pizzeria(new Coordinate(1, 1), 1));
        Distributer distributer = new Distributer(2, 2, pizzerias);

        List<Result> results = distributer.distribute();

        Assert.assertEquals(results.size(), 2);
        Assert.assertTrue(results.contains(new Result(0, 1, 0, 0)));
        Assert.assertTrue(results.contains(new Result(0, 0, 0, 1)));
    }

    @Test
    public void testSameCapacityPizzeriasCapacity() {
        List<Pizzeria> pizzerias = List.of(
                new Pizzeria(new Coordinate(0, 0), 3),
                new Pizzeria(new Coordinate(2, 1), 1),
                new Pizzeria(new Coordinate(1, 2), 3),
                new Pizzeria(new Coordinate(0, 3), 5)
        );
        Distributer distributer = new Distributer(4, 4, pizzerias);

        List<Result> results = distributer.distribute();

        Assert.assertEquals(results.size(), 4);
        Assert.assertTrue(results.contains(new Result(0, 3, 0, 0)));
        Assert.assertTrue(results.contains(new Result(0, 1, 0, 0)));
        Assert.assertTrue(results.contains(new Result(1, 2, 0, 0)));
        Assert.assertTrue(results.contains(new Result(2, 3, 0, 0)));
    }

    @Test
    public void exampleTest() {
        List<Pizzeria> pizzerias = List.of(
                new Pizzeria(new Coordinate(0, 2), 2),
                new Pizzeria(new Coordinate(1, 0), 4),
                new Pizzeria(new Coordinate(1, 4), 4),
                new Pizzeria(new Coordinate(2, 2), 5),
                new Pizzeria(new Coordinate(4, 1), 2),
                new Pizzeria(new Coordinate(4, 3), 2)
                );
        Distributer distributer = new Distributer(5, 5, pizzerias);

        List<Result> results = distributer.distribute();

        Assert.assertEquals(results.size(), 6);
        Assert.assertTrue(results.contains(new Result(1, 0, 1, 0)));
        Assert.assertTrue(results.contains(new Result(0, 2, 1, 1)));
        Assert.assertTrue(results.contains(new Result(1, 2, 0, 1)));
        Assert.assertTrue(results.contains(new Result(1, 2, 1, 1)));
        Assert.assertTrue(results.contains(new Result(1, 0, 0, 1)));
        Assert.assertTrue(results.contains(new Result(0, 0, 1, 1)));
    }
}
