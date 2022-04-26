package org.navi.assessment;

import org.navi.assessment.controller.RideController;
import org.navi.assessment.controller.UserController;
import org.navi.assessment.controller.VehicleController;
import org.navi.assessment.repository.RideRepository;
import org.navi.assessment.repository.UserRepository;
import org.navi.assessment.repository.VehicleRepository;
import org.navi.assessment.service.RideService;
import org.navi.assessment.service.UserService;
import org.navi.assessment.service.VehicleService;
import org.navi.assessment.service.strategy.RideSelectionStrategy;
import org.navi.assessment.utility.StrategyUtil;

import java.util.List;

/*
Initialising all object that are needed
List of objects that are needed :
1. Controllers ->
    Ride controller dependent on Ride service object
    User Controller dependent on User service object
    Vehicle Controller dependent on Vehicle Service object
2. Services ->
    Ride Services needs ride repo, user service, vehicle service, list of strategies
    User service needs user repo
    Vehicle service needs vehicle repo, user service
3. Repositories ->
    Ride, user, vehicle repos are not dependent on any layer
 */
public class ApplicationContext {

    private final List<RideSelectionStrategy> strategyList;

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    private final UserService userService;
    private final VehicleService vehicleService;
    private final RideService rideService;

    private final RideController rideController;
    private final UserController userController;
    private final VehicleController vehicleController;

    public ApplicationContext(){
        this.strategyList = StrategyUtil.getStrategyList();

        this.rideRepository = new RideRepository();
        this.userRepository = new UserRepository();
        this.vehicleRepository = new VehicleRepository();

        this.userService = new UserService(this.userRepository);
        this.vehicleService = new VehicleService(this.vehicleRepository, this.userService);
        this.rideService = new RideService(this.rideRepository, this.userService, this.vehicleService, this.strategyList);

        this.rideController = new RideController(this.rideService);
        this.userController = new UserController(this.userService);
        this.vehicleController = new VehicleController(this.vehicleService);

    }

    public List<RideSelectionStrategy> getStrategyList() {
        return strategyList;
    }

    public RideRepository getRideRepository() {
        return rideRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    public UserService getUserService() {
        return userService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public RideService getRideService() {
        return rideService;
    }

    public RideController getRideController() {
        return rideController;
    }

    public UserController getUserController() {
        return userController;
    }

    public VehicleController getVehicleController() {
        return vehicleController;
    }
}
