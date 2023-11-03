package code_improvement.improved_version;

import java.util.ArrayList;
import java.util.Scanner;

class NumberAnalyser {

	public void addNumber(ArrayList<Integer> arr, int number) {
		if (number != -1)
			arr.add(number);
	}

	public int sum(ArrayList<Integer> arr) {
		int sum = 0;
		for (Integer num : arr) {
			sum += num;
		}
		return sum;
	}

	public int pruduct(ArrayList<Integer> arr) {
		int pruduct = 1;
		for (Integer num : arr) {
			pruduct *= num;
		}
		return pruduct;
	}

	public int max(ArrayList<Integer> arr) {
		int max = arr.get(0);
		for (Integer num : arr) {
			if (num > max) {
				max = num;
			}
		}
		return max;
	}

	public int min(ArrayList<Integer> arr) {
		int min = arr.get(0);
		for (Integer num : arr) {
			if (num < min) {
				min = num;
			}
		}
		return min;
	}
}

public class Sample2 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		NumberAnalyser numberAnalyser = new NumberAnalyser();
		ArrayList<Integer> arr = new ArrayList<Integer>();

		System.out.println("Welcome to the Number Analysis Program!");
		int num = 0;

		do {
			System.out.print("Enter a number (or -1 to quit): ");
			num = scanner.nextInt();
			numberAnalyser.addNumber(arr, num);

			if (num == -1) {
				System.out.println("Sum: " + numberAnalyser.sum(arr));
				System.out.println("Product: " + numberAnalyser.pruduct(arr));
				System.out.println("Minimum: " + numberAnalyser.min(arr));
				System.out.println("Maximum: " + numberAnalyser.max(arr));
			}

		} while (num != -1);

		scanner.close();
	}

}
