package exeptions;

public class SessionExpried extends RuntimeException {

	public SessionExpried() {
	 super ("Your Session Is Expired. Login again");
	}

}
