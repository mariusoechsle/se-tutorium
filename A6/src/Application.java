import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * GUI for Labyrinth class.
 * @author <a mailto:ab@cs.hm.edu>Axel B&ouml;ttcher</a>
 *
 */
@SuppressWarnings("serial")
public class Application extends JPanel {

    private static final int LAB_HEIGHT = 3;
    private static final int LAB_WIDTH = 3;
    private static final int LINE_LENGTH = 10;

    private final Labyrinth labyrinth;

    /**
     * Default ctor to initialize the labyrinth object.
     */
    public Application() {
        this.labyrinth = new Labyrinth(LAB_HEIGHT, LAB_WIDTH);
        this.labyrinth.createLabyrinth();
    }

    /**
     * Paints the labyrinth.
     * Instantiates the {@code Rectangle} classes under test to this end.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        graphics.drawLine(0, 0, LINE_LENGTH * LAB_WIDTH, 0);
        graphics.drawLine(0, 0, 0, LINE_LENGTH * LAB_HEIGHT);
        for (int row = 0; row < LAB_HEIGHT; row++) {
            for (int col = 0; col < LAB_WIDTH; col++) {
                Cell cell = labyrinth.getCell(row, col);
                if (cell.isWallDown()) {
                    int startX = col * LINE_LENGTH;
                    int startY = (row + 1) * LINE_LENGTH;
                    graphics.drawLine(startX, startY, startX + LINE_LENGTH, startY);
                }
                if (cell.isWallRight()) {
                    int startX = (col + 1) * LINE_LENGTH;
                    int startY = row * LINE_LENGTH;
                    graphics.drawLine(startX, startY, startX, startY + LINE_LENGTH);
                }
            }
        }
    }

    /**
     * Application entry point. Fires up the frame.
     * @param args Commandline arguments. None used herein.
     */
    public static void main(String[] args) {
        Application app = new Application();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(app);
        f.getContentPane().setPreferredSize(new Dimension(LAB_WIDTH * LINE_LENGTH + 1, LAB_HEIGHT * LINE_LENGTH + 1));
        f.pack();
        f.setVisible(true);
    }

}
