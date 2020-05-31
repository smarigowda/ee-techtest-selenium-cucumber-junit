### Manual Test Report

Hello Tester,

I have conducted the manual/ exploratory testing for the app with following test cases.
I have given the steps I followed for most of the cases.

1. User should be able to book a hotel `with` deposit - PASS
2. User should be able to book `more than one hotels` with deposit - PASS
3. User should be able to book a hotel `without` deposit - PASS
4. User should be able to book more than one hotel `without` deposit - PASS
5. User should be able to delete bookings - PASS
6. User should not be able to book if any one of the detail is missing - PASS
7. User should not be able to book if the Price is less than or equal to 0 - FAILED

**Test ID: 1**
Test Title:  
User should be able to book a hotel `with` deposit

Steps To Follow:

1. Open the application in browser (url given above)
2. Fill in the following details
   First Name: `Santosh`  
   Surname: `Marigowda`  
   Price: `100`  
   Deposit: `true`  
   Checkin: `20 May 2020`  
   Checkout: `27 May 2020`
3. Click on SAVE button
4. Form will be cleared, and a new entry should appear on top of the form

**Test ID: 2**
Test Title:  
User should be able to book `more than one hotels` with deposit

Steps To Follow:

1. Follow the steps of Test ID = 1 and try to make 2 bookings.
2. You should be able to successfully make `2 bookings`.
3. Try to use different data

**Test ID: 3**
Test Title:  
User should be able to book a hotel `without` deposit

Steps To Follow:

1. Follow the steps of Test ID 1, but choose the Deposit as `false`
2. You should be able to successfully make the booking.

**Test ID: 4**
Test Title:  
User should be able to book more than one hotel `without` deposit

1. Follow the steps of Test ID 1, choose Deposit as false and make more than one booking.
2. You should be able to make 2 bookings successfully.

**Test ID: 5**
Test Title:  
User should be able to delete bookings

1. Look into the steps for Test ID 1 and make 2 bookings
2. Delete both the bookings
3. You should be able to delete the bookings successfully.