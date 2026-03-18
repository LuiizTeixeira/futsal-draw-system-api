package br.com.futsal.futsaldrawsystemapi.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
