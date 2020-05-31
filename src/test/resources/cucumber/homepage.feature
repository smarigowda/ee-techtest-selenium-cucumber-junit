Feature: Hotel Booking
  Background: Open the application in browser and clear any old bookings
    Given User opens the application
    And User Deletes all the bookings

  Scenario Outline: User books successfully books a hotel
    When User inputs "<firstName>" "<lastName>" "<totalPrice>" "<checkoutDate>" "<checkinDate>" and "<depositPaid>"
    And User Saves the booking
    Then User sees one booking saved successfully
    And User deletes the booking
    And User sees the booking successfully deleted

    Examples:
    |depositPaid|firstName|lastName  |totalPrice|checkoutDate|checkinDate|
    |true       |Santosh  |Marigowda |100       |2020-05-28  |2020-05-20 |
    |false      |Datta    |Sakrepatna|200       |2020-05-28  |2020-05-20 |