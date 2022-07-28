package com.hungernet2.converter;

public abstract class AbstractConverter<ENTITY, DTO> {

	public abstract ENTITY toEntity(DTO dto);
	public abstract DTO toDTO(ENTITY entity);
}