package br.mmmgg.tirolofficeservice;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -7365598893414334098L;
	
	private String description;
	
	private Throwable cause;
	
	public ValidationException(Throwable cause, String message) {
		super(message);
		this.description = message;
		this.cause = cause;
	}
	
	public ValidationException(String message) {
		super(message);
		this.description = message;
	}
	
	public ValidationException() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Throwable getCause() {
		return cause;
	}

}
