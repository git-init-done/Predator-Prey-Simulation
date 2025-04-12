/*
Expected Behavior: -
1) Doodlebugs (X) will eat adjacent ants (o) if available.
2) Ants will move randomly and breed every 3 steps if there is an adjacent empty cell.
3) Doodlebugs will breed every 8 steps and starve if they do not eat an ant within 3 steps.
4) The population of ants and doodlebugs should demonstrate a dynamic equilibrium, with neither species dominating indefinitely (unless random conditions lead to extinction). 
*/

import java.util.*;

abstract class Organism {
    protected int x;
    protected int y;
    protected int breedSteps;
    protected boolean moved;

    public Organism(int x, int y) {
        this.x = x;
        this.y = y;
        this.breedSteps = 0;
        this.moved = false;
    }

    public abstract void move(Organism[][] grid);

    public abstract char getSymbol();

    public abstract boolean isPrey();

    public abstract void breed(Organism[][] grid);
}

class Ant extends Organism {
    private static final int BREED_TIME = 3;

    public Ant(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(Organism[][] grid) {
        // Get random directions for movement (up, down, left, right)
        List<int[]> directions = Arrays.asList(new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } });
        Collections.shuffle(directions);

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == null) {
                grid[newX][newY] = this;
                grid[x][y] = null;
                x = newX;
                y = newY;
                break;
            }
        }
        moved = true;
        breedSteps++;
    }

    @Override
    public void breed(Organism[][] grid) {
        if (breedSteps >= BREED_TIME) {
            List<int[]> directions = Arrays.asList(new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } });
            Collections.shuffle(directions);
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == null) {
                    grid[newX][newY] = new Ant(newX, newY);
                    breedSteps = 0;
                    break;
                }
            }
        }
    }

    @Override
    public char getSymbol() {
        return 'o';
    }

    @Override
    public boolean isPrey() {
        return true;
    }
}

class Doodlebug extends Organism {
    private static final int BREED_TIME = 8;
    private static final int STARVE_TIME = 3;
    private int starveSteps;

    public Doodlebug(int x, int y) {
        super(x, y);
        this.starveSteps = 0;
    }

    @Override
    public void move(Organism[][] grid) {
        List<int[]> directions = Arrays.asList(new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } });
        // Shuffle directions to randomize movement choice
        Collections.shuffle(directions);
        boolean hasEaten = false;

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                    && grid[newX][newY] instanceof Ant) {
                grid[newX][newY] = this;
                grid[x][y] = null;
                x = newX;
                y = newY;
                starveSteps = 0;
                hasEaten = true;
                break;
            }
        }

        if (!hasEaten) {
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == null) {
                    grid[newX][newY] = this;
                    grid[x][y] = null;
                    x = newX;
                    y = newY;
                    break;
                }
            }
            starveSteps++;
        }

        breedSteps++;
        moved = true;
    }

    // Attempt to breed if the organism has reached the breeding time
    @Override
    public void breed(Organism[][] grid) {
        if (breedSteps >= BREED_TIME) {
            List<int[]> directions = Arrays.asList(new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } });
            Collections.shuffle(directions);
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == null) {
                    grid[newX][newY] = new Doodlebug(newX, newY);
                    breedSteps = 0;
                    break;
                }
            }
        }
    }

    @Override
    public char getSymbol() {
        return 'X';
    }

    @Override
    public boolean isPrey() {
        return false;
    }

    // Remove starving doodlebug from the grid if it hasn't eaten within 3 steps
    public boolean isStarving() {
        return starveSteps >= STARVE_TIME;
    }
}

class GameWorld {
    private final Organism[][] grid;
    private final int rows;
    private final int cols;

    public GameWorld(int rows, int cols, int numAnts, int numDoodlebugs) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Organism[rows][cols];
        initializeGrid(numAnts, numDoodlebugs);
    }

    // Populate grid with random positions for ants and doodlebugs
    private void initializeGrid(int numAnts, int numDoodlebugs) {
        Random random = new Random();
        int totalPlaced = 0;

        // Randomly populate the grid with the specified number of ants and doodlebugs
        while (totalPlaced < numAnts + numDoodlebugs) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            // Check if the cell is empty before placing a new organism
            if (grid[x][y] == null) {
                if (totalPlaced < numDoodlebugs) {
                    grid[x][y] = new Doodlebug(x, y);
                } else {
                    grid[x][y] = new Ant(x, y);
                }
                totalPlaced++;
            }
        }
    }

    public void runSimulation() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printGrid();
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            moveOrganisms();
        }
    }

    private void moveOrganisms() {
        for (Organism[] row : grid) {
            for (Organism organism : row) {
                if (organism != null) {
                    organism.moved = false;
                }
            }
        }

        // First, move all doodlebugs and handle their breeding and starvation
        for (Organism[] row : grid) {
            for (Organism organism : row) {
                if (organism instanceof Doodlebug && !organism.moved) {
                    organism.move(grid);
                    organism.breed(grid);
                    if (((Doodlebug) organism).isStarving()) {
                        // Remove starving doodlebug from the grid
                        grid[organism.x][organism.y] = null;
                    }
                }
            }
        }

        for (Organism[] row : grid) {
            for (Organism organism : row) {
                if (organism instanceof Ant && !organism.moved) {
                    organism.move(grid);
                    organism.breed(grid);
                }
            }
        }
    }

    // Display grid with '.' for empty, 'o' for ants, and 'X' for doodlebugs
    private void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] == null ? "." : grid[i][j].getSymbol());
            }
            System.out.println();
        }
    }
}

public class simulation {
    public static void main(String[] args) {
        GameWorld game = new GameWorld(20, 20, 100, 5);
        game.runSimulation();
    }
}
