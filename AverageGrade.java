package AverageGrade;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.text.DecimalFormat;


public class AverageGrade {
	
	//Since I'm using another method, I brought out the arrays and scanner and made them global.
	static int[] testOne = new int[4];
	static int[] testTwo = new int[4];
	static double[] average = new double[4];
	static char[] grade = new char[4];

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		//Variables
		String fileName;
		int choice;

		final int GRADE_REPORT = 1;
		final int GRADE_REPORT_IN_FILE = 2;
		final int EXIT = 3;
		
		//Used for formatting the report
		DecimalFormat gradeDisplay = new DecimalFormat("000");
		DecimalFormat avgDisplay = new DecimalFormat("000.0");

		//Input
		System.out.println("Welcome to the Grade Center!\n" +
						   "Enter " + GRADE_REPORT + " to generate and display a report.\n" +
						   "Enter " + GRADE_REPORT_IN_FILE + " to generate a report and save it into a file.\n" +
						   "Enter " + EXIT + " to quit.");
		choice = scan.nextInt();
		
		//Processing
		switch (choice) {
		case GRADE_REPORT:
			average();
			System.out.println("Test 1    Test 2    Average    Grade\n" +
							   "------    ------    ------     ------");
			for (int i = 0; i < average.length; i++) {
				System.out.println(gradeDisplay.format(testOne[i]) + "       " + gradeDisplay.format(testTwo[i]) + "       " + avgDisplay.format(average[i]) + "      " + grade[i]);
			}
			break;
		case GRADE_REPORT_IN_FILE:
			average();
			//Output
			System.out.println("Enter the filename (include a .txt at the end): ");
			fileName = scan.next();
			
			File file = new File(fileName);

			if(file.exists()) { //Safety check.
				System.out.println("The file " + fileName + " already exists");
				break;
			}
			
			PrintWriter outputFile = new PrintWriter(file);
			
			outputFile.println("Test 1    Test 2    Average    Grade\n" +
					   		   "------    ------    ------     ------");
			for (int i = 0; i < average.length; i++) {
				outputFile.println(gradeDisplay.format(testOne[i]) + "       " + gradeDisplay.format(testTwo[i]) + "       " + avgDisplay.format(average[i]) + "      " + grade[i]);
			}
			outputFile.close();
			System.out.println("Report written into a file: " + fileName);
			break;
		case EXIT: //No need to write anything for this case because of the way switch-cases work.
		default:
			break;
		}
		
		scan.close();
	}
	
	//Even more processing
	public static void average() { //For simplicity purposes, I wrote another method to generate the report.
		int numberGrade;
		
		System.out.println("Enter grades for test1 and test2");
		for (int i = 0; i < 2; i++) {
			System.out.println("For test " + (i + 1) + ",");
			for (int j = 0; j < average.length; j++) {
				System.out.println("Enter score " + (j+1) + ":");
				numberGrade = scan.nextInt();
				if (i == 0) {
					testOne[j] = numberGrade;
				} else if (i == 1) {
					testTwo[j] = numberGrade;
				}
				average[j] = (double)(testOne[j] + testTwo[j]) / 2;
				
				if (average[j] > 90) {
					grade[j] = 'A';
				} else if (average[j] >= 80) {
					grade[j] = 'B';
				} else if (average[j] >= 70) {
					grade[j] = 'C';
				} else {
					grade[j] = 'F';
				}
			}
		}
	}

}
