package ciolli.rob.trafficCounter.interfaces;

import java.time.LocalDateTime;
import java.util.Map;

public interface TrafficRepository {
    Map<LocalDateTime, Integer> getTrafficData();
}
