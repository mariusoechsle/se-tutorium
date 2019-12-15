public class Cell {
    //attributes - done
    private int row;
    private int column;
    private boolean wallDown;
    private boolean wallRight;
    private boolean visited;

    //constructor - done
    Cell(int row, int column){
        this.row = row;
        this.column = column;
        wallDown = true;
        wallRight = true;
        visited = false;
    }

    //mark cell as visited - done
    public void visit() {
        visited = true;
    }

    //remove wall on the bottom side - done
    public void removeWallDown() {
        wallDown = false;
    }

    //remove wall of the right side - done
    public void removeWallRight() {
        wallRight = false;
    }


    //remove wall in between of two cells - done
    public void carveThrough(Cell other) {
        //remove above
        if(this.getRow() == other.getRow() + 1 && this.getColumn() == other.getColumn()) {
            other.removeWallDown();
            //remove right
        } else if (this.getRow() == other.getRow() && this.getColumn() +1 == other.getColumn()){
            this.removeWallRight();
            //remove below
        } else if(this.getRow()+1 == other.getRow() && this.getColumn() == other.getColumn()) {
            this.removeWallDown();
            //remove left
        } else if (this.getRow() == other.getRow() && this.getColumn() == other.getColumn()+1) {
            other.removeWallRight();
        }
    }


    //GETTER - done
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public boolean isWallDown() {
        return wallDown;
    }
    public boolean isWallRight() {
        return wallRight;
    }
    public boolean isVisited() {
        return visited;
    }

}
