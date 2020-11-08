package fr.insee.habilitationexample.controleacces;

public class InvalidHttpMethodNameException extends Exception {
    public InvalidHttpMethodNameException(String method) {
        super(method +" is not a valid http method name");
    }
}
