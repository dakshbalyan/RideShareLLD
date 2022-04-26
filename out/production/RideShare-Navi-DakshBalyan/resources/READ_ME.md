# Ride Share Design Document

To run the project - 
1. Unzip the RideShare-Navi-DakshBalyan.zip
2. Import the RideShare-Navi-DakshBalyan folder into an IDE of choice (intelliJ Recommended)
3. Run the RideShareApp.java class's main method.

## Features to be Implemented
1. The application allows users to share rides on a route.
2. Users can either offer a shared ride (Driver) or consume a shared
ride (Passenger).
3. Users can search and select one from multiple available rides on a
route with the same source and destination.
4. At a time there will be some active ride offers. 

## Models
1. User - userName (key), name, gender, age , numOfRidesOffered, numOfRidesTaken
2. Vehicle - ownerName (userName in user), vehicleNum (unique), vehicleName
3. Rides - vehicle object, origin, destination, availableSeats, status (active/inactive)
4. RideSelectionRequest - requesterName, origin, destination, numOfSeats, strategy, preferredVehicle

## Design Choices
1. Requests to controller should be valid when sent from RideShareApp class.
2. UserName and vehicle number are the primary keys for our data storing structures. For every method calls the 
parameters that need to be sent are vehicle number and usernames.
3. There can be only unique userNames but duplicate names for users.
4. There can exist non taken rides (active/inactive).
5. A Vehicle can belong to only 1 user.
6. Vehicle before ending ride cannot offer another ride.
7. Users can have multiple vehicles or no vehicles.

## APIs that need to be implemented
### A. User onboarding :
1. "/createUser" : To add user basic details
2. "/addVehicle" : To add user vehicle's details

### B. Offer Ride by User
1. "/offerRide" : Model Ride; attributes - vehicle, origin, destination, available seats

### C. Choosing rides by User (Can only request 1 or 2 seats)
1. "/selectRide" : attributes - source, destination, seats, selectionStrategy
   1. firstSelectionStrategy - by preferred vehicle (Activa/Polo/XUV)
   2. secondSelectionStrategy - by most vacant

### D. If a ride from a vehicle has been offered then it can only be offered again after ending the first ride
1. "/endRide" : ride details

### E. Total ride stats of Users (taken & offered)
1. "/printRideStats"

## Implementation
1. The <mark>RideShareApp.java</mark> is the driver class from where the application will be run and there are a few test cases.
2. To store data we will be only using existing data structures.
3. Exception handling; separation concerns; interface or abstract classes

## Sample Test Cases
1. ```Onboard 5 users``` 
   1. add_user(“Rohan, M, 36”) & add_vehicle(“Rohan, Swift, KA-01-12345)
   2. add_user(“Shashank, M, 29”) & add_vehicle(“Shashank, Baleno, TS-05-62395)
   3. add_user(“Nandini, F, 29)
   4. add_user(“Shipra, F, 27”) & add_vehicle(“Shipra”, Polo,KA-05-41491); add_vehicle(“Shipra, Activa KA-12-12332”)
   5. add_user(“Gaurav, M, 29)
   6. add_user(“Rahul, M, 35); add_vehicle(“Rahul”, “XUV”, KA-05-1234);
2. ```Offer 4 rides by 3 users```
   1. offer_ride(“Rohan, Origin=Hyderabad, Available Seats=1, Vehicle=Swift, KA-01-12345, Destination= Bangalore”)
   2. offer_ride(“Shipra, Origin=Bangalore, Available Seats=1, Vehicle=Activa KA-12-12332, Destination=Mysore”)
   3. offer_ride(“Shipra, Origin=Bangalore, Available Seats=2, Vehicle=Polo, KA-05-41491, Destination=Mysore”)
   4. offer_ride(“Shashank, Origin=Hyderabad, Available Seats=2, Vehicle=Baleno, TS-05-62395, Destination=Bangalore”)
   5. offer_ride(“Rahul, Origin=Hyderabad, Available Seats=5, Vehicle=XUV, KA-05-1234, Destination=Bangalore”)
   6. offer_ride(“Rohan, Origin=Bangalore, Available Seats=1, Vehicle=Swift, KA-01-12345, Destination=Pune”)
      1. This call should fail, since a ride has already been offered by this user for this vehicle
3. ```Find rides for 4 users```
   1. select_ride(“Nandini, Origin=Bangalore, Destination=Mysore, Seats=1, Most Vacant”)
      1. 2(c) is the desired output.
   2. select_ride(“Gaurav, Origin=Bangalore, Destination=Mysore, Seats=1, Preferred Vehicle=Activa”)
      1. 2(b) is the desired output
   3. select_ride(“Shashank, Origin=Mumbai, Destination=Bangalore, Seats=1, Most Vacant”) 
      1. No rides found
   4. select_ride(“Rohan, Origin=Hyderabad, Destination=Bangalore, Seats=1, Preferred Vehicle=Baleno”)
      1. 2(d) is the desired output
   5. select_ride(“Shashank, Origin=Hyderabad, Destination=Bangalore, Seats=1,Preferred Vehicle=Polo”)
      1. No rides found
4. ```End Rides```
   1. end_ride(2-a)
   2. end_ride(2-b)
   3. end_ride(2-c)
   4. end_ride(2-d)
5. ```Find total rides by user```
   1. Rides Taken: Rides that have were taken and have been marked as “ended”
   2. Rides Offered: Rides that were offered and have been marked as “ended”
   <br><mark>Note:</mark> Even if the offered ride was not taken by any user, it counts as an offered ride.
6. print_ride_stats()
   1. Nandini: 1 Taken, 0 Offered
   2. Rohan: 1 Taken, 1 Offered
   3. Shashank: 0 Taken,1 Offered
   4. Gaurav: 1 Taken, 0 Offered
   5. Rahul: 0 Taken, 0 Offered
   6. Shipra: 0 Taken, 2 Offered