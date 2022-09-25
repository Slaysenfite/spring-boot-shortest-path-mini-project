package com.slaysenfite.web.dto.mapper;

import com.slaysenfite.domain.entity.Route;
import com.slaysenfite.web.dto.RouteDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapstruct mapping interface for Route entities and dto's
 */
@Mapper(componentModel = "spring")
public interface RouteMapper {
    RouteDto routeEntityToDto(Route source);
    Route routeDtoToEntity(RouteDto destination);

    List<RouteDto> RoutesEntityToDto(List<Route> source);
    List<Route> RoutesDtoToEntity(List<RouteDto> destination);
}
