/*
 * Copyright 2015 ChartistJSF.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chartistjsf.model.chart;

import java.util.*;

/**
 * @author Hatem Alimam
 * @since 0.1
 */
public class CartesianChartModel extends ChartModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6182574506516748699L;

	private List<ChartSeries> series;
	protected Map<AxisType, Axis> axes;
	private int high;
	private int low;
	private boolean showGridBackground = false;

	public CartesianChartModel() {
		series = new ArrayList<>();
		createAxes();
	}

	protected CartesianChartModel(CartesianChartModel cartesianChartModel) {
		super(cartesianChartModel);
		this.series = cartesianChartModel.series;
		this.axes = cartesianChartModel.axes;
		this.high = cartesianChartModel.high;
		this.low = cartesianChartModel.low;
		this.showGridBackground = cartesianChartModel.showGridBackground;
	}

	/**
	 * Creates the default Axes for CartesianChartModel
	 * 
	 */
	protected void createAxes() {
		axes = new HashMap<AxisType, Axis>();
		axes.put(AxisType.X, new LinearAxis());
		axes.put(AxisType.Y, new LinearAxis());
	}

	/**
	 * Returns the current Series
	 * 
	 * @return List<ChartSeries>
	 */
	public List<ChartSeries> getSeries() {
		return series;
	}

	/**
	 * Adds a series
	 * 
	 * @param {@link ChartSeries}
	 */
	public void addSeries(ChartSeries chartSeries) {
		this.series.add(chartSeries);
	}

	/**
	 * Clears the added series
	 * 
	 */
	public void clear() {
		this.series.clear();
	}

	/**
	 * Returns the current Axes
	 * 
	 * @return Map<AxisType, Axis>
	 */
	public Map<AxisType, Axis> getAxes() {
		return axes;
	}

	/**
	 * Returns the appropriate Axis based on the supplied type
	 * 
	 * @param {@link AxisType}
	 * @return the {@link Axis}
	 */
	public Axis getAxis(AxisType type) {
		return axes.get(type);
	}

	/**
	 * Overriding the natural high of the chart allows you to zoom in or limit the charts highest displayed value
	 * 
	 * @return the high
	 */
	public int getHigh() {
		return high;
	}

	/**
	 * Overriding the natural high of the chart allows you to zoom in or limit the charts highest displayed value
	 * 
	 * @param high
	 *            the high to set
	 */
	public void setHigh(int high) {
		this.high = high;
	}

	/**
	 * Overriding the natural low of the chart allows you to zoom in or limit the charts lowest displayed value
	 * 
	 * @return the low
	 */
	public int getLow() {
		return low;
	}

	/**
	 * Overriding the natural low of the chart allows you to zoom in or limit the charts lowest displayed value
	 * 
	 * @param low
	 *            the low to set
	 */
	public void setLow(int low) {
		this.low = low;
	}

	/**
	 * add a background fill to the .ct-grids group
	 * 
	 * @return the showGridBackground
	 */
	public boolean isShowGridBackground() {
		return showGridBackground;
	}

	/**
	 * add a background fill to the .ct-grids group
	 * 
	 * @param showGridBackground
	 *            the showGridBackground to set
	 */
	public void setShowGridBackground(boolean showGridBackground) {
		this.showGridBackground = showGridBackground;
	}

	public void updateYAxisRangeByChartSeries() {
		Number high = 0;
		Number low = 0;
		for (ChartSeries chartSeries : series) {
			high = chartSeries.getHigh().doubleValue() > high.doubleValue() ? chartSeries.getHigh() : high;
			low = chartSeries.getLow().doubleValue() < low.doubleValue() ? chartSeries.getLow() : low;
		}
		Axis yAxis = getAxis(AxisType.Y);
		if(high.doubleValue() > 0) {
			yAxis.setHigh(high.doubleValue() * 1.1);
		} else if(high.doubleValue() == 0) {
			yAxis.setHigh(1.0);
		} else {
			yAxis.setHigh(high.doubleValue() * 0.9);
		}

		if(low.doubleValue() > 0) {
			yAxis.setLow(low.doubleValue() * 0.9);
		} else if(low.doubleValue() == 0) {
			yAxis.setLow(-1.0);
		} else {
			yAxis.setLow(low.doubleValue() * 1.1);
		}
	}
}
