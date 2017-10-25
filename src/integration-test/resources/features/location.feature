Feature: Location CRUD
  As an admin, I want to be able to create, read, update and delete data

  Scenario: Add location
    Given i create a location with name of area Glasgow
    When i save the location
    Then i should have location Glasgow in the database