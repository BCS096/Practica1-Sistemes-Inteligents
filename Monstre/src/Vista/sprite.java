package Vista;

public enum sprite {
    AGENT("Agent"),
    BRISA("Brisa"),
    MONSTRE("Monstre"),
    PRECIPICI("Precipici"),
    TRESOR("Tresor"),
    ONEUP("Tresor obtingut"),
    MAP("Casella del mapa"),
    HEDOR("Toxic hedor"),
    COMBINE("Hedor i brisa");

    private final String type;

    sprite(String type) {
        this.type = type;
    }

    public String getInstType() {
        return this.type;
    }
}