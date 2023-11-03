# CodeImprovement & Testing techniques 

## Stubbing Registration system

## Exercise1: Review and Suggest Improvements

public class Sample2 {

    public static void main(String[] args) {
    
        System.out.println("Welcome to the Number Analysis Program!");
        
        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        int product = 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        System.out.print("Enter a number (or -1 to quit): ");
        int num = scanner.nextInt();

        while (num != -1) {
            sum += num;
            product *= num;

            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }

            System.out.print("Enter another number (or -1 to quit): ");
            num = scanner.nextInt();
        }

        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);
        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);

        scanner.close();
    }
}

## Exercise2: Student Management System

import java.util.ArrayList;

class Student {
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class StudentManagementSystem {

    ArrayList<Student> students = new ArrayList<Student>();

    public void addStudent(String name, int age) {
        Student student = new Student(name, age);
        students.add(student);
        System.out.println("Student added successfully!");
    }
    public void listStudents() {
        System.out.println("Student List:");
        for (Student student : students) {
            System.out.println("Name: " + student.name);
            System.out.println("Age: " + student.age);
            System.out.println();
        } 
      }
      
    public static void main(String[] args) {
    
        StudentManagementSystem sms = new StudentManagementSystem();

        sms.addStudent("Alice", 20);
        sms.addStudent("Bob", 22);
        sms.listStudents();
    }
}
