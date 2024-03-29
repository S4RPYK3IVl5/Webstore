package webstore.domain;

public enum  Type {
    TECHNICAL("Technicals"),
    ANIMAL("Animals"),
    REALTY("Realty"),
    CLOTHES("Clothes"),
    INTERIOR("Interior"),
    OTHER("Other");

    private String value;

    public String getValue() {
        return value;
    }

    Type(String value) {
        this.value = value;
    }

    public static Type isType(String string){
        for (Type type : Type.values()){
            if (string.equalsIgnoreCase(type.value))
                return type;
        }
        return null;
    }
}
