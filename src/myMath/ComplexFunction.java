package myMath;


public class ComplexFunction implements complex_function {
	function l;
	function r;
	Operation op;



	/**
	 * Constructs an empty ComplexFunction
	 */
	public ComplexFunction() {
		this.l = null;
		this.r = null;
		this.op = Operation.None;
	}
	
	/**
	 * 
	 * Constructs a ComplexFunction from our operation, a function on its left ans right side.
	 * 
	 * @param op
	 * @param left
	 * @param right
	 */
	public ComplexFunction(Operation op, function left, function right) {
		this.op = op;
		this.l = left;
		this.r = right; 
		if (this.l== null) {
			this.r = left;
			this.r= null;
		}
		if (this.r== null) {
			this.op = Operation.None;
		}
	}
	
	/**
	 * * Constructs a ComplexFunction from a String only. 
	 * @param s
	 */
	public ComplexFunction(String s) {
		s = s.toLowerCase();
		if(s.isEmpty()) {
			return;
		}
		String operation = "";
		int counter = 0;
		int i = 0;
		for (; i < s.length(); i++) {
			if (s.charAt(i) != '(') {
				operation = operation + s.charAt(i);
			}
			else {
				counter++;
				i++;
				break;
			}
		}
		String temp1 = "";
		for (; i < s.length(); i++) {
			
			if (s.charAt(i) == ')') {
				counter--;
			}
			if (s.charAt(i) == '(') {
				counter++;
			}
			if(s.charAt(i) == ',' && counter == 1) {
				i++;
				break;
			}
			temp1 = temp1 + s.charAt(i);
		}
		String temp2 = "";
		for (; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				counter++;
			}
			if (s.charAt(i) == ')') {
				counter--;
			}
			if(s.charAt(i) == ')' && counter == 0) {
				break;
			}
			temp2 = temp2 + s.charAt(i);
		}
		if (!temp1.isEmpty()) {
			if((temp1.charAt(0) >= '0' && temp1.charAt(0) <= '9') || temp1.charAt(0) == 'x' || temp1.charAt(0) == '-' || temp1.charAt(0) == '+') {
				if(temp1.charAt(0) == '+') {
					temp1 = temp1.substring(1);
				}
				this.l = new Polynom(temp1);
			}
			else {
				this.l = new ComplexFunction(temp1);
			}
		}
		else {
			this.l= null;
		}
		if (!temp2.isEmpty()) {
			if((temp2.charAt(0) >= '0' && temp2.charAt(0) <= '9') || temp2.charAt(0) == 'x' || temp2.charAt(0) == '-' || temp2.charAt(0) == '+') {
				if(temp2.charAt(0) == '+') {
					temp2 = temp2.substring(1);
				}
				this.r = new Polynom(temp2);
			}
			else {
				this.r = new ComplexFunction(temp2);
			}
			this.op =  string_became_operation(operation);
		}
		}
	public ComplexFunction(function f) {
		initFromString(f.toString());
	}
	
	public ComplexFunction(Polynom p1){
        function left = p1;
        this.l = left;

    }
	/**
	 * 
	 * Constructs a ComplexFunction by receiving a String(this  is the operation), a function on its left side  and right side.
	 * @param s
	 * @param left
	 * @param right
	 */
	public ComplexFunction(String s, function left, function right) {
		s = s.toLowerCase();
		this.op =  string_became_operation(s);
		this.l = left.copy();
		this.r = right.copy(); 
		if (this.l == null) {
			this.r = left;
			this.r = null;
		}
		if (this.r == null) {
			this.op = Operation.None;
		}
	}
	
	/**
	 * 	 * This function is analyzed  on the String , the function is returning an Operation from 
	 * the enum class. 
	 *
	 * @param s
	 * @return Operation
	 */
	public static Operation string_became_operation(String s) {
		s = s.toLowerCase();
		if (s.equals("plus") || s.equals("add")) {
			return  Operation.Plus;
		}
		
		else if (s.equals("div") || s.equals("divide") || s.equals("divid")) {
			return Operation.Divid;
		}
		else if (s.equals("times") || s.equals("mul")) {
			return Operation.Times;
		}
		else if (s.equals("min")) {
			return Operation.Min;
		}
		else if (s.equals("max")) {
			return Operation.Max;
		}
		
		else if (s.equals("comp")) {
			return Operation.Comp;
		}
		return Operation.None;
	}
	
	

	public Polynom help(String s) {
		return new Polynom(s);
	}


	/**
	 * Implementing the f function from complex_function interface.
	 * return  the value of f(x) for a given number x.
	 * 
	 * @param x
	 */
		@Override
		public double f(double x) {
			double l1 = 0;
			double r1 = 0;
			if (this.r != null) {
				r1 = r.f(x);
			}
			if (this.l!= null) {
				l1 = l.f(x);
			}
			if(op == Operation.Plus) {
				return l1+r1;
			}
			if(op == Operation.Times) {
				return l1*r1;
			}
			if(op == Operation.Divid) {
				return l1/r1;
			}
			if(op == Operation.Max) {
				return Math.max(l1, r1);
			}
			if(op == Operation.Min) {
				return Math.min(l1, r1);
			}
			if(op == Operation.Comp) {
				return l.f(r.f(x));
			}
			return 0;
		}

		@Override
		public function initFromString(String s) {
			//check if instance polynom
			if((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == 'x' || s.charAt(0) == '-' || s.charAt(0) == '+') {
				return new Polynom(s);
			}
			//else is complexfunction
			ComplexFunction ans = new ComplexFunction(s);
			return ans;
		}
		/*
		 * Implementing the copy function from complex_function interface.
		 * Performs a deep copy of a given function.
		 */
		@Override
		public function copy() {
			String temp = this.toString();
			ComplexFunction ans = new ComplexFunction(temp);
			return ans;
		}
		
		/**
		 *  * Implementing the plus function from complex_function interface.
		 * put this function it on left side of the ComplexFunction and f1 on the right side of it. 
		 * Sets the operation "plus" between this function and f1.
		 * @param f1
		 */
		@Override
		public void plus(function f1) {
			function copy=this.copy();
			this.l=copy;
			this.op=Operation.Plus;
			this.r=f1;
		}
		/**
		 * * Implementing the mul function from complex_function interface.
		 * this put it on left side of the ComplexFunction and f1 on the right side of it. 
		 * Sets the operation "multiply" between this function and f1.
		 * @param f1
		 */
		@Override
		public void mul(function f1) {
			// TODO Auto-generated method stub
			if (this.l == null && this.r == null) {
				this.l = f1.copy();
				return;
			}
			if(this.r== null) {
				this.op = Operation.Times;
				this.r = f1.copy();
				return;
			}
			function temp = this.copy();
			this.l= temp;
			this.op = Operation.Times;
			this.r = f1;
		}
		/**
		 * Implementing the div function from complex_function interface.
		 *this  put it on left side of the ComplexFunction and f1 on the right side of it. 
		 * Sets the operation "divide" between this function and f1.
		 * @param f1
		 */
		@Override
		public void div(function f1) {
			if (this.l == null && this.r== null) {
				this.l= f1.copy();
				return;
			}
			if(this.r == null) {
				this.op = Operation.Divid;
				this.r = f1.copy();
				return;
			}
			function temp = this.copy();
			this.l= temp;
			this.op = Operation.Divid;
			this.r = f1;
		}

		
		/**
		 * Implementing the max function from complex_function interface.
		 * this put it on left side of the ComplexFunction and f1 on the right side of it. 
		 * Sets the operation "maximum" between this function and f1.
		 */
		@Override
		public void max(function f1) {
			// TODO Auto-generated method stub
			if (this.l == null && this.r == null) {
				this.l = f1.copy();
				return;
			}
			if(this.r == null) {
				this.op = Operation.Max;
				this.r= f1.copy();
				return;
			}
			function temp = this.copy();
			this.l= temp;
			this.op = Operation.Max;
			this.r = f1;
		}

		@Override
		public void min(function f1) {
			// TODO Auto-generated method stub
			function copy=this.copy();
			this.l=copy;
			this.op=Operation.Min;
			this.r=f1;
		}

		@Override
		public void comp(function f1) {
			// TODO Auto-generated method stub
			function copy=this.copy();
			this.l=copy;
			this.op=Operation.Comp;
			this.r=f1;
		}
		/*
		 * Implementing the left function from complex_function interface.
		 * Returns the left side of this function.
		 */
		@Override
		public function left() {
			// TODO Auto-generated method stub
			return this.l;
		}
		/*
		 * Implementing the left function from complex_function interface.
		 * Returns the right side of this function.
		 */
		@Override
		public function right() {
			// TODO Auto-generated method stub
			return this.r;
		}
		/*
		 * Implementing the left function from complex_function interface.
		 * Returns the operation .
		 */
		@Override
		public Operation getOp() {
			// TODO Auto-generated method stub
			return this.op;
		}
		
		/*
		 * Function that turns a complexfunction into a String.
		 * 
		 */
		public String toString() {
			String ans = "";
			if (this.l != null && this.r != null) {
				ans =  this.op.toString() + "(" + this.l.toString() + "," + this.r.toString() + ")";
			}
			if (this.r == null && this.l != null) {
				ans = this.l.toString();
			}
			return ans;		}
		
		
		/**
		 * Compares two  ComplexFunction 
		 * Returns true or false.
		 */
		public boolean equals(ComplexFunction p) {
			return(this.toString().equals(p.toString()));
			
		}
	}
