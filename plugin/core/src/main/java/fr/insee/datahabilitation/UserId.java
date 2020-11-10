package fr.insee.datahabilitation;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
public class UserId {

    @NonNull
    String value;

    public String toString(){
        return getValue();
    }

}
