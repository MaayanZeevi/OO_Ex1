
<h1>Polynom Project</h1>


<h3>About the program</h3>
The purpose of the program is to represent a polynomial by elementary actions between monom's,
using this program you can calculate complex functions with form of polynomial such as addition, substruct, multiplying, devision and more.
In addition this program also provides a GUI of the polynomial for the user .
<h3>The Project Includes:</h3>
<h3>Package myMath:</h3>
<hr>

<h3>Monom Class</h3>This class represents a simple "Monom" of shape a*x^b, where a is 'a' real number and 'b' is an integer.
<h4>A valid init for Monom:</h4><list>
<li>From other Monom</li><li>From coefficient and power</li><li>From string </li>
<h3>Polynom Class</h3>This class represents a list of  monoms defined by the structure a1x^b1+a2x^b2+…+an*x^bn.
<h4>A valid init for Polynom:</h4><list>
<li>From String which consists of addition and subtraction operations between valid monoms</li>
<li>From other Polynom</li>
<h4>ComplexFunction Class</h4>This class represents a complex function of type y=g(f1(x), f2(x)), while both f1(x) and f2(x) could be instance of polynomial or another complex function.
<h4>Function_GUI Class</h4>This class drawing a graph that simulates the function.
The graph could be recieved by three ways: </list>
    <li> A function that receives parameters from JSON</li> <li>Static function that receives user parameters</li><li>Function that builds without parameters</li>

<h3>Package test:</h3>
<hr>
Unit Testing files in order to test classes of  Monom, Polynom, ComplexFunction and GUI_Function that can be found at myMath package by using JUnit testers.

