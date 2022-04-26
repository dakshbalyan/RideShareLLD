package org.navi.assessment.service.strategy;

import org.navi.assessment.models.Ride;
import org.navi.assessment.models.RideSelectionRequest;

import java.util.List;
import java.util.Optional;

public class PreferredVehicleStrategy implements RideSelectionStrategy {

    private static final String PREFERRED_VEHICLE_STRATEGY = "preferred vehicle";

    @Override
    public Optional<Ride> findRideAccordingToStrategy(RideSelectionRequest rideRequest, List<Ride> applicableRides) {
        String preferredVehicle = rideRequest.getPreferredVehicle();

        return applicableRides.stream()
                .filter(ride -> ride.getVehicle().getVehicleName().equals(preferredVehicle))
                .findFirst();

    }

    @Override
    public boolean isStrategyApplicable(String strategy) {
        strategy = strategy.toLowerCase();

        return strategy.equals(PREFERRED_VEHICLE_STRATEGY);
    }


}
