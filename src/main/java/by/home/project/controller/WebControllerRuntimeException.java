package by.home.project.controller;

public class WebControllerRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -6602757810841013794L;
	
	public WebControllerRuntimeException() {
		super();
	}

	public WebControllerRuntimeException(String message) {
		super(message);
	}

	public WebControllerRuntimeException(Exception e) {
		super(e);
	}

	public WebControllerRuntimeException(String message, Exception e) {
		super(message, e);
	}

}
