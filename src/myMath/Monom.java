
package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative),
 * see: https://en.wikipedia.org/wiki/Monomial
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply.
 *
 * @author Levana and Maayan
 */
public class Monom implements function {
    public static final Monom ZERO = new Monom(0, 0);
    public static final Monom MINUS1 = new Monom(-1, 0);
    public static final double EPSILON = 0.0000001;
    public static final Comparator<Monom> _Comp = new Monom_Comperator();

    public static Comparator<Monom> getComp() {
        return _Comp;
    }

    private double _coefficient;
    private int _power;

    /**
     * Constructor who creates an object of the class
     *
     * @param a this is the parameter that defines my coefficient of the Monom for exemple 3*x^4 as a is 3
     * @param b this is the parameter that defines the power of the Monom for exemple 3*x^4 as b is 4
     */
    public Monom(double a, int b) {
        this.set_coefficient(a);
        this.set_power(b);
    }

    /**
     * Copy Constructor of Monom
     *
     * @param ot receives an object Monom and the copy in our initial object
     */
    public Monom(Monom ot) {

        this(ot.get_coefficient(), ot.get_power());
    }

    /**
     * Method that returns the Coefficient of a function
     *
     * @return the value of the coefficient 'a' of my function
     */
    public double get_coefficient() {
        return this._coefficient;
    }

    /**
     * Method that returns the Power of a function
     *
     * @return the value of the Power 'b' of my function
     */
    public int get_power() {
        return this._power;
    }

    /**
     * Method that returns the result of multiplying the function by number
     *
     * @return the value of the Multiply num with 'a' of the function
     */
    public void multByNumber(int num) {
        this.set_coefficient(_coefficient * num);
    }

    /**
     * Method that returns the derivative of the current function
     *
     * @return the result of the derivative of the current monom
     */
    public Monom derivative() {
        if (this.get_power() == 0) {
            return getNewZeroMonom();
        }
        return new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1);
    }

    /**
     * Method that returns the calculate of function with the value x
     *
     * @return the result of output of the function with value x, f(x)
     */
    public double f(double x) {
        double ans = 0;
        double p = this.get_power();
        ans = this.get_coefficient() * Math.pow(x, p);
        return ans;
    }

    /**
     * Check if a Monom is 0.
     *
     * @return True if a Monom is 0 else False.
     */
    public boolean isZero() {
        return this.get_coefficient() == 0;
    }
    /**
     * Constructor who creates an object of the class by a String
     * @param s this is a String which includes the monom details of 'a' and 'b'
     */
    public Monom(String s) {
        if (!s.contains("x")) {
            s = s + "x^0";
        }
        if (s.indexOf('x') == 0) {
            s = '1' + s;
        }
        if (s.indexOf('x') == s.length() - 1) {
            s = s + "^1";
        }

        String a = "";
        String b = "";
        int index = s.indexOf('x');
        a = s.substring(0, index);
        System.out.println(a);
        b = s.substring(index + 2);

        if (a.equals("-")) {
            a = a + '1';
        }
        double a1 = Double.parseDouble(a);
        int b1 = Integer.parseInt(b);
        this._coefficient = a1;
        this._power = b1;
    }

    /**
     * This method calculate addition between two Monoms, only if their powers are equal.
     *
     * @param m is a Monom which will be added to the current Monom
     */
    public void add(Monom m) {
        if (this.get_power() != m.get_power()) {
            throw new IllegalArgumentException("Exponents must match in order to add");
        }
        else {
            this.set_coefficient(this.get_coefficient() + m.get_coefficient());
        }
    }

    /**
     * This method calculate multiply between two Monoms.
     *
     * @param d is a Monom which will be multiply with the current Monom
     */
    public void multipy(Monom d) {
        this.set_coefficient(this.get_coefficient() * d.get_coefficient());
        this.set_power(this.get_power() + d.get_power());
    }

    /**
     * This method is printing the current Monom for instance: "Monom: 2.0x^9"
     *
     * @return String in the form of the current Monom
     */
    public String toString() {
        if (this._power == 0) {

            return this._coefficient + "";

        }

        if (this._coefficient == 0) {

            return 0 + "";

        } else

            return this._coefficient + "x^" + this._power;


    }
    /**
     * This method is generate performance of function String
     *
     * @param s is a String with the details of the function
     */
    @Override
    public function initFromString(String s) {
        Monom mn= new Monom(s);
        return mn;
    }
    /**
     * This method is generate identical appearance of the current Monom with property of function
     *
     * @return function
     */
    @Override
    public function copy() {
        double a= this.get_coefficient();
        int b= this.get_power();
        Monom mn= new Monom(a,b);
        return mn;
    }

    /**
     * Method that change the coefficient of a Monom
     *
     * @param a Input a coefficient 'a' in the Object
     */
    private void set_coefficient(double a) {

        this._coefficient = a;
    }

    /**
     * Method that change the Power of a Monom , the method only accepts positive numbers.If the user enters a negative value,
     * If the user enters a negative value, by default the value enter will be 1.
     * @param p Input a Power 'p' in the Object
     */
    private void set_power(int p) {
        if (p < 0) {
            throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
        }
        this._power = p;
    }
    /**
     * This Method is generate Monom with zero values
     *
     * @return Monom with zero values
     */
    private static Monom getNewZeroMonom() {
        return new Monom(ZERO);
    }
    /**
     * This Method is checking if the monom is zero
     *
     */
    public boolean isZeroMonom() {

        return this._coefficient == 0;

    }
    public boolean equals(Object obj){
        if(!(obj instanceof Monom)){
            return false;
        }
        return ((this.get_power()==((Monom) obj).get_power()) && (this.get_coefficient()==((Monom) obj).get_coefficient()));
    }

}
