package com.slaysenfite.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.slaysenfite.domain.algorithm.model.Result;
import com.slaysenfite.service.AlgorithmService;

@RestController
@RequestMapping(value = "/shortestPath")
@CrossOrigin(origins = "${angularUrl}")
public class ShortestPathController {
    private final AlgorithmService algorithmService;

    @Autowired
    public ShortestPathController(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    @GetMapping
    public @ResponseBody
    Result getShortestPath(@RequestParam("originPlanetName") String origin,
                           @RequestParam("destinationPlanetName") String destination) {
        return algorithmService.findShortestPath(origin, destination);
    }
}
