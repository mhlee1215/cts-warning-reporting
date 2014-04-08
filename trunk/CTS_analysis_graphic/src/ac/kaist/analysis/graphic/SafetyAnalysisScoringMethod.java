package ac.kaist.analysis.graphic;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ShapeUtilities;
import org.joda.time.LocalDate;

import swing.ax.CustomCellRenderer;
import ac.kaist.analysis.WarningAnalyzer;
import ac.kaist.analysis.model.WarningAnalysisInputData;
import ac.kaist.analysis.model.WarningAnalysisResultData;
import ac.kaist.analysis.utils.WarningAnalysisLoadExcel;
import ac.kaist.analysis.utils.WarningAnalysisWriteExcel;

public class SafetyAnalysisScoringMethod {
	
	WarningAnalysisResultData waResultData;
	String outPath;
	JTable table;
	JPanel scoringMethodChartpanel;
	JFreeChart scoringMethodChart;
	public SafetyAnalysisScoringMethod(WarningAnalysisResultData waResultData, String outPath){
		this.waResultData =  waResultData;
		this.outPath = outPath;
	}
	
	public JPanel createPanel(){
		JPanel main = new JPanel();
		scoringMethodChart = createChart();
		scoringMethodChartpanel = new ChartPanel(scoringMethodChart);
		
		
		JScrollPane scoreTalbeP = createScoreTable();
		//scoreTalbeP.setSize(new Dimension(500, 400));
		//jp_scoringMethod.add("West", scoreTalbeP);
		
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scoreTalbeP,scoringMethodChartpanel);
		sp.setDividerLocation(400);

		main.removeAll();
		main.setLayout(new BorderLayout());
		main.add("Center", sp);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton btnExport = new JButton("Export");
		btnPanel.add(btnExport);
		btnExport.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String path = outPath+"/"+"Scoring_Method.xls";
				System.out.println("outPath:" +path);
				WarningAnalysisWriteExcel.writeTable(table, path);
				JOptionPane.showMessageDialog(null, "Table contents were saved in "+path);
				
				Dimension size = scoringMethodChartpanel.getSize();
				try {
					String filename = "Scoring_Method.png";
					path = outPath+"/"+filename;
					OutputStream os = new FileOutputStream(path);
					BufferedImage chartImage = scoringMethodChart.createBufferedImage( size.width, size.height, null);
					ImageIO.write( chartImage, "png", os ); 
					os.close();
					JOptionPane.showMessageDialog(null, "Chart image was saved in "+path);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		main.add("South", btnPanel);
		
		return main;
	}
	
	public JScrollPane createScoreTable()
	{
			
		Map<String, Float> injuryMillionDesc = waResultData.getInjuryMillionDescMatrix();
	    Map<String, Float> damageMillionDesc = waResultData.getDamageMillionDescMatrix();
	    Map<String, Float> likelihood = waResultData.getLikelihoodMap();
	    
	    Vector<Vector> rowData = new Vector<Vector>();
		for(String s : waResultData.getsDesc()){
			Vector oneRow = new Vector();
			oneRow.add(s);
			oneRow.add(likelihood.get(s));
			Float injuryMillion = injuryMillionDesc.get(s);
			if(injuryMillion == null) continue;
			oneRow.add(injuryMillion);
			Float damageMillion = damageMillionDesc.get(s);
			if(damageMillion == null) continue;
			oneRow.add(damageMillion);
			oneRow.add(injuryMillion + damageMillion);

			rowData.add(oneRow);
			
		}
				

	    Vector<String> columnNames = new Vector<String>();
	    columnNames.addElement("Hazard");
	    columnNames.addElement("Likelihood");
	    columnNames.addElement("Injury Million");
	    columnNames.addElement("Damage Million");
	    columnNames.addElement("Risk Score");
		
		//DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(rowData, columnNames){
			 @Override
			   public TableCellRenderer getCellRenderer(int row, int column) {
			    // TODO Auto-generated method stub
			    return new CustomCellRenderer();
			   }
		};
		
		TableColumnModel cm = table.getColumnModel();
	    cm.getColumn(0).setPreferredWidth(200);
	    
	    table.getTableHeader().setForeground(Color.white);
	    table.getTableHeader().setBackground(CustomCellRenderer.headerBG);
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane( table );
		return scrollPane;
	}
	
	public static void main(String[] argv){
		String inputPath = "E:/ext_work/respace/workspace/CTS_analysis/input/Process2.xls";
		String inputSheetName = "input data";
		int descriptor_depth = 3;
		
		//Load from excel file
		WarningAnalysisLoadExcel load = null;
		try {
			load = new WarningAnalysisLoadExcel(inputPath, inputSheetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WarningAnalysisInputData waInputData = load.getWaInputData();
			
		
		//Analysis
		int totalDeparture = 192847;
		WarningAnalyzer wa = new WarningAnalyzer(waInputData, descriptor_depth, totalDeparture, "20080118");
		//Get Result data
		WarningAnalysisResultData waResultData = wa.getWaResultData();
		
		JFrame frame = new JFrame("TEST");
			    
	    
		LocalDate sDate = new LocalDate("2008-01-18");
		LocalDate eDate = new LocalDate("2011-01-18");
				
		SafetyAnalysisScoringMethod sm = new SafetyAnalysisScoringMethod(waResultData, "E:/ext_work/respace/workspace/CTS_analysis/input");
	    
		SafetyAnalysisScoringMethod sasm = new SafetyAnalysisScoringMethod(waResultData, "E:/ext_work/respace/workspace/CTS_analysis/input");
		
		frame.getContentPane().add( sasm.createPanel() );
		frame.setVisible(true);
		frame.setSize( 1024, 500 ); 
	}
	
	public XYDataset createDataset(WarningAnalysisResultData waResultData)   
    {
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
	
	public JFreeChart createChart()
	{
		return createChart(createDataset(waResultData));
	}

	public JFreeChart createChart(XYDataset dataset)   
    {  
		JFreeChart chart = ChartFactory.createXYLineChart(
	            "Risk Score Chart",
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
	        
//	        LegendItemCollection legendItemsOld = plot.getLegendItems();
//			final LegendItemCollection legendItemsNew = new LegendItemCollection();
	//
//			for (int i = 0; i < legendItemsOld.getItemCount(); i++) {
//				if (!(i % 2 == 0)) {
//					legendItemsNew.add(legendItemsOld.get(i));
//				}
//			}
//			LegendItemSource source = new LegendItemSource() {
//				LegendItemCollection lic = new LegendItemCollection();
//				{
//					lic.addAll(legendItemsNew);
//				}
	//
//				public LegendItemCollection getLegendItems() {
//					return lic;
//				}
//			};
//			chart.addLegend(new LegendTitle(source));
	        
	        
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
	        

	        
	        plot.setRenderer(renderer);
	        
	        return chart;
    }
}
