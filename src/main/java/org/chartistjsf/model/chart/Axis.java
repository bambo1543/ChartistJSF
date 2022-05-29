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
import java.io.Serializable;

import jakarta.faces.context.ResponseWriter;

/**
 * @author Hatem Alimam
 * @since 0.1
 */
public abstract class Axis implements Serializable {

	public enum Type {

		AUTO_SCALE_AXIS("Chartist.AutoScaleAxis"), FIXED_SCALE_AXIS("Chartist.FixedScaleAxis"), STEP_AXIS("Chartist.StepAxis");

		private String name;

		Type(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6059542550827836357L;

	private int offset = 30;
	private AxisPosition axisPosition;
	private int xLabelOffset = 0;
	private int yLabelOffset = 0;
	private boolean showLabel = true;
	private boolean showGrid = true;

	private int roundDigits = -1;
	private String dateFormat;
	private String labelInterpolationFnc;
	private int scaleMinSpace = 20;

	private Type type = Type.AUTO_SCALE_AXIS;

	private int divisor = 5;

	private Double high;
	private Double low;

	/**
	 * The offset of the labels to the chart area
	 * 
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * The offset of the labels to the chart area
	 * 
	 * @param offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	/**
	 * Position where labels are placed.
	 * Can be set to AxisPosition.START or AxisPosition.END where `start` is equivalent to left or top on vertical axis and `end` is equivalent to right or bottom on horizontal axis.
	 * @return the axisPosition
	 */
	public AxisPosition getAxisPosition() {
		return axisPosition;
	}

	/**
	 * Position where labels are placed.
	 * Can be set to AxisPosition.START or AxisPosition.END where `start` is equivalent to left or top on vertical axis and `end` is equivalent to right or bottom on horizontal axis.
	 * @param axisPosition the axisPosition to set
	 */
	public void setAxisPosition(AxisPosition axisPosition) {
		this.axisPosition = axisPosition;
	}

	/**
	 * Allows to correct label positioning on this axis by positive or negative
	 * x offset.
	 * 
	 * @return the xLabelOffset
	 */
	public int getXLabelOffset() {
		return xLabelOffset;
	}

	/**
	 * Allows to correct label positioning on this axis by positive or negative
	 * x offset.
	 * 
	 * @param xLabelOffset
	 */
	public void setXLabelOffset(int xLabelOffset) {
		this.xLabelOffset = xLabelOffset;
	}

	/**
	 * Allows to correct label positioning on this axis by positive or negative
	 * y offset.
	 * 
	 * @return the yLabelOffset
	 */
	public int getYLabelOffset() {
		return yLabelOffset;
	}

	/**
	 * Allows to correct label positioning on this axis by positive or negative
	 * y offset.
	 * 
	 * @param yLabelOffset
	 */
	public void setYLabelOffset(int yLabelOffset) {
		this.yLabelOffset = yLabelOffset;
	}

	/**
	 * If labels should be shown or not
	 * 
	 * @return the showLabel
	 */
	public boolean getShowLabel() {
		return showLabel;
	}

	/**
	 * If labels should be shown or not
	 * 
	 * @param showLabel
	 *            the showLabel to set
	 */
	public void setShowLabel(boolean showLabel) {
		this.showLabel = showLabel;
	}

	/**
	 * If the axis grid should be drawn or not
	 * 
	 * @return the showGrid
	 */
	public boolean getShowGrid() {
		return showGrid;
	}

	/**
	 * If the axis grid should be drawn or not
	 * 
	 * @param showGrid
	 *            the showGrid to set
	 */
	public void setShowGrid(boolean showGrid) {
		this.showGrid = showGrid;
	}
	
	

	/**
	 * Interpolation function that allows you to intercept the value from the
	 * axis label
	 * 
	 * @return the labelInterpolationFnc
	 */
	public String getLabelInterpolationFnc() {
		return labelInterpolationFnc;
	}

	/**
	 * Interpolation function that allows you to intercept the value from the
	 * axis label
	 * 
	 * @param labelInterpolationFnc
	 *            the labelInterpolationFnc to set
	 */
	public void setLabelInterpolationFnc(String labelInterpolationFnc) {
		this.labelInterpolationFnc = labelInterpolationFnc;
	}

	/**
	 * This value specifies the minimum height in pixel of the scale steps
	 * 
	 * @return the scaleMinSpace
	 */
	public int getScaleMinSpace() {
		return scaleMinSpace;
	}

	/**
	 * This value specifies the minimum height in pixel of the scale steps
	 * 
	 * @param scaleMinSpace
	 *            the scaleMinSpace to set
	 */
	public void setScaleMinSpace(int scaleMinSpace) {
		this.scaleMinSpace = scaleMinSpace;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getDivisor() {
		return divisor;
	}

	public void setDivisor(int divisor) {
		this.divisor = divisor;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public int getRoundDigits() {
		return roundDigits;
	}

	public void setRoundDigits(int roundDigits) {
		this.roundDigits = roundDigits;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public abstract void render(ResponseWriter writer, AxisType axisType) throws IOException;

}
