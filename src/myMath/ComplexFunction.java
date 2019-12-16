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
	
	public ComplexFunction(function f) {
		initFromString(f.toString());
	}
	
	public ComplexFunction(Polynom p1){
        function left = p1;
        this.l = left;

    }
	public ComplexFunction(String s, function left, function right) {
		s = s.toLowerCase();
		this.op = StringToOperation(s);
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
	public static Operation StringToOperation(String s) {
		s = s.toLowerCase();
		if (s.equals("plus") || s.equals("add")) {
			return  Operation.Plus;
		}
		else if (s.equals("times") || s.equals("mul")) {
			return Operation.Times;
		}
		else if (s.equals("div") || s.equals("divide") || s.equals("divid")) {
			return Operation.Divid;
		}
		else if (s.equals("max")) {
			return Operation.Max;
		}
		else if (s.equals("min")) {
			return Operation.Min;
		}
		else if (s.equals("comp")) {
			return Operation.Comp;
		}
		return Operation.None;
	}
	
	

	public Polynom PolynomHelper(String s) {
		return new Polynom(s);
	}


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
		String func1 = "";
		for (; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				counter++;
			}
			if (s.charAt(i) == ')') {
				counter--;
			}
			if(s.charAt(i) == ',' && counter == 1) {
				i++;
				break;
			}
			func1 = func1 + s.charAt(i);
		}
		String func2 = "";
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
			func2 = func2 + s.charAt(i);
		}
		if (!func1.isEmpty()) {
			if((func1.charAt(0) >= '0' && func1.charAt(0) <= '9') || func1.charAt(0) == 'x' || func1.charAt(0) == '-' || func1.charAt(0) == '+') {
				if(func1.charAt(0) == '+') {
					func1 = func1.substring(1);
				}
				this.l = new Polynom(func1);
			}
			else {
				this.l = new ComplexFunction(func1);
			}
		}
		else {
			this.l= null;
		}
		if (!func2.isEmpty()) {
			if((func2.charAt(0) >= '0' && func2.charAt(0) <= '9') || func2.charAt(0) == 'x' || func2.charAt(0) == '-' || func2.charAt(0) == '+') {
				if(func2.charAt(0) == '+') {
					func2 = func2.substring(1);
				}
				this.r = new Polynom(func2);
			}
			else {
				this.r = new ComplexFunction(func2);
			}
			this.op = StringToOperation(operation);
		}
		}
		
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
			if((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == 'x' || s.charAt(0) == '-' || s.charAt(0) == '+') {
				return new Polynom(s);
			}
			ComplexFunction ans = new ComplexFunction(s);
			return ans;
		}

		@Override
		public function copy() {
			String temp = this.toString();
			ComplexFunction ans = new ComplexFunction(temp);
			return ans;
		}
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
			String ans = "";
			if (this.l != null && this.r != null) {
				ans =  this.op.toString() + "(" + this.l.toString() + "," + this.r.toString() + ")";
			}
			if (this.r == null && this.l != null) {
				ans = this.l.toString();
			}
			return ans;		}
		
		public boolean equals(ComplexFunction p) {
			return(this.toString().equals(p.toString()));
			
		}
	}
