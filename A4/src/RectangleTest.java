import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RectangleTest {

    //Testfälle:
    // 1. Erstellung von Rechteck und Getter
    // 2. IsSquare-Methode true     3. IsSquare-Methode false
    // 4. area-Methode
    // 5. contains(Point) false     6. contains(Point) true
    // 7. contains(other Rechteck) false    8. contains(other Rechteck) true
    // 9.-12. intersect(other Rechteck) --> 4x false --> oben, rechts, unten, links
    // 13. intersects true

    @Test
    void testCreationOfARectangleAndGetter() {
        //Given
        Point point = new Point(2, 4);
        //When
        Rectangle sut = new Rectangle(point, 5, 1);
        //Then
        Assertions.assertEquals(2, sut.getPoint().getXCoord());
        Assertions.assertEquals(4, sut.getPoint().getYCoord());
        Assertions.assertEquals(5, sut.getWidth());
        Assertions.assertEquals(1, sut.getHeight());
    }

    @Test
    void testIsSquareTrue() {
        //Given
        Point point = new Point(2, 4);
        //When
        Rectangle sut = new Rectangle(point, 2, 2);
        //Then
        Assertions.assertEquals(true, sut.isSquare());
    }

    @Test
    void testIsSquareIsFalse() {
        //Given
        Point point = new Point(2, 4);
        //When
        Rectangle sut = new Rectangle(point, 2, 1);
        //Then
        Assertions.assertEquals(false, sut.isSquare());
    }

    @Test
    void testAreaMethod() {
        //Given
        Point point = new Point(2, 4);
        //When
        Rectangle sut = new Rectangle(point, 3,2);
        //Then
        Assertions.assertEquals(6, sut.area());
    }

    @Test
    void testContainsPointFalse() {
        //Given
        Point point1 = new Point(1, 1);
        Point point2 = new Point(3, 3);
        Rectangle sut1 = new Rectangle(point1, 2,1);
        //When
        boolean result = sut1.contains(point2);


        //Then
        Assertions.assertEquals(false, sut1.contains(point2));
    }

    @Test
    void testContainsPointTrue() {
        //Given
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 2);
        //When
        Rectangle sut1 = new Rectangle(point1, 2,1);

        //Then
        Assertions.assertEquals(true, sut1.contains(point2));
    }

    @Test
    void testContainsOtherRectangleFalse() {
        //Given
        Point point1 = new Point(4, 1);
        Point point2 = new Point(5, 1);
        //When
        Rectangle sut1 = new Rectangle(point1, 3,2);
        Rectangle sut2 = new Rectangle(point2, 2,3);

        //Then
        Assertions.assertEquals(false, sut1.contains(sut2));
    }

    @Test
    void testContainsOtherRectangleTrue() {
        //Given
        Point point1 = new Point(4, 1);
        Point point2 = new Point(5, 1);
        //When
        Rectangle sut1 = new Rectangle(point1, 3,2);
        Rectangle sut2 = new Rectangle(point2, 2,1);

        //Then
        Assertions.assertEquals(true, sut1.contains(sut2));
    }

    @Test
    void testIntersectsOtherRectangleFalseAbove() {
        //Given
        Point point1 = new Point(2, 2);
        Point point2 = new Point(2, 4);
        //When
        Rectangle sut1 = new Rectangle(point1, 1,1);
        Rectangle sut2 = new Rectangle(point2, 1,1);

        //Then
        Assertions.assertEquals(false, sut1.intersects(sut2));
    }

    @Test
    void testIntersectsOtherRectangleFalseRight() {
        //Given
        Point point1 = new Point(2, 2);
        Point point2 = new Point(4, 2);
        //When
        Rectangle sut1 = new Rectangle(point1, 1,1);
        Rectangle sut2 = new Rectangle(point2, 1,1);

        //Then
        Assertions.assertEquals(false, sut1.intersects(sut2));
    }

    @Test
    void testIntersectsOtherRectangleFalseBelow() {
        //Given
        Point point1 = new Point(2, 2);
        Point point2 = new Point(2, 0);
        //When
        Rectangle sut1 = new Rectangle(point1, 1,1);
        Rectangle sut2 = new Rectangle(point2, 1,1);

        //Then
        Assertions.assertEquals(false, sut1.intersects(sut2));
    }

    @Test
    void testIntersectsOtherRectangleFalseLeft() {
        //Given
        Point point1 = new Point(2, 2);
        Point point2 = new Point(0, 2);
        //When
        Rectangle sut1 = new Rectangle(point1, 1,1);
        Rectangle sut2 = new Rectangle(point2, 1,1);

        //Then
        Assertions.assertEquals(false, sut1.intersects(sut2));
    }

    @Test
    void testIntersectsOtherRectangleTrue() {
        //Given
        Point point1 = new Point(2, 2);
        Point point2 = new Point(3, 2);
        //When
        Rectangle sut1 = new Rectangle(point1, 1,1);
        Rectangle sut2 = new Rectangle(point2, 1,1);

        //Then
        Assertions.assertEquals(true, sut1.intersects(sut2));
    }








}
