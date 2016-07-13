/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lqas.automation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Sveta
 */
public class StudentsXML {
  
  static List<Student> studentsList = new ArrayList<>();
  static String XML_name = "students.xml";
  
  
  static void DOMXMLParser() throws ParserConfigurationException, SAXException, IOException {
	DocumentBuilderFactory factory
			= DocumentBuilderFactory.newInstance();

	DocumentBuilder builder = factory.newDocumentBuilder();

	Document document
			= builder.parse(
					ClassLoader.getSystemResourceAsStream(XML_name));

	NodeList nodeList = document.getDocumentElement().getChildNodes();

	for (int i = 0; i < nodeList.getLength(); i++) {

	  Node node = nodeList.item(i);
	  if (node instanceof Element) {
		Student student = new Student();
		
		NodeList childNodes = node.getChildNodes();
		for (int j = 0; j < childNodes.getLength(); j++) {
		  Node cNode = childNodes.item(j);

		  if (cNode instanceof Element) {
			String content = cNode.getLastChild().
					getTextContent().trim();
			switch (cNode.getNodeName()) {
			  case "firstName":
				student.firstName = content;
				break;
			  case "lastName":
				student.lastName = content;
				break;
			  case "course":
				student.course = content;
				break;
			  case "age":
				student.age = content;
				break;
			}
		  }
		}
		studentsList.add(student);
	  }

	}
	
	Collections.sort(studentsList, new MySort());

	for (Student emp : studentsList) {
	  System.out.println(emp);
	}
  }

}


class Student {

  String firstName;
  String lastName;
  String course;
  String age;

  @Override
  public String toString() {
	return lastName + " " + firstName + " course - " + course + " age - " + age;
  }
}

class MySort implements Comparator<Student>{

  @Override
  public int compare(Student s1, Student s2) {
	for (int i = 0; i < s1.lastName.length() && i < s2.lastName.length(); i++) {
	  if (s1.lastName.charAt(i) < s2.lastName.charAt(i)){
            return -1;
        } 
	  else if (s1.lastName.charAt(i) > s2.lastName.charAt(i)){
            return 1;
        }
	}
	return 0;
  }
}
