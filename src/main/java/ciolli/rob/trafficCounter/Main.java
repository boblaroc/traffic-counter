package ciolli.rob.trafficCounter;

import ciolli.rob.trafficCounter.impl.TrafficRepositoryImpl;

public class Main {

    public static void main(String[] args) {
        System.out.println("Traffic Counter");
        System.out.println("---------------");

        if (args.length == 0) {
            System.out.println("Please provide data file path");
            return;
        }

        TrafficRepositoryImpl repo = new TrafficRepositoryImpl(args[0]);
        App app = new App(repo);
        app.run();
    }
}
