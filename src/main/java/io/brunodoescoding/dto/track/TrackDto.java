package io.brunodoescoding.dto.track;

import lombok.Data;

import java.util.List;

@Data
public class TrackDto {
    private List<ItemDto> items;

    private Integer total;
    private Integer offset;
    private Integer limit;
}
