import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

/**
 * GUI for testing Point and Rectangle classes. Draws three Rectangles that can
 * be dragged around. Changes their colors when intersection and contain methods
 * return {@code true}.
 * @author <a mailto:ab@cs.hm.edu>Axel B&ouml;ttcher</a>
 *
 */
@SuppressWarnings("serial")
public class Application extends JPanel {

    private static final int INITIAL_WINDOW_SIZE_X = 800;
    private static final int INITIAL_WINDOW_SIZE_Y = 600;
    private static final int INITIAL_Y_COORD = 50;
    private static final int SIZE_RASTER = 50;


    private static final Color COLOR_R1 = Color.RED;
    private static final Color COLOR_R2 = Color.BLUE;
    private static final Color COLOR_R3 = Color.YELLOW;
    private static final Color INTERSECTION_COLOR = Color.GRAY;
    private static final Color CONTAINED_COLOR = Color.BLACK;
    /**
     * The rectangles to drag around.
     * The assumption here is that awtRect2 can be contained in awtRect1.
     * This is important when sizes are changed!
     */
    protected java.awt.Rectangle awtRect1 = new java.awt.Rectangle(SIZE_RASTER, INITIAL_Y_COORD, 2 * SIZE_RASTER, 6 * SIZE_RASTER);
    protected java.awt.Rectangle awtRect2 = new java.awt.Rectangle(4 * SIZE_RASTER, INITIAL_Y_COORD, 6 * SIZE_RASTER, 2 * SIZE_RASTER);
    protected java.awt.Rectangle awtRect3 = new java.awt.Rectangle(12 * SIZE_RASTER, INITIAL_Y_COORD, 2 * SIZE_RASTER, SIZE_RASTER);
    protected boolean draggingIMG1 = false;
    protected boolean draggingIMG2 = false;
    protected boolean draggingIMG3 = false;

    /**
     * Paints the rectangles and uses different color if they are intersecting or not.
     * Instantiates the {@code Rectangle} classes under test to this end.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle r1 = new Rectangle(new Point(awtRect1.x, awtRect1.y), awtRect1.width, awtRect1.height);
        Rectangle r2 = new Rectangle(new Point(awtRect2.x, awtRect2.y), awtRect2.width, awtRect2.height);
        Rectangle r3 = new Rectangle(new Point(awtRect3.x, awtRect3.y), awtRect3.width, awtRect3.height);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setPaint(COLOR_R1);
        if (r1.intersects(r2) || r1.intersects(r3)) {
            graphics.setPaint(INTERSECTION_COLOR);
        }
        graphics.fillRect(awtRect1.x, awtRect1.y, awtRect1.width, awtRect1.height);
        graphics.setPaint(COLOR_R2);
        if (r2.intersects(r1) || r2.intersects(r3)) {
            graphics.setPaint(INTERSECTION_COLOR);
        }
        graphics.fillRect(awtRect2.x, awtRect2.y, awtRect2.width, awtRect2.height);
        graphics.setPaint(COLOR_R3);
        if (r1.contains(r3) || r2.contains(r3)) {
            graphics.setPaint(CONTAINED_COLOR);
        } else if (r3.intersects(r1) || r3.intersects(r2)) {
            graphics.setPaint(INTERSECTION_COLOR);
        }
        graphics.fillRect(awtRect3.x, awtRect3.y, awtRect3.width, awtRect3.height);
    }

    /**
     * Callback method to set the rectangles to the position determined by
     * the drag/drop logic.
     * @param xCoord x-coordinate of position.
     * @param yCoord y-coordinate of position.
     */
    public void setRect(int xCoord, int yCoord) {
        if (draggingIMG1) {
            awtRect1.setLocation(xCoord, yCoord);
        }
        else if (draggingIMG2) {
            awtRect2.setLocation(xCoord, yCoord);
        } else {
            awtRect3.setLocation(xCoord, yCoord);
        }
        repaint();
    }

    /**
     * Application entry point. Fires up the frame.
     * @param args Commandline arguments. None used herein.
     */
    public static void main(String[] args) {
        Application app = new Application();
        new GraphicDragController(app);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(app);
        f.setSize(INITIAL_WINDOW_SIZE_X, INITIAL_WINDOW_SIZE_Y);
        f.setVisible(true);
    }
}

/**
 * Controller drag/drop operations.
 */
class GraphicDragController extends MouseInputAdapter {
    Application applicationComponent;
    java.awt.Point offset = new java.awt.Point();

    /**
     * Standard controller for DD operations.
     * @param application The Application object.
     */
    GraphicDragController(Application application) {
        applicationComponent = application;
        applicationComponent.addMouseListener(this);
        applicationComponent.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        java.awt.Point p = event.getPoint();
        java.awt.Rectangle r;
        if (applicationComponent.awtRect1.contains(p)) {
            r = applicationComponent.awtRect1;
            offset.x = p.x - r.x;
            offset.y = p.y - r.y;
            applicationComponent.draggingIMG1 = true;
        } else if (applicationComponent.awtRect2.contains(p)) {
            r = applicationComponent.awtRect2;
            offset.x = p.x - r.x;
            offset.y = p.y - r.y;
            applicationComponent.draggingIMG2 = true;
        } else if (applicationComponent.awtRect3.contains(p)) {
            r = applicationComponent.awtRect3;
            offset.x = p.x - r.x;
            offset.y = p.y - r.y;
            applicationComponent.draggingIMG3 = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        applicationComponent.draggingIMG1 = false;
        applicationComponent.draggingIMG2 = false;
        applicationComponent.draggingIMG3 = false;
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (applicationComponent.draggingIMG1) {
            int x = event.getX() - offset.x;
            int y = event.getY() - offset.y;
            applicationComponent.setRect(x, y);
        } else if (applicationComponent.draggingIMG2) {
            int x = event.getX() - offset.x;
            int y = event.getY() - offset.y;
            applicationComponent.setRect(x, y);
        } else if (applicationComponent.draggingIMG3) {
            int x = event.getX() - offset.x;
            int y = event.getY() - offset.y;
            applicationComponent.setRect(x, y);
        }
    }

}
