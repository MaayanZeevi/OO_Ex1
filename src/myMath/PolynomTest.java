package myMath;

import Exceptions.wrongDataException;

public class PolynomTest {
	public static void main(String[] args) throws wrongDataException {
		test1();
		test2();
		testPolynom();
	}
	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		p1.add(m);
		double aa = p1.area(0, 1, 0.0001);
		p1.substract(p1);
		System.out.println(p1);
	}
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}


	System.out.println("p1: "+p1);
	
	System.out.println("p2: "+p2);
	p1.add(p2);
	System.out.println("p1+p2: "+p1);
	p1.multiply(p2);
	System.out.println("(p1+p2)*p2: "+p1);
	String s1 = p1.toString();

	}
	
	public static void testPolynom() throws wrongDataException  {
		//////Test Polynom

		//constractors
		System.out.println("\ncon Test\n");
		Polynom p1= new Polynom("0+0+0+2");
		Polynom p2= new Polynom("0");
		Polynom p3= new Polynom("3x^2+2x-4+0x^5");
		Polynom p4= new Polynom("2*x^4-7*x^3");
		Polynom p5= new Polynom("x^1+9.5x^2-1x^3");
		Polynom p6= new Polynom(p5);

	
		System.out.println("\ncon Test\n");
		//to String

		System.out.println(p1.toString());  // 2
		System.out.println(p2.toString());  // 0
		System.out.println(p3.toString());  //-4+2x+3x^2
		System.out.println(p4.toString());  //-7x^3+2x^4
		System.out.println(p5.toString());  //x+9.5x^2-x^3
		System.out.println(p6.toString());  //x+9.5x^2-x^3
		
		
		// copy
		System.out.println("\ncopy Test\n");

		Polynom_able pv= p5.copy();
		System.out.println(pv.toString());   //x+9.5x^2-x^3
         
		//add monom 
		System.out.println("\nadd Monom Test\n");

		p5.add(new Monom("0"));
		System.out.println(p5.toString());  //x+9.5x^2-x^3
		p5.add(new Monom("x^3"));
		System.out.println(p5.toString());  //x+9.5x^2
		p5.add(new Monom("-x"));
		System.out.println(p5.toString());   //9.5x^2
		p5.add(new Monom("0.5x^2"));
		System.out.println(p5.toString());   //10.0x^2

		
		System.out.println("test p6"+p6.toString());
		//add polynom
		System.out.println("\nadd polynom Test\n");
		p6.add(p1);
		System.out.println(p6.toString());   //10.0x^2+2
		p6.add(p3);
		System.out.println(p6.toString()); // 13x
		Monom mm= new Monom(2,0) ;
		Polynom m= new Polynom();
		m.add(mm);
		m.add(new Monom(-1.5,0));
		m.add(new Monom(-0.5,0));
		System.out.println(m.isZero());  // true
		System.out.println(m.toString());  // 0

		//sub
		System.out.println("\nsub Test\n");
		p6.substract(p3);
		System.out.println(p6.toString());  //2.0-2.0x+10.0x^2-x^3
		p6.substract(p2);
		System.out.println(p6.toString());  // 2.0-2.0x+10.0x^2-x^3

		//mult
		System.out.println("\nmult Test\n");

		p6.multiply(p2);
		System.out.println(p6.toString());    //0
		Polynom po= new Polynom("-3x+x^2");   
		Polynom pol= new Polynom("0+x-2x^3");  
		po.multiply(pol);
		System.out.println(po.toString());  //-3.0x^2+x^3+6.0x^4-2.0x^5
		p3.multiply(po);
		System.out.println(p3.toString());   //0-12.0x^2+10.0x^3+31.0x^4-23.0x^5-14.0x^6+6.0x^7

		//equals
		System.out.println("\nequals Test\n");
		Polynom w1= new Polynom("3x^7+2");
		Polynom w2= new Polynom("-2x^4+2+3x^7");
		w2.add(new Monom(2,4));
		System.out.println(w2.toString());
		System.out.println(w1.toString());
		
		System.out.println(p1.equals(p2));  // false
		Polynom p11= new Polynom("2.0");
		System.out.println(p1.equals(p11));  //true
		Polynom p= new Polynom(w1.derivative().toString());
		System.out.println(w1.toString());
		System.out.println(p.toString()); 

		
	}
}
