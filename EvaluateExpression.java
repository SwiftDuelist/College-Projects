import java.util.Scanner;
import java.io.*;
public class EvaluateExpression {

	public static void main(String[] args) throws IOException{

		// Read value.txt and populate the linked list
		// you need write all of your code in main, NOT in the function as hw1 or hw2
		// data part of your linked list is ValueData
		// you can use insertFront or insertBack to do this job
		String fileName = "value.txt";
		String fileNameTwo = "expression.txt";
		
		linkedList list = new linkedList();
		// File Scanner
		Scanner scan = new Scanner(new File(fileName));
			
		while(scan.hasNext()) {
			String nodeString = scan.next();
			double nodeDouble = scan.nextDouble();
			ValueData place = new ValueData(nodeString, nodeDouble);
			list.insertFront(place);
		}
		scan.close();
		
		double result = find(list.getHead(), "b");
		System.out.println(result);
		
		result = find(list.getHead(), "a");
		System.out.println(result);
		
		// Phase 2 Quiz 6
		// Read expression.txt
		result = 0;
		double total;
		Scanner scanTwo = new Scanner(new File(fileNameTwo));
		
		String expression = scanTwo.next();
		scanTwo.close();
		ObjectStack stk = new ObjectStack();
		
		// Process each symbol of this expression
		// Two type of symbol: Variable's name and operator
		for(int i = 0; i < expression.length(); i++) {
			if(expression.charAt(i) == '+') {

				double b = (double) stk.pop();
				double a = (double) stk.pop();
				total = (a + b);
				stk.push(total);
				
			} else if(expression.charAt(i) == '-') {
				
				double b = (double) stk.pop();
				double a = (double) stk.pop();
				total = (a - b);
				stk.push(total);
				
			} else if(expression.charAt(i) == '/') {
				
				double b = (double) stk.pop();
				double a = (double) stk.pop();
				total = (a / b);
				stk.push(total);
				
			} else if(expression.charAt(i) == '*') {
			
				double b = (double) stk.pop();
				double a = (double) stk.pop();
				total = (a * b);
				stk.push(total);
				
			} else {
				
				// Otherwise, it is variable name
				// find its value and push onto stack
				result = find(list.getHead(), expression.substring(i, i+1));
				stk.push(result);
			}

		}
		// Pop off the final value on the stack, which is the result of the postfix expression
		System.out.println(stk.pop());
	}

	public static double find(LinkedListNode head, String s) {
		
		double a = ((ValueData)head.getData()).getValue();
		String b = ((ValueData)head.getData()).getVariable();

		while (head.getNext() != null) {
			
			if(!b.contentEquals(s)) {
			head = head.getNext();
			b = ((ValueData)head.getData()).getVariable();
			}
			if(b.contentEquals(s)){
				a = ((ValueData)head.getData()).getValue();
				return a;
			}    
		}	
		return 0;
	}
}
