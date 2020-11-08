package fr.insee.habilitationexample.controleacces;

public interface ResourcesMatcher {

    default MatcherWithTarget checkIf(Resource resource){
        return new MatcherWithTarget(this, resource);
    }

    boolean match(Resource targetResource, Resource resource);
}
