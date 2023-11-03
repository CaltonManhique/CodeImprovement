package code_improvement;

import java.util.Scanner;

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
