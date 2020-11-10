package fr.insee.datahabilitation;

import javax.servlet.http.HttpServletRequest;

public interface UserIdFinder {

    UserId find(HttpServletRequest req);

}
