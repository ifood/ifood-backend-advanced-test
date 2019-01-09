package br.com.imood.domain.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemsEnvelopeDTO<T> {

    private List<T> items;

}
