# Traffic Counter
#### Solution By Rob Ciolli

This application is a solution to the problem specified in the document `AIPS_Coding_Challenge_Backend Aug 2019.pdf`

It is a simple command line application that accepts a file name as an argument.

## Prerequisites
This application requires Java 1.8 or above to be installed.
Gradle is used for build tool
 
## Build

To build the jar file execute following command in root directory

```./gradlew clean assemble```

## Test

Tests can be executed with the following command

```./gradlew test```
## Run

Once built - the app can be executed with the following command:

```java -jar build/libs/seek-traffic-counter-1.0-SNAPSHOT.jar <Taffic Data File>```

a sample data file is included and can used as such:

```java -jar build/libs/seek-traffic-counter-1.0-SNAPSHOT.jar traffic-data.txt```