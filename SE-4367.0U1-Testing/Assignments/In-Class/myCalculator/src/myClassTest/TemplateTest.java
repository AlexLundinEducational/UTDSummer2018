package myClassTest;

import myClass.Template;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//Some requirements
//- $(firstName) and $(lastName) will replace at runtime
//- no variables will raise an error
//- not found variables will be ignored
//
//some test ideas
//1 - "Hello, ${name}." with Reader => "Hello, Reader."
//2 - "${greeting}, ${name}. " with "Hi" and "Reader" => "Hi, Reader."
//3 - "Hello ${name}." with nothing =>
//4 - "Hello ${name}." with "Hi" and "Reader" => "Hello, reader" ignoring

public class TemplateTest {
	private Template template;
	
	@Before 
	public void Setup(){
		template = new Template("${one}, ${two} ${three}");
		template.set("one", "1");
		template.set("two", "2");
		template.set("three", "3");
	}
//	@Test
//	public void oneVariable(){
//		Template template = new Template("Hello, ${name}.");
//		template.set("name", "Reader");
//		assertEquals("Hello, Reader.", template.evaluate());
//	}
//	
//	@Test
//	public void differentVarialbe(){
//		Template template = new Template("Hello, ${name}.");
//		template.set("name", "Reader2");
//		assertEquals("Hello, Reader2.", template.evaluate());		
//	}
	
	@Test
	public void multiVarialbe(){
		assertTemplateEvaluateTo("1, 2, 3");
	}	
	

	@Test
	public void unknownVarialbesAreIgnore(){
		template.set("unknown", "anything");
		assertTemplateEvaluateTo("1, 2, 3");
	}	
	
	// methods
	private void assertTemplateEvaluateTo(String expected){
		assertEquals(expected, template.evaluate());
	}
}
