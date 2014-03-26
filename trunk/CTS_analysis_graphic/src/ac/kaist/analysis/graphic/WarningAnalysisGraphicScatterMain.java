package ac.kaist.analysis.graphic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;

import ac.kaist.analysis.WarningAnalyzer;
import ac.kaist.analysis.model.WarningAnalysisInputData;
import ac.kaist.analysis.model.WarningAnalysisResultData;
import ac.kaist.analysis.utils.WarningAnalysisLoadExcel;

/**
 * A simple demonstration of the {@link XYLineAndShapeRenderer} class.
 */
public class WarningAnalysisGraphicScatterMain extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public WarningAnalysisGraphicScatterMain(final String title) {

        super(title);
        XYDataset dataset = createSampleDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(
            title,
            "Likelihood",
            "Severity",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            false,
            false
        );
        
        chart.setBackgroundPaint(Color.white);
        
        LegendTitle legend = chart.getLegend();        
        legend.setPosition(RectangleEdge.RIGHT);
        
       
        XYPlot plot = (XYPlot) chart.getPlot();
        
//        LegendItemCollection legendItemsOld = plot.getLegendItems();
//		final LegendItemCollection legendItemsNew = new LegendItemCollection();
//
//		for (int i = 0; i < legendItemsOld.getItemCount(); i++) {
//			if (!(i % 2 == 0)) {
//				legendItemsNew.add(legendItemsOld.get(i));
//			}
//		}
//		LegendItemSource source = new LegendItemSource() {
//			LegendItemCollection lic = new LegendItemCollection();
//			{
//				lic.addAll(legendItemsNew);
//			}
//
//			public LegendItemCollection getLegendItems() {
//				return lic;
//			}
//		};
//		chart.addLegend(new LegendTitle(source));
        
        
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(new Color(134, 134, 134));
        NumberAxis ra = (NumberAxis) plot.getRangeAxis();
        ra.setTickUnit(new NumberTickUnit(1));
        ra.setRange(-2, 3);
        ra.setNumberFormatOverride(new NumberFormat(){

			@Override
			public StringBuffer format(double arg0, StringBuffer arg1,
					FieldPosition arg2) {
				// TODO Auto-generated method stub
				String f = "%."+Integer.toString((int)(Math.abs(arg0-Math.abs(arg0))/2))+"f";
				return new StringBuffer(String.format(f, Math.pow(10, arg0)));
			}

			@Override
			public StringBuffer format(long arg0, StringBuffer arg1,
					FieldPosition arg2) {
				// TODO Auto-generated method stub
				return null;//new StringBuffer(String.format("%.2f", Math.pow(10, arg0)));
			}

			@Override
			public Number parse(String arg0, ParsePosition arg1) {
				// TODO Auto-generated method stub
				return null;
			}

        });
        
        NumberAxis da = (NumberAxis) plot.getDomainAxis();
        da.setTickUnit(new NumberTickUnit(1));
        da.setRange(-2, 3);
        da.setNumberFormatOverride(new NumberFormat(){

			@Override
			public StringBuffer format(double arg0, StringBuffer arg1,
					FieldPosition arg2) {
				String f = "%."+Integer.toString((int)(Math.abs(arg0-Math.abs(arg0))/2))+"f";
				return new StringBuffer(String.format(f, Math.pow(10, arg0)));
			}

			@Override
			public StringBuffer format(long arg0, StringBuffer arg1,
					FieldPosition arg2) {
				// TODO Auto-generated method stub
				return new StringBuffer(String.format("%.2f", Math.pow(10, arg0)));
			}

			@Override
			public Number parse(String arg0, ParsePosition arg1) {
				// TODO Auto-generated method stub
				return null;
			}

        });
        
        plot.setRangeGridlinePaint(new Color(134, 134, 134));
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f));
        renderer.setSeriesStroke(2, new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f));
        renderer.setSeriesStroke(3, new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f));
        
        renderer.setSeriesPaint(0, new Color(242, 242, 242));
        renderer.setSeriesPaint(1, new Color(217, 217, 217));
        renderer.setSeriesPaint(2, new Color(191, 191, 191));
        renderer.setSeriesPaint(3, new Color(166, 166, 166));
        
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesLinesVisible(2, true);
        renderer.setSeriesShapesVisible(2, false);
        renderer.setSeriesLinesVisible(3, true);
        renderer.setSeriesShapesVisible(3, false);
        renderer.setSeriesLinesVisible(4, false);
        renderer.setSeriesShapesVisible(4, true);       
        
        double size = 4.0;
        double delta = size / 2.0;
        Shape shape1 = new Rectangle2D.Double(-delta, -delta, size, size);
        Shape shape2 = new Ellipse2D.Double(-delta, -delta, size, size);
        Shape s = ShapeUtilities.createDiamond(5);
        renderer.setSeriesShape(4, shape2);
        
        
        //XYPlot categoryplot = chart.getXYPlot();
        //ValueAxis valueaxis = categoryplot.getRangeAxis();   
        //valueaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());   
        //valueaxis.setLowerMargin(0.14999999999999999D);   
        //valueaxis.setUpperMargin(0.14999999999999999D);   
        //valueaxis.setRange(-2, 3);
        
        //ValueAxis domainaxis = categoryplot.getDomainAxis();
        //domainaxis.setRange(-2, 3);
        
        
        plot.setRenderer(renderer);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 500));
        setContentPane(chartPanel);

        
        
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return A dataset.
     */
    private XYDataset createSampleDataset() {
    	
    	String inputPath = "E:/ext_work/respace/workspace/CTS_analysis/input/Process2.xls";
		String inputSheetName = "input data";
		int descriptor_depth = 3;
		
		//Load from excel file
		WarningAnalysisLoadExcel load = null;
		try{
			load = new WarningAnalysisLoadExcel(inputPath, inputSheetName, descriptor_depth);
		}catch(Exception e){
			e.printStackTrace();
		}
		WarningAnalysisInputData waInputData = load.getWaInputData();
			
		
		//Analysis
		int totalDeparture = 10598491;
		WarningAnalyzer wa = new WarningAnalyzer(waInputData, totalDeparture, "20080118");
		//Get Result data
		WarningAnalysisResultData waResultData = wa.getWaResultData();
		
		
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();   
        
        Set<String> sDesc = waResultData.getsDesc();
        
        Map<String, Float> injuryMillionDesc = waResultData.getInjuryMillionDescMatrix();
        Map<String, Float> damageMillionDesc = waResultData.getDamageMillionDescMatrix();
        Map<String, Float> likelihood = waResultData.getLikelihoodMap();
        
        
    	
        XYSeries risk1 = new XYSeries("Risk=1");
        risk1.add(-100, 100);
        risk1.add(100, -100);
        
        XYSeries risk2 = new XYSeries("Risk=10");
        risk2.add(-100, 101);
        risk2.add(100, -99);
        
        XYSeries risk3 = new XYSeries("Risk=100");
        risk3.add(-100, 102);
        risk3.add(102, -100);
        
        XYSeries risk4 = new XYSeries("Risk=1000");
        risk4.add(-100, 103);
        risk4.add(103, -100);
        
        XYSeries series2 = new XYSeries("Hazard");
        for(String desc : sDesc){
        	Float im = injuryMillionDesc.get(desc);
        	if(im == null) im = 0.0f;
        	Float dm = damageMillionDesc.get(desc);
        	if(dm == null) dm = 0.0f;
        	Float severityMillion = im+dm;
        	Float likelihoodVal = likelihood.get(desc);
        	//System.out.println(severityMillion + "("+Math.log10(severityMillion)+")"+", "+likelihoodVal+"("+Math.log10(likelihoodVal)+")");
        	series2.add(Math.log10(likelihoodVal), Math.log10(severityMillion));
        }
        
        
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(risk1);
        dataset.addSeries(risk2);
        dataset.addSeries(risk3);
        dataset.addSeries(risk4);
        dataset.addSeries(series2);
        return dataset;
    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final WarningAnalysisGraphicScatterMain demo = new WarningAnalysisGraphicScatterMain(
            "Risk Score Shart"
        );
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}