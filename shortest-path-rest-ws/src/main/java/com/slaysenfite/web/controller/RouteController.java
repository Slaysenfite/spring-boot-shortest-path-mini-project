package com.slaysenfite.web.controller;

import com.slaysenfite.domain.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.slaysenfite.domain.entity.Route;
import com.slaysenfite.web.dto.RouteDto;
import com.slaysenfite.web.dto.mapper.RouteMapper;

import java.util.List;
import java.util.Optional;

import static com.slaysenfite.web.controller.common.ControllerUtils.entityWithLocation;

/**
 * Rest controller that exposes CRUD behaviour for Routes
 */
@RestController
@RequestMapping(value = "/routes")
@CrossOrigin(origins = "${angularUrl}")
public class RouteController {
    @Autowired
    private RouteMapper routeMapper;

    private RouteRepository routeRepository;

    @Autowired
    public RouteController(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @GetMapping
    public @ResponseBody
    List<RouteDto> getAllRoutes() {
        return routeMapper.RoutesEntityToDto(routeRepository.findAll());
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Route routeDetails(@PathVariable("id") Integer id) {
        Optional<Route> optionalRoute = routeRepository.findById(id);
        if (optionalRoute.isPresent()) {
            return optionalRoute.get();
        } else {
            return new Route();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createRoute(@RequestBody RouteDto newRoute) {
        Route route = routeRepository.save(routeMapper.routeDtoToEntity(newRoute));
        return entityWithLocation("{id}", route.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> updateRouteDetails(@PathVariable("id") Integer id, @RequestBody Route updatedRoute) {
        Route route = routeRepository.findById(id).get();
        copyRouteDetails(updatedRoute, route);
        routeRepository.save(route);
        return entityWithLocation("{id}", route.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeRoute(@PathVariable("id") Integer id) {
        routeRepository.deleteById(id);
    }

    private void copyRouteDetails(@RequestBody Route updatedRoute, Route route) {
        route.setOrigin(updatedRoute.getOrigin());
        route.setDestination(updatedRoute.getDestination());
        route.setDistance(updatedRoute.getDistance());
        route.setDelay(updatedRoute.getDelay());
    }
}
