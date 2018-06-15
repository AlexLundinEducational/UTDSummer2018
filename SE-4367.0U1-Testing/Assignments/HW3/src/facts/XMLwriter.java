package facts;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.xml.sax.*;
import org.w3c.dom.*;

// Alex Lundin
// 06-13-2018
// SE-4367-Testing

// Alex modified this class file after original post...stayed up until 4am to get this hunk of garbage working
// search for this comment tag to find what I had to edit to make this build functional
// ***



// *** class re-work by Alex
// implement (abstract extension) on StringConstants
// this way all paths are always available and easily changeable
public class XMLwriter implements StringConstants {
	

	// this variable will hold all the Facts during the xmlReadThenWriteWrapper method
	FactList combinedFactList = new FactList();
	
	
	// *** method created by Alex
	// top level wrapper to abstract the control flow
	public void xmlReadThenWriteWrapper(Fact newFact) throws IOException, XMLStreamException{
		
		// ensure this list resets every time the wrapper is called
		combinedFactList = new FactList();
		
		// 1.) Read existing XML data into a FactList data structure
		boolean readSuccess = readXML();
		if (readSuccess == true){
			System.out.println("Read any existing XML files successfully");
		}else{
			// try catch block in readXML should catch these errors
			System.out.println("Failed to read exsiting XML files *** controll flow should NEVER reach here**");
		}
		
		// 2.) Add the new fact into the existing FactList
		combinedFactList.set(newFact);
		
		// 3.) Write the combined combinedFactList
		writeToXml(combinedFactList);
	}
	
	
	// *** method created by Alex
    // this method is the first step in writing to XML file
    // first read the XML into a NodeList
    // if nothing exists, then control flow continues to writer
    public boolean readXML() {

        Document dom;
        
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            // parse using the builder to get the DOM mapping of the    
            // XML file defined in StringConstants
            dom = db.parse(xmlFile);
             
            
            Fact tempFact;
            String authorText = "";
            String typeText = "";
            String quoteText = "";
            
            // loop through all fact XML elements parsed by db.parse
            // store facts into a NodeList, which is what the document builder can output easily
            // in the loop, assemble the Fact object from the individual nodes
            NodeList existingXMLFactList = dom.getElementsByTagName("fact");
            
            for (int i = 0; i < existingXMLFactList.getLength(); i++) {
            	
                NodeList childList = existingXMLFactList.item(i).getChildNodes();
                System.out.println("--Fact read from XML");
                for (int j = 0; j < childList.getLength(); j++) {
                    Node childNode = childList.item(j);
                  
                    
                    // print facts to screen during storage for verification
                    // store fact nodes into a FactList for writer
                    if ("fact-text".equals(childNode.getNodeName())) {
                    	
                    	quoteText = childList.item(j).getTextContent().trim();
                    	System.out.println("------Quote" + quoteText);
                    }
                    if ("author".equals(childNode.getNodeName())) {
                    	
                    	authorText = childList.item(j).getTextContent().trim();
                    	System.out.println("------Author" + authorText);
                    }
                    if ("fact-type".equals(childNode.getNodeName())) {
                    	typeText = childList.item(j).getTextContent().trim();
                    	System.out.println("------Type" + typeText);
                    }                    
                   
                    
                  
                }
                
            	// create fact object inside loop for simplicity
            	tempFact = new Fact(authorText, typeText, quoteText);
        		combinedFactList.set(tempFact);                 
            }            
            
            return true;

        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return false;
    }   
    

    // *** method re-work by Alex
	// this method accepts the factList and attempts to write it to XML
    public void writeToXml(FactList factList) throws IOException, XMLStreamException {

    	// create path from the xmlFile variable which implements from StringConstants.java
    	Path path = Paths.get(xmlFile);
    	
        try (OutputStream os = Files.newOutputStream(path)) {
            XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter writer = null;
            try {
                writer = outputFactory.createXMLStreamWriter(os, "utf-8");
                writeFactsElem(writer, factList);
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
    }
    
    // *** method re-work by Alex
    // this method wraps the write function inside a loop
    private void writeFactsElem(XMLStreamWriter writer, FactList factList) throws XMLStreamException {
        writer.writeStartDocument("UTF-8", "1.0");
        
        writer.writeCharacters("\n");
        
        writer.writeStartElement("fact-list");

        
        System.out.println("Total facts ready to write back into XML file: " + factList.getSize());
        // loop re-work
		for (int i = 0; i < factList.getSize(); i++)
		{
			Fact fact = factList.get(i);
			if (fact == null){
				i = factList.getSize();
			}
			writeFactElem(writer, fact);
			
		}        
		writer.writeCharacters("\n");
		writer.writeEndElement();
        writer.writeEndDocument();
    }
 
    // *** method created by Alex
    // this method actually pulls to data from the fact object and writes to the xml file
    private void writeFactElem(XMLStreamWriter writer, Fact fact) throws XMLStreamException {
    	
    	writer.writeCharacters("\n\t");
    	writer.writeStartElement("fact");

	    	writer.writeCharacters("\n\t\t");
	        writer.writeStartElement("fact-text");
	        writer.writeCharacters(fact.getText());
	        writer.writeEndElement();
	        
	        writer.writeCharacters("\n\t\t");
	        writer.writeStartElement("author");
	        writer.writeCharacters(fact.getAuthor());
	        writer.writeEndElement();
	 
	        writer.writeCharacters("\n\t\t");
	        writer.writeStartElement("fact-type");
	        writer.writeCharacters(fact.getType());
	        writer.writeEndElement();
 
        writer.writeCharacters("\n\t");
        writer.writeEndElement();
    }
 

    

}
