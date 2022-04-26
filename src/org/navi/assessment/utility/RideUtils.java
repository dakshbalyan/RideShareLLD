package org.navi.assessment.utility;

import org.navi.assessment.models.Ride;

import java.util.List;
import java.util.stream.Collectors;

public class RideUtils {

    public static List<Ride> filterRidesByRequest(String origin, String destination, int requestedSeats, List<Ride> activeRides){
        return activeRides.stream()
                .filter(ride ->
                        ride.getOrigin().equals(origin) && ride.getDestination().equals(destination)
                                && ride.getAvailableSeats() >= requestedSeats)
                .collect(Collectors.toList());
    }

}
