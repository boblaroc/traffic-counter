package ciolli.rob.trafficCounter.impl;

import ciolli.rob.trafficCounter.interfaces.TrafficRepository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TrafficRepositoryImpl
 *
 * Implementation of TrafficRepository that returns data from provided
 * text file.
 */
public class TrafficRepositoryImpl implements TrafficRepository {

    final String mDataPath;

    public TrafficRepositoryImpl(String dataPath) {
        mDataPath = dataPath;
    }

    @Override
    public Map<LocalDateTime, Integer> getTrafficData() {

        try (Stream<String> stream = Files.lines(Paths.get(mDataPath))) {
            return stream
                    .map(x -> x.split(" "))
                    .collect(Collectors.toMap(
                            x -> LocalDateTime.parse(x[0]),
                            x -> Integer.parseInt(x[1])));

        } catch (Exception e) {
            System.out.println("Error reading from file");
            e.printStackTrace();
            return null;
        }
    }
}

