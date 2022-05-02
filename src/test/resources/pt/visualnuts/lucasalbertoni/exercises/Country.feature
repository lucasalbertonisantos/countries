Feature: Country

  Scenario: 1 - Number of countries in the world
    Given there are the bellow countries in the world
      | US | en |
      | BE | nl, fr, de |
      | NL | nl, fy |
      | DE | de |
      | ES | es |
    When I ask for the number of the countries in the world
    Then it must be 5 countries
    And the http code must be 200
    And the quantity saved on database must be 5

  Scenario: 2 - Country with the most official languages, where they officially speak German (de)
    Given there are the bellow countries in the world
      | US | en |
      | BE | nl, fr, de |
      | NL | nl, fy |
      | DE | de |
      | ES | es |
    When I ask for the most official languages where they officially speak German "de"
    Then the found country must be "BE"
    And the http code must be 200

  Scenario: 3 - Counts all the official languages spoken in the listed countries
    Given there are the bellow countries in the world
      | US | en |
      | BE | nl, fr, de |
      | NL | nl, fy |
      | DE | de |
      | ES | es |
    When I ask all the official languages spoken in the list bellow
      | US |
      | BE |
    Then it must return 4 languages
    And the http code must be 200

  Scenario: 4 - Find the country with the highest number of official languages
    Given there are the bellow countries in the world
      | US | en |
      | BE | nl, fr, de |
      | NL | nl, fy |
      | DE | de |
      | ES | es |
    When I ask for the highest number of official languages in the world
    Then the found country must be "BE"
    And the http code must be 200

  Scenario: 5 - Find the most common official language(s), of all countries
    Given there are the bellow countries in the world
      | US | en |
      | BE | nl, fr, de |
      | NL | nl, fy |
      | DE | de |
      | ES | es |
    When I ask for most common official languages in the world
    Then the found country must be
      | de |
      | nl |
    And the http code must be 200
