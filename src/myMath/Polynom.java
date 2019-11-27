package myMath;


import Exceptions.wrongDataException;
import java.lang.invoke.WrongMethodTypeException;
import java.util.ArrayList;
import myMath.Monom;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author levana and mayanne
 *
 */
public class Polynom implements Polynom_able{
ArrayList<Monom> p;
	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		p=new ArrayList<Monom>();
	}
	/**
	 * Create a Polynom by inserting a Polynom
	 * @param poly is Polynom which serves to create a new Polynom
	 */
	public Polynom (Polynom copy) {
		
		p=new  ArrayList<Monom>();
		Iterator<Monom>i=copy.iteretor();
		while(i.hasNext()) {
		Monom m= new Monom(i.next());
		p.add(m);
	}
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String string) throws wrongDataException {	

		this();

		PolytoString(string);		



	}

	private void PolytoString(String string) throws wrongDataException {
		// TODO Auto-generated method stub
		boolean is_coefficient = true;

		String coefficient = "" , power = "";

		// replace +- to -

		string = string.replaceAll("\\+-", "-");

		// remove spaces

		string = string.replaceAll(" ", "");

		// remove [ . . . ] 

		string = (string.charAt(0) =='[' && string.charAt(string.length()-1) ==']') ? string.substring(1, string.length()-1) : string;

		for (int i = 0; i < string.length(); i++)

		{	

			// now in coefficient

			if (is_coefficient)

			{

				// not last char

				if (i+1<string.length())

				{

					// end of coefficient because of x X *

					if(string.charAt(i) =='x' || string.charAt(i) =='*' || string.charAt(i) =='X')	

					{

						//if my coefficient is blank so it means the the coefficient is 1

						if (coefficient.equals("") || coefficient.equals("-"))

						{

							coefficient += "1";

						}

						// i already have an x and the + or - means that i dont have a power so it means we are at power 1

						if(string.charAt(i+1)=='+' || string.charAt(i+1) == '-')

						{

							add(new Monom(Double.parseDouble(coefficient),1));

							coefficient = "";

							power = "";

						}

						// i already have an x		

						// regular done with coefficient so we are moving to power so coefficient is now false

						else

						{

							is_coefficient = false;

						}

					}

					// not x nor X nor *

					// if next value is + or - so no x's and its a monom where x^0

					else if (string.charAt(i+1)=='+' || string.charAt(i+1) == '-')

					{

						add(new Monom(Double.parseDouble(coefficient + string.charAt(i)), 0));

						coefficient = "";

					}

					// not x nor X nor * nor + nor -

					// if this value is - or . or digit so add it to coefficient	

					else if((Character.isDigit(string.charAt(i))|| string.charAt(i) == '-' || string.charAt(i) == '.'))

					{

						coefficient = coefficient + string.charAt(i);

					}

					// number starting is + so do nothing

					else if (string.charAt(i) == '+')

					{



					}

					// all other cases where this value is not valid

					else

					{

						throw new wrongDataException("A polynom can not have a not number value =  " + string.charAt(i) + " at location " + i);

					}

				}

				// this is the last char in the string

				// no need the if since its only case that its not "i+1<string.length()" so it would be easier to debug

				else if (i+1==string.length())

				{	

					//  when last char is x or X 

					if(string.charAt(i) =='x' || string.charAt(i) =='X')		

					{

						// make sure coefficient has a value

						if (coefficient.equals("") || coefficient.equals("-"))

						{

							coefficient += "1";

						}

						add(new Monom(Double.parseDouble(coefficient), 1));	

					}

					// when last char is number

					else if (Character.isDigit(string.charAt(i)))

					{

						add(new Monom(Double.parseDouble(coefficient + string.charAt(i)), 0));	

					}

					// all other cases where this value is not valid

					else

					{

						throw new wrongDataException("A polynom can not have a not number value =  " + string.charAt(i) + " at location " + i);

					}

				}

			}

			// now in power

			else

			{



				// not last char

				if (i+1<string.length())

				{

					// making sure that the beginning of power is written correctly

					if (string.charAt(i) == '^' && !Character.isDigit(string.charAt(i+1)) || ((string.charAt(i) == 'x' || string.charAt(i) == 'X') && (string.charAt(i+1) != '^' && string.charAt(i+1) != ',')))

					{

						throw new wrongDataException("A power can not be a negitave power or a random symbal at location " + (i+1) + string.charAt(i) );

					}

					// when the string is presented as the toString value

					// for example "3.0*X^8, -8.0*X^3, 1.1*X, -3.0" 

					// we will need to skip the 2 values ", " and this means we are done with this monom

					if (string.charAt(i+1) ==',')

					{

						add(new Monom(Double.parseDouble(coefficient),(power=="" && (string.charAt(i)=='x' || string.charAt(i)=='X')) ? 1 :Integer.parseInt(power+ string.charAt(i))));

						coefficient = "";

						power = "";

						is_coefficient = true;

						i+=1;

					}

					// please notice && 

					// this case when the power is - we throw an exception

					else if (string.charAt(i+1) == '-' && string.charAt(i)=='^')

					{

						throw new wrongDataException("A power can not be a negitave power at location " + i );

					}

					// if next value is + or - it means we are done with this monom

					else if(string.charAt(i+1)=='+' || string.charAt(i+1) == '-' )

					{

						add(new Monom(Double.parseDouble(coefficient),Integer.parseInt(power+ string.charAt(i))));

						coefficient = "";

						power = "";

						is_coefficient = true;

					}

					// if we are not - nor + 

					// and we are a digit so add it to the power

					else if (Character.isDigit(string.charAt(i)))				

					{

						power = power + string.charAt(i);

					}

					else if (string.charAt(i) == 'x' || string.charAt(i) == 'X' || string.charAt(i) == '^')

					{



					}

					else if (string.charAt(i) == ',' && power =="")				

					{

						add(new Monom(Double.parseDouble(coefficient),1));

						coefficient = "";

						power = "";

						is_coefficient = true;			

					}

					// if not number 

					// all other cases where this value is not valid

					else

					{

						throw new wrongDataException("A polynom can not have a not number value =  " + string.charAt(i) + " at location " + i);

					}

				}

				// this is the last char in the string

				// no need the if since its only case that its not "i+1<string.length()" so it would be easier to debug

				else if (i+1==string.length())

				{

					// making sure last char is a digit

					if (Character.isDigit(string.charAt(i)))

					{

						add(new Monom(Double.parseDouble(coefficient), Integer.parseInt(power+ string.charAt(i))));

					}

					// all other cases where this value is not valid

					else

					{

						throw new wrongDataException("A polynom can not have a not number value = '" + string.charAt(i) + "' at location " + i);

					}

				}

			}

		}

	}



	
	
	/**
	 * This function calculate the value of y by giving x from the user of this polynom.
	 * @param x Is the entry entered into the function f
	 */
	
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		Iterator<Monom>i=this.iteretor();
		double ans=0;
		while(i.hasNext()) {
			Monom m=i.next();
			ans=ans+m.f(x);
		}
		return ans;
	}
	/**
	 * Function that allows us to add a Polynom to our current Polynom
	 */
	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom>i=p1.iteretor();
		while(i.hasNext()) {
			Monom m=i.next();
			this.add(m);
		}
	}
	/**
	 * Function that allows us to add a Monom to our current Polynom and sort them
	 */
	@Override
	public void add(Monom m1) {
		// TODO Auto-generated method stub
		Iterator<Monom>i=this.iteretor();
		boolean flag=false;
		while(i.hasNext()&&flag==false) {
			Monom m=i.next();
			if(m.get_power()==m1.get_power()) {
			m.add(m1);flag=true;	
			}
		
	}
if(flag==false)
	p.add(m1);
	}
	
	/**
	 * Function that allows us to substract a Polynom to our current Polynom
	 */
	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom>i=p1.iteretor();
		while(i.hasNext()) {
			Monom m=i.next();
			m.multByNumber(-1);
			this.add(m);
		}
	}
	/**
	 * Function that allows us to multiply a Polynom to our current Polynom
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom_able mult= this.copy();
		p.clear();
		Iterator<Monom> it= mult.iteretor();
		while(it.hasNext()) {
			Monom m1= it.next();
			Iterator<Monom> it2= p1.iteretor();
			while(it2.hasNext()) {
				Monom m2= new Monom(it2.next());
				m2.multipy(m1);
				this.add(m2);
			}
		}
	}
	/**
	 *  Gets a given polynom and equals its monoms and checks if the monoms are the same as in this polynom
	 *
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		// TODO Auto-generated method stub
		
		if(this.toString().equals(p1.toString()))return true;
		return false;
	}

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		Iterator<Monom>i=this.iteretor();
		while(i.hasNext()) {
			Monom m=i.next();
			if(!m.isZero())return false;
		}
		return true;
	}

	@Override

	
		
		
		public double root(double x0, double x1, double eps) {
		double c = x0;

		if((f(x0)*f(x1)) >= 0) {
			System.out.println("There is no solotion for this polynom.");
			return Double.MAX_VALUE;
		}
		else {
			while ((x1 - x0) >= eps) {			
				c = (x0+x1)/2;
				if (f(c) == 0.0) {
					break;
				}

				else if (f(c)*f(x0) < 0) {
					x1 = c;
				}

				else {
					x0 = c;
				}
			}

		}

		return c;
		}
	

	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
				return new Polynom(this);

	}
	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		Polynom p=new Polynom();
		Iterator<Monom> it= this.iteretor();
		while(it.hasNext()) {
			Monom m= it.next();
			m.derivative();
			p.add(m);
		}
		return p;
	}
	
	/**
	 *   Printed a Polynom in the following style: "Polynom :2.0x^5, 6.0x^3, 5.0x^2, 4.0"
	 @return String in the form of a Polynom  
	 */
	public String toString() {

		return p.toString();



	}
	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		
		double sum=0;
		double min=Math.min(x0, x1);
		double max=Math.max(x0, x1);
		while(min<max) {
			min=min+eps;
			if(this.f(min)>0) {
				sum=sum+eps*this.f(min);
			}
			
		}
		return sum;
	}

	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		
		return p.listIterator();
	}
	@Override
	public void multiply(Monom m1) {
		// TODO Auto-generated method stub
		Polynom_able mult= this.copy();
		p.clear();
		Iterator<Monom> it= mult.iteretor();
		while(it.hasNext()) {
			Monom m= it.next();	
			m.multipy(m1);
			p.add(m);
	}
	
}
}
