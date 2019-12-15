import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {

    // Hier koennen Sie Ihre Tests einfuegen

    
    
    
    
    
    // --------------------------------------------------------------------------------------------------------------
    // Diesen Teil duerfen Sie nicht veraendern
    // --------------------------------------------------------------------------------------------------------------

    @Test
    public void testConstructor() {
        Cell cell = new Cell(5, 6);
        assertEquals("Row of Cell not as expected", 5, cell.getRow());
        assertEquals("Column of Cell not as expected", 6, cell.getColumn());
    }

}
