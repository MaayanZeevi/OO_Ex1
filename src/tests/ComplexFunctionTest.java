package tests;

import myMath.Monom;
import myMath.Polynom;
import myMath.Polynom_able;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myMath.ComplexFunction;
import myMath.Monom;
import myMath.Operation;
import myMath.Polynom;
import myMath.function;
public class ComplexFunctionTest{
	


	@Test
	void Test1() {
		
		//assertEquals(t1.toString(), "");
		//System.out.println(t1.toString());

		Polynom p1 = new Polynom("3.1+2.4x^2-x^4");
		Polynom p2 = new Polynom("5+2x-3.3x+0.1x^5");
		

		ComplexFunction cf = new ComplexFunction("div",p1,p2);
		ComplexFunction cf1 = new ComplexFunction("div",p1,p2);
		ComplexFunction cf2 = new ComplexFunction("div(mul(2x^2,3x^2-5x+2),-x)");
		ComplexFunction cf3 = new ComplexFunction("div(mul(2x,3x^2-5x+2),-x");
		ComplexFunction cf4 = new ComplexFunction("div(3.1+2.4x^2-x^4,5+2x-3.3x+0.1x^5)");

		assertTrue(cf.equals(cf1));
		assertNotEquals(cf2.toString(),cf3.toString());
		assertEquals(cf4.toString(), cf.toString());
	}
	@Test
	void Test2() {
		String s1 = "2x^2-5x+1";
		Monom m1 = new Monom("-2x");
		Polynom p1 = new Polynom("2x^2+5x");

		
		ComplexFunction cf3 = new ComplexFunction("max", new Polynom(s1), p1 );
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("5+2x-3.3x+0.1x^5"), m1 );
		ComplexFunction cf5 = new ComplexFunction("div", new Polynom("5+2x-3.3x+0.1x^5"), new Monom("-2x") );
		
		assertTrue(cf4.equals(cf5));
	}
	@Test
	void test3() {
		//Plus
		ComplexFunction cf1 = new ComplexFunction(Operation.Plus, new Polynom ("-5x^2+3x"), new Polynom("7x^2-x"));
		assertEquals(40,cf1.f(-5));
		assertEquals(4,cf1.f(1));
		assertEquals(60,cf1.f(5));
		//Times
		ComplexFunction cf2 = new ComplexFunction(Operation.Times, new Polynom ("-5x^2+3x"), new Polynom("7x^2-x"));
		assertEquals(-25200,cf2.f(-5));
		assertEquals(0,cf2.f(0));
		assertEquals(-18700,cf2.f(5));
		//Divid
		ComplexFunction cf3 = new ComplexFunction(Operation.Divid, new Polynom ("-5x^2+3x"), new Polynom("7x^2-x"));
		assertEquals(-0.7777777777777778,cf3.f(-5));
		assertEquals(-1.0,cf3.f(-1));
		
		assertEquals(-0.6470588235294118,cf3.f(5));
		//Max
		ComplexFunction cf4 = new ComplexFunction(Operation.Max, new Polynom ("7x^2-5"), new Polynom("x"));
		assertEquals(170,cf4.f(-5));
		assertEquals(2.,cf4.f(1));
		assertEquals(170,cf4.f(5));
		//Min
		ComplexFunction cf5 = new ComplexFunction(Operation.Min, new Polynom ("7x^2-5"), new Polynom("x"));
		assertEquals(-5,cf5.f(-5));
		assertEquals(-1,cf5.f(-1));
		assertEquals(-5,cf5.f(0));
		;
		//Comp
		ComplexFunction cf6 = new ComplexFunction(Operation.Comp, new Polynom ("-5x^2+3x"), new Polynom("7x^2-x"));
		assertEquals(-161460,cf6.f(-5));
		assertEquals(0,cf6.f(0));
		assertEquals(-162,cf6.f(1));
	
	}

	@Test
	void testGetOp() {
		ComplexFunction cf1 = new ComplexFunction("plus(2x^2,3x^2)");
		assertEquals(Operation.Plus.toString(), cf1.getOp().toString());
	}
	
	
	
	@Test
	void testLeft() {
		ComplexFunction cf1 = new ComplexFunction("plus(2x^2,3x^2)");
		assertEquals(new Monom("2x^2").toString(), cf1.left().toString());
	}
	void testmul() {
		String s = "3x^2-10x+2";
		ComplexFunction cf1 = new ComplexFunction("plus(2x^2,3x^2-5x+2)");
		function f1 = cf1.initFromString(s);
		cf1.mul(f1);
		ComplexFunction cf = new ComplexFunction(Operation.Times, new ComplexFunction("plus(2x^2,3x^2-5x+2)"), new Polynom("3x^2-10x+2"));
		assertTrue(cf1.equals(cf));
		assertEquals(cf1.toString(), cf.toString());
	}
	void testPplus() {
		String s = "3x^2-10x+2";
		ComplexFunction cf1 = new ComplexFunction("plus(2x^2,3x^2-5x+2)");
		function f1 = cf1.initFromString(s);
		cf1.plus(f1);
		ComplexFunction cf = new ComplexFunction(Operation.Plus, new ComplexFunction("plus(2x^2,3x^2-5x+2)"), new Polynom("3x^2-10x+2"));
		assertTrue(cf1.equals(cf));
		assertEquals(cf1.toString(), cf.toString());
	}
	


	

	



}