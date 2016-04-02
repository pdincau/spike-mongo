public class EqualityCriteria {

    private final String field;
    private final String value;

    public EqualityCriteria(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }
}
