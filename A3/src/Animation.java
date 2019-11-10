/**
 * Contains helper methods such as wait.
 * 
 * @author Axel Boettcher
 * @author Daniela Zehetmeier
 *
 */
public final class Animation {
    
    /**
     * This class only contains static helper methods, so we do not want someone
     * to instantiate it.
     */
    private Animation() { }

    /**
     * Wait for a specified number of milliseconds before erasing the canvas.
     * This provides an easy way to specify a small delay which can be used when
     * producing animations.
     * @param milliseconds the number of msec to wait.
     */
    public static void waitAndErase(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            // ignoring exception at the moment
        }
        Ellipse.Canvas.getCanvas().erase();
    }

    /**
     * Wait for a specified number of milliseconds.
     * This provides an easy way to specify a small delay which can be used when
     * producing animations.
     * @param milliseconds the number of msec to wait.
     */
    public static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            // ignoring exception at the moment
        }
    }

}
