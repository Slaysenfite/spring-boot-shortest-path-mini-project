Feature: Find the shortest path between two planets

  Scenario:
    Given a user provides two planet names but a given name does not belong to an existing planet
      | Name                |
      | Earth               |
      | Planet McPlanetface |
    When the user requires the shortest path between the two planets to be found
    Then the user notified that a given name belongs to a planet that does not exist

  Scenario:
    Given a user provides two planet names
      | Name    |
      | Neptune |
      | Pollux  |
    When the user requires the shortest path between the two planets
    Then the user is notified that the shortest path found is "<hopCount>" hops
      | 4 |
    And the user is provided with the route between the two planets
      | Nodes |
      | I     |
      | Z     |
      | Y     |
      | A'    |
