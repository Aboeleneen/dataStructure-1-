package eg.edu.alexu.csd.datastructure.stack.cs48;

import eg.edu.alexu.csd.datastructure.stack.IExpressionEvaluator;

public class stackApp implements IExpressionEvaluator {

	public Stack operators = new Stack();
	public Stack operands = new Stack();
	public String postFix = "";
	public String output = "";

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eg.edu.alexu.csd.datastructure.stack.IExpressionEvaluator#infixToPostfix(
	 * java.lang.String)
	 */
	public String infixToPostfix(final String expression) {
		// TODO Auto-generated method stub
		boolean space=true;
		if (expression == null || expression.charAt(0) == ' ') {
			throw new RuntimeException();
		}
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			switch (ch) {
			case '+':
			case '-':
				addOper(ch, 1);
				space=true;
				break;
			case '*':
			case '/':
				addOper(ch, 2);
				space=true;
				break;
			case '(':
				operators.push(ch);
				space=true;
				break;
			case ')':
				Paren(ch);
				space=true;
				break;
			default:
				if(space){
				postFix = postFix + ch;
				space=false;}
				break;
			}

		}
		/*
		 * System.out.println("we are " +operators.peek()); operators.pop();
		 * System.out.println("you are " +operators.peek()); operators.pop();
		 * System.out.println(operators.isEmpty());
		 */

		while (!operators.isEmpty()) {
			char j = (char) operators.pop();
			postFix = postFix + j;
		}
		return postFix;

	}

	@Override
	public int evaluate(final String expression) {
		// TODO Auto-generated method stub
		if (expression == null || expression.charAt(0) == ' ') {
			throw new RuntimeException();
		}
		output =expression;
		int num1, num2,res;
		for (int i = 0; i < output.length(); i++) {
			char ch = output.charAt(i);

			if (ch == ' ') {
			} else {
				if (ch > '0' && ch < '9') {
					operands.push(ch);
				} else {
					num1 = Integer.parseInt(""+operands.pop());
					num2 = Integer.parseInt(""+operands.pop());
					switch (ch) {
					case '+':
						operands.push(num1 + num2);
						break;
					case '-':
						operands.push(num2 - num1);
						break;
					case '*':
						operands.push(num1 * num2);
						break;
					case '/':
						operands.push(num1 / num2);
						break;
					}
				}
			}
		}
		
		res = Integer.parseInt("" + operands.pop());

		return res;
	}

	public void addOper(final char operator, final int prec1) {
		while (!(operators.isEmpty())) {
			char topOper = (char) operators.peek();
			if (topOper == '(') {
				break;
			} else {
				{
					int prec2 = 0;
					if (topOper == '+' || topOper == '-')
						prec2 = 1;
					else if (topOper == '*' || topOper == '/')
						prec2 = 2;
					if (prec2 < prec1) {
						break;
					} else {
						postFix = postFix + topOper;
						operators.pop();
					}
				}
			}
		}
		operators.push(operator);
	}

	public void Paren(char ch) {
		while (!operators.isEmpty()) {
			char x = (char) operators.pop();
			if (x == '(')
				break;
			else
				postFix += x;
		}
	}

}
