package myMath;

import java.awt.Color;


public class test {
	public static void main(String[] args) {
		Functions_GUI fg = new Functions_GUI();
	//	fg.add(new Monom("2x"));
		//fg.add(new Polynom("2x^2+2x-5"));
		ComplexFunction temp = new ComplexFunction("Times(x,x)");
		fg.add(new ComplexFunction("Plus(x,x)"));
		Range rx = new Range(-10,10);
		Range ry = new Range(-10,10);
		fg.drawFunctions(500,500,rx,ry,100);
		
		
	}

}
