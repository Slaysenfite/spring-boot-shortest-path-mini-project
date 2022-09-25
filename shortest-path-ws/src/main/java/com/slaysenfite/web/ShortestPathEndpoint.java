package com.slaysenfite.web;

import com.slaysenfite.service.AlgorithmService;
import com.slaysenfite.web.mapper.GeneratedSourcesMapper;
import slaysenfite_shortest_path_service.generated_sources.GetShortestPathRequest;
import slaysenfite_shortest_path_service.generated_sources.GetShortestPathResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.slaysenfite.domain.algorithm.model.Result;

/**
 * Service endpoint to expose the shortest path algorithm
 */
@Endpoint
public class ShortestPathEndpoint {
    private static final String NAMESPACE_URI = "http://slaysenfite-shortest-path-service/generated-sources";

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private GeneratedSourcesMapper mapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getShortestPathRequest")
    @ResponsePayload
    public GetShortestPathResponse getShortestPath(@RequestPayload GetShortestPathRequest request) {
        GetShortestPathResponse response = new GetShortestPathResponse();
        Result requestResult = algorithmService.findShortestPath(request.getOriginPlanetName(), request.getDestinationPlanetName());
        response.setShortestPathResult(mapper.ResultEntityToDto(requestResult));
        return response;
    }

}
