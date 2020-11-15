package fr.insee.datahabilitation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;

import java.net.URI;
import java.net.URISyntaxException;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Resource {

    @NonNull
    private HttpmethodForRequest method;
    @NonNull
    private URI resourcePath;

    public Resource(@NonNull String method, @NonNull String requestURI) throws InvalidHttpMethodNameException, InvalidResourcePathException {

        if (! EnumUtils.isValidEnum(HttpmethodForRequest.class,method)){
            throw new InvalidHttpMethodNameException(method);
        }
        this.method= HttpmethodForRequest.valueOf(method);
        try {
            this.resourcePath= new URI(null, null, requestURI, null);
        } catch (URISyntaxException e) {
            throw new InvalidResourcePathException(requestURI, e);
        }
    }

}
