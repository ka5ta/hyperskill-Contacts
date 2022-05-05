package contacts.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Gender {
    FEMALE("F"), MALE("M"), UNDEFINED("U");

    private String label;

    Gender(String label) {
        this.label = label;
    }

    private static final Map<String, Gender> BY_LABEL = new HashMap<>();

    static {
        for(Gender g : values()){
            BY_LABEL.put(g.label, g);
        }
    }

    public static Gender byLabel(String genderInput) {
        try{
            return Gender.valueOf(genderInput.toUpperCase());
        } catch (IllegalArgumentException e){
            return Objects.requireNonNullElse(BY_LABEL.get(genderInput), UNDEFINED);
        }
    }
}
