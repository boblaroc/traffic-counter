package ciolli.rob.trafficCounter.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public interface TrafficCounterUseCases {
    int getTotalCarCount();
    Map<LocalDate, Integer> getDailyCarCount();
    Map<LocalDateTime, Integer> getBusiestPeriod(Integer numberOfRecords);
    LocalDateTime getSlowestPeriod(int periodLengthInSeconds);
}
