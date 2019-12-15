package tests;
import org.junit.Test;
import myMath.Monom;
import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;

public class MonomTest {

    @Test
    public void multByNumber_givenNonZeroMonomAndIntegerNumber_shouldChangeTheMonomCoeficientToTheProductOfTheCoefficientAndTheNumber() throws Exception {
        int multiplier = 5;
        Monom testedUnit = new Monom(3,2);
        double expectedCoefficient = 15;
        testedUnit.multByNumber(multiplier);
        assertTrue(expectedCoefficient == testedUnit.get_coefficient());
    }

    @Test
    public void multByNumber_givenZeroMonomAndIntegerNumber_shouldNotChangeTheMonomCoefficient() throws Exception {
        int multiplier = 5;
        Monom testedUnit = new Monom(0,1);
        Monom expectedResult = new Monom(0, 1);
        testedUnit.multByNumber(multiplier);
        assertEquals(testedUnit, expectedResult);
    }

    @Test
    public void derivative() throws Exception {
    }

    @Test
    public void f() throws Exception {
    }

    @Test
    public void isZero() throws Exception {

    }

    @Test
    public void add_givenMonomWithDifferentPowerAndCoefficientsAreNotZero_thenIllegalArgumentExceptionShouldBeThrown(){
      //  assertThrows(IllegalArgumentException.class, () -> {
            Monom testedUnit = new Monom(2, 5);
            Monom addedMonom = new Monom(4, 9);
            testedUnit.add(addedMonom);
       // });
    }

    @Test
    public void add_givenMonomWithDifferentPowerAndTestedMonomCoefficientIsZero_thenTestedMonomShouldBeEqualToTheAddedMonom(){
        Monom testedUnit = new Monom(0, 5);
        Monom addedMonom = new Monom(6, 9);
        Monom expectedMonom = new Monom(6, 9);
        testedUnit.add(addedMonom);
        assertEquals(testedUnit, expectedMonom);
    }

    @Test
    public void add_givenMonomWithDifferentPowerAndAddedMonomCoefficientIsZero_thenTestedMonomShouldNotBeChanged(){
        Monom testedUnit = new Monom(8, 5);
        Monom addedMonom = new Monom(0, 9);
        Monom expectedMonom = new Monom(8, 5);
        testedUnit.add(addedMonom);
        assertEquals(testedUnit, expectedMonom);
    }

    @Test
    public void add_givenMonomWithSamePowerAndBothMonomsCoefficientIsNotZero_thenTestedMonomShouldEqualToTheSumOfBothMonoms(){
        Monom testedUnit = new Monom(2, 5);
        Monom addedMonom = new Monom(4, 5);
        Monom expectedMonom = new Monom(6, 5);
        testedUnit.add(addedMonom);
        assertEquals(testedUnit, expectedMonom);
    }

    @Test
    public void multipy_givenMonomCofficientZeroMultiplyWithMonomIsNotZero_thenMultResultShouldBeZero()  {
        Monom testedUnit= new Monom(0,5);
        Monom multMonom= new Monom(3,4);
        testedUnit.multipy(multMonom);
        String multResult= "0";
        assertEquals(testedUnit.toString(), multResult);
    }

    @Test
    public void multipy_givenPositiveMonomsWithDiffPowersAndCofficient()  {
       Monom multiplicand= new Monom(2,5);
       Monom multiplier=new Monom(5,2);
       multiplicand.multipy(multiplier);
       Monom expected= new Monom(10,7);
       assertEquals(multiplicand,expected);
    }

    @Test
    public void multipy_givenPositiveMonomAndNegativeWithDiffPowersAndCofficient()  {
        Monom multiplicand= new Monom(-2,5);
        Monom multiplier=new Monom(5,2);
        multiplicand.multipy(multiplier);
        Monom expected= new Monom(-10,7);
        assertEquals(multiplicand,expected);
    }

    @Test
    public void multipy_givenPositiveMonomsWithZeroMonom_exxpectedToGetZero(){

        Monom multiplicand= new Monom(2,5);
        Monom multiplier=new Monom(0,0);
        multiplicand.multipy(multiplier);
        String ansExpected= "0";
        assertEquals(multiplicand.toString(),ansExpected);
    }

    @Test
    public void toString_givenBla_shouldBla() {

    }

    @Test
    public void initFromString() {
        String str= "3x^4";
        Monom testedUnit= new Monom (0,0);
        testedUnit.initFromString(str);
        System.out.println(testedUnit.toString());
        Monom ansExpected= new Monom(3,4);
        assertEquals(testedUnit, ansExpected);
    }

    @Test
    public void copy() throws Exception {
    }

    @Test
    public void isZeroMonom() throws Exception {
    }

    @Test
    public void equals() throws Exception {
    }

}