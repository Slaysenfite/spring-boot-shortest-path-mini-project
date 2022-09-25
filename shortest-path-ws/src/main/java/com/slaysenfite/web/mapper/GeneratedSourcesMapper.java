package com.slaysenfite.web.mapper;

import com.slaysenfite.domain.algorithm.model.Result;
import com.slaysenfite.domain.algorithm.model.Vertex;
import com.slaysenfite.domain.entity.Planet;
import slaysenfite_shortest_path_service.generated_sources.BigDecimalDto;
import slaysenfite_shortest_path_service.generated_sources.PlanetDto;
import slaysenfite_shortest_path_service.generated_sources.ResultDto;
import slaysenfite_shortest_path_service.generated_sources.VertexDto;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A mapstruct abstract class to provide mapping between domain entities
 * and spring-ws generated domain transfer objects
 */
@Mapper(componentModel = "spring")
public abstract class GeneratedSourcesMapper {

    public List<BigDecimalDto> BigDecimalEntitiesToDtos(List<BigDecimal> sources) {
        List<BigDecimalDto> dtos = new ArrayList<>();
        for (BigDecimal source : sources) {
            BigDecimalDto dto = new BigDecimalDto();
            dto.setCost(source.floatValue());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<BigDecimal> BigDecimalDtosToEntities(List<BigDecimalDto> destinations) {
        List<BigDecimal> entities = new ArrayList<>();
        for (BigDecimalDto destination : destinations) {
            entities.add(new BigDecimal(destination.getCost()));
        }
        return entities;
    }

    public abstract ResultDto ResultEntityToDto(Result source);

    public abstract Result ResultDtoToEntity(ResultDto destination);

    public abstract VertexDto VertexEntityToDto(Vertex source);

    public abstract Vertex VertexDtoToEntity(VertexDto destination);

    public abstract PlanetDto PlanetEntityToDto(Planet source);

    public abstract Planet PlanetDtoToEntity(PlanetDto destination);


}
