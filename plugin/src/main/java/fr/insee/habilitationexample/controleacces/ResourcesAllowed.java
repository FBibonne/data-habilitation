package fr.insee.habilitationexample.controleacces;

import java.util.stream.Stream;

public interface ResourcesAllowed {
    Stream<Resource> resources();
}
