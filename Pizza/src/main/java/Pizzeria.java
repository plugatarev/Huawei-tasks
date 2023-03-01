import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Pizzeria information class.
 */
@AllArgsConstructor
public class Pizzeria {
    @Getter private final Coordinate location;
    @Getter @Setter private int availableBlocksCapacity;

    public void decreaseAvailableBlocksCapacity() {
        availableBlocksCapacity--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizzeria pizzeria = (Pizzeria) o;
        return Objects.equals(location, pizzeria.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
}
