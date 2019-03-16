package br.com.ifood.challenge.celsiustracks.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PLAYLISTCATEGORY_X_TEMPERATURE_RANGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaylistCategoryByTemperatureRangeEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PLAYLIST_CATEGORY", referencedColumnName = "ID", nullable = false)
    private PlaylistCategoryEntity playlistCategoryEntity;

    @Column(name = "START_TEMPERATURE", nullable = false)
    private Double startTemperature;

    @Column(name = "END_TEMPERATURE", nullable = false)
    private Double endTemperature;

    public PlaylistCategoryByTemperatureRangeEntity(final PlaylistCategoryEntity playlistCategoryEntity, final Double startTemperature, final Double endTemperature) {
        this.playlistCategoryEntity = playlistCategoryEntity;
        this.startTemperature = startTemperature;
        this.endTemperature = endTemperature;
    }
}
