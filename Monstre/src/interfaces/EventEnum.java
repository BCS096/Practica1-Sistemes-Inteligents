package interfaces;

public enum EventEnum {
    MOVER("Moure agent"),
    END("Tresor trobat");

    private final String text;

    EventEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
