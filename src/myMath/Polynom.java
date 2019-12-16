package myMath;

import java.util.ArrayList;

import java.util.Iterator;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 *
 * @author Levana and Maayan
 */
public class Polynom implements Polynom_able {
	ArrayList<Monom> p;

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {

		p = new ArrayList<Monom>();
	}

	/**
	 * Create a Polynom by inserting a Polynom
	 *
	 * @param copy is a Polynom which serves to create a new Polynom
	 */
	public Polynom(Polynom copy) {

		p = new ArrayList<Monom>();
		Iterator<Monom> i = copy.iteretor();
		while (i.hasNext()) {
			Monom m = new Monom(i.next());
			p.add(m);
		}
	}

	/**
	 * init a Polynom from a String such as:
	 * {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 *
	 * @param string: is a string represents a Polynom
	 */



	public Polynom(String string) {
		String monom="";
		if(this.p==null) p=new ArrayList<Monom>();
		for (int i = 0; i <string.length(); i++) {
			
			if(string.charAt(i)=='+')	{
				Monom m=new Monom(monom);
				this.add(m);
				monom="";
			}
			else if(string.charAt(i)=='-'){
				Monom m=new Monom(monom);
				this.add(m);
				monom="-";
			}
			else {
				monom=monom+string.charAt(i);
			}
		
		}
		
		Monom m=new Monom(monom);
		
		
		
		this.add(m);
		
	}

	

	/**
	 * This function calculate the value of y by giving x from the user of this Polynom.
	 *
	 * @param x Is the entry entered into the function f
	 */

	@Override
	public double f(double x) {
		Iterator<Monom> i = this.iteretor();
		double ans = 0;
		while (i.hasNext()) {
			Monom m = i.next();
			ans = ans + m.f(x);
		}
		return ans;
	}

	/**
	 * Function that allows us to add a Polynom to our current Polynom
	 *
	 * @param p1 is the Polynom which added to the current Polynom
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> i = p1.iteretor();
		while (i.hasNext()) {
			Monom m = i.next();
			this.add(m);
		}
	}

	/**
	 * Function that allows us to add a Monom to our current Polynom and sort them
	 */
	@Override
	public void add(Monom m1) {
		boolean flag = true; // check if we already added the monom (m1) to this Polynom
		if (this.p.isEmpty()) {
			this.p.add(m1);
			flag = false;
		}
		else {
			Iterator<Monom> iterator = this.iteretor();
			Monom temp;
			while (flag && iterator.hasNext()) {
				temp = iterator.next();
				if (temp.get_power() == m1.get_power()) { 
					temp.add(m1);
					flag = false;
				}
				else if(temp.get_power() < m1.get_power()) { 
					Monom temp1 = temp;
					int index = this.p.indexOf(temp1); 
					this.p.set(index, m1); 
					Monom temp2;
					while (iterator.hasNext()) { 
						temp2 = iterator.next();
						index++;
						this.p.set(index, temp1);
						temp1 = temp2;
					}
					this.p.add(temp1);
					flag = false;
				}
			}
		}
		if (flag) { // place it at the end.
			this.p.add(m1);
		}
			
	}

	/**
	 * Function that allows us to substract a Polynom to our current Polynom
	 *
	 * @param p1 is the Polynom which substracted from our Polynom
	 */
	@Override
	public void substract(Polynom_able p1) {
		if (this == p1) {
			this.p.clear();
			this.p.add(new Monom(0,0));
		}
		else {
			Iterator<Monom> iterator = p1.iteretor();
			while (iterator.hasNext()) {
				Monom temp = iterator.next();
				this.substract(temp);
			}
		}
	}
	
	public void substract(Monom m1) { // Similar to the add function.
		if (!this.p.isEmpty()) {
			Iterator<Monom> iterator = this.iteretor();
			Monom temp;
			boolean flag = false;
			while (!flag && iterator.hasNext()) {
				temp = iterator.next();
				if (temp.get_power() == m1.get_power()) {
					temp.substract(m1);
					flag = true;
				}
				else if(temp.get_power() < m1.get_power()) {
					Monom m = temp;
					int index = this.p.indexOf(m);
					this.p.set(index, m1);
					Monom temp1;
					while (iterator.hasNext()) {
						temp1 = iterator.next();
						index++;
						this.p.set(index, m);
						m = temp1;
					}
					this.p.add(m);
					flag = true;
				}
				
			}
		}
		else {
			m1.opposite();
			this.p.add(m1);
		}
	}



	/**
	 * Function that allows us to multiply a Polynom to our current Polynom
	 *
	 * @param multPolynom is the Polynom we mult with the current Polynom
	 */
	@Override
	public void multiply(Polynom_able multPolynom) {
		Polynom_able Polynom = this.copy();
		
		p.clear();
		Iterator<Monom> i1 = Polynom.iteretor();
		while (i1.hasNext()) {
			Monom m1 = i1.next();
			Iterator<Monom> it2 = multPolynom.iteretor();
			while (it2.hasNext()) {
				Monom m2 = new Monom(it2.next());
				m2.multipy(m1);
				this.add(m2);
			}
		}
	}

	
	/**
	 * Gets a given polynom and equals its monoms and checks if the monoms are the same as in this polynom
	 */
	@Override
	public boolean equals(Object p1) {
		if(! (p1 instanceof Polynom_able)) {
			return false;
		}
		Polynom p1Casting= ((Polynom) p1);
		if(p1Casting.getP().size()!= this.getP().size()){
			return false;
		}
		Iterator<Monom> i1 = this.iteretor();
		boolean flag;
		while(i1.hasNext()){
			flag=false;
			Monom m1= new Monom(i1.next());
			Iterator<Monom> i2 = p1Casting.iteretor();
			while(i2.hasNext()&& !flag){
				Monom m2=i2.next();
				if(m1.equals(m2)){
					flag=true;
				}
			}
			if(!flag){
				return false;
			}
		}
		return true;
	}
	private ArrayList<Monom> getP(){
		return p;
	}
	@Override
	public boolean isZero() {
		Iterator<Monom> i = this.iteretor();
		while (i.hasNext()) {
			Monom m = i.next();
			if (!m.isZero()) return false;
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if(f(x0)*f(x1)>0) throw new RuntimeException("The func` do not Cross the X line");
		double mid = (x0+x1)/2;
		while(Math.abs(f(mid))>eps){
			if(f(mid)*f(x0)<0) x1 = mid;
			else x0 = mid;
			mid = (x0+x1)/2;
		}
		return mid;	
	}


	@Override
	public Polynom_able copy() {
				Polynom newpolynom = new Polynom();
				Iterator<Monom> iterator = this.iteretor();
				Monom temp;
				while(iterator.hasNext()) {
					temp = iterator.next();
					Monom newMonom = new Monom(temp);
					newpolynom.add(newMonom);
				}
				return newpolynom;
			}
	

	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		Iterator<Monom> iterator = this.p.iterator();
		Monom temp;
		Polynom ans = new Polynom();
		while(iterator.hasNext()) {
			temp = iterator.next();
			ans.add(temp.derivative());
		}
		return ans;
	}

	/**
	 * Printed a Polynom in the following style: "Polynom :2.0x^5, 6.0x^3, 5.0x^2, 4.0"
	 *
	 * @return String in the form of a Polynom
	 */
	public String toString() {

		String result = "";
		Iterator<Monom> iterator = this.iteretor();
		if(iterator.hasNext()) {
			result = iterator.next().toString();
		}
		while (iterator.hasNext()) {
			String temp = iterator.next().toString();
			if(!temp.equals("0")) {
				result = result + "+" + temp;
			}
		}
		return result;

	}

	@Override
	public function initFromString(String s) {
		return new Polynom(s);
	}

	@Override
	public double area(double x0, double x1, double eps) {

		double ans = 0;
		while(x0 < x1 && f(x0) > 0) {
			double w = this.f(x0);
			double temp = eps * w;
			temp = Math.abs(temp);
			ans= ans + temp;
			x0 = x0 + eps;
		}
		return ans;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return p.listIterator();
	}

	@Override
	public void multiply(Monom m1) {
		Polynom_able mult = this.copy();
		p.clear();
		Iterator<Monom> it = mult.iteretor();
		while (it.hasNext()) {
			Monom m = it.next();
			m.multipy(m1);
			p.add(m);
		}

	}
}
