package org.navi.assessment.service;

import org.navi.assessment.enums.RideStatus;
import org.navi.assessment.enums.VehicleStatus;
import org.navi.assessment.exceptions.BadRequestException;
import org.navi.assessment.exceptions.ResourceExistenceException;
import org.navi.assessment.models.Ride;
import org.navi.assessment.models.RideSelectionRequest;
import org.navi.assessment.models.User;
import org.navi.assessment.models.Vehicle;
import org.navi.assessment.repository.RideRepository;
import org.navi.assessment.service.strategy.RideSelectionStrategy;
import org.navi.assessment.utility.RideUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RideService {
    private final RideRepository rideRepository;
    private final UserService userService;
    private final VehicleService vehicleService;
    private final StrategyExecutor strategyExecutor;

    public RideService(RideRepository rideRepository, UserService userService, VehicleService vehicleService,
                       List<RideSelectionStrategy> strategies) {
        this.rideRepository = rideRepository;
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.strategyExecutor = new StrategyExecutor(strategies);
    }

    public void createRide(String vehicleOwner, String origin, int availableSeats, String vehicleName,
                           String vehicleNum, String destination) {

            validateUser(vehicleOwner);
            validateVehicle(vehicleNum);
            validateVehicleOwnerMapping(vehicleOwner, vehicleNum);

            Vehicle vehicle = vehicleService.getVehicleByVehicleNum(vehicleNum);
            User user = userService.getUserByUserName(vehicleOwner);

            if (vehicle.getStatus().equals(VehicleStatus.AVAILABLE)){
                Ride ride = new Ride(vehicle, origin, destination, availableSeats);
                vehicle.setStatus(VehicleStatus.BUSY);
                rideRepository.save(ride);
                user.incrementRidesOffered();
            } else {
                throw new BadRequestException("Ride has not ended yet for "+vehicleNum+" !");
            }

    }


    public void endRide(String vehicleNum) {
        try {
            validateVehicle(vehicleNum);
            Vehicle vehicle = vehicleService.getVehicleByVehicleNum(vehicleNum);
            if (vehicle.getStatus().equals(VehicleStatus.AVAILABLE))
                throw new BadRequestException("No ride exists for vehicle : "+vehicleNum);

            Ride ride = rideRepository.findRideByVehicleNum(vehicleNum);

            vehicle.setStatus(VehicleStatus.AVAILABLE);
            ride.endRide();

        } catch ( ResourceExistenceException | BadRequestException e ){
            System.err.println(e.getMessage());
        }
    }

    public Optional<Ride> selectRide(RideSelectionRequest rideRequest) {
        List<Ride> applicableRideList = RideUtils.filterRidesByRequest(rideRequest.getOrigin(), rideRequest.getDestination(),
                rideRequest.getNumOfSeats(), getAllActiveRides());
        Optional<Ride> selectedRide = strategyExecutor.execute(rideRequest, applicableRideList);

        if (selectedRide.isPresent()){
            selectedRide.get().decrementAvailableSeatsBy(rideRequest.getNumOfSeats());

            User passenger = userService.getUserByUserName(rideRequest.getRequesterName());

            passenger.incrementRidesTaken();

        }

        return selectedRide;
    }

    private void validateUser(String vehicleOwner){
        if (!userService.checkUserExists(vehicleOwner)) {
            throw new ResourceExistenceException("User : " + vehicleOwner + " doesn't exist!");
        }
    }
    private void validateVehicle(String vehicleNum){
        if (!vehicleService.checkVehicleExists(vehicleNum)) {
            throw new ResourceExistenceException("Vehicle : " + vehicleNum + " doesn't exist!");
        }
    }
    private void validateVehicleOwnerMapping(String vehicleOwner, String vehicleNum){
        if (!vehicleService.checkVehicleOwner(vehicleOwner, vehicleNum)) {
            throw new BadRequestException("Vehicle and owner mismatch!");
        }
    }

    public List<Ride> getAllActiveRides(){
        return rideRepository.getAllAvailableRides()
                .stream()
                .filter(ride -> ride.getStatus().equals(RideStatus.ACTIVE))
                .collect(Collectors.toList());
    }
}
