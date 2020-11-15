package fr.insee.datahabilitation.utils;

import lombok.NonNull;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {

    public static <T> Stream<T> enumerationToParallelisableStream(@NonNull Enumeration<T> enumeration, int... characteristics){
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        enumeration.asIterator(), Arrays.stream(characteristics).sum()),true
        );
    }

}
