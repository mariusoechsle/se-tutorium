import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class ElixirTest {
    @Test
    void testCreationOfElixir() {
        //Given
        Elixir sut = new Elixir("super");
        //When
        //Then
        Assertions.assertEquals("super", sut.characteristic);
    }

    @Test
    void testHealingOfOgreEven() {
        //Given
        Elixir testElixir = new Elixir("superdrink");
        Ogre testOgre = new Ogre(100, 0, "green", 80, 150, 200);
        //When
        testOgre.drinkElixir(testElixir);
        //Then
        Assertions.assertEquals(160, testOgre.lifepoints);
    }

    @Test
    void testHealingOfOgreOdd() {
        //Given
        Elixir testElixir = new Elixir("superdrink2");
        Ogre testOgre = new Ogre(100, 0, "green", 80, 150, 200);
        //When
        testOgre.drinkElixir(testElixir);
        //Then
        Assertions.assertEquals(172, testOgre.lifepoints);
    }

    @Test
    void testMakingOgreUnvisible() {
        //Given
        Elixir testElixir = new Elixir("Maa");
        Ogre testOgre = new Ogre(100, 0, "green", 80, 150, 200);
        //When
        testOgre.drinkElixir(testElixir);
        //Then
        Assertions.assertEquals("white", testOgre.colour);
    }

    @Test
    void testMakingOgreBackVisible() {
        //Given
        Elixir testElixir1 = new Elixir("Maa");
        Ogre testOgre = new Ogre(100, 0, "green", 80, 150, 200);
        Elixir testElixir2 = new Elixir("aaa");

        //When
        testOgre.drinkElixir(testElixir1);
        testOgre.drinkElixir(testElixir2);
        //Then
        Assertions.assertEquals("green", testOgre.colour);
    }

    @Test
    void testDeadOgreDrinks() {
        //Given
        Elixir testElixir = new Elixir("Maa");
        Ogre testOgre = new Ogre(100, 0, "green", 80, -10, 200);
        //When
        testOgre.drinkElixir(testElixir);
        //Then
        Assertions.assertEquals(-10, testOgre.lifepoints);
        Assertions.assertEquals("green", testOgre.colour);
    }




}
