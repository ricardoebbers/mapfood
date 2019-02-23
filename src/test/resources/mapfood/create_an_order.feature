Feature: Create an order
  Users should be able to order food (products) from restaurants registered in the mapfood database

  Scenario: User should be able to list restaurants
    Given there are available restaurants
    When users want to list restaurants
    Then the requested list is returned

  Scenario: Users should be able to list restaurants' products
    Given restaurants have products
    When users want to see products from restaurants
    Then the requested list is returned

  Scenario: Users should be able to create an order containing products of a restaurant
    Given users have chosen a list of products from a restaurant
    When users request a order
    Then the order should be created
    And the order should have all the products ordered
    And the order should have the user details
    And the order should have the restaurant details
    And the order should have the products detail
    And the order should have the expected deliver time
