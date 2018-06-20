package tests;

import facts.Fact;
import facts.FactList;
import facts.Parser;
import facts.StringConstants;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//Alex Lundin
//06-13-2018
//SE-4367-Testing

// Tests for search method in the FactList class 
// there is only one return statement in the method
public class FactListTest implements StringConstants{
	
	// fields for tests
	private FactList testList;
	private FactList emptyTestList = new FactList();
	private int mode;
	private Fact newFact = new Fact();
	private int listSize = 0;
	
	// create all necessary components for using the FactList class
	@Before 
	public void setup(){
		
		// use the parser to setup the list
		Parser parser = new Parser(xmlFile);
		testList = parser.getFactList();	
		
		// add a newFact to search for later
		newFact.setAuthor("Dr. Mehra Borazjany");
		newFact.setType ("Fact");
		newFact.setText ("Test driven developement requires Automation.");
		testList.set(newFact);

	}
	

	// Search by Author
	// FactList contains fact
	@Test
	public void FactListTest1(){
		
		// set the search mode to Author
		mode = 0;
		String searchString = "Dr. Mehra Borazjany";
		
		// search the list
		FactList returnedFactList = testList.search(searchString, mode);
		
		// compare actual list size with expected list size
		listSize = returnedFactList.getSize();
		assertTrue("Test 1: Fact exists in FactList", listSize == 1);
	}
	
	// Search by Text
	// FactList does not contain fact
	@Test
	public void FactListTest2(){
		
		// set the search mode to Text
		mode = 1;
		String searchString = "Random Text that is not in the file";
		
		// search the list
		FactList returnedFactList = testList.search(searchString, mode);
		
		// compare actual list size with expected list size
		listSize = returnedFactList.getSize();
		assertTrue("Test 2: Fact exists in FactList", listSize == 0);
	}	
	
	
	// Search by Type
	@Test
	public void FactListTest3(){
		
		// set the search mode to Type
		mode = 2;
		String searchString = "Fact";
		
		// search the list
		FactList returnedFactList = testList.search(searchString, mode);
		
		// compare actual list size with expected list size
		listSize = returnedFactList.getSize();
		assertTrue("Test 3: Fact exists in FactList", listSize == 9);
	}	
	
	// Search by All
	@Test
	public void FactListTest4(){
		
		// set the search mode to Type
		mode = 3;
		String searchString = "Glass";
		
		// search the list
		FactList returnedFactList = testList.search(searchString, mode);
		
		// compare actual list size with expected list size
		listSize = returnedFactList.getSize();
		assertTrue("Test 3: Fact exists in FactList", listSize == 10);
	}		
}
