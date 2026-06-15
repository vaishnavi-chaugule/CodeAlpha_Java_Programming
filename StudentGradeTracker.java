package com.javaprogramming.studentgradetracker;

import java.util.Scanner;
import java.util.ArrayList;

public class StudentGradeTracker {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<String> studentNames = new ArrayList<>();
		ArrayList<Double> studentGrades = new ArrayList<>();
		
		System.out.print("Enter the number of student:");
		int n = sc.nextInt();
		sc.nextLine();
		
		for(int i=0; i<n; i++) {
			System.out.println("\nStudent " + (i + 1));
			
			System.out.print("Enter student name:");
			String name = sc.nextLine();
			
			System.out.println("Enter grade: ");
			double grade = sc.nextDouble();
			sc.nextLine();
			
			studentNames.add(name);
			studentGrades.add(grade);
			
		}
		
		double total = 0;
		double highest = studentGrades.get(0);
		double lowest = studentGrades.get(0);
		
		for(double grade: studentGrades) {
			total += grade;
			
			if(grade > highest) {
				highest = grade;
			}
			
			if(grade < lowest) {
				lowest = grade;
			}
		}
		
		double average = total/n;
		
		System.out.println("\n===== STUDENT GRADE REPORT ====");
		
		for(int i = 0; i < n; i++) {
			System.out.println(studentNames.get(i) + " : " + studentGrades.get(i));
			
		}
		
		System.out.println("\nAverage Score:" + average);
		System.out.println("Highest Score:" + highest);
		System.out.println("Lowest Score:" + lowest);
		
		
	}

}
