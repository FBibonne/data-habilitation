package fr.insee.datahabilitation;

public interface AllowedResourcesMatcherBuilder {

    default AllowedResourcesMatcher checkIf(Resource resource){
        return new AllowedResourcesMatcher(this.matcher(), resource);
    }

    ResourceMatcher matcher();
}
