package fr.stazi.epsi.spring.boot.dto;

public class DtoSuccess{
	private String message;
	
	public DtoSuccess(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
