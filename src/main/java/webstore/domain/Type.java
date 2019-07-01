package webstore.domain;

public enum  Type {
    TECHNICAL("technicals"),
    ANIMAL("animals"),
    REALTY("realty"),
    CLOTHES("clothes"),
    OTHER("other");

    private String value;

    Type(String value) {
        this.value = value;
    }

    public Type isType(String string){
        for (Type type : Type.values()){
            if (string.equalsIgnoreCase(type.value))
                return type;
        }
        throw new IllegalArgumentException("No such value");
    }
}
