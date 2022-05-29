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

import java.io.Serializable;
import java.util.*;

/**
 * @author Hatem Alimam
 * @since 0.1
 */
public class ChartSeries<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6344218439648560381L;

	private String name;

	protected T data;

	private AxisType xaxis;

	private AxisType yaxis;

	public ChartSeries(T data) {
		this.data = data;
	}

	public ChartSeries(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public AxisType getXaxis() {
		return xaxis;
	}

	public void setXaxis(AxisType xaxis) {
		this.xaxis = xaxis;
	}

	public AxisType getYaxis() {
		return yaxis;
	}

	public void setYaxis(AxisType yaxis) {
		this.yaxis = yaxis;
	}

}
