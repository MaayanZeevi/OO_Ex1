package myMath;
import java.util.ArrayList;
/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
*****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
	}
	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"2", "-x","-3.2x^2","0"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}
	private static void test2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
	}
	
	private static void test3() {
		Monom d=new Monom(3,5);
		Monom f=new Monom(1,5);
		d.add(f);
		boolean ans=d.toString().equals("4.0x^5");
		System.out.println(ans);  //true 
		d.multipy(f);
		boolean ans1=d.toString().equals("4.0x^10");
		System.out.println(ans1);
		
	}
	
	//
	//מה שכתוב בהערות זה מה שאנחנו מצפים לקבל אנחנו בנינו טסטים בשביל מחלקה של המונום כדי לבדוק שהבנאים והמתודות עובדות כמו שצריך
	private static void test4() {
		Monom m1= new Monom(0,0);
		Monom m2= new Monom("2x^1");
		Monom m4= new Monom("0x^4");
		Monom m5= new Monom(-0.3,0);
		Monom m6= new Monom(5.2,11);
		Monom m7= new Monom(-0,9);
		Monom m8= new Monom(m2);
		Monom m9= new Monom(3,9);
		Monom m0= new Monom(-1,9);


		//to String
		System.out.println("\ntostring Test\n");

		System.out.println(m1.toString());    // 0
		System.out.println(m2.toString());    //2x
		System.out.println(m4.toString());    //0
		System.out.println(m5.toString());    //-0.3
		System.out.println(m6.toString());    //5.2x^11
		System.out.println(m7.toString());    //0
		System.out.println(m8.toString());    //2x

		//f
		System.out.println("\nf Test\n");

		System.out.println(m1.f(2));   //0
		System.out.println(m2.f(2));   //4
		System.out.println(m4.f(2));   //0
		System.out.println(m5.f(2));  //-0.3
		System.out.println(m6.f(2));  //10649.6
		System.out.println(m7.f(2)); //0
		System.out.println(m8.f(2));  //4

		//add
		System.out.println("\nadd Test\n");
		m5.add(m1);
		m9.add(m0); 
		System.out.println(m5.toString());  // -0.3
		System.out.println(m9.toString());  // 2x^9

		//mult
		System.out.println("\nmult Test\n");

		m1.multipy(m9);
		m6.multipy(m5);
		m0.multipy(m5);
		m2.multipy(m8);
		m7.multipy(m2);
		System.out.println(m1.toString());  //0
		System.out.println(m6.toString());  // -1.56x^11
		System.out.println(m0.toString());  // 0.3x^9
		System.out.println(m2.toString());  // 4x^2
		System.out.println(m7.toString());  //0

		//derivative
		System.out.println("\nder Test\n");

		m6.derivative();
		m5.derivative();
		m0.derivative();
		m1.derivative();
		m2.derivative();
		System.out.println(m6.toString()); // -1.56x^11
		System.out.println(m5.toString());  //-0.3
		System.out.println(m0.toString());  // 0.3x^9
		System.out.println(m1.toString());  //0
		System.out.println(m2.toString());  //4.0x^2

		//is zero
		System.out.println("\nzero Test\n");

		System.out.println(m1.isZero()); // true
		System.out.println(m2.isZero()); // false
	}
}
