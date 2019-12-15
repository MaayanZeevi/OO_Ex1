package myMath;


public class ComplexFunction implements complex_function {
	function l;
	function r;
	Operation op;




	public ComplexFunction() {
		this.l = null;
		this.r = null;
		this.op = Operation.None;
	}


	public Polynom PolynomHelper(String s) {
		return new Polynom(s);
	}


	public ComplexFunction(String s) {
		s = s.toLowerCase();

		String op = "";
		boolean endOp=true;
		int parenthesis=0;
		int index=0;
		for (int i = 0; i < s.length() &&endOp; i++) {
			if (s.charAt(i) == '(') {
				parenthesis++;
				endOp = false;
				index = i +1;
			}
			else {
				op =op+s.charAt(i);
			}

		}
		String l = "";
		boolean endL=true;
		for (int i = index; i < s.length()&&endL; i++) {
			if (s.charAt(i) == ',' && parenthesis == 1) {
				endL = false;
				index = i +1;
			}
			else {
				l=l+s.charAt(i);
				if (s.charAt(i) == ')')parenthesis--;
				if (s.charAt(i) == '(')parenthesis++;
			}
		}
		if(!(l.charAt(0)>= 'a' && l.charAt(0)< 'x') && !(l.charAt(0)> 'x' && l.charAt(0)<= 'z')) {
			this.l = PolynomHelper(l);
		}
		else {
			this.l = new ComplexFunction(l);
		}

		String r = "";
		boolean endR=true;
		for (int i = index; i < s.length()&&endR; i++) {
			if (s.charAt(i) == ')' && parenthesis == 1) {
				endL = false;
			}
			else {
				r=r+s.charAt(i);
				if (s.charAt(i) == ')')parenthesis--;
				if (s.charAt(i) == '(')parenthesis++;
			}
		}
		if(!(r.charAt(0)>= 'a' && r.charAt(0)< 'x') && !(r.charAt(0)> 'x' && r.charAt(0)<= 'z')) {
			this.r = PolynomHelper(l);
		}
		else {
			this.r = new ComplexFunction(l);
		}

		//todo!!!!!!!!!!!!!!!!!!!
		if(op.equals("plus") || op.equals("plus")) {
			this.op = Operation.Plus;

		}
		if(op.equals("times") || op.equals("mul")) {
			this.op = Operation.Times;

		}
		if(op.equals("divid") || op.equals("div")) {
			this.op = Operation.Divid;

		}

		if(op.equals("max") || op.equals("max")) {
			this.op = Operation.Max;

		}
		if(op.equals("min") || op.equals("min")) {
			this.op = Operation.Min;

		}
		if(op.equals("comp") || op.equals("comp")) {
			this.op = Operation.Comp;

		}
		}
		@Override
		public double f(double x) {
			double f1 = 0;
			if(this.l!=null) {
				if (this.l instanceof Polynom ) {
					Polynom p = (Polynom) this.l;
					f1 = p.f(x);
				}
				else {
					f1= this.l.f(x);
				}}
			double f2 = 0;

			if(this.r!=null) {
				if (this.r instanceof Polynom) {
					Polynom p = (Polynom) this.r;
					f2 = p.f(x);
				}
				else {
					f2= this.r.f(x);
				}
			}
			if (op == Operation.None) {
				return 0;
			}
			if (op == Operation.Plus) {
				return f1 + f2;
			}
			if (op == Operation.Times) {
				return f1 * f2;
			}
			if (op == Operation.Divid) {
				return l.f(x) / r.f(x);
			}
			if (op == Operation.Max) {
				return Math.max(l.f(x) , r.f(x));
			}
			if (op == Operation.Min) {
				return Math.min(l.f(x),r.f(x));
			}
			if (op == Operation.Comp) { //to do
				return l.f(r.f(x));
			}
			return 0;
		}

		@Override
		public function initFromString(String s) {
			ComplexFunction cf1 = new ComplexFunction(s);
			return cf1;
		}

		@Override
		public function copy() {
			ComplexFunction copy = new ComplexFunction();
			copy.l = new ComplexFunction(this.l.toString()); //TODO 
			copy.r = new ComplexFunction(this.r.toString());
			copy.op = this.op;
			return copy;
		}

		@Override
		public void plus(function f1) {
			function copy=this.copy();
			this.l=copy;
			this.op=Operation.Plus;
			this.r=f1;
		}

		@Override
		public void mul(function f1) {
			// TODO Auto-generated method stub
			function copy=this.copy();
			this.l=copy;
			this.op=Operation.Times;
			this.r=f1;
		}

		@Override
		public void div(function f1) {
			// TODO Auto-generated method stub
			function copy=this.copy();
			this.l=copy;
			this.op=Operation.Divid;
			this.r=f1;	
		}

		@Override
		public void max(function f1) {
			// TODO Auto-generated method stub
			function copy=this.copy();
			this.l=copy;
			this.op=Operation.Max;
			this.r=f1;
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

		@Override
		public function left() {
			// TODO Auto-generated method stub
			return this.l;
		}

		@Override
		public function right() {
			// TODO Auto-generated method stub
			return this.r;
		}

		@Override
		public Operation getOp() {
			// TODO Auto-generated method stub
			return this.op;
		}
		public String toString() {
			return""+this.op.toString()+"("+this.l.toString()+","+this.r.toString()+")";
		}
		
		public boolean equals(ComplexFunction p) {
			return(this.toString().equals(p.toString()));
			
		}
	}
