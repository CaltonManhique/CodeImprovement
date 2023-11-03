package stubbing_mocking;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration {

	private User user;
	private Scanner scanner = new Scanner(System.in);
	private Stubb stubb = new Stubb();

	public String register() {
		System.out.println("First name:");
		String firstName = scanner.nextLine();

		System.out.println("Last name:");
		String lastName = scanner.nextLine();

		System.out.println("Email:");
		String email = scanner.nextLine();

		System.out.println("Define password:");
		String password = scanner.nextLine();

		System.out.println("Confirm password:");
		String confirmPassword = scanner.nextLine();

		String register = null;
		if (validateEmail(email) && validatePassword(password, confirmPassword)) {
			user = new User(firstName, lastName, email, password, confirmPassword);

			// Stubbing Database connection
			register = stubb.createUserIntoDatabase(user);

		} else {
			register = "Email or password don't match.";
		}
		return register;
	}

	public boolean validateEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	// Simple password validation
	public boolean validatePassword(String password, String confirmPassword) {

		if (password.length() < 8 && !password.equals(confirmPassword)) {
			return false;
		}

		return true;
	}

}
