package org.chartistjsf.component.chart.renderer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;

import org.chartistjsf.component.chart.Chart;
import org.chartistjsf.model.chart.Axis;
import org.chartistjsf.model.chart.AxisType;
import org.chartistjsf.model.chart.ChartSeries;
import org.chartistjsf.model.chart.LineChartModel;
import org.primefaces.util.EscapeUtils;

public class LineRenderer extends BaseChartistRenderer {

	private static final Logger logger = Logger.getLogger(LineRenderer.class.getName());

	@Override
	protected void encodeData(FacesContext context, Chart chart) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		LineChartModel model = (LineChartModel) chart.getModel();

//		if (model.getLabels().isEmpty()) {
//			logger.log(Level.SEVERE,
//					"Make sure to set the required lables for LineChart, otherwise the chart won't render");
//			return;
//		}
		writer.write(",data:{");
		if (!model.getLabels().isEmpty()) {
			writer.write("labels: [");

			for (Iterator<Object> labelsItr = model.getLabels().iterator(); labelsItr.hasNext(); ) {
				Object label = labelsItr.next();

				if (label instanceof String)
					writer.write("\"" + EscapeUtils.forJavaScript(label.toString()) + "\"");
				else
					writer.write(label != null ? label.toString() : "");

				if (labelsItr.hasNext()) {
					writer.write(",");
				}
			}
			writer.write("], ");
		}

		writer.write("series:[");
		for (Iterator<ChartSeries> it = model.getSeries().iterator(); it.hasNext();) {
			ChartSeries series = it.next();
			writer.write("{");
			writer.write("name:\"" + EscapeUtils.forJavaScript(series.getName()) + "\"");
			writer.write(", data:[");
			if(series.getData() instanceof List<?>) {
				List<Number> data = (List<Number>) series.getData();
				for (Iterator<Number> numbersIter = data.iterator(); numbersIter.hasNext();) {
					Number number = numbersIter.next();
					String numberAsString = (number != null) ? number.toString() : "null";

					writer.write(numberAsString);

					if (numbersIter.hasNext()) {
						writer.write(",");
					}
				}
			} else if(series.getData() instanceof Map<?,?>) {
				Map<?,?> data = (Map<?,?>) series.getData();
				List sortedKeys = new ArrayList(data.keySet());
				Collections.sort(sortedKeys);

				for (Iterator<Object> keyIter = sortedKeys.iterator(); keyIter.hasNext();) {
					Object key = keyIter.next();
					if(key instanceof Date) {
						Date date = (Date) key;
						Number number = (Number) data.get(key);
						String numberAsString = (number != null) ? number.toString() : "null";

//						{x: new Date(143134652600), y: 53},
						writer.write("{x: new Date(" + date.getTime() + "), y: " + numberAsString + "}");
//						writer.write("{x: \"" + new SimpleDateFormat("yyyy-MM-dd").format(date) + "\", y: " + numberAsString + "}");

						if (keyIter.hasNext()) {
							writer.write(",");
						}
					}
				}
			}
			writer.write("]");
			writer.write("}");

			if (it.hasNext()) {
				writer.write(",");
			}
		}
		writer.write("]");
		writer.write("}");
	}

	@Override
	protected void encodeOptions(FacesContext context, Chart chart) throws IOException {
		super.encodeOptions(context, chart);

		ResponseWriter writer = context.getResponseWriter();
		LineChartModel model = (LineChartModel) chart.getModel();
		writer.write(",animateAdvanced:" + model.isAnimateAdvanced());
		writer.write(",animatePath:" + model.isAnimatePath());
		writer.write(",options:{");
		for (Iterator<AxisType> it = model.getAxes().keySet().iterator(); it.hasNext();) {
			AxisType axisType = it.next();
			Axis axis = model.getAxes().get(axisType);
			axis.render(writer, axisType);
			if (it.hasNext()) {
				writer.write(",");
			}
		}

		if (model.getWidth() != null)
			writer.write(",width:\"" + EscapeUtils.forJavaScript(model.getWidth()) + "\"");

		if (model.getHeight() != null)
			writer.write(",height:\"" + EscapeUtils.forJavaScript(model.getHeight()) + "\"");

		writer.write(",showLine:" + model.isShowLine());
		writer.write(",showPoint:" + model.isShowPoint());
		writer.write(",showArea:" + model.isShowArea());
		writer.write(",areaBase:" + model.getAreaBase());
		writer.write(",lineSmooth:" + model.isLineSmooth());
		writer.write(",showGridBackground:" + model.isShowGridBackground());

		if (model.getLow() != 0)
			writer.write(",low:" + model.getLow());

		if (model.getHigh() != 0)
			writer.write(",high:" + model.getHigh());

		if (model.getChartPadding() != null)
			writer.write(",chartPadding:" + model.getChartPadding());

		if (chart.getPlugins() != null) {
			writer.write(",plugins:" + chart.getPlugins());
		}
		writer.write(",fullWidth:" + model.isFullWidth());
		writer.write(",reverseData:" + model.isReverseData());

		writer.write("}");
	}
}
