public enum Periodicita {
    SETTIMANALE("SETTIMANALE"),
    MENSILE("MENSILE"),
    SEMESTRALE("SEMESTRALE");

    private String value;

    private Periodicita(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
