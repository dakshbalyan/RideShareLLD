package org.navi.assessment;

import org.navi.assessment.controller.RideController;
import org.navi.assessment.controller.UserController;
import org.navi.assessment.controller.VehicleController;
import org.navi.assessment.models.RideSelectionRequest;
import org.navi.assessment.repository.RideRepository;
import org.navi.assessment.repository.UserRepository;
import org.navi.assessment.repository.VehicleRepository;
import org.navi.assessment.service.RideService;
import org.navi.assessment.service.UserService;
import org.navi.assessment.service.VehicleService;

/*
Application Flow
1. Main driver calls controller layer where requests are sent
2. From controller requests are sent to service layer to execute business logic and return response
3. Service layer only has access to the repository where it fetches the elem it needs form the in memory db
4. ApplicationContext usually is responsible for making objects that are needed by the main app

 */
public class RideShareApp {

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    private final RideService rideService;
    private final UserService userService;
    private final VehicleService vehicleService;

    private RideController rideController;
    private UserController userController;
    private VehicleController vehicleController;


    public RideShareApp(ApplicationContext applicationContext) {
        this.rideRepository = applicationContext.getRideRepository();
        this.userRepository = applicationContext.getUserRepository();
        this.vehicleRepository = applicationContext.getVehicleRepository();

        this.rideService = applicationContext.getRideService();
        this.userService = applicationContext.getUserService();
        this.vehicleService = applicationContext.getVehicleService();

        this.rideController = applicationContext.getRideController();
        this.userController = applicationContext.getUserController();
        this.vehicleController = applicationContext.getVehicleController();
    }

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ApplicationContext();
        RideShareApp rideShareApp = new RideShareApp(applicationContext);

        rideShareApp.addUsers();
        rideShareApp.addVehicleForUser();
        rideShareApp.offerRides();
        rideShareApp.selectRides();
        rideShareApp.endRides();
        rideShareApp.printStats();
    }
    /*
Adding user test
1. add_user(“Rohan, M, 36”) & add_vehicle(“Rohan, Swift, KA-01-12345)
2. add_user(“Shashank, M, 29”) & add_vehicle(“Shashank, Baleno, TS-05-62395)
3. add_user(“Nandini, F, 29)
4. add_user(“Shipra, F, 27”) & add_vehicle(“Shipra”, Polo,KA-05-41491); add_vehicle(“Shipra, Activa KA-12-12332”)
5. add_user(“Gaurav, M, 29)
6. add_user(“Rahul, M, 35); add_vehicle(“Rahul”, “XUV”, KA-05-1234);

*/
    private void addUsers() {
        userController.createUser("rohan1", "Rohan", 36, "MALE");
        userController.createUser("shashank1", "Shashank", 29, "MALE");
        userController.createUser("nandini1", "Nandini", 29, "FEMALE");
        userController.createUser("shipra1", "Shipra", 27, "FEMALE");
        userController.createUser("gaurav1", "Gaurav", 29, "FEMALE");
        userController.createUser("rahul1", "Rahul", 35, "MALE");
    }

    private void addVehicleForUser() {
        vehicleController.addVehicle("rohan1", "KA-01-12345", "Swift");
        vehicleController.addVehicle("shashank1", "TS-05-62395", "Baleno");
        vehicleController.addVehicle("shipra1", "KA-05-41491", "Polo");
        vehicleController.addVehicle("shipra1", "KA-12-12332", "Activa");
        vehicleController.addVehicle("rahul1", "KA-05-1234", "XUV");
    }

    /*
   Offer Ride
   1. offer_ride(“Rohan, Origin=Hyderabad, Available Seats=1, Vehicle=Swift, KA-01-12345, Destination= Bangalore”)
   2. offer_ride(“Shipra, Origin=Bangalore, Available Seats=1, Vehicle=Activa KA-12-12332, Destination=Mysore”)
   3. offer_ride(“Shipra, Origin=Bangalore, Available Seats=2, Vehicle=Polo, KA-05-41491, Destination=Mysore”)
   4. offer_ride(“Shashank, Origin=Hyderabad, Available Seats=2, Vehicle=Baleno, TS-05-62395, Destination=Bangalore”)
   5. offer_ride(“Rahul, Origin=Hyderabad, Available Seats=5, Vehicle=XUV, KA-05-1234, Destination=Bangalore”)
   6. offer_ride(“Rohan, Origin=Bangalore, Available Seats=1, Vehicle=Swift, KA-01-12345, Destination=Pune”) should fail

    */

    private void offerRides() {
        rideController.offerRide("rohan1","Hyderabad",1,"Swift", "KA-01-12345", "Bangalore");
        rideController.offerRide("shipra1","Bangalore",1,"Activa", "KA-12-12332", "Mysore");
        rideController.offerRide("shipra1","Bangalore",2,"Polo", "KA-05-41491", "Mysore");
        rideController.offerRide("shashank1","Hyderabad",2,"Polo", "TS-05-62395", "Bangalore");
        rideController.offerRide("rahul1","Hyderabad",5,"XUV", "KA-05-1234", "Bangalore");
        rideController.endRide("KA-01-12345");
        rideController.offerRide("rohan1","Bangalore",1,"Swift", "KA-01-12345", "Pune");

    }

    /*
    Select Ride
    1. select_ride(“Nandini, Origin=Bangalore, Destination=Mysore, Seats=1, Most Vacant”) return ride
    2. select_ride(“Gaurav, Origin=Bangalore, Destination=Mysore, Seats=1, Preferred Vehicle=Activa”) return ride
    3. select_ride(“Shashank, Origin=Mumbai, Destination=Bangalore, Seats=1, Most Vacant”) return no ride
    4. select_ride(“Rohan, Origin=Hyderabad, Destination=Bangalore, Seats=1, Preferred Vehicle=Baleno”) return ride
    5. select_ride(“Shashank, Origin=Hyderabad, Destination=Bangalore, Seats=1,Preferred Vehicle=Polo”) return no ride
     */

    private void selectRides() {
        rideController.selectRide(new RideSelectionRequest("nandini1", "Bangalore", "Mysore", 1, "Most Vacant", null));
        rideController.selectRide(new RideSelectionRequest("gaurav1", "Bangalore", "Mysore", 1, "Preferred Vehicle", "Activa"));
        rideController.selectRide(new RideSelectionRequest("shashank1", "Mumbai", "Bangalore", 1, "Most Vacant", null));
        rideController.selectRide(new RideSelectionRequest("rohan1", "Hyderabad", "Bangalore", 1, "Preferred Vehicle", "Baleno"));
        rideController.selectRide(new RideSelectionRequest("nandini1", "Hyderabad", "Bangalore", 1, "Preferred Vehicle", "Polo"));
        rideController.selectRide(new RideSelectionRequest("nandini1", "Hyderabad", "Bangalore", 1, "Preferred Vehicle", "Polo"));
        rideController.selectRide(new RideSelectionRequest("shashank1", "Bangalore", "Pune", 1, "Preferred Vehicle", "Swift"));
        rideController.selectRide(new RideSelectionRequest("shashank1", "Hyderabad", "Bangalore", 1, "Preferred Vehicle", "XUV"));
    }

    /*
    Ending rides
    1. end_ride(2-a)
    2. end_ride(2-b)
    3. end_ride(2-c)
    4. end_ride(2-d)
     */

    private void endRides(){
        rideController.endRide("KA-01-12345");
        rideController.endRide("KA-12-12332");
        rideController.endRide("KA-05-41491");
        rideController.endRide("TS-05-62395");
    }
    /*
    print_ride_stats()
     */

    private void printStats(){
        userController.printUserRideStats();
    }
}
