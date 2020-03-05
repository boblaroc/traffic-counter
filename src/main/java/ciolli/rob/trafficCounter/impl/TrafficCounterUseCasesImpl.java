package ciolli.rob.trafficCounter.impl;

import ciolli.rob.trafficCounter.interfaces.TrafficCounterUseCases;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TrafficCounterUseCasesImpl implements TrafficCounterUseCases {
    final Map<LocalDateTime, Integer> mData;

    public TrafficCounterUseCasesImpl(Map<LocalDateTime, Integer> data) {
        mData = data != null ? data : new HashMap<>();
    }

    @Override
    public int getTotalCarCount() {
        return mData.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public Map<LocalDate, Integer> getDailyCarCount() {

        Map<LocalDate, Integer> result = new HashMap<>();

        mData.forEach((key, value) -> {
            LocalDate d = key.toLocalDate();
            result.put(d, (result.getOrDefault(d, 0)) + value);
        });

        return result.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    @Override
    public Map<LocalDateTime, Integer> getBusiestPeriod(Integer numberOfRecords) {
        return mData.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(numberOfRecords)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    @Override
    public LocalDateTime getSlowestPeriod(int periodLengthInSeconds) {

        return mData.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> mData.entrySet().stream()
                        .filter(ent -> {
                            long duration = Duration.between(e.getKey(), ent.getKey()).getSeconds();
                            return duration >= 0 && duration < periodLengthInSeconds;
                        })
                        .mapToInt(Map.Entry::getValue)
                        .sum()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new))
                .entrySet().stream().findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
