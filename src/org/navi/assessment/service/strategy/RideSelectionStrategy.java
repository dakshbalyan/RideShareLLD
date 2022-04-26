package org.navi.assessment.service.strategy;

import org.navi.assessment.models.Ride;
import org.navi.assessment.models.RideSelectionRequest;

import java.util.List;
import java.util.Optional;

public interface RideSelectionStrategy {
    public Optional<Ride> findRideAccordingToStrategy(RideSelectionRequest rideRequest, List<Ride> applicableRides);
    public boolean isStrategyApplicable(String strategy);

//    protected List<Ride> filterRidesByRequestBonus(String origin, String destination, List<Ride> activeRides){
//        return activeRides.stream()
//                .filter(ride ->
//                        ride.getOrigin().equals(origin) || ride.getDestination().equals(destination))
//                .collect(Collectors.toList());
//    }
}
