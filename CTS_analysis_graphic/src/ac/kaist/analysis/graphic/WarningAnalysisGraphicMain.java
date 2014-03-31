package ac.kaist.analysis.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

import demo.ExtendedStackedBarRenderer;
import ac.kaist.analysis.WarningAnalyzer;
import ac.kaist.analysis.model.WarningAnalysisInputData;
import ac.kaist.analysis.model.WarningAnalysisResultData;
import ac.kaist.analysis.utils.WarningAnalysisLoadExcel;

public class WarningAnalysisGraphicMain extends ApplicationFrame {
	
	static int maxVal = 0;
	
	public WarningAnalysisGraphicMain(String title) {
		super(title);
		CategoryDataset categorydataset;
		try {
			categorydataset = createDataset();
			JFreeChart jfreechart = createChart(categorydataset);   
	        ChartPanel chartpanel = new ChartPanel(jfreechart);   
	        chartpanel.setPreferredSize(new Dimension(1500, 800));   
	        setContentPane(chartpanel);   
	        
	        //jfreechart.createBufferedImage(width, height)

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] argv) {
		WarningAnalysisGraphicMain warningAnalysisGraphicMain = new WarningAnalysisGraphicMain("Chromatograph of Descriptive Factor");   
		warningAnalysisGraphicMain.pack();   
        RefineryUtilities.centerFrameOnScreen(warningAnalysisGraphicMain);   
        warningAnalysisGraphicMain.setVisible(true);   
	}
	
	public static CategoryDataset createDataset(WarningAnalysisResultData waResultData) throws IOException   
    {
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();   
        
        Set<String> sDesc = waResultData.getsDesc();
        Vector<String> sDamage = waResultData.getsDamage();
        Vector<String> sInjury = waResultData.getsInjury();
        Map<String, Map<String, Integer> > aircraftDamageDesc = waResultData.getAircraftDamageDescMatrix();
        Map<String, Map<String, Integer> > injuryLevelDesc = waResultData.getInjuryLevelDescMatrix();

        
        for(String desc : sDesc){
        	Map<String, Integer> m = aircraftDamageDesc.get(desc);
        	int cnt = 1;
        	for(int i = sDamage.size()-1 ; i >= 0 ; i--){
        		String damage = sDamage.get(i);
        	//}
        	//for(String damage : sDamage){
        		Integer damageVal = m.get(damage);
        		if(damageVal == null) damageVal = 0;
        		if (maxVal < damageVal) maxVal = damageVal;
        		defaultcategorydataset.addValue(damageVal, damage+"(damage)", desc);	
        		cnt++;
        	}
        }
        
        for(String desc : sDesc){
        	Map<String, Integer> m = injuryLevelDesc.get(desc);
        	int cnt = 1;
        	for(int i = sInjury.size()-1 ; i >= 0 ; i--){
        		String injury = sInjury.get(i);
        	//for(String injury : sInjury){
        		//System.out.println(m.get(injury));
        		Integer injuryVal = m.get(injury);
        		if (injuryVal == null) injuryVal = 0;
        		if (maxVal < injuryVal) maxVal = injuryVal;
        		defaultcategorydataset.addValue(-injuryVal, injury+"(injury)", desc);	
        		cnt++;
        	}
        }
 
        return defaultcategorydataset;   
    }
	
	public static CategoryDataset createDataset() throws IOException   
    {   
		String inputPath = "E:/ext_work/respace/workspace/CTS_analysis/input/Process2.xls";
		String inputSheetName = "input data";
		int descriptor_depth = 3;
		
		//Load from excel file
		WarningAnalysisLoadExcel load = new WarningAnalysisLoadExcel(inputPath, inputSheetName);
		WarningAnalysisInputData waInputData = load.getWaInputData();
			
		
		//Analysis
		int totalDeparture = 192847;
		WarningAnalyzer wa = new WarningAnalyzer(waInputData, descriptor_depth, totalDeparture, "20080118");
		//Get Result data
		WarningAnalysisResultData waResultData = wa.getWaResultData();
		
		return createDataset(waResultData);
		
    }   
   
	public static JFreeChart createChart(CategoryDataset categorydataset)   
    {   
        JFreeChart jfreechart = ChartFactory.createStackedBarChart("Chromatograph of Descriptive Factor", "", "Injury | Damage", categorydataset, PlotOrientation.VERTICAL, true, false, false);
        
        // get a reference to the plot for further customisation...      
               
        CategoryPlot categoryplot = jfreechart.getCategoryPlot();
        
        
        
        categoryplot.setBackgroundPaint(Color.white);
        categoryplot.setDomainGridlinePaint(new Color(134, 134, 134));
        categoryplot.setRangeGridlinePaint(new Color(134, 134, 134));
        
        LegendTitle legend = jfreechart.getLegend();
        legend.setPosition(RectangleEdge.RIGHT);
        
        //categoryplot.setBackgroundPaint(Color.LIGHT_GRAY);
        ExtendedStackedBarRenderer extendedstackedbarrenderer = new ExtendedStackedBarRenderer();   
        extendedstackedbarrenderer.setItemLabelsVisible(false);
        //extendedstackedbarrenderer.setitem
        //extendedstackedbarrenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());   
        extendedstackedbarrenderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());   
        extendedstackedbarrenderer.setSeriesPaint(3, Color.red);
        extendedstackedbarrenderer.setSeriesPaint(2, Color.yellow);
        extendedstackedbarrenderer.setSeriesPaint(1, Color.green);
        extendedstackedbarrenderer.setSeriesPaint(0, Color.gray);
        extendedstackedbarrenderer.setSeriesPaint(7, Color.red);
        extendedstackedbarrenderer.setSeriesPaint(6, Color.yellow);
        extendedstackedbarrenderer.setSeriesPaint(5, Color.green);
        extendedstackedbarrenderer.setSeriesPaint(4, Color.gray);
        
        BarRenderer br = (BarRenderer) categoryplot.getRenderer();
        br.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        br.setSeriesPaint(3, Color.red);
        br.setSeriesPaint(2, Color.yellow);
        br.setSeriesPaint(1, Color.green);
        br.setSeriesPaint(0, Color.gray);
        br.setSeriesPaint(7, Color.red);
        br.setSeriesPaint(6, Color.yellow);
        br.setSeriesPaint(5, Color.green);
        br.setSeriesPaint(4, Color.gray);
        
        //categoryplot.setRenderer(extendedstackedbarrenderer);   
        ValueAxis valueaxis = categoryplot.getRangeAxis();   
        
        final CategoryAxis domainAxis = categoryplot.getDomainAxis();
        //domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setCategoryLabelPositions(
        		                CategoryLabelPositions.createUpRotationLabelPositions(
        		                        Math.PI / 3.0));
        domainAxis.setCategoryMargin(0.5);
        domainAxis.setCategoryLabelPositionOffset(0);
        
        valueaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());   
           
        valueaxis.setRange(-maxVal-5, maxVal+5);
        
        ValueAxis va = categoryplot.getRangeAxis();
        ((NumberAxis) va).setNumberFormatOverride(new NumberFormat(){

			@Override
			public StringBuffer format(double arg0, StringBuffer arg1,
					FieldPosition arg2) {
				
				return new StringBuffer(String.format("%d", (int)Math.abs(arg0)));
			}

			@Override
			public StringBuffer format(long arg0, StringBuffer arg1,
					FieldPosition arg2) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Number parse(String arg0, ParsePosition arg1) {
				// TODO Auto-generated method stub
				return null;
			}

        });
        return jfreechart;   
    }   
   
    public static JPanel createDemoPanel() throws IOException   
    {   
        JFreeChart jfreechart = createChart(createDataset());   
        return new ChartPanel(jfreechart);   
    }   
	
	
}
