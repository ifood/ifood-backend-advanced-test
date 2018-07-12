package ifood.model;

public enum TrackCategory {
    PARTY,
    POP,
    ROCK,
    CLASSICAL;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
