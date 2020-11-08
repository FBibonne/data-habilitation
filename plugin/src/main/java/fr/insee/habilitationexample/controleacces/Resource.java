package fr.insee.habilitationexample.controleacces;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Scanner;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Resource {

    @NonNull
    private RequestMethod method;
    @NonNull
    private URI resourcePath;

    public Resource(@NonNull String method, @NonNull String requestURI) throws InvalidHttpMethodNameException, InvalidResourcePathException {

        if (! EnumUtils.isValidEnum(RequestMethod.class,method)){
            throw new InvalidHttpMethodNameException(method);
        }
        this.method=RequestMethod.valueOf(method);
        try {
            this.resourcePath= new URI(null, null, requestURI, null);
        } catch (URISyntaxException e) {
            throw new InvalidResourcePathException(requestURI, e);
        }
    }

}
