package fr.insee.habilitationexample.controleacces;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatcherWithTarget {

    @NonNull
    private ResourcesMatcher resourceMatcher;
    @NonNull
    private Resource targetResource;

    public boolean match(ResourcesAllowed resourcesAllowed){
        return resourcesAllowed.resources().anyMatch(this::match);
    }

    private boolean match(Resource resource) {
        return this.resourceMatcher.match(this.targetResource, resource);
    }
}
