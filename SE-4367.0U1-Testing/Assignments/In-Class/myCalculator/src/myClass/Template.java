package myClass;

import java.util.*;
import java.util.Map.Entry;

public class Template {
	
	// attributes
	private String value;
	private String text;
	private Map<String,String> variables;
	
	// constructors
	public Template(String text){
		this.text = text;
		this.variables = new HashMap<String,String>();
	}
	
	// setters and getters
	public void set(String variable, String value){
		this.value = value;
		//this.variables.put(variable, value);
	}
	
	// methods
	public String evaluate(){
		//return "Hello, " + this.value + ".";
		return text.replaceAll("\\$\\{name\\}", this.value);
//		String result = this.text;
//		for(Entry<String, String> entry : variables.entrySet()){
//			String regex = "\\$\\{" + entry.getValue() +"\\}";
//			result = result.replaceAll(regex, entry.getValue());
//		}
//		return result;
	}
}
