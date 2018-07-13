package ifood.utils;

import ifood.model.TrackCategoryEnum;
import ifood.service.PlaylistBuilderService;
import org.junit.Assert;
import org.junit.Test;

public class CategoryByTempTest {
    @Test
    public void getPartyCategoryMaxLimit() {
        final TrackCategoryEnum expected = TrackCategoryEnum.PARTY;
        final TrackCategoryEnum actual = CategoryTemp.getCategoryByTemp(100);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPartyCategoryMinLimit() {
        final TrackCategoryEnum expected = TrackCategoryEnum.PARTY;
        final TrackCategoryEnum actual = CategoryTemp.getCategoryByTemp(31);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPopCategoryMaxLimit() {
        final TrackCategoryEnum expected = TrackCategoryEnum.POP;
        final TrackCategoryEnum actual = CategoryTemp.getCategoryByTemp(30);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPopCategoryMiddleLimit() {
        final TrackCategoryEnum expected = TrackCategoryEnum.POP;
        final TrackCategoryEnum actual = CategoryTemp.getCategoryByTemp(20);

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void getPopCategoryMinLimit() {
        final TrackCategoryEnum expected = TrackCategoryEnum.POP;
        final TrackCategoryEnum actual = CategoryTemp.getCategoryByTemp(15);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getRockCategoryMaxLimit() {
        final TrackCategoryEnum expected = TrackCategoryEnum.ROCK;
        final TrackCategoryEnum actual = CategoryTemp.getCategoryByTemp(14.9);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getRockCategoryMiddleLimit() {
        final TrackCategoryEnum expected = TrackCategoryEnum.ROCK;
        final TrackCategoryEnum actual = CategoryTemp.getCategoryByTemp(12);

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void getRockCategoryMinLimit() {
        final TrackCategoryEnum expected = TrackCategoryEnum.ROCK;
        final TrackCategoryEnum actual = CategoryTemp.getCategoryByTemp(10);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getClassicalCategoryMaxLimit() {
        final TrackCategoryEnum expected = TrackCategoryEnum.CLASSICAL;
        final TrackCategoryEnum actual = CategoryTemp.getCategoryByTemp(9.9);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getClassicalCategoryMiddleLimit() {
        final TrackCategoryEnum expected = TrackCategoryEnum.CLASSICAL;
        final TrackCategoryEnum actual = CategoryTemp.getCategoryByTemp(0);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getClassicalCategoryMinLimit() {
        final TrackCategoryEnum expected = TrackCategoryEnum.CLASSICAL;
        final TrackCategoryEnum actual = CategoryTemp.getCategoryByTemp(-5);

        Assert.assertEquals(expected, actual);
    }
}
