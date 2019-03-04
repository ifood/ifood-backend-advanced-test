package br.com.ifood.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService {

	private ModelMapper modelMapper;
	
	private void load() {
		if(modelMapper == null) {
			modelMapper = new ModelMapper();
		}
	}
	
	public <T> T convertSimple(Object convertTo, Class<T> classToConvert) {
		load();
		return (T) modelMapper.map(convertTo, classToConvert);
	}
	
	public <D, T> List<D> convertList(Collection<T> listToConvert, Class<D> classToConvert) {
		load();
		return listToConvert.stream()
				.map(el -> convertSimple(el, classToConvert))
				.collect(Collectors.toList());
	}
}
