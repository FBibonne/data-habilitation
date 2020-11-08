package fr.insee.habilitationexample.controleacces;

import javax.servlet.http.HttpServletRequest;

public interface UserIdFinder {

    UserId find(HttpServletRequest req);

}
