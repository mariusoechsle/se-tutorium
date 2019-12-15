import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Labyrinth. Prepared to run on courseware.
 * 
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */
public class LabyrinthTest {

    private static final int LAB_HEIGHT = 50;
    private static final int LAB_WIDTH = 100;

    private Labyrinth labyrinth = new Labyrinth(LAB_HEIGHT, LAB_WIDTH);

    
    
    
    // Hier koennen Sie Ihre Tests einfuegen

    
    
    
    // --------------------------------------------------------------------------------------------------------------
    // Diesen Teil duerfen Sie nicht veraendern
    // --------------------------------------------------------------------------------------------------------------

    private static final ByteArrayOutputStream SYSTEM_OUT_REPLACEMENT_STREAM = new ByteArrayOutputStream();

    /**
     * Feeds a {@code ByteArrayOutputStream}-based {@code PrintStream} to
     * {@code System.out/System.err}. All tests will eventually fail if the
     * stream contains characters afterwards (see the
     * {@link #afterClass()}-method).
     */
    @Before
    public void setUp() {
        final PrintStream ps = new PrintStream(SYSTEM_OUT_REPLACEMENT_STREAM);
        System.setOut(ps);
        System.setErr(ps);
    }

    /**
     * Makes tests fail if {@code System.out} (or {@code System.err}) was used
     * (see the {@link #beforeClass()}-method).
     */
    @After
    public void tearDown() {
        final String printedOutput = SYSTEM_OUT_REPLACEMENT_STREAM.toString();
        if (!printedOutput.equals("")) {
            fail("Unallowed use of print(ln) to System.out or System.err detected:\n>>> " + printedOutput);
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("Width of Labyrinth not as expected", LAB_WIDTH, labyrinth.getWidth());
        assertEquals("Height of Labyrinth not as expected", LAB_HEIGHT, labyrinth.getHeight());
    }

    @Test(timeout = 1000)
    public void testNumberOfNeighbors() {
        assertEquals(2, labyrinth.getNeighbors(new Cell(0, 0)).length);
        assertEquals(2, labyrinth.getNeighbors(new Cell(0, LAB_WIDTH - 1)).length);
        assertEquals(2, labyrinth.getNeighbors(new Cell(LAB_HEIGHT - 1, 0)).length);
        assertEquals(2, labyrinth.getNeighbors(new Cell(LAB_HEIGHT - 1, LAB_WIDTH - 1)).length);

        assertEquals(3, labyrinth.getNeighbors(new Cell(0, 5)).length);
        assertEquals(3, labyrinth.getNeighbors(new Cell(5, 0)).length);

        assertEquals(4, labyrinth.getNeighbors(new Cell(5, 5)).length);
    }

    @Test(timeout = 1000)
    public void testNeighborsNotNull() {
        Cell[] neighbors = labyrinth.getNeighbors(new Cell(5, 5));
        for (int index = 0; index < neighbors.length; index++) {
            assertNotNull("null neighbor entry at index " + index, neighbors[index]);
        }
    }

    @Test(timeout = 1000)
    public void testAllVisited() {
        labyrinth.createLabyrinth();
        Cell cell = allVisited();
        assertNull("Unvisited cell " + cell, cell);
    }

    private Cell allVisited() {
        for (int row = 0; row < labyrinth.getHeight(); row++) {
            for (int col = 0; col < labyrinth.getWidth(); col++) {
                Cell cell = labyrinth.getCell(row, col);
                if (!cell.isVisited()) {
                    return cell;
                }
            }
        }
        return null;
    }

}