package ifood.model;

public enum TrackCategoryEnum {
    PARTY,
    POP,
    ROCK,
    CLASSICAL;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
