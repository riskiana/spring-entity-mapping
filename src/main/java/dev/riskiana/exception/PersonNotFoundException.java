package dev.riskiana.exception;

public class PersonNotFoundException extends RuntimeException {
  public PersonNotFoundException(String message) {
    super(message);
  }

}
