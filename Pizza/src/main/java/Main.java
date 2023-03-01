import java.util.List;

public class Main {
    public static void main(String[] args) {
        CityReader reader = new CityReader(System.in);
        int caseNumber = 1;

        do {
            City newCity = reader.readCity();
            if (newCity == null) break;
            System.out.println("Case " + caseNumber + ":");
            Distributer distributer = new Distributer(newCity.height(), newCity.width(), newCity.pizzerias());
            List<Result> result = distributer.distribute();
            for (Result r : result) System.out.println(r.toString());

            caseNumber++;
            System.out.println();
        } while (true);
    }
}
