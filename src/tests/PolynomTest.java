package tests;

import myMath.Monom;
import myMath.Polynom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PolynomTest {

	 @Test
	    void f() {
	        Polynom test1 = new Polynom("-x^4+5x^3+444");
	        Polynom test2 = new Polynom("-x");
	        
	        double test11 = test1.f(3);
	        double test12 = test1.f(0);
	        double test13 = test2.f(-1);
	       
	        Assertions.assertEquals(498,test11);
	        Assertions.assertEquals(444,test12);
	        Assertions.assertEquals(1,test13);
	      
	 }
	 
	 @Test
	    void addPolynom() {
	        Polynom a = new Polynom("x^4+x^3+x^2+x+1");
	        Polynom a1 = new Polynom("-x^4-x^3-x^2-x-1");
	        Polynom a2 = new Polynom("3x^4+3x^3+3x^2+3x+1");
	        Polynom a3 = new Polynom("1+2+4+5+0000");
	        a.add(a1);
	        a.add(a2);
	        a.add(a3);
	        Assertions.assertEquals("3.0x^4+3.0x^3+3.0x^2+3.0x^1+13.0x^0",a.toString());
	        
	           
	    }
	 
	    void substract() {
	        Polynom test1 = new Polynom("5x^2+3x^2+2");
	        Polynom test2 = new Polynom("x-8x");
	        Polynom test3 = new Polynom("5x^6-8x^2");
	        Polynom test4 = new Polynom("0");
	        Polynom test5 = new Polynom("8");
	        test1.substract(test2);
	        test1.substract(test3);
	        test1.substract(test4);
	        test1.substract(test5);
	        Assertions.assertEquals("-5.0x^6+16.0x^2+7.0x^1+-6.0x^0+",test1.toString());
	    }
	    
	    
	    
	    @Test
		void AddPolynomsCopyAndEquals() {
			Polynom p1 = new Polynom("3x^5+2x^4-x");
			Polynom p2 = new Polynom("10x^5+4x^4-2x");
			
			Polynom p1c = (Polynom) p1.copy();
			Polynom p2c = (Polynom) p2.copy();
			
			Assertions.assertTrue(p1.equals(p1c));
			Assertions.assertTrue(p2.equals(p2c));
		
			
		}
		
	    
	    @Test
		void ConstructorAndtoString() {
	    	Assertions.assertEquals((new Polynom("1+x+x^2+x^3+x^4")).toString(), new Polynom("x^4+x^3+x^2+x+1").toString());
	    	Assertions.assertEquals((new Polynom("x+4+x^2+2x^2+5+5x")).toString(), new Polynom("3x^2+6x+9").toString());
			
			
		}
	    
	    @Test
	    void testAddMonom() {
	        Polynom a = new Polynom("x^4+x^3+x^2+x+1");
	        Monom m1 = new Monom("5");
	        Monom m2 = new Monom("5x");
	        Monom m3 = new Monom("-15x^2");
	        Monom m4 = new Monom("x^100");
	        a.add(m1);
	        a.add(m2);
	        a.add(m3);
	        a.add(m4);
	        Assertions.assertEquals("1.0x^100+1.0x^4+1.0x^3+-14.0x^2+6.0x^1+6.0x^0",a.toString());
	    }
	    
	    @Test
	    void isZero(){
	        Polynom p = new Polynom("0");
	        Polynom r = new Polynom("0");
	        Polynom s = new Polynom("0");
	        Assertions.assertEquals(p.isZero(),true);
	        r.substract(r);
	        Assertions.assertEquals(r.isZero(),true);
	    }
	    
	    @Test
	    void area() {
	       
	        Polynom r = new Polynom("-3x^2+2");
	        Polynom s = new Polynom("4x^5-5x^3+2x^2+4x+15+1");
	        double d = s.area(-1.5, 0, 0.000001);
	        Assertions.assertEquals(d,20.484375,0.1);
	        double e = r.area(-0.8, 0.8, 0.1);
	        Assertions.assertEquals(e,2.176,0.1);
	       

	    }
	    
	    @Test
	    void root() {
	       
	        Polynom a = new Polynom("5x+5");
	        Polynom b = new Polynom("-5x+5");

	        Assertions.assertEquals(-1 ,a.root(-5, 5, 0.001) ,0.1 );
	       
	      
	    }
	    

	    @Test
	    void multiply() {
	        Polynom test1 = new Polynom("5x^2+3x^2+2");
	        Polynom test2 = new Polynom("x-8x");
	        Polynom test3 = new Polynom("5x^6-8x^2");
	        test1.multiply(new Monom("0"));
	        test2.multiply(new Monom("x"));
	        test3.multiply(new Monom("3x^2"));
	      
	        Assertions.assertEquals("-7.0x^2",test2.toString());
	        Assertions.assertEquals("15.0x^8+-24.0x^4",test3.toString());
	        


	    }

	
}