package fr.insee.datahabilitation;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class DefaultUserIdFinder implements UserIdFinder {

    @Override
    public UserId find(HttpServletRequest req) {
        UserId retour=null;
        String authType=req.getAuthType();
        if (authType==null){
            retour=UserId.empty();
            log.debug("User not authentified");
        }else{
            switch (authType){
                case "BASIC_AUTH" :
                    break;
                case "FORM_AUTH" :
                    break;
                case "CLIENT_CERT_AUTH" :
                    break;
                case "DIGEST_AUTH" :
                    break;
                default:
                    Object pluginName=null;
                    log.error("Unkown auth method for plugin %s",ConstantAndProp);
            }
        }
        log.debug("User %s with %s", retour ,authType);
        Constante.AuthType authType = (Constante.AuthType) request.getAttribute("authType");
        AccessToken token = ((KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName()))
                .getToken();
        String idep = token.getPreferredUsername().toUpperCase();
        switch (authType) {
            case KEYCLOAK:
                return facade.getUserByIdep(idep, false, false).get(0);
            default:
                throw new IllegalArgumentException("Bad authentication type " + authType);
        }


        org.keycloak.KeycloakSecurityContext
    }
}
