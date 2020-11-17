package fr.insee.datahabilitation;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Slf4j
public class DefaultUserIdFinder implements UserIdFinder {

    @Override
    public UserId find(HttpServletRequest req) throws UnexpectedDataForAuthentificationException {
        UserId retour=null;
        String authType=req.getAuthType();
        if (authType==null){
            retour=UserId.empty();
            log.debug("User not authentified");
        }else{
            switch (authType){
                case "BASIC_AUTH" :
                    retour=authentifyWithBasicAuth(req.getHeader("Authorization"));
                    break;
                case "FORM_AUTH" :
                    break;
                case "CLIENT_CERT_AUTH" :
                    break;
                case "DIGEST_AUTH" :
                    break;
                default:
                    log.error("Unkown auth method for plugin %s", ConstantAndProperties.getPluginName());
                    throw new UnexpectedDataForAuthentificationException(String.format("Unkown auth method for plugin %s", ConstantAndProperties.getPluginName()));
            }
        }
        log.debug("User %s with %s", retour==null?"null":retour ,authType==null?"null":authType);
        return retour;
    }

    /**
     * Resolve userId from the content of header authorization. The content must follow this structure :
     * <code>Basic $AUTHENTIFICATION_ENCODED_STRING</code>
     * where $AUTHENTIFICATION_ENCODED_STRING is a base64 encoding of :
     * <code>$ID:$PASSWORD</code>
     * where $ID is the non null value of the UserId which is return
     *
     *  //TODO: make a class for this method
     *
     * @param authorization
     * @return a userId instance whose value is the identifier of the basic authentification encoded String
     * @throws UnexpectedDataForAuthentificationException if content of header Authorization does not fit expected structure
     */
    private UserId authentifyWithBasicAuth(@NonNull String authorization) throws UnexpectedDataForAuthentificationException{
        String[] authComponents=authorization.split(" ");
        if (! (authComponents.length==2 && "Basic".equalsIgnoreCase(authComponents[0]))&&authComponents[1]!=null){
            throw new UnexpectedDataForAuthentificationException(String.format("Content of Authorization header does not fit data structure for basic type authentification : %s", authorization));
        }
        return new UserId(new String(Base64.getDecoder().decode(authComponents[1])).split(":")[0]);
    }

    /**
     * Resolve userId from the content of the KeycloakSecurityContext attribute of request.
     * The attribute must be nonnull and the method is going to return the preferredUsername of the attribute
     *
     *  //TODO: make a class for this method
     *
     * @param req
     * @return a userId instance whose value is the identifier of the keycloakSecurityContext.preferredUsername
     * @throws UnexpectedDataForAuthentificationException if the attribute does not fit expected structure
     */
    private UserId authentifyWithKeycloak(@NonNull HttpServletRequest req) throws UnexpectedDataForAuthentificationException{
        AccessToken token = ((KeycloakSecurityContext) req.getAttribute(KeycloakSecurityContext.class.getName()))
                .getToken();
        return new UserId(token.getPreferredUsername());
    }

}
