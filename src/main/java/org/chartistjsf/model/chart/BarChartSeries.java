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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hatem Alimam
 * @since 0.1
 */
public class BarChartSeries extends ChartSeries<List<Number>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6036024411116468354L;

	public BarChartSeries() {
		super(new ArrayList<>());
	}

	public void set(Number number) {
		data.add(number);
	}

}
