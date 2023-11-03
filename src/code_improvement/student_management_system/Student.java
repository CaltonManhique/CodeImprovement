package code_improvement.student_management_system;

import java.util.Objects;

//Aleksandra, Calton and Fadi

public class Student {

	private String name;
	private int age;
	private int id;
	private String contact;

	public Student(String name, int age, int id, String contact) {
		this.name = name;
		this.age = age;
		this.contact = contact;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return id == other.id;
	}

	public String getContact() {
		return contact;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

}
