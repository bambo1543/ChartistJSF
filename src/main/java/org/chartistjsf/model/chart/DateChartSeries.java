package org.chartistjsf.model.chart;

import java.util.*;

public class DateChartSeries extends ChartSeries<Map<Date, Number>> {

    public DateChartSeries() {
        super(new HashMap<>());
    }

    public void put(Date date, Number number) {
        data.put(date, number);
    }

    public Number getHigh() {
        return Collections.max(data.values(), Comparator.comparingDouble(Number::doubleValue));
    }
    public Number getLow() {
        return Collections.min(data.values(), Comparator.comparingDouble(Number::doubleValue));
    }

}
