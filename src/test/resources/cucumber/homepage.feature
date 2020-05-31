Feature: Hotel Booking
  Background: Open the application in browser and clear any old bookings
    Given User opens the application
    And User Deletes all the bookings

  Scenario: User books hotel with deposit
    When User provides all the details with deposit true
    And User Saves the booking
    Then User sees one booking saved successfully
    And User deletes the booking
    And User sees the booking successfully deleted

  Scenario: User books hotel without deposit
    When User provides all the details with deposit true
    And User Saves the booking
    Then User sees one booking saved successfully
    And User deletes the booking
    And User sees the booking successfully deleted