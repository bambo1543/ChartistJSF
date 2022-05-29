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

import java.io.IOException;

import jakarta.faces.context.ResponseWriter;

/**
 * @author Hatem Alimam
 * @since 0.1
 */
public class LinearAxis extends Axis {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1502211061227399235L;

	public LinearAxis() {
		setOffset(30);
	}

	public String getRenderer() {
		return "LinearAxisRenderer";
	}

	@Override
	public void render(ResponseWriter writer, AxisType axisType) throws IOException {
		writer.write(axisType + ":{");

		writer.write("offset:" + this.getOffset());

		writer.write(", labelOffset: {");
		writer.write("x:" + getXLabelOffset());
		writer.write(",y:" + getYLabelOffset());
		writer.write("}");

		writer.write(", showLabel: " + this.getShowLabel());
		writer.write(", showGrid: " + this.getShowGrid());
		writer.write(", divisor: " + this.getDivisor());
		writer.write(", type: " + this.getType().getName());

		if (this.getAxisPosition() != null) {
			writer.write(", position: '" + this.getAxisPosition() + "'");
		}

		if(getRoundDigits() > -1) {
			double multiplier = Math.pow(10, getRoundDigits());
			writer.write(", labelInterpolationFnc: " + "function (value) {return Math.round(value* "+multiplier+") /"+multiplier+";}");
		} else if (getDateFormat() != null && !getDateFormat().equals("")) {
			writer.write(", labelInterpolationFnc: " + "function(value) {return moment(value).format('" + getDateFormat() + "');}");
		} else if (getLabelInterpolationFnc() != null && !getLabelInterpolationFnc().equals("")) {
			writer.write(", labelInterpolationFnc: " + getLabelInterpolationFnc());
		}

		if (axisType.equals(AxisType.Y)) {
			writer.write(", scaleMinSpace: " + getScaleMinSpace());
		}

		if(getHigh() != null) {
			writer.write(", high: " + getHigh());
		}
		if(getLow() != null) {
			writer.write(", low: " + getLow());
		}

		writer.write("}");
	}
}
