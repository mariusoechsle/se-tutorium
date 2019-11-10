class Ogre {
    
    int xPos;
    int yPos;
    String colour;
    Head head;
    int lifepoints;
    int strength;
    Ogre companion = null;
    boolean dead = false;

    Ogre(int xPos, int yPos, String colour, int iq, int lifepoints, int strenght) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.colour = colour;
        this.lifepoints = lifepoints;
        this.strength = strenght;
        this.head = new Head(this.xPos, this.yPos, colour, iq);
    }
    
    void draw(){
        Ellipse body = new Ellipse(xPos, 100+yPos, 150, 180, colour);
        body.draw();
        head.draw();
    }
    
    void move(int deltaX, int deltaY) {
    	this.xPos += deltaX;
    	this.yPos += deltaY;
    	this.head.move(deltaX, deltaY);
    }
    
    void setColour(String colour) {
    	this.colour = colour;
    	this.head.setColour(colour);
    }

    void becomeSmarter() {
        this.head.iq++;
    }

    String rememberLastColour;

    void drinkElixir( Elixir elixir) {
        if (this.lifepoints < 0) {
            return;
        } else {
            this.lifepoints = this.lifepoints + elixir.heal();
            if(elixir.makesInvisible() == true) {
                if(this.colour != "white") {
                    rememberLastColour = this.colour;
                }
                this.setColour("white");
            } else {
                this.setColour(rememberLastColour);
            }
        }
    }

    void meet(Ogre other) {
        //wenn der andere nicht schon Gefährte ist und der andere nicht derselbe ist und beide nicht tot sind
        if (other != companion && other != this && this.lifepoints > 0 && other.lifepoints > 0) {
            //wenn beide keinen Gefährten haben, mache sie zu Gefährten
            if (this.companion == null && other.companion == null) {
                this.companion = other;
                other.companion = this;
                //ansonsten lasse sie kämpfen
            } else {
                int thisOgreTotalStrength;
                int otherOgreTotalStrenght;
                //summiere Stärken der Oger und Gefährten
                if (this.companion != null) {
                    thisOgreTotalStrength = this.strength + this.companion.strength;
                } else {
                    thisOgreTotalStrength = this.strength;
                }

                if (other.companion != null) {
                    otherOgreTotalStrenght = other.strength + other.companion.strength;
                } else {
                    otherOgreTotalStrenght = other.strength;
                }

                //Verlierer verliert Lebenspunkte
                if (thisOgreTotalStrength > otherOgreTotalStrenght) {
                    other.lifepoints = other.lifepoints - (thisOgreTotalStrength-otherOgreTotalStrenght);
                } else if (otherOgreTotalStrenght > thisOgreTotalStrength) {
                    this.lifepoints = this.lifepoints - (otherOgreTotalStrenght-thisOgreTotalStrength);
                }

                if (this.lifepoints < 0) {
                    this.setColour("black");
                }

                if (other.lifepoints < 0) {
                    other.setColour("black");
                }
            }
        }
    }
}
