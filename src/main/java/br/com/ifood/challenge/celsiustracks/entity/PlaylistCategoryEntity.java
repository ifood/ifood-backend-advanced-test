package br.com.ifood.challenge.celsiustracks.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "PLAYLIST_CATEGORIES")
public class PlaylistCategoryEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    public PlaylistCategoryEntity(final String name) {
        this.name = name;
    }
}
