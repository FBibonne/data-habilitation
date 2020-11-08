package fr.insee.habilitationexample.controleacces;

import lombok.NonNull;

import java.net.URISyntaxException;

public class InvalidResourcePathException extends Exception {
    public InvalidResourcePathException( String requestURI, @NonNull URISyntaxException e) {
        super(requestURI+" is not a valid path for an URI (see cause)", e);
    }
}
