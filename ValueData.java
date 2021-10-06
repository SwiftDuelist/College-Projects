
public class ValueData {
	// Variable's Name
	private String variable;
	// Variable's value
	private double value;
	public ValueData(String var, double val) {
		
		variable = var;
		value = val;
		
	}
	//getValue
	public double getValue() {
		
		return value;
	}
	//setValue
	public void setValue(double given) {
		
		value = given;
	}
	//getVariable
	public String getVariable() {
		return variable;
	}
	//setVariable
	public void setVariable(String input) {
		variable = input;
	}
	
	//toString
	public String toString() {
		
		return variable + " " + value;
	}
}
