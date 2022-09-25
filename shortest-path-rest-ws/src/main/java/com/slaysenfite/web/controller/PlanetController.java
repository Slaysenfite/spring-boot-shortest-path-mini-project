package com.slaysenfite.web.controller;

import com.slaysenfite.domain.repository.PlanetRepository;
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
import com.slaysenfite.domain.entity.Planet;
import com.slaysenfite.web.dto.PlanetDto;
import com.slaysenfite.web.dto.mapper.PlanetMapper;

import java.util.List;
import java.util.Optional;

import static com.slaysenfite.web.controller.common.ControllerUtils.entityWithLocation;

/**
 * Rest controller that exposes CRUD behaviour for Planets
 */
@RestController
@RequestMapping(value = "/planets")
@CrossOrigin(origins = "${angularUrl}")
public class PlanetController {

    @Autowired
    private PlanetMapper planetMapper;

    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetController(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @GetMapping
    public @ResponseBody
    List<PlanetDto> getAllPlanets() {
        return planetMapper.PlanetsEntityToDto(planetRepository.findAll());
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Planet planetDetails(@PathVariable("id") Long id) {
        Optional<Planet> optionalPlanet = planetRepository.findById(id);
        if (optionalPlanet.isPresent()) {
            return optionalPlanet.get();
        } else {
            return new Planet();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createPlanet(@RequestBody PlanetDto newPlanet) {
        Planet planet = planetRepository.save(planetMapper.PlanetDtoToEntity(newPlanet));
        return entityWithLocation("{id}", planet.getEntityId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> updatePlanetDetails(@PathVariable("id") Long id, @RequestBody Planet updatedPlanet) {
        Planet planet = planetRepository.findById(id).get();
        copyPlanetDetails(updatedPlanet, planet);
        planetRepository.save(planet);
        return entityWithLocation("{id}", planet.getEntityId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removePlanet(@PathVariable("id") Long id) {
        planetRepository.deleteById(id);
    }

    private void copyPlanetDetails(@RequestBody Planet updatedPlanet, Planet planet) {
        planet.setName(updatedPlanet.getName());
        planet.setNode(updatedPlanet.getNode());
    }
}
