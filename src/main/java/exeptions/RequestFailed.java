package exeptions;

public class RequestFailed extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestFailed() {
		super("Request Failed");
	}

}
