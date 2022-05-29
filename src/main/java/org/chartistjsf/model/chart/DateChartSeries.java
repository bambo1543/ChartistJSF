package org.chartistjsf.model.chart;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateChartSeries extends ChartSeries<Map<Date, Number>> {

    public DateChartSeries() {
        super(new HashMap<>());
    }

    public void put(Date date, Number number) {
        data.put(date, number);
    }

}
