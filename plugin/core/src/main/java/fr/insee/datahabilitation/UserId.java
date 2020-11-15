package fr.insee.datahabilitation;

import lombok.*;

@EqualsAndHashCode(of="value")
public class UserId {

    private String value;
    private boolean empty=false;

    public UserId(@NonNull String value ){
        this.value=value;
    }

    private UserId(){
        empty=true;
    }

    public static UserId empty(){
        return new UserId();
    }

    public String getValue(){
        return empty?"***NOT AUTHENTIFIED***":this.value;
    }

    public String toString(){
        return getValue();
    }

    public boolean isNotAuthentified() {
        return empty;
    }
}
