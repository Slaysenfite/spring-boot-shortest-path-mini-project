Feature: Test Shortest Path Algorithm Web Service

  Background:
    * url 'http://localhost:8080/ws/shortestPath'

  Scenario: Shortest path is requested between two existing planets
    Given request read('getShortestPathRequest.xml')
    And header content-type = 'application/xml'
    When soap action 'getShortestPathRequest'
    Then status 200
    And match $ == read('getShortestPathResponse.xml')

  Scenario: Shortest path is requested between planets but one planet does not exist
    Given request read('faultyRequest.xml')
    And header content-type = 'application/xml'
    When soap action 'getShortestPathRequest'
    Then match $ == read('getShortestPathFault.xml')

