package org.navi.assessment.utility;

import org.navi.assessment.service.strategy.MaxVacantSeatsStrategy;
import org.navi.assessment.service.strategy.PreferredVehicleStrategy;
import org.navi.assessment.service.strategy.RideSelectionStrategy;

import java.util.ArrayList;
import java.util.List;

public class StrategyUtil {
    public static List<RideSelectionStrategy> getStrategyList(){

        List<RideSelectionStrategy> strategyList = new ArrayList<>();
        strategyList.add(new MaxVacantSeatsStrategy());
        strategyList.add(new PreferredVehicleStrategy());

        return strategyList;

    }
}
