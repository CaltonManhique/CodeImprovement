package code_improvement.student_management_system;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

//Aleksandra, Calton and Fadi

public class StudentFileSystem {

	private static String pathName = "dir/";

	public void fileWriterStudent(Student student, String fileName) throws IOException {
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file, true);

		CSVWriter writer = new CSVWriter(outputFile);

		String[] data = { String.valueOf(student.getId()), student.getName(), String.valueOf(student.getAge()),
				student.getContact() };
		writer.writeNext(data);

		writer.close();
	}

	public void fileWriterEnrolledStudent(Student student, String course, String fileName) throws IOException {
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file, true);

		CSVWriter writer = new CSVWriter(outputFile);

		String[] data = { String.valueOf(student.getId()), student.getName(), course };
		writer.writeNext(data);

		writer.close();
	}

	public Set<Student> fileReaderStudent(String fileName) throws IOException, CsvValidationException {
		Set<Student> arr = new HashSet<Student>();

		FileReader inputFile = new FileReader(pathName + fileName);

		CSVReader reader = new CSVReader(inputFile);
		String[] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			Student student = new Student(nextLine[1], Integer.parseInt(nextLine[2]), Integer.parseInt(nextLine[0]),
					nextLine[3]);
			arr.add(student);

		}

		reader.close();

		return arr;
	}

	public Map<Student, String> fileReaderEnrolledStudent(String fileName) throws IOException, CsvValidationException {
		Map<Student, String> arr = new HashMap<Student, String>();
		FileReader inputFile = new FileReader(pathName + fileName);

		CSVReader reader = new CSVReader(inputFile);
		String[] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			Student student = new Student(nextLine[1], 0, Integer.parseInt(nextLine[0]), null);
			arr.put(student, nextLine[2]);
		}

		reader.close();

		return arr;
	}

	// Updates the file overwriting all data in the file
	public void updateStudentFile(Set<Student> students, String fileName) throws IOException {
		Set<String[]> arr = new HashSet<String[]>();
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file);

		CSVWriter writer = new CSVWriter(outputFile);

		for (Student student : students) {
			String[] data = { String.valueOf(student.getId()), student.getName(), String.valueOf(student.getAge()),
					student.getContact() };
			arr.add(data);
		}
		writer.writeAll(arr);

		writer.close();
	}

	public void updateEnrolledStudentFile(Map<Student, String> enrolledStudents, String fileName) throws IOException {
		Set<String[]> arr = new HashSet<>();
		File file = new File(fileName);

		FileWriter outputFile = new FileWriter(pathName + file);

		CSVWriter writer = new CSVWriter(outputFile);

		for (Entry<Student, String> map : enrolledStudents.entrySet()) {
			String[] data = { String.valueOf(map.getKey().getId()), map.getKey().getName(), map.getValue() };
			arr.add(data);
		}
		writer.writeAll(arr);

		writer.close();
	}
}
