package ru.vaspoz.relo.exceptions;

public class CountryNotFoundException extends Exception {
    public CountryNotFoundException() {
        super();
    }

    public CountryNotFoundException(String message) {
        super(message);
    }

}
