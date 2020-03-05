import ciolli.rob.trafficCounter.impl.TrafficCounterUseCasesImpl;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TrafficCounterUseCasesTests {
    /**
     * Total Count Tests
     */
    @Test
    public void emptyMap_TotalCountReturnsZero() {

        Map<LocalDateTime, Integer> data = new HashMap<>();

        TrafficCounterUseCasesImpl tc = new TrafficCounterUseCasesImpl(data);
        Assert.assertEquals(0, (int) tc.getTotalCarCount());
    }

    @Test
    public void mapWithData_TotalCountReturnsCorrectValue() {
        Map<LocalDateTime, Integer> data = new HashMap<>();
        data.put(LocalDateTime.now(), 10);
        data.put(LocalDateTime.now(), 20);

        TrafficCounterUseCasesImpl tc = new TrafficCounterUseCasesImpl(data);
        Assert.assertEquals(30, (int) tc.getTotalCarCount());
    }

    /**
     * Daily Count Tests
     */
    @Test
    public void emptyMap_ReturnsEmptyResultSet() {
        Map<LocalDateTime, Integer> data = new HashMap<>();

        TrafficCounterUseCasesImpl tc = new TrafficCounterUseCasesImpl(data);
        Assert.assertEquals(0, tc.getDailyCarCount().size());
    }

    @Test
    public void dataSetWithOneDate_ReturnsSingleRowWithCorrectCount() {
        Map<LocalDateTime, Integer> data = new HashMap<>();
        LocalDateTime date1 = LocalDateTime.parse("2020-01-01T00:00:00");
        LocalDateTime date2 = LocalDateTime.parse("2020-01-01T00:30:00");
        data.put(date1, 10);
        data.put(date2, 20);

        TrafficCounterUseCasesImpl tc = new TrafficCounterUseCasesImpl(data);
        Assert.assertEquals(30, (int) tc.getDailyCarCount().get(date1.toLocalDate()));
    }

    @Test
    public void dataSetWithMultipleDates_ReturnsMultipleRowsWithCorrectCounts() {
        Map<LocalDateTime, Integer> data = new HashMap<>();
        LocalDateTime date1 = LocalDateTime.parse("2020-01-01T00:00:00");
        LocalDateTime date2 = LocalDateTime.parse("2020-01-01T00:30:00");
        LocalDateTime date3 = LocalDateTime.parse("2020-01-02T00:00:00");
        LocalDateTime date4 = LocalDateTime.parse("2020-01-02T00:30:00");
        LocalDateTime date5 = LocalDateTime.parse("2020-01-03T00:00:00");
        LocalDateTime date6 = LocalDateTime.parse("2020-01-03T00:30:00");

        data.put(date1, 10);
        data.put(date2, 20);
        data.put(date3, 30);
        data.put(date4, 40);
        data.put(date5, 50);
        data.put(date6, 60);

        TrafficCounterUseCasesImpl tc = new TrafficCounterUseCasesImpl(data);
        Assert.assertEquals(30, (int) tc.getDailyCarCount().get(date1.toLocalDate()));
        Assert.assertEquals(70, (int) tc.getDailyCarCount().get(date3.toLocalDate()));
        Assert.assertEquals(110, (int) tc.getDailyCarCount().get(date5.toLocalDate()));
    }

    /**
     * Busiest Periods Tests
     */

    @Test
    public void emptyDataSet_EmptyResultSet() {
        Map<LocalDateTime, Integer> data = new HashMap<>();

        TrafficCounterUseCasesImpl tc = new TrafficCounterUseCasesImpl(data);
        Assert.assertEquals(0, tc.getBusiestPeriod(3).size());
    }

    @Test
    public void dataWith3orLessRecords_AllRecordsReturned() {
        Map<LocalDateTime, Integer> data = new HashMap<>();
        LocalDateTime date1 = LocalDateTime.parse("2020-01-01T00:00:00");
        LocalDateTime date2 = LocalDateTime.parse("2020-01-02T00:30:00");

        data.put(date1, 10);
        data.put(date2, 20);

        TrafficCounterUseCasesImpl tc = new TrafficCounterUseCasesImpl(data);
        Assert.assertEquals(2, tc.getBusiestPeriod(3).size());
        Assert.assertEquals(10, (int) tc.getBusiestPeriod(3).get(date1));
        Assert.assertEquals(20, (int) tc.getBusiestPeriod(3).get(date2));
    }

    @Test
    public void dataSetWithMoreThan3Records_TopThreeRecordsReturned() {
        Map<LocalDateTime, Integer> data = new HashMap<>();
        LocalDateTime date1 = LocalDateTime.parse("2020-01-01T00:00:00");
        LocalDateTime date2 = LocalDateTime.parse("2020-01-02T00:30:00");
        LocalDateTime date3 = LocalDateTime.parse("2020-01-01T01:00:00");
        LocalDateTime date4 = LocalDateTime.parse("2020-01-02T01:30:00");
        LocalDateTime date5 = LocalDateTime.parse("2020-01-01T02:00:00");
        LocalDateTime date6 = LocalDateTime.parse("2020-01-02T02:30:00");

        data.put(date1, 10);
        data.put(date2, 20);
        data.put(date3, 40);
        data.put(date4, 60);
        data.put(date5, 50);
        data.put(date6, 30);

        TrafficCounterUseCasesImpl tc = new TrafficCounterUseCasesImpl(data);
        Assert.assertEquals(3, tc.getBusiestPeriod(3).size());
        Assert.assertEquals(60, (int) tc.getBusiestPeriod(3).get(date4));
        Assert.assertEquals(50, (int) tc.getBusiestPeriod(3).get(date5));
        Assert.assertEquals(40, (int) tc.getBusiestPeriod(3).get(date3));
    }

    // Slowest 1.5 hour period

    @Test
    public void emptyDataset_ReturnsNull() {
        Map<LocalDateTime, Integer> data = new HashMap<>();

        TrafficCounterUseCasesImpl tc = new TrafficCounterUseCasesImpl(data);
        Assert.assertNull(tc.getSlowestPeriod(5400));
    }

    @Test
    public void largeDataSet_ReturnsCorrectDateTime() {
        Map<LocalDateTime, Integer> data = new HashMap<>();
        LocalDateTime date1 = LocalDateTime.parse("2020-01-01T00:00:00");
        LocalDateTime date2 = LocalDateTime.parse("2020-01-01T00:30:00");
        LocalDateTime date3 = LocalDateTime.parse("2020-01-01T01:00:00");
        LocalDateTime date4 = LocalDateTime.parse("2020-01-01T01:30:00");
        LocalDateTime date5 = LocalDateTime.parse("2020-01-01T02:00:00");
        LocalDateTime date6 = LocalDateTime.parse("2020-01-01T02:30:00");

        data.put(date1, 50);
        data.put(date2, 30);
        data.put(date3, 10);
        data.put(date4, 20);
        data.put(date5, 40);
        data.put(date6, 60);

        TrafficCounterUseCasesImpl tc = new TrafficCounterUseCasesImpl(data);
        Assert.assertEquals(date2, tc.getSlowestPeriod(5400));
    }
}
