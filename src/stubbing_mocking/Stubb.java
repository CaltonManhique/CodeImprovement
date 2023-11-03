package stubbing_mocking;

public class Stubb {

	String stubbEmail = "stubbing@dci.de";

	// Database connection for creation
	public String createUserIntoDatabase(User user) {
		if (user.getEmail().equals(stubbEmail)) {
			return "User already registered!!";
		}
		return "User registered successfull";
	}

}
