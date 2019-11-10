import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class OgreTest {

    @Test
    void testCreationOfOgre() {
        //Given
        Ogre sut = new Ogre(100, 0, "green", 80, 150, 200);
        //When
        //Then
        Assertions.assertEquals(100, sut.xPos);
        Assertions.assertEquals(0, sut.yPos);
        Assertions.assertEquals("green", sut.colour);
        Assertions.assertEquals(80, sut.head.iq);
        Assertions.assertEquals(150, sut.lifepoints);
        Assertions.assertEquals(200, sut.strength);

    }

    @Test
    void testMoveOgre() {
        //Given
        Ogre sut = new Ogre(100, 0, "green", 80, 150, 200);
        //When
        sut.move(100,100);
        //Then
        Assertions.assertEquals(200, sut.xPos);
        Assertions.assertEquals(100, sut.yPos);
    }

    @Test
    void testSetColourOfOgre() {
        //Given
        Ogre sut = new Ogre(100, 0, "green", 80, 150, 200);
        //When
        sut.setColour("magenta");
        //Then
        Assertions.assertEquals("magenta", sut.colour);
    }



}
