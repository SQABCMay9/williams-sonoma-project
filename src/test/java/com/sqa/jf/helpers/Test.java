package com.sqa.jf.helpers;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Test {
	// ArrayList to hold test data
	public static ArrayList<Object> tests = new ArrayList<Object>();
	// StringBuilder class in place of String class so that less objects are
	// created, more efficient when dealing with String concatenation
	public static StringBuilder testString = new StringBuilder();

	public static void main(String[] args) {
		// File name should include directories and relative location when using
		// a Relative path
		String fileNameRel = "src/main/resources/temp.json";
		// File name should include directories and absolute location when using
		// an Absolute path
		String fileNameAbs = "C:/Users/SQA/Dropbox/SQA BC May 9 Core/workspace/basic-project/src/main/resources/temp.json";

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileNameRel);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// While there is a line to read or not null line
			// (also setting line to current line)
			while ((line = bufferedReader.readLine()) != null) {
				// Pass current line to the gatherDataString Method
				gatherDataString(line);
			}
			evaluateDataString();
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileNameRel + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileNameRel + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		displayTests();
	}

	public static void mainNew(String args[]) {

		// String to be scanned to find the pattern.
		String line = "This order was placed for QT3000! OK?";
		String pattern = "(.*)(\\d+)(.*)";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println("Found value: " + m.group(0));
			System.out.println("Found value: " + m.group(1));
			System.out.println("Found value: " + m.group(2));
		} else {
			System.out.println("NO MATCH");
		}
	}

	private static void displayTests() {
		System.out.println(testString.toString());

	}

	private static void evaluateDataString() {
		// String[] tests = testString.toString().split("},{");
		// for(int i = 0; i < tests.length; i++) {
		// //String[] elements = "\d";
		// "\d+"
		// }
		// String regexString = "\\"\\d+\\"";
		// Gather all content from file into a String object
		String myString = testString.toString();

		// Regex Pattern to specify format of text document
		String pattern = "(\")(\\d+)(\")";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(myString);

		// While match is found
		while (m.find()) {
			// Print out the second group of the match
			System.out.println("Found value: " + m.group(2));
		}
	}

	private static void gatherDataString(String line) {
		// Add line of code to ArrayList collection
		testString.append(line);
	}
}
