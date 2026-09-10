Feature: Lookup Postcode
  As a client I want to lookup a postcode so that I can find out whether it exists


  @HappyPath
  Scenario Outline: Postcode exists
    When I lookup <postcode>
    Then The returned status code should be 200

    Examples:
      | postcode  |
      | "HA40AY"  |
      | "BN16GL" |
      | "EC2Y5AB" |
      | "L77AJ"  |

  @SadPath
  Scenario Outline: Postcode doesn't exist
    When I lookup <postcode>
    Then The returned status code should be 404

    Examples:
      | postcode  |
      | "GB51BZ"  |
      | "AM05FK"  |
      | "XY2Y5AB" |
      | "K66ZI"   |

  @SadPath
  Scenario: Missing parameter
    When I omit the postcode in the path parameter
    Then The returned status code should be 400

  @SadPath
  Scenario: Wrong REST verb
    When I use a DELETE request on the lookup endpoint
    Then The returned status code should be 405

