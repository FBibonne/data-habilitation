package fr.insee.habilitationexample.controleacces;

public interface ResourcesAllowedService {

    ResourcesAllowed findByUserId(UserId userId);
}
