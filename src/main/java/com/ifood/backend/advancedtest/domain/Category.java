package com.ifood.backend.advancedtest.domain;

public enum Category {
    PARTY("37i9dQZF1DX2nwuHNKim4S"),
    ROCK("37i9dQZF1DWUaThf8nMdW6"),
    POP("37i9dQZF1DWXRqgorJj26U"),
    CLASSICAL("37i9dQZF1DX8Sz1gsYZdwj");

    private final String playlistId;

    Category(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistId() {
        return playlistId;
    }
}
