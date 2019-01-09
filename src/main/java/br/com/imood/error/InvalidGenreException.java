package br.com.imood.error;

public class InvalidGenreException extends Exception {

    public InvalidGenreException(){
        super();
    }

    public InvalidGenreException(String message){
        super(message);
    }

}
