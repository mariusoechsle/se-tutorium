import java.util.Random;

public class Labyrinth {

    //attributes - done
    private int height;
    private int width;
    Random numberGenerator = new Random();
    private Cell cells[][];

    //constructor - done
    Labyrinth(int height, int width) {
        this.height = height;
        this.width = width;
        cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i,j);
            }
        }
    }

    //return a cell with a certain position - done
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    //find all neighbors and return an array of cells - done
    public Cell[] getNeighbors(Cell cell) {
        int index = 0;
        Cell neighbors[] = new Cell[4];
        //check top neighbour
        if (cell.getRow() > 0) {
            neighbors[index] = cells[cell.getRow() - 1][cell.getColumn()];
            index++;
        }
        //check left neighbor
        if (cell.getColumn() > 0) {
            neighbors[index] = cells[cell.getRow()][cell.getColumn()-1];
            index++;
        }
        //check bottom neighbor
        if (cell.getRow() < height - 1) {
            neighbors[index] = cells[cell.getRow() + 1][cell.getColumn()];
            index++;
        }
        //check right neighbor
        if (cell.getColumn() < width - 1) {
            neighbors[index] = cells[cell.getRow()][cell.getColumn()+1];
            index++;
        }

        //if there are only 3 neighbor cells, copy cells into smaller array
        if (index == 3) {
            Cell neighbors2[] = new Cell[3];
            for (int i = 0; i < neighbors2.length; i++) {
                neighbors2[i] = neighbors[i];
            }
            return neighbors2;
            //if there are only 2 neighbor cells, copy cells into smaller array
        } else if (index == 2) {
            Cell neighbors2[] = new Cell[2];
            for (int i = 0; i < neighbors2.length; i++) {
                neighbors2[i] = neighbors[i];
            }
            return neighbors2;
        }
        return neighbors;
    }

    //find all neighbors that have been visited/not visited and return an array of cells - done
    public Cell[] getNeighbors(Cell cell, boolean visited) {
        //get all neighbors
        Cell neighbors[] = getNeighbors(cell);
        //visited-Counter
        int visitedNeighborCounter = 0;
        //count visited neighbors
        for (int i = 0; i < neighbors.length;i++) {
            if (neighbors[i].isVisited()) {
                visitedNeighborCounter++;
            }
        }
        //if visited==true
        if (visited == true) {
            Cell neighbors2[] = new Cell[visitedNeighborCounter];
            for (int i = 0; i < neighbors2.length; i++) {
                if (neighbors[i].isVisited()) {
                    neighbors2[i] = neighbors[i];
                }
            }
            return neighbors2;
        }
        //if visited==false
        Cell neighbors2[] = new Cell[neighbors.length - visitedNeighborCounter];
        for (int i = 0; i < neighbors2.length; i++) {
            if (neighbors[i].isVisited() == false) {
                neighbors2[i] = neighbors[i];
            }
        }
        return neighbors2;
    }

    //choose random unvisited neighbor - done
    public Cell randomUnvisitedNeighbor(Cell cell) {
        //find all unvisited neighbors and store them in an array
        Cell unvisitedNeighbors[] = getNeighbors(cell, false);

        //if there are no unvisited neighbors, return null
        if (unvisitedNeighbors.length == 0) {
            return null;
        }
        //choose randomly one of the unvisited neighbors and return this cell
        Cell result = unvisitedNeighbors[numberGenerator.nextInt(unvisitedNeighbors.length)];
        return result;
    }

    //kill step: as long as there are unvisited neighbors, walk and connect
    public void walkRandom(Cell start) {
        //2.1 mark start as visited
        start.visit();
        //2.2 find unvisited neighbors of start cell
        Cell[] unvisitedNeighborsOfStart = getNeighbors(start, false);
        //2.3 if there are no unvisited neighbors, go hunting --> maybe delete this step here
        if (unvisitedNeighborsOfStart.length == 0) {
            return;
        }
        //3.1 choose random univisited neighbor
        Cell randomUnvisitedNeighbor = randomUnvisitedNeighbor(start);
        //3.2 connect start and random unvisited neighbot
        start.carveThrough(randomUnvisitedNeighbor);
        //4.1 choose random unvisited neighbor as new start
        walkRandom(randomUnvisitedNeighbor);
    }

    //returns a cell that has been visited now, after being hunted, if everything has been visited, return null
    public Cell hunt() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isVisited() == false && getNeighbors(cells[i][j], true).length >= 1) {
                    //mark the found cell as visited
                    cells[i][j].visit();
                    //find all visited neighbors
                    Cell[] visitedNeighbors = getNeighbors(cells[i][j], true);
                    //connect this cell and one of the visited neighbors (choose randomly)
                    cells[i][j].carveThrough(visitedNeighbors[numberGenerator.nextInt(visitedNeighbors.length)]);
                    return cells[i][j];
                }
            }
        }
        return null;
    }


    public void createLabyrinth() {
        //choose a random cell as starting point
        int x = numberGenerator.nextInt(width-1);
        int y = numberGenerator.nextInt(height-1);
        //first walk until stop
        walkRandom(cells[x][y]);
    }


    //GETTER - done
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }


}
