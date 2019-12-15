public class Test {

    public static void main(String args[]) {

        Cell cell = new Cell(2,2);
        Labyrinth labyrinth = new Labyrinth(5,5);
        Cell neighbors[] = labyrinth.getNeighbors(cell);
        int rowNeighborTop = neighbors[0].getRow();
        int columnNeighborRight = neighbors[3].getColumn();

        System.out.println(rowNeighborTop);
        System.out.println((columnNeighborRight));
    }
}
