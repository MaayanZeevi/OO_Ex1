package tests;
import myMath.Monom;
import myMath.Polynom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class MonomTest {
	  @Test
	void derivative() {
	     Monom test1 = new Monom("5x^3");
	     Monom test2 = new Monom("x");
	     Monom test3 = new Monom("x^2");
	     Assertions.assertEquals(new Monom("15x^2").toString(),test1.derivative().toString());
	     Assertions.assertEquals(new Monom("1").toString(),test2.derivative().toString());
	     Assertions.assertEquals(new Monom("2x").toString(),test3.derivative().toString());
	    }
	  
	  @Test
	    void f() {
	        Monom test1 = new Monom("5x^3");
	        Monom test2 = new Monom("x");
	        Monom test3 = new Monom("-x^2");
	        Monom test4 = new Monom("2x^3");
	        Monom test5 = new Monom("-1");
	        Monom test6 = new Monom("0");
	        Assertions.assertEquals(5.0,test1.f(1));
	        Assertions.assertEquals(40.0,test1.f(2));
	        Assertions.assertEquals(0.0,test1.f(0));
	        Assertions.assertEquals(1.0,test2.f(1));
	        Assertions.assertEquals(-5,test2.f(-5));
	        Assertions.assertEquals(-25,test3.f(-5));
	        Assertions.assertEquals(-250,test4.f(-5));
	        Assertions.assertEquals(-1,test5.f(0));
	        Assertions.assertEquals(0,test6.f(100));
	    }

	    @Test
	    void add() {
	        Monom test1 = new Monom("5x^5");
	        Monom test2 = new Monom("x^5");
	        test1.add(test2);
	        Monom test3 = new Monom("-x^2");
	        Monom test4 = new Monom("2x^2");
	        test3.add(test4);
	        Monom test5 = new Monom("-1");
	        Monom test6 = new Monom("0");
	        test5.add(test6);
	        Assertions.assertEquals("6.0x^5",test1.toString());
	        Assertions.assertEquals("1.0x^2",test3.toString());
	    
	        Assertions.assertEquals("-1.0x^0",test5.toString());
	      
	    
	    }
	    
	    @Test
	    void equals() {
	    ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			
			String s = m.toString();
			Monom m1 = new Monom(s);
			Assertions.assertEquals(m.toString(),m1.toString());
			
			

		}
		

}   
	    @Test
	    void isZero() 	{
	    	Monom m=new Monom(0,5);
	    	Assertions.assertTrue(m.isZero());
	    }
	    
	    @Test
	    void tostring() {
		String[] monoms = {"2", "-x","-3.2x^2","0"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
}
	    }		
}