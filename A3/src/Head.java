class Head {
	int xPos;
	int yPos;
	String colour;
	int iq;
	
	Head(int xPos, int yPos, String color, int iq) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.iq = iq;
		this.colour = color;
	}
	
	void setColour(String colour) {
		this.colour = colour;
	}
	
	void move(int deltaX, int deltaY) {
		this.xPos += deltaX;
		this.yPos += deltaY;
	}
	
	void draw() {
		Ellipse head = new Ellipse(25+xPos, 10+yPos, 100, 120, colour);
        head.draw();
        
        Ellipse leftIris = new Ellipse(45+xPos, 40+yPos, 20, 20, "black");
        leftIris.draw();
        
        Ellipse leftPupile = new Ellipse(50+xPos, 45+yPos, 8, 8, "white");
        leftPupile.draw();
        
        Ellipse rightIris = new Ellipse(85+xPos, 40+yPos, 20, 20, "black");
        rightIris.draw();
        
        Ellipse rightPupile = new Ellipse(90+xPos, 45+yPos, 8, 8, "white");
        rightPupile.draw();
        
        Ellipse leftEar = new Ellipse(10+xPos, 40+yPos, 25, 25, colour);
        leftEar.draw();
        
        Ellipse rightEar = new Ellipse(115+xPos, 40+yPos, 25, 25, colour);
        rightEar.draw();
        
        Ellipse leftNose = new Ellipse(60+xPos, 70+yPos, 10, 10, "black");
        leftNose.draw();
        
        Ellipse rightNose = new Ellipse(80+xPos, 70+yPos, 10, 10, "black");
        rightNose.draw();
	}
}
