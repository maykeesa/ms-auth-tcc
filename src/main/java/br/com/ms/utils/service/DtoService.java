package br.com.ms.utils.service;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DtoService {

    private static ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper;
    }

    public static <E, D> D entityToDto(E entity, Class<D> dtoClass){
        return getModelMapper().map(entity, dtoClass);
    }

    public static <D, E> E dtoToEntity(D dto, Class<E> entityClass){
        return getModelMapper().map(dto, entityClass);
    }

    public static <E, D> List<D> entitysToDtos(List<E> entitys, Class<D> dtoClass){
        return entitys.stream()
                .map(entity -> entityToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public static <D, E> List<E> dtosToEntitys(List<D> dtos, Class<E> entityClass){
        return dtos.stream()
                .map(dto -> entityToDto(dto, entityClass))
                .collect(Collectors.toList());
    }

}
