package fr.insee.datahabilitation;

public interface ResourceMatcher {

    boolean match (Resource resourceTarget, Resource resourceAllowed);
}
