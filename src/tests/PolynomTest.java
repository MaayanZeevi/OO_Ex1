package tests;
import Exceptions.wrongDataException;
import myMath.Polynom;
import myMath.Monom;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PolynomTest {

    @Test
    public void constructorTest() throws wrongDataException {
        String str="2x^2+3x^3-4x^4";
        Polynom testUnite= new Polynom(str);
        Polynom expectedPoly= new Polynom();
        expectedPoly.add(new Monom(2,2));
        expectedPoly.add(new Monom(3,3));
        expectedPoly.add(new Monom(-4,4));
        assertEquals(testUnite, expectedPoly);
    }

    @Test
    public void f() throws Exception {
        Polynom testUnit= new Polynom("2x^2+3x^3-4x^4");
        assertThat(1.0,is(testUnit.f(1)));
    }

    @Test
    public void f_givenParameterXIsZero_acceptedToGetZero() throws Exception {
        Polynom testUnit= new Polynom("2x^2+3x^3-4x^4");
        assertThat(0.0,is(testUnit.f(0)));
    }

    @Test
    public void f_givenNegativeParameterX() throws Exception {
        Polynom testUnit= new Polynom("2x^2+3x^3-4x^4");
        assertThat(-5.0,is(testUnit.f(-1))); //accepted to get: 2-3-4=-5
    }

    @Test
    public void add_addedToOurPolynomAnotherPolynom() throws Exception {
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
        p1.add(p2);
        Polynom expectedPoly= new Polynom("8 + 0.7x -3.2x^2");
        assertEquals(p1, expectedPoly);
    }

    @Test
    public void add_addToOurPolynomZeroMonom_acceptedThePolynomBeTheSame() throws Exception {
        String str= "5+1.7x+3.2x^2";
        Polynom testUnit= new Polynom(str);
        Polynom expectedPolynom= new Polynom(str);
        testUnit.add(new Monom (0,0));
        assertEquals(testUnit, expectedPolynom);
    }

    @Test
    public void substract() throws Exception {
    Polynom testUnit=new Polynom("2+3.5x^2-4+2.2x^3");
    Polynom subUnit= new Polynom ("4-1.5x^2-2.8x^3-5.5x^5");
    testUnit.substract(subUnit);
    Polynom acceptedPoly= new Polynom("-6+5x^2+5x^3+5.5x^5");
    assertEquals(testUnit, acceptedPoly);
    }

    @Test
    public void substract_substractFromOrPolynomZero_acceptedThatOutPolynomWillBeTheSame() throws Exception {
        String str= "4-1.5x^2-2.8x^3-5.5x^5";
        Polynom testUnit= new Polynom (str);
        Polynom zeroPoly= new Polynom();
        testUnit.substract(zeroPoly);
        Polynom acceptedPoly= new Polynom(str);
        assertEquals(testUnit, acceptedPoly);
    }

    @Test
    public void multiply() throws Exception {
        Polynom testUnit= new Polynom("3+2.5x^2-3x^3");
        Polynom multiplier= new Polynom("1-4x^2+3x");
        testUnit.multiply(multiplier);
        Polynom accpetedAns=new Polynom("12x^5-19x^4+4.5x^3-9.5x^2+9x+3");
        System.out.println("unitAccepted Polynom:"+ " " + testUnit);
        System.out.println("is equals to.. " +" "+ accpetedAns);
        assertEquals(testUnit, accpetedAns);
    }

    @Test
    public void multiply_multOurPolynomByZero_acceptedToGetZero () throws Exception {
        Polynom testUnit= new Polynom("3+2.5x^2-3x^3");
        Polynom zeroPoly= new Polynom();
        testUnit.multiply(zeroPoly);
        assertEquals(new Polynom(), testUnit);
    }

    @Test
    public void equals() throws Exception {
        Polynom p1= new Polynom("3+2.5x^2-3x^3");
        Polynom p2= new Polynom("3+2.5x^2-3x^3");
        boolean ans= p1.equals(p2);
        assertEquals(ans, true);
    }
    @Test
    public void equals_tryToCompareTwoDiffrentPolynoms() throws Exception {
        Polynom p1= new Polynom("3+2.5x^2-3x^3");
        Polynom p2= new Polynom();
        boolean ans= p1.equals(p2);
        assertEquals(ans, false);
    }

    @Test
    public void equals_tryToCompareSamePolynomsButInDiffOrganize() throws Exception {
        Polynom p1= new Polynom("3+2.5x^2-3x^3");
        Polynom p2= new Polynom("-3x^3+3+2.5x^2");
        assertThat(true,is(p1.equals(p2)));
    }

    @Test
    public void isZero() throws Exception {
        Polynom p1= new Polynom();
        assertThat(true, is(p1.isZero()));
    }

    @Test
    public void isZero_testMethodOnunZeroPolynom() throws Exception {
        Polynom testUnit= new Polynom("3+2.5x^2-3x^3");
        assertThat(false, is(testUnit.isZero()));
    }

    @Test
    public void root() throws Exception {
    }

    @Test
    public void copy() throws Exception {
        Polynom p1= new Polynom("3+2.5x^2-3x^3");
        Polynom p2= (Polynom) p1.copy();
        assertThat(true, is(p1.equals(p2)));
    }

    @Test
    public void derivative() throws Exception {
    }

    @Test
    public void toString_givenBla_shouldBla() {
    }

    @Test
    public void initFromString() throws Exception {
    }

    @Test
    public void area() throws Exception {
    }

    @Test
    public void multiply1() throws Exception {
    }

}