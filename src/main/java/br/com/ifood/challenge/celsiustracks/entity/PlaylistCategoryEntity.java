package br.com.ifood.challenge.celsiustracks.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PLAYLIST_CATEGORIES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaylistCategoryEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    public PlaylistCategoryEntity(final String name) {
        this.name = name;
    }
}
