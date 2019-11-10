import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

//4 Cases: Check if fights happen
//9 Cases: Check Companions and Winners, all possibilities
//2 Cases: This-Ogre dies and Other-Ogre dies

public class OgreMeetTest {

    //Check if Fights happened (4 Cases)
    @Test
    void testBothHaveNoCompanion() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 200);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 180);
        //When
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(testOgre2, testOgre1.companion);
        Assertions.assertEquals(testOgre1, testOgre2.companion);
    }

    @Test
    void testFirstHasCompanion() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 200);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 180);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 100);
        //When
        testOgre1.meet(testOgre3);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(30, testOgre2.lifepoints);
        Assertions.assertEquals(150, testOgre1.lifepoints);
    }

    @Test
    void testSecondHasCompanion() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 200);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 200);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 100);
        //When
        testOgre2.meet(testOgre3);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(150, testOgre2.lifepoints);
        Assertions.assertEquals(50, testOgre1.lifepoints);
    }

    @Test
    void testBothHaveCompanion() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 100, 40);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 100, 30);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 100, 20);
        Ogre testOgre4 = new Ogre(100, 0, "green", 80, 100, 20);
        //When
        testOgre1.meet(testOgre4);
        testOgre2.meet(testOgre3);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(testOgre4, testOgre1.companion);
        Assertions.assertEquals(testOgre3, testOgre2.companion);
        Assertions.assertEquals(90, testOgre2.lifepoints);
        Assertions.assertEquals(100, testOgre1.lifepoints);
    }

    //Check winners of Fights (9 Cases)

    //1-3: Other-Ogre always wins
    @Test
    void testFirstHasCompanionAndLooses() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 110);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 50);
        //When
        testOgre1.meet(testOgre3);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(140, testOgre1.lifepoints);
        Assertions.assertEquals(150, testOgre2.lifepoints);
    }

    @Test
    void testBothHaveCompanionsSecondWins() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 110);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre4 = new Ogre(100, 0, "green", 80, 150, 50);
        //When
        testOgre1.meet(testOgre3);
        testOgre2.meet(testOgre4);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(90, testOgre1.lifepoints);
        Assertions.assertEquals(150, testOgre2.lifepoints);
    }

    @Test
    void testSecondOgreHasCompanionAndWins() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 110);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 50);
        //When
        testOgre2.meet(testOgre3);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(40, testOgre1.lifepoints);
        Assertions.assertEquals(150, testOgre2.lifepoints);
    }

    //This-Ogre always wins
    @Test
    void testFirstHasCompanionAndWins() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 110);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 70);
        //When
        testOgre1.meet(testOgre3);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(150, testOgre1.lifepoints);
        Assertions.assertEquals(140, testOgre2.lifepoints);
    }

    @Test
    void testBothHaveCompanionsFirstWins() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 60);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre4 = new Ogre(100, 0, "green", 80, 150, 50);
        //When
        testOgre1.meet(testOgre3);
        testOgre2.meet(testOgre4);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(150, testOgre1.lifepoints);
        Assertions.assertEquals(140, testOgre2.lifepoints);
    }

    @Test
    void testSecondOgreHasCompanionAndLooses() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 170);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 110);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 50);
        //When
        testOgre2.meet(testOgre3);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(150, testOgre1.lifepoints);
        Assertions.assertEquals(140, testOgre2.lifepoints);
    }

    //Ogers don't fight
    @Test
    void testFirstHasCompanionNoFight() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 110);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 60);
        //When
        testOgre1.meet(testOgre3);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(150, testOgre1.lifepoints);
        Assertions.assertEquals(150, testOgre2.lifepoints);
    }

    @Test
    void testBothHaveCompanionsNoFight() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre4 = new Ogre(100, 0, "green", 80, 150, 50);
        //When
        testOgre1.meet(testOgre3);
        testOgre2.meet(testOgre4);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(150, testOgre1.lifepoints);
        Assertions.assertEquals(150, testOgre2.lifepoints);
    }

    @Test
    void testSecondOgreHasCompanionNoFight() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 170);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 110);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 60);
        //When
        testOgre2.meet(testOgre3);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(150, testOgre1.lifepoints);
        Assertions.assertEquals(150, testOgre2.lifepoints);
    }

    //Check if dead
    @Test
    void testThisOgreDies() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre4 = new Ogre(100, 0, "green", 80, 150, 250);
        //When
        testOgre1.meet(testOgre3);
        testOgre2.meet(testOgre4);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(-50, testOgre1.lifepoints);
        Assertions.assertEquals("black", testOgre1.colour);
        Assertions.assertEquals("black", testOgre1.head.colour);
    }

    @Test
    void testOtherOgreDies() {
        //Given
        Ogre testOgre1 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre2 = new Ogre(100, 0, "green", 80, 150, 50);
        Ogre testOgre3 = new Ogre(100, 0, "green", 80, 150, 250);
        Ogre testOgre4 = new Ogre(100, 0, "green", 80, 150, 50);
        //When
        testOgre1.meet(testOgre3);
        testOgre2.meet(testOgre4);
        testOgre1.meet(testOgre2);
        //Then
        Assertions.assertEquals(-50, testOgre2.lifepoints);
        Assertions.assertEquals("black", testOgre2.colour);
        Assertions.assertEquals("black", testOgre2.head.colour);
    }



}
