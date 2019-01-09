package br.com.imood.error;

public class ExceptionWithCustomMessage extends Exception {

    public ExceptionWithCustomMessage(String message, Object... params){
        super(String.format(message, params));
    }

    public ExceptionWithCustomMessage(){
        super();
    }
}
