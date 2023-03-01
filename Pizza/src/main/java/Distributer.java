import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Pizzeria distributor.
 */
public record Distributer(int cityHeight, int cityWidth, List<Pizzeria> pizzerias) {

    /**
     * City block.
     */
    private static class Block {
        @Getter
        @Setter
        private boolean isMark = false;
        private final List<Pizzeria> possiblePizzerias = new ArrayList<>();

        public Block() {
        }

        public void addPizzeria(Pizzeria pizzeria) {
            possiblePizzerias.add(pizzeria);
        }

        public void markPizzeria(Pizzeria pizzeria) {
            possiblePizzerias.clear();
            addPizzeria(pizzeria);
            isMark = true;
        }

        public void markBlock(Pizzeria pizzeria) {
            possiblePizzerias.clear();
            addPizzeria(pizzeria);
            pizzeria.decreaseAvailableBlocksCapacity();
            isMark = true;
        }
    }

    /**
     * Method determines a pizzeria for each block of the city
     *
     * @return list of results with information for each pizzeria
     */
    public List<Result> distribute() {
        Block[][] city = new Block[cityHeight][cityWidth];
        for (int i = 0; i < cityHeight; i++) {
            for (int j = 0; j < cityWidth; j++) {
                city[i][j] = new Block();
            }
        }
        for (Pizzeria pizzeria : pizzerias) {
            Block rootBlock = city[pizzeria.getLocation().y()][pizzeria.getLocation().x()];
            rootBlock.markPizzeria(pizzeria);
            markPossiblePizzeriaBlocks(city, pizzeria);
        }
        return distribute(city);
    }

    private List<Result> distribute(Block[][] city) {
        while (!isFillCity(city)) {
            if (!markSingleBlocks(city)) {
                Block block = findBlockWithDifferentCandidats(city);
                if (block == null) return null;
                for (Pizzeria p : block.possiblePizzerias) {
                    block.markBlock(p);
                    remarkBlocks(city);
                    distribute(city);
                }
            }
            remarkBlocks(city);
        }

        return getResult(city, pizzerias);
    }

    private Block findBlockWithDifferentCandidats(Block[][] blocks) {
        for (int i = 0; i < cityHeight; i++) {
            for (int j = 0; j < cityWidth; j++) {
                if (blocks[i][j].possiblePizzerias.size() > 1) return blocks[i][j];
            }
        }
        return null;
    }

    private boolean isFillCity(Block[][] blocks) {
        for (int i = 0; i < cityHeight; i++) {
            for (int j = 0; j < cityWidth; j++) {
                if (!blocks[i][j].isMark()) return false;
            }
        }
        return true;
    }

    private List<Result> getResult(Block[][] blocks, List<Pizzeria> pizzerias) {
        List<Result> results = new ArrayList<>();
        for (Pizzeria p : pizzerias) {
            Result result = new Result(northCount(blocks, p), eastCount(blocks, p), southCount(blocks, p), westCount(blocks, p));
            results.add(result);
        }
        return results;
    }

    private int northCount(Block[][] blocks, Pizzeria pizzeria) {
        int count = 0;
        for (int i = pizzeria.getLocation().y() - 1; i >= 0; i--) {
            if (blocks[i][pizzeria.getLocation().x()].possiblePizzerias.get(0).equals(pizzeria)) count++;
        }
        return count;
    }

    private int southCount(Block[][] blocks, Pizzeria pizzeria) {
        int count = 0;
        for (int i = pizzeria.getLocation().y() + 1; i < cityHeight; i++) {
            if (blocks[i][pizzeria.getLocation().x()].possiblePizzerias.get(0).equals(pizzeria)) count++;
        }
        return count;
    }

    private int eastCount(Block[][] blocks, Pizzeria pizzeria) {
        int count = 0;
        for (int i = pizzeria.getLocation().x() + 1; i < cityHeight; i++) {
            if (blocks[pizzeria.getLocation().y()][i].possiblePizzerias.get(0).equals(pizzeria)) count++;
        }
        return count;
    }

    private int westCount(Block[][] blocks, Pizzeria pizzeria) {
        int count = 0;
        for (int i = pizzeria.getLocation().x() - 1; i >= 0; i--) {
            if (blocks[pizzeria.getLocation().y()][i].possiblePizzerias.get(0).equals(pizzeria)) count++;
        }
        return count;
    }

    private void checkRemaining(List<Pizzeria> pizzerias, Block[][] blocks) {
        for (Pizzeria p : pizzerias) {
            List<Block> remaining = getRemainingBlocks(p, blocks);
            if (p.getAvailableBlocksCapacity() != 0 && remaining.size() == p.getAvailableBlocksCapacity())
                markAs(p, remaining);
        }
    }

    private List<Block> getRemainingBlocks(Pizzeria pizzeria, Block[][] blocks) {
        List<Block> remaining = new ArrayList<>();
        for (int i = 0; i < cityWidth; i++) {
            Block block = blocks[pizzeria.getLocation().y()][i];
            if (!block.isMark() && block.possiblePizzerias.contains(pizzeria)) remaining.add(block);
        }
        for (int i = 0; i < cityHeight; i++) {
            Block block = blocks[i][pizzeria.getLocation().x()];
            if (!block.isMark() && block.possiblePizzerias.contains(pizzeria)) remaining.add(block);
        }
        return remaining;
    }

    private void markAs(Pizzeria pizzeria, List<Block> blocks) {
        for (Block b : blocks) {
            b.markBlock(pizzeria);
        }
    }

    private void remarkBlocks(Block[][] city) {
        for (int i = 0; i < cityHeight; i++) {
            for (int j = 0; j < cityWidth; j++) {
                if (!city[i][j].isMark) {
                    List<Pizzeria> possiblePizzerias = city[i][j].possiblePizzerias;
                    possiblePizzerias.removeIf(pizzeria -> pizzeria.getAvailableBlocksCapacity() == 0);
                }
            }
        }
        checkRemaining(pizzerias, city);
    }

    private boolean markSingleBlocks(Block[][] city) {
        boolean flag = false;
        for (int i = 0; i < cityHeight; i++) {
            for (int j = 0; j < cityWidth; j++) {
                if (city[i][j].possiblePizzerias.size() == 1 && !city[i][j].isMark()) {
                    flag = true;
                    markBlockBetweenPizzeriaAndBlock(new Coordinate(j, i), city);
                }
            }
        }
        return flag;
    }

    private static void markBlockBetweenPizzeriaAndBlock(Coordinate block, Block[][] blocks) {
        Block pizzeriaBlock = blocks[block.y()][block.x()];
        Pizzeria pizzeria = pizzeriaBlock.possiblePizzerias.get(0);
        Coordinate root = pizzeriaBlock.possiblePizzerias.get(0).getLocation();
        if (block.x() == root.x()) {
            if (block.y() >= root.y()) {
                for (int i = root.y(); i <= block.y(); i++) {
                    if (!blocks[i][block.x()].isMark()) blocks[i][block.x()].markBlock(pizzeria);
                }
            } else {
                for (int i = block.y(); i <= root.y(); i++) {
                    if (!blocks[i][block.x()].isMark()) blocks[i][block.x()].markBlock(pizzeria);
                }
            }
        } else {
            if (block.x() >= root.x()) {
                for (int i = root.x(); i <= block.x(); i++) {
                    if (!blocks[block.y()][i].isMark()) blocks[block.y()][i].markBlock(pizzeria);
                }
            } else {
                for (int i = block.x(); i <= root.x(); i++) {
                    if (!blocks[block.y()][i].isMark()) blocks[block.y()][i].markBlock(pizzeria);
                }
            }
        }
    }

    private void markPossiblePizzeriaBlocks(Block[][] city, Pizzeria pizzeria) {
        int pizzeriaCapacity = pizzeria.getAvailableBlocksCapacity();

        int curX = pizzeria.getLocation().x();
        for (int i = curX + 1; i <= curX + pizzeriaCapacity && i < cityWidth; i++) {
            if (!city[pizzeria.getLocation().y()][i].isMark()) {
                city[pizzeria.getLocation().y()][i].addPizzeria(pizzeria);
            }
        }

        for (int i = curX - 1; i >= 0 && i >= curX - pizzeriaCapacity; i--) {
            if (!city[pizzeria.getLocation().y()][i].isMark()) {
                city[pizzeria.getLocation().y()][i].addPizzeria(pizzeria);
            }
        }

        int curY = pizzeria.getLocation().y();
        for (int i = curY + 1; i <= curY + pizzeriaCapacity && i < cityHeight; i++) {
            if (!city[i][pizzeria.getLocation().x()].isMark()) {
                city[i][pizzeria.getLocation().x()].addPizzeria(pizzeria);
            }
        }

        for (int i = curY - 1; i >= 0 && i >= curY - pizzeriaCapacity; i--) {
            if (!city[i][pizzeria.getLocation().x()].isMark()) {
                city[i][pizzeria.getLocation().x()].addPizzeria(pizzeria);
            }
        }
    }
}