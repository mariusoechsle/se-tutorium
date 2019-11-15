public class Rectangle {

    private Point point;
    private int width;
    private int height;

    Rectangle(Point point, int width, int height) {
        this.point = point;
        this.width = width;
        this.height = height;
    }

    public Point getPoint() {
        return point;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isSquare() {
        if (width == height) {
            return true;
        }
        return false;
    }

    public int area() {
        return width*height;
    }

    public boolean contains(Point pointObject) {
        if (pointObject.getXCoord() >= this.getPoint().getXCoord() && pointObject.getXCoord() <= (this.getPoint().getXCoord() + width)
                && pointObject.getYCoord() >= this.getPoint().getYCoord() && pointObject.getYCoord() <= (this.getPoint().getYCoord() + height)
        ) {
            return true;
        }
        return false;
    }

    public boolean contains(Rectangle other) {
        if (other.getPoint().getXCoord() >= this.getPoint().getXCoord()
                && (other.getPoint().getXCoord() + other.getWidth()) <= (this.getPoint().getXCoord() + this.getWidth())
                && other.getPoint().getYCoord() >= this.getPoint().getYCoord()
                && (other.getPoint().getYCoord() + other.getHeight()) <= (this.getPoint().getYCoord() + this.getHeight())) {
            return true;
        }
        return false;
    }


    public boolean intersects(Rectangle other) {
        //Wenn other.links-unterer Punkt überhalb von this.links-unterer Punkt + height liegt
        // oder wenn other.links-unterer Punkt rechts von this.links-unterer Punkt + width liegt
        // oder wenn other.links-unterer Punkt + other.lupunkt + height unterhalb von this.lup
        //

        if (other.getPoint().getYCoord() > (this.getPoint().getYCoord() + this.getHeight())) {
            return false;
        }

        if (other.getPoint().getXCoord() > (this.getPoint().getXCoord() + this.getWidth())) {
            return false;
        }

        if ((other.getPoint().getYCoord() + other.getHeight()) < this.getPoint().getYCoord()) {
            return false;
        }

        if ((other.getPoint().getXCoord() + other.getWidth()) < this.getPoint().getXCoord()) {
            return false;
        }

        return true;

    }
}
