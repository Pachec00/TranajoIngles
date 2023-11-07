package service;

public class JugadorsServiceException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 8747484744430276411L;

	public JugadorsServiceException() {
		super();
	}

	public JugadorsServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JugadorsServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public JugadorsServiceException(String message) {
		super(message);
	}

	public JugadorsServiceException(Throwable cause) {
		super(cause);
	}

}
