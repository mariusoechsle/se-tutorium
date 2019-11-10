import java.util.Random;

/**
 * Einfache GUI-Klasse, um einen Oger auf einem Spielfeld ein paar Abenteuer erleben zu lassen.
 * Diese Klasse wurde zu didaktischen Zwecken geschrieben und erfuellt nicht zwingend
 * Ansprueche an guten Quellcode.
 * 
 * @author <a mailto:daniela.zehetmeier@hm.edu>Daniela Zehetmeier</a>
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */
public class Quest {

    private static final int ANIMATION_INTERVAL = 3_000;
    
    private static final int OGRES_INITIAL_IQ = 80;
    private static final int OGRE_INITIAL_LIFEPOINTS = 25;
    private static final int OGRE_INITIAL_STRENGTH = 30;

    private static final int OGRE_X = 1000;
    private static final int OGRE_Y = 100;
    private static final int OGRE_DIST = 100;
    private static final int MIN_CHARACTERISTIC_LENGTH = 5;
    private static final int TEXT_POS_X = 260;
    private static final int TEXT_POS_Y = 450;
    /*
     * Der Pfad mit allen Events ist fest vorgegeben:
     */
    private final Field[] path = new Field[] { 
            new Field(0, 7, Event.NONE), new Field(1, 7, Event.MEET), new Field(2, 7, Event.NONE), 
            new Field(2, 6, Event.NONE), new Field(2, 5, Event.MEET), new Field(1, 5, Event.NONE), 
            new Field(0, 5, Event.DRINK_ELIXIR), new Field(0, 4, Event.NONE), new Field(0, 3, Event.MEET), 
            new Field(0, 2, Event.DRINK_ELIXIR), new Field(0, 1, Event.NONE), new Field(0, 0, Event.MEET), 
            new Field(1, 0, Event.NONE), new Field(2, 0, Event.NONE), new Field(2, 1, Event.MEET), 
            new Field(2, 2, Event.NONE), new Field(3, 2, Event.MEET), new Field(4, 2, Event.NONE), 
            new Field(5, 2, Event.NONE), new Field(5, 1, Event.NONE), new Field(5, 0, Event.NONE), 
            new Field(6, 0, Event.NONE), new Field(7, 0, Event.DRINK_ELIXIR), new Field(7, 1, Event.NONE), 
            new Field(7, 2, Event.MEET), new Field(7, 3, Event.NONE), new Field(7, 4, Event.MEET), 
            new Field(7, 5, Event.NONE), new Field(7, 6, Event.NONE), new Field(7, 7, Event.NONE), 
            new Field(6, 7, Event.MEET), new Field(5, 7, Event.NONE), new Field(4, 7, Event.NONE), 
            new Field(4, 8, Event.NONE), new Field(4, 9, Event.DRINK_ELIXIR), new Field(4, 10, Event.NONE), 
            new Field(5, 10, Event.NONE), new Field(6, 10, Event.NONE), new Field(6, 11, Event.NONE), 
    };
    private final Ogre adventurer;
    /*
     * Der jeweilige Inhalt dieses Attributs wird in jedem Schritt auf dem Canvas ausgegeben.
     */
    private String currentText = "";
    private final Random random = new Random();


    /**
     * Im Konstruktor instanziieren wir den Oger, der das Abenteuer bestehen muss. 
     */
    public Quest() {
        this.adventurer = new Ogre(OGRE_X - OGRE_DIST, OGRE_Y, "green", OGRES_INITIAL_IQ, OGRE_INITIAL_LIFEPOINTS, OGRE_INITIAL_STRENGTH);
    }

    /**
     * Diese Methode startet das Quest und lässt den Oger über den vorgegebenen Pfad laufen.
     */
    void run() {
        this.drawPath();
        adventurer.draw();

        for (int index = 0; index < path.length; index++) {
            path[index].visit();
            switch(path[index].getEvent()) {
            case MEET:
                // we create a new ogre that is met.
                Ogre other = new Ogre(OGRE_X, OGRE_Y, "blue", OGRES_INITIAL_IQ, random.nextInt(OGRE_INITIAL_LIFEPOINTS), random.nextInt(2 * OGRE_INITIAL_STRENGTH));
                // the other ogre randomly is given a companion
                if (random.nextBoolean()) {
                    other.meet(new Ogre(OGRE_X - 2 * OGRE_DIST, OGRE_Y, "yellow", OGRES_INITIAL_IQ, OGRE_INITIAL_LIFEPOINTS, OGRE_INITIAL_STRENGTH));
                }
                // now our adventurer meets the other ogre(s)
                currentText = stringifyOgre(adventurer);
                currentText += "MEETS -- " + stringifyOgre(other);
                adventurer.meet(other);
                break;
            case DRINK_ELIXIR:
                Elixir elixir = new Elixir(createRandomCharacteristic());
                adventurer.drinkElixir(elixir);
                currentText = stringifyOgre(adventurer);
                currentText += "DRINKS (" + elixir.characteristic + ") -- ";
                break;
            default:
                currentText = stringifyOgre(adventurer);
                currentText += "BECOMES SMARTER --";
                adventurer.becomeSmarter();
                break;
            }
            if (adventurer.companion != null) {
                adventurer.companion.draw();
            }
            adventurer.draw();
            Ellipse.Canvas.getCanvas().setForegroundColor("black");
            Ellipse.Canvas.getCanvas().drawString(currentText, TEXT_POS_X, TEXT_POS_Y);
            this.drawPath();
            Animation.wait(ANIMATION_INTERVAL);
            this.erase();
        }
    }
    
    /**
     * Methode zur Generierung einer String-Darstellung eines Ogers, die dann
     * engezeigt werden kann. Diese basiert aus didaktischen Gründen nicht auf der 
     * Ogre.toString-Methode.
     * @param ogre Der Ogre, für den eine String-Darstellung generiert werden soll.
     * @return String-Repräsentation des Ogers.
     */
    String stringifyOgre(Ogre ogre) {
        String s = "";
        s += "LP: " + ogre.lifepoints + ", ST: " + ogre.strength + ", IQ: " + ogre.head.iq + " -- ";
        Ogre companion = ogre.companion;
        if (companion != null) {
            s += "Companion LP: " + companion.lifepoints + ", ST: " + companion.strength + ", IQ: " + companion.head.iq + " -- ";
        } else {
            s += "No companion -- ";
        }
        return s;
    }

    /**
     * Hilfsmethode, um den Canvas zu löschen.
     * Die Methode steht hier, um sie aus der Klasse Ellipse herauszuhalten.
     */
    private void erase() {
        Ellipse.Canvas.getCanvas().erase();
    }

    /**
     * Diese Methode steht in der Quest-Klasse, weil die Elixir-Klasse von den Studierenden 
     * implementiert wird.
     * @return zufällige Characteristik.
     */
    private String createRandomCharacteristic() {
        StringBuilder characteristic = new StringBuilder();
        int cahracteristicsLength = random.nextInt(2 * MIN_CHARACTERISTIC_LENGTH) + MIN_CHARACTERISTIC_LENGTH;
        for (int i = 0; i < cahracteristicsLength; i++) {
            characteristic.append((char) (random.nextInt('Z' - 'A') + 'A'));
        }
        return characteristic.toString();
    }

    /**
     * Hilfsmethode, um den Pfad zu zeichnen.
     */
    private void drawPath() {
        for (int index = 0; index < path.length; index++) {
            path[index].draw();
        }
    }

    /**
     * Einstiegspunkt in das Quest.
     * @param args Kommandozeilenparameter - nicht verwendet.
     */
    public static void main(String[] args) {
        Quest quest = new Quest();
        quest.run();
    }


    /**
     * Inner class that defines events for the adventurer.
     */
    private enum Event {
        NONE, MEET, DRINK_ELIXIR, 
    }
    
    /**
     * Class that represents a field on an Oger's Qust.
     * Diese Klasse wurde zu didaktischen Zwecken geschrieben und erfuellt nicht zwingend
     *
     * @author <a mailto:daniela.zehetmeier@hm.edu>Daniela Zehetmeier</a>
     * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
     *
     */
    private static final class Field {
        
        private static final int FIELD_SIZE = 50;
        private static final String[] FIELD_COLOURS = new String[] {"gray", "green", "yellow"};

        private final int xPos;
        private final int yPos;
        private final Event event;
        private boolean visited = false;

        /**
         * Field constructor.
         * @param xPos x-coordinate of field.
         * @param yPos y-coordinate of the field.
         * @param event Event for the adventurer that is attached to the field.
         */
        Field(int xPos, int yPos, Event event) {
            this.xPos = xPos;
            this.yPos = yPos;
            this.event = event;
        }

        /**
         * Draw a field in its corresponding color.
         */
        public void draw() {
            Ellipse field;
            if (this.visited) {
                field = new Ellipse(this.xPos * FIELD_SIZE, this.yPos * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE, "black");
            } else {
                field = new Ellipse(this.xPos * FIELD_SIZE, this.yPos * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE, FIELD_COLOURS[event.ordinal()]);
            }
            field.draw();
        }

        /**
         * Getter for event-attribute.
         * @return The event attached to the field.
         */
        public Event getEvent() {
            return event;
        }

        /**
         * Mark the field as already being visited.
         */
        public void visit() {
            this.visited = true;
        }
        
        /**
         * Getter for x-coordinate of the field.
         * @return field's x-coordinate.
         */
        public int getxPos() {
            return xPos;
        }

        /**
         * Getter for y-coordinate of the field.
         * @return field's y-coordinate.
         */
        public int getyPos() {
            return yPos;
        }
        
        @Override
        public String toString() {
            return "Field [xPos=" + xPos + ", yPos=" + yPos + ", event=" + event + "]";
        }
        
    }

}
