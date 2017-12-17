# Acceptance Criteria

## Functionality
__Get music playlist__
 
    As an user of the service
    I want to send a location
    And get a music playlist according to the temperature of the location

### Scenary
City with 30 degrees, get Party playlist

    Given I am in city 'Manaus' with 30 degrees
    When I send the city as request to service
    Then I must get the 'PARTY' music playlist

### Scenary
City with 25 degrees, get POP playlist

    Given I am in city 'Salvador' with 25 degrees
    When I send the city as request to service
    Then I must get the 'POP' music playlist

### Scenary
City with 13 degrees, get ROCK playlist

    Given I am in city 'Curitiba' with 13 degrees
    When I send the city as request to service
    Then I must get the 'ROCK' music playlist

### Scenary
City with 5 degrees, get CLASSICAL playlist

    Given I am in city 'Moscow' with 5 degrees
    When I send the city as request to service
    Then I must get the 'CLASSICAL' music playlist
    
    
  ---------
    
### Scenary
Coordinates with 30 degrees, get Party playlist

    Given I am in coordinates '-3.119027,-60.021731' with 30 degrees
    When I send the city as request to service
    Then I must get the 'PARTY' music playlist

### Scenary
Coordinates with 25 degrees, get POP playlist

    Given I am in coordinates '-12.97304,-38.502304' with 25 degrees
    When I send the city as request to service
    Then I must get the 'POP' music playlist

### Scenary
Coordinates with 13 degrees, get ROCK playlist

    Given I am in coordinates '-25.428954,-49.267137' with 13 degrees
    When I send the coordinates as request to service
    Then I must get the 'ROCK' music playlist

### Scenary
Coordinates with 5 degrees, get CLASSICAL playlist

    Given I am in coordinates '55.755826,37.6173' with 5 degrees
    When I send the coordinates as request to service
    Then I must get the 'CLASSICAL' music playlist