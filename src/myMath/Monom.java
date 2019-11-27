
package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author levana
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	
	/**
	 * Constructor who creates an object of the class 
	 * @param a this is the parameter that defines my coefficient of the Monom for exemple 3*x^4 as a is 3
	 * @param b this is the parameter that defines the powerof the Monom for exemple 3*x^4 as a is 4
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	
	/**
	 * Copy Constructor of Monom 
	 * @param ot receives an object Monom and the copy in our initial object
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	/** 
	 * Method that returns the Coefficient of a function 
	 * @return the value of the coefficient 'a' of my function
	 */
	public double get_coefficient() {
		return this._coefficient;
	}
	/**
	 * Method that returns the Power of a function 
	 * @return the value of the Power 'b' of my function
	 */
	public int get_power() {
		return this._power;
	}
	
	public void multByNumber(int num) {
		this.set_coefficient(_coefficient*num);
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return monom 
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	/**
	 * Check if a Monom is 0.
	 * @return True if a Monom is 0 else False.
	 */
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	public Monom(String s) {
		
if(!s.contains("x")) {
	s=s+"x^0";
}
if(s.indexOf('x')==0) {
	s='1'+s;
}
if(s.indexOf('x')==s.length()-1) {
	s=s+"^1";
}

		String a="";
		String b="";
		int index=s.indexOf('x');
		a=s.substring(0, index);
		System.out.println(a);
		b=s.substring(index+2);

if(a.equals("-")) {
	a=a+'1';
}
double  a1=Double.parseDouble(a);

int b1=Integer.parseInt(b);
this._coefficient=a1;
this._power=b1;
	}
	
	/**
	 * Adding between two Monoms, only if their powers are equal. 
	 * @param m Is a Monom with which i want to make the adding.
	 */
	public void add(Monom m) {
		if(this.get_power()!=m.get_power())
			throw new IllegalArgumentException("Exponents must match in order to add");
		else {
			this.set_coefficient(this.get_coefficient()+m.get_coefficient());
		}
}
	/**
	 * Multiply between two Monoms. 
	 * @param m Is a Monom with which i want to make the Multiply.
	 * @return A Monom of the multiplication of my two Monoms.
	 */
	public void multipy(Monom d) {
	this.set_coefficient(this.get_coefficient()*d.get_coefficient());
	this.set_power(this.get_power()+d.get_power());
	}
	/**
	 * Printed a Monom in the following style: "Monom: 2.0x^9"
	 @return String in the form of a Monom 
	 */
	public String toString() {
		if (this._power == 0) {

			return this._coefficient+"";

		}

		if (this._coefficient == 0) {

			return 0+"";

		}

		else

			return this._coefficient+"x^"+this._power;



	
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	
	/**
	 * Method that change the coeffcient of a Monom 
	 * @param a Input a coefficient 'a' in the Object 
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 *Method that change the Power of a Monom , the method only accepts positive numbers.If the user enters a negative value, by default the value enter will be 1.
	 * @param p Input a Power 'p' in the Object
	 */
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	
	public boolean isZeroMonom() {

		return this._coefficient == 0;

	}
	private double _coefficient; 
	private int _power;
	
	
}
