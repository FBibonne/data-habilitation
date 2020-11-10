package fr.insee.datahabilitation;

public interface ResourcesAllowedService {

    ResourcesAllowed findByUserId(UserId userId);
}
