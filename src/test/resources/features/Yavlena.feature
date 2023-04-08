@Yavlena
@Yavlena:UI
@context:UI

Feature: Yavlena Broker List

  @Yavlena_UI_01
  @PositivePath @Automated
  Scenario: As a user of Yavlena, Mason wants to see a list of all the brokers
    Given Mason is a user of Yavlena services
    When  He tries to view all the brokers
    Then  He is prompted with a list of all the available brokers

  @Yavlena_UI_02
  @PositivePath @Automated
  Scenario: As a user of Yavlena, Django wants to search for a specific broker
    Given Django is a user of Yavlena services
    When  He searches for a specific broker that works for Yavlena
    Then  He sees the broker is present in the system

  @Yavlena_UI_03
  @PositivePath @Automated
  Scenario: As a user of Yavlena, Brad wants to search all the brokers in the system
    Given Brad is a user of Yavlena services
    And   He searches every broker in the system
    Then  He is able to complete the task