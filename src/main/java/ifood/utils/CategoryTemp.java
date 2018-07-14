package ifood.utils;

import ifood.model.TrackCategoryEnum;

public class CategoryTemp {

    private static final int PARTY_MIN_TEMP = 30;
    private static final int POP_MIN_TEMP = 15;
    private static final int ROCK_MIN_TEMP = 10;

    private CategoryTemp() {}

    public static TrackCategoryEnum getCategoryByTemp(final double temp) {
        if (temp > PARTY_MIN_TEMP) {
            return TrackCategoryEnum.PARTY;
        } else if (temp >= POP_MIN_TEMP) {
            return TrackCategoryEnum.POP;
        } else if (temp >= ROCK_MIN_TEMP) {
            return TrackCategoryEnum.ROCK;
        }
        return TrackCategoryEnum.CLASSICAL;
    }
}
