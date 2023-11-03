package code_improvement.student_management_system;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import com.opencsv.exceptions.CsvValidationException;

//Aleksandra, Calton and Fadi

public class StudentManagementSystem {

	private String studentFile = "student.csv";
	private String enrolledStudentFile = "enrolledStudents.csv";
	private StudentFileSystem studentFileSystem = new StudentFileSystem();

	private int lastId = 100;
	private AtomicInteger idGenerator = new AtomicInteger(lastId);
	private Scanner scanner = new Scanner(System.in);

	private Set<Student> students = new HashSet<Student>();
	private Map<Student, String> enrolledStudents = new HashMap<Student, String>();
	@SuppressWarnings("serial")
	private LinkedHashMap<String, String> courses = new LinkedHashMap<String, String>() {
		{
			put("REFERENCE", "COURSE");
			put("J23E01", "Java Backend");
			put("P23E01", "Phyton");
			put("W23E01", "Web development");
			put("M23E01", "Digital marketing");
		}
	};

	public void addStudent(String name, int age, int id, String contact) {
		Student student = new Student(name, age, id, contact);
		students.add(student);
		System.out.println("Student added successfully!");
	}

	public void listStudents() {
		System.out.println("Student List:");
		for (Student student : students) {
			System.out.println("ID: " + student.getId());
			System.out.println("Name: " + student.getName());
			System.out.println("Age: " + student.getAge());
			System.out.println("Contact: " + student.getContact());
			System.out.println();
		}
	}

	public int idGenerator() {
		if (students.isEmpty()) {
			lastId = idGenerator.incrementAndGet();
			return lastId;
		} else {
			lastId = new AtomicInteger(getLastId()).incrementAndGet();
			return lastId;
		}
	}

	public void displayCourses() {
		for (Entry<String, String> map : courses.entrySet()) {
			System.out.printf("%-10s |%s\n", map.getKey(), map.getValue());
		}
	}

	public void listEnrolledStudents() {
		for (Entry<Student, String> map : enrolledStudents.entrySet()) {
			System.out.printf("%s, %-10s |%s\n", map.getKey().getId(), map.getKey().getName(), map.getValue());
		}
	}

	public void enrollStudentToCourse(int id, String ref) throws IOException {
		if (students.isEmpty()) {
			System.out.println("Add students before enrolling to the course...");
		} else {
			if (courses.containsKey(ref)) {
				for (Student student : students) {
					if (student.getId() == id && !enrolledStudents.containsKey(student)) {
						enrolledStudents.put(student, courses.get(ref));
						System.out.println("Student enrolled successfully.");
						studentFileSystem.fileWriterEnrolledStudent(student, courses.get(ref), enrolledStudentFile);
						return;
					}
				}
				System.out.println("Student not enrolled: Invalid id or student already enrolled.");
			} else {
				System.out.println("Student not enrolled: Invalid reference course.");
			}
		}
	}

	private int getLastId() {
		int max = lastId;
		for (Student std : students) {
			if (std.getId() > max) {
				max = std.getId();
			}
		}
		return max;
	}

	public void searchStudent(String searchValue) {
		System.out.println("Search details: ");

		Student std = null;

		for (Student student : students) {
			if (student.getName().equalsIgnoreCase(searchValue)
					|| String.valueOf(student.getAge()).equalsIgnoreCase(searchValue)
					|| String.valueOf(student.getId()).equalsIgnoreCase(searchValue)) {
				std = student;
				System.out.println("Name: " + std.getName());
				System.out.println("Age: " + std.getAge());
				System.out.println("Student Id: " + std.getId());
				System.out.println("Contact information: " + std.getContact());
				System.out.println();
			}
		}

		if (std == null) {
			System.out.println("Student not found");
		}
	}

	public void removeStudent(int studentID) {
		for (Student std : students) {
			if (std.getId() == studentID) {
				students.remove(std);
				System.out.println("Student removed successfully!");
				return;
			}
		}
		System.out.println("Error: Student with the given StudentID not found in the database.");
	}

	public void startMenu() {

		try {
			students = studentFileSystem.fileReaderStudent(studentFile);
			enrolledStudents = studentFileSystem.fileReaderEnrolledStudent(enrolledStudentFile);
		} catch (CsvValidationException | IOException e) {
		}

		lastId = getLastId();
		System.out.println("last id " + lastId);

		int option = 0;
		System.out.print("Welcome to student management system.");
		do {

			System.out
					.println("\n1. Add student \n2. Remove student\n3. List all students\n4. Enroll student to a course"
							+ "\n5. Search student by name, age or contact\n6. List enrolled students\n7. Exit application");

			option = scanner.nextInt();
			scanner.nextLine(); // To clear the buffer

			switch (option) {
			case 1 -> {
				System.out.println("Enter student name:");
				String name = scanner.nextLine();

				System.out.println("Enter age:");
				int age = scanner.nextInt();
				scanner.nextLine();

				System.out.println("Enter contact:");
				String contact = scanner.nextLine();

				int id = idGenerator();
				addStudent(name, age, id, contact);

				try {
					studentFileSystem.fileWriterStudent(new Student(name, age, id, contact), studentFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			case 2 -> {
				System.out.println("Enter student ID:");
				int studentID = scanner.nextInt();
				removeStudent(studentID);

				try {
					studentFileSystem.updateStudentFile(students, studentFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			case 3 -> listStudents();

			case 4 -> {
				System.out.println("Enter student id to enroll to the course:");
				int id = scanner.nextInt();
				scanner.nextLine();

				System.out.println("Enter the course reference, choose one from the following list:");
				displayCourses();
				String ref = scanner.nextLine();

				try {
					enrollStudentToCourse(id, ref.toUpperCase());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			case 5 -> {
				System.out.println("Enter parameter to search for student:");
				String searchParameter = scanner.nextLine();
				searchStudent(searchParameter.trim());
			}
			case 6 -> listEnrolledStudents();
			case 7 -> System.out.println("Closing application!!");
			default -> System.out.println("Invalid option.");
			}

		} while (option != 7);

		scanner.close();
	}

}
