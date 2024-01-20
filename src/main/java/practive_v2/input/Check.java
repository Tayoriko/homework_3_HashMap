package practive_v2.input;

import practive_v2.RecordsInBase;

import java.util.Scanner;

public class Check<T> {
    private boolean error;
    private String message = "";
    private T value;

    public Check(T value){
        this.value = value;
    }

    public boolean isError() {
        return error;
    }

    public T getValue() {
        return value;
    }

    public void setError() {
        this.error = true;
    }

    public String getMessage() {
        return message;
    }

    public void addMessage(String message) {
        this.message += message;
        this.message += " ";
    }
}
