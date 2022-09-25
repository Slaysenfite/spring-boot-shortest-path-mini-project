package com.slaysenfite.web.dto.mapper;

import com.slaysenfite.domain.entity.Planet;
import com.slaysenfite.web.dto.PlanetDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapstruct mapping interface for Planet entities and dto's
 */
@Mapper(componentModel = "spring")
public interface PlanetMapper {
    PlanetDto PlanetEntityToDto(Planet source);
    Planet PlanetDtoToEntity(PlanetDto destination);

    List<PlanetDto> PlanetsEntityToDto(List<Planet> source);
    List<Planet> PlanetsDtoToEntity(List<PlanetDto> destination);
}
