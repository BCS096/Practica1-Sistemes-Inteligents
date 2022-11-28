package interfaces;

public enum EventEnum {
    MOVER("Moure agent"),
    FOUND("Tresor trobat"),
    RETURN("Tornar a inici");

    private final String text;

    EventEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
