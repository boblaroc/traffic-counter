package ciolli.rob.trafficCounter;

import ciolli.rob.trafficCounter.impl.TrafficCounterUseCasesImpl;
import ciolli.rob.trafficCounter.interfaces.TrafficCounterUseCases;
import ciolli.rob.trafficCounter.interfaces.TrafficRepository;

public class App {

    final TrafficCounterUseCases mTrafficCounterUseCases;

    public App(TrafficRepository repo) {
        mTrafficCounterUseCases = new TrafficCounterUseCasesImpl(repo.getTrafficData());
    }

    public void run() {
        displayTotalCarCount();
        displayDailyCarCount();
        displayBusiestPeriod();
        displaySlowestPeriod();
    }

    private void displayTotalCarCount() {
        System.out.println("\nTotal number of cars observed:");
        System.out.println(mTrafficCounterUseCases.getTotalCarCount());
    }

    private void displayDailyCarCount() {
        System.out.println("\nDaily Car Count:");
        mTrafficCounterUseCases.getDailyCarCount().forEach((date, count) ->
                System.out.println(date.toString() + " " + count));
    }

    private void displayBusiestPeriod() {
        System.out.println("\nBusiest periods observed:");
        mTrafficCounterUseCases.getBusiestPeriod(3).forEach((datetime, count) ->
                System.out.println(datetime.toString() + " " + count));
    }

    private void displaySlowestPeriod() {
        System.out.println("\n1.5 hour period least cars observed starting:");
        System.out.println(mTrafficCounterUseCases.getSlowestPeriod(5400));
    }
}
