package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;

public class AutoGrader {

	// Test if the code correctly implements HashMap methods
	public boolean testHashMapMethods(String filePath) throws IOException {
		System.out.println("Starting testHashMapMethods with file: " + filePath);

		File participantFile = new File(filePath); // Path to participant's file
		if (!participantFile.exists()) {
			System.out.println("File does not exist at path: " + filePath);
			return false;
		}

		FileInputStream fileInputStream = new FileInputStream(participantFile);
		JavaParser javaParser = new JavaParser();
		CompilationUnit cu;
		try {
			cu = javaParser.parse(fileInputStream).getResult()
					.orElseThrow(() -> new IOException("Failed to parse the Java file"));
		} catch (IOException e) {
			System.out.println("Error parsing the file: " + e.getMessage());
			throw e;
		}

		System.out.println("Parsed the Java file successfully.");

		// Flags to check presence and calls of specific methods
		boolean hasMainMethod = false;

		// Check for method declarations
		for (MethodDeclaration method : cu.findAll(MethodDeclaration.class)) {
			String methodName = method.getNameAsString();
			// Check for the presence of the main method
			if (methodName.equals("main")) {
				hasMainMethod = true;
			}
		}

		// Check for method calls in the main method
		boolean calledPut = false;
		boolean calledGet = false;
		boolean calledRemove = false;
		boolean loopedThroughHashMap = false;

		for (MethodCallExpr methodCall : cu.findAll(MethodCallExpr.class)) {
			String methodName = methodCall.getNameAsString();
			if (methodName.equals("put")) {
				calledPut = true;
			} else if (methodName.equals("get")) {
				calledGet = true;
			} else if (methodName.equals("remove")) {
				calledRemove = true;
			}
		}

		// Check for looping through the HashMap
		if (cu.toString().contains("entrySet()")) {
			loopedThroughHashMap = true;
		}

		// Log method presence and calls
		System.out.println("Method 'main' is " + (hasMainMethod ? "present" : "NOT present"));
		System.out.println("Method 'put' is " + (calledPut ? "called" : "NOT called"));
		System.out.println("Method 'get' is " + (calledGet ? "called" : "NOT called"));
		System.out.println("Method 'remove' is " + (calledRemove ? "called" : "NOT called"));
		System.out.println("Looping through HashMap is " + (loopedThroughHashMap ? "implemented" : "NOT implemented"));

		// Final result
		boolean result = hasMainMethod && calledPut && calledGet && calledRemove && loopedThroughHashMap;

		System.out.println("Test result: " + result);
		return result;
	}
}
