package jac.learning.exception;


public class DatabaseException extends RuntimeException{

    private String message;

    public DatabaseException(String message){
        super(message);
        this.message = message;
    }

    public DatabaseException(){

    }
}


