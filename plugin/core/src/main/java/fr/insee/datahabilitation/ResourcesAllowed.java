package fr.insee.datahabilitation;

import java.util.stream.Stream;

public interface ResourcesAllowed {
    Stream<Resource> resources();
}
