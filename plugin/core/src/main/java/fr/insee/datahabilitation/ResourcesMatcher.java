package fr.insee.datahabilitation;

public interface ResourcesMatcher {

    default MatcherWithTarget checkIf(Resource resource){
        return new MatcherWithTarget(this, resource);
    }

    boolean match(Resource targetResource, Resource resource);
}
