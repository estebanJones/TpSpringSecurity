package fr.stazi.epsi.spring.boot.exception;

public class NotAdminException extends Exception{
	public NotAdminException(String message) {
		super(message);
	}
}
