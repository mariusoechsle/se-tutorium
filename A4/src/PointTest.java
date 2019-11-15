import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PointTest {


    @Test
    void testCreationOfAPointAndGetter() {
        //Given
        //When
        Point sut = new Point(3, 4);
        //Then
        Assertions.assertEquals(3, sut.getXCoord());
        Assertions.assertEquals(4, sut.getYCoord());
    }

}
