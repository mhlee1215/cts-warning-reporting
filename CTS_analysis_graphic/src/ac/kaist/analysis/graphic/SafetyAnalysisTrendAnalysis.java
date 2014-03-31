package ac.kaist.analysis.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import swing.ax.ColumnGroup;
import swing.ax.CustomCellRenderer;
import swing.ax.GroupableTableHeader;
import swing.ax.MyTableModel;
import ac.kaist.analysis.WarningAnalyzer;
import ac.kaist.analysis.model.WarningAnalysisInputData;
import ac.kaist.analysis.model.WarningAnalysisResultData;
import ac.kaist.analysis.utils.WarningAnalysisLoadExcel;
import ac.kaist.analysis.utils.WarningAnalysisWriteExcel;

public class SafetyAnalysisTrendAnalysis {
	
	JTable table;
	WarningAnalysisResultData waResultData;
	WarningAnalysisInputData waInputData;
	LocalDate sDate;
	LocalDate eDate;
	JFrame detail_graph;
	JPanel detail_graph_pane;
	JPanel detail_graph_pane2;
	String selectedDesc;
	String outPath;
	
	JFreeChart chart;
	ChartPanel chartPanel;
	
	JLabel p;
	JLabel p2;
	
	public SafetyAnalysisTrendAnalysis(WarningAnalysisResultData waResultData, WarningAnalysisInputData waINputData, LocalDate sDate, LocalDate eDate, String outPath){
		this.waResultData = waResultData;
		this.waInputData = waINputData;
		this.sDate = sDate;
		this.eDate = eDate;
		this.outPath = outPath;
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
				
		SafetyAnalysisTrendAnalysis ta = new SafetyAnalysisTrendAnalysis(waResultData, waInputData, sDate, eDate, "E:/ext_work/respace/workspace/CTS_analysis/input");
	    
		frame.getContentPane().add( ta.createPanel() );
		frame.setVisible(true);
		frame.setSize( 1024, 500 ); 
	}
	
	public JPanel createPanel(){
		JPanel m = new JPanel();
		m.setLayout(new BorderLayout());
		
		JScrollPane trendTable = createTable();
		JPanel periodPanel = new JPanel();
		periodPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		p = new JLabel("Analyze Period : ");
    	p2 = new JLabel(sDate.getYear()+"/"+sDate.getMonthOfYear()+"/"+sDate.getDayOfMonth()+" ~ "+eDate.getYear()+"/"+eDate.getMonthOfYear()+"/"+eDate.getDayOfMonth());
    	
    	periodPanel.add(p);
    	periodPanel.add(p2);
    	
    	m.add("North", periodPanel);
		m.add("Center", trendTable);
		
		JPanel jp_trendAnalysisButton = new JPanel();
		jp_trendAnalysisButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton buttonTrendAnalysisExport = new JButton("Export");
		
		
		buttonTrendAnalysisExport.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String path = outPath+"/"+"TrendAnalysis.xls";
				System.out.println("outPath:" +path);
				WarningAnalysisWriteExcel.writeTable(p.getText()+p2.getText(), table, path);
				JOptionPane.showMessageDialog(null, "Table contents were saved in "+path);
			}
		});
		
		jp_trendAnalysisButton.add(buttonTrendAnalysisExport);
		m.add("South", jp_trendAnalysisButton);
		
		return m;
	}
	
	public JScrollPane createTable(){

		
		Map<String, Map<String, Integer> > dmg = waResultData.getAircraftDamageDescMatrix();
	    Map<String, Map<String, Integer> > inj = waResultData.getInjuryLevelDescMatrix();
	    
	    Vector<Vector> rowData = new Vector<Vector>();
	    for(String s : waResultData.getsDesc()){
			Vector oneRow = new Vector();
			oneRow.add(s);
			
			int sum = 0;
			Map<String, Integer> dmg2 = dmg.get(s);
			for(String damage : waResultData.getsDamage()){
				Integer d = dmg2.get(damage);
				if(d == null) d = 0;
				sum += d;
				oneRow.add(new Integer(d));
			}
			
			Map<String, Integer> inj2 = inj.get(s);
			for(String injury : waResultData.getsInjury()){
				Integer d = inj2.get(injury);
				if(d == null) d = 0;
				oneRow.add(d);
			}
			oneRow.add(sum);
			rowData.add(oneRow);
		
		}
	 	    
 	    Vector<String> columnNames = new Vector<String>();
 	    columnNames.addElement("Hazard");
 	    columnNames.addElement("DEST");
 	    columnNames.addElement("SUBS");
 	    columnNames.addElement("MINR");
 	    columnNames.addElement("NONE");
 	    columnNames.addElement("FATL");
	    columnNames.addElement("SERS");
	    columnNames.addElement("MINR");
	    columnNames.addElement("NONE");
	    columnNames.addElement("TOTAL");
	    
	    		   
	    MyTableModel mm = new MyTableModel();
	    mm.setData(rowData);
	    mm.setColumnNames(columnNames);

	    table = new JTable(mm)
	    {
			 @Override
			   public TableCellRenderer getCellRenderer(int row, int column) {
			    // TODO Auto-generated method stub
			    return new CustomCellRenderer();
			   }
		};

	    
	    table.setAutoCreateRowSorter(true);
	    table.setRowSelectionAllowed(true);
	    table.setColumnSelectionAllowed(false);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.getSelectionModel().addListSelectionListener(new RowListener());
	    TableColumnModel cm = table.getColumnModel();
	    
	    cm.getColumn(0).setPreferredWidth(400);

//	    ColumnGroup g_name = new ColumnGroup("Aircraft Damage");
//	    g_name.add(cm.getColumn(1));
//	    g_name.add(cm.getColumn(2));
//	    g_name.add(cm.getColumn(3));
//	    g_name.add(cm.getColumn(4));
//	    ColumnGroup g_lang = new ColumnGroup("Injury Level");
//	    g_lang.add(cm.getColumn(5));
//	    g_lang.add(cm.getColumn(6));
//	    g_lang.add(cm.getColumn(7));
//	    g_lang.add(cm.getColumn(8));
//	    GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
//	    header.addColumnGroup(g_name);
//	    header.addColumnGroup(g_lang);
	    
	    table.getTableHeader().setForeground(Color.white);
	    table.getTableHeader().setBackground(CustomCellRenderer.headerBG);
	    
		
		JScrollPane scroll = new JScrollPane( table );
		return scroll;
	}
	
	public void createChartFrame(int intervalMonth){
		if (detail_graph == null)
			detail_graph = new JFrame(selectedDesc+" Time Analysis");
		
		if(detail_graph_pane == null){
			detail_graph_pane = new JPanel();
			detail_graph_pane2 = new JPanel();
		}
		detail_graph.getContentPane().add(detail_graph_pane2);
		detail_graph_pane2.setLayout(new BorderLayout());
		detail_graph_pane2.add("Center", detail_graph_pane);
		//System.out.println(detail_graph.getComponentCount());
		//System.out.println(detail_graph_pane.getComponentCount());
		detail_graph_pane.removeAll();
		
        //int type = 1;
        
        
        Vector<LocalDate> sDateV = new Vector<LocalDate>();
        Vector<LocalDate> eDateV = new Vector<LocalDate>();
        
        
        LocalDate cur = sDate;
        
        sDateV.add(sDate);
        
        cur = getNextStart(cur, intervalMonth);
		
        while(cur.compareTo(eDate) <= 0){
        	LocalDate curP = cur;
        	curP = curP.plusDays(-1);
        	eDateV.add(curP);
        	sDateV.add(cur);

        	cur = getNextStart(cur, intervalMonth);
        	
        }
        eDateV.add(eDate);
        
        //System.out.println(sDateV);
        //System.out.println(eDateV);
        
        CategoryDataset dataset = createDataset(waResultData, sDateV, eDateV);
        String title = selectedDesc + " Occurrence Histogram";
        chart = createChart(title, dataset);
        
        CategoryPlot categoryplot = chart.getCategoryPlot();
        categoryplot.setBackgroundPaint(Color.white);
        chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        
        detail_graph_pane.setLayout(new BorderLayout());
        detail_graph_pane.add("Center", chartPanel);
        detail_graph.setSize(new Dimension(900, 600));
        detail_graph.setVisible(true);
        
        JPanel bPanel = new JPanel();
        JButton bMonth = new JButton("Month");
        bMonth.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				createChartFrame(1);
			}
        	
        });
        JButton bQuater = new JButton("Quarter");
        bQuater.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createChartFrame(2);
			}
        	
        });
        JButton bHalf = new JButton("Half");
        bHalf.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createChartFrame(3);
			}
        	
        });
        JButton bYear = new JButton("Year");
        bYear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createChartFrame(4);
			}
        	
        });
        
        bPanel.setLayout(new FlowLayout());
        bPanel.add(bMonth);
        bPanel.add(bQuater);
        bPanel.add(bHalf);
        bPanel.add(bYear);
        
        detail_graph_pane.add("South", bPanel);
        
        JPanel exportPanel = new JPanel();
        exportPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton btnExport = new JButton("Export");
        btnExport.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Dimension size = chartPanel.getSize();
				try {
					//String outPath = textFieldSelectPath.getText();
					String filename = selectedDesc.replace("/", "_")+"_"+"Time_Analysis.png";
					String path = outPath+"/"+filename;
					OutputStream os = new FileOutputStream(path);
					System.out.println(outPath+"/"+filename+"///"+size.width + " " + size.height);
					BufferedImage chartImage = chart.createBufferedImage( size.width, size.height, null);
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
        exportPanel.add(btnExport);
        detail_graph_pane2.add("South", exportPanel);
        
        detail_graph.revalidate();
        //detail_graph_pane.repaint();
	}
	
    public class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            selectedDesc = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);            
            createChartFrame(1);
        }
    }
    
    public LocalDate getNextStart(LocalDate cur, int type){
    	LocalDate next;
    	int y, m;
    	//Month
    	if(type == 1){
    		next = cur.plusMonths(1);
    		y = next.getYear();
    		m = next.getMonthOfYear();
    		cur = new LocalDate(y, m, 1);

    	}
    	//Quater
    	else if(type == 2){
    		y = cur.getYear();
    		m = cur.getMonthOfYear();
    		if( m < 3)
    			m = 4;
    		else if( m < 6)
    			m = 7;
    		else if( m < 9)
    			m = 10;
    		else{
    			m = 1;
    			y++;
    		}
    		cur = new LocalDate(y, m, 1);
    	}
    	//Half
    	else if(type == 3){
    		y = cur.getYear();
    		m = cur.getMonthOfYear();
    		if( m < 6)
    			m = 7;
    		else{
    			m = 1;
    			y++;
    		}
    		cur = new LocalDate(y, m, 1);
    	}
    	//Year
    	else if(type == 4){
    		y = cur.getYear();
    		cur = new LocalDate(y+1, 1, 1);
    	}
    	return cur;
    }
    
    private static JFreeChart createChart(String title, CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
        		title,       // chart title
            "Period",               // domain axis label
            "Count",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // ******************************************************************
        //  More than 150 demo applications are included with the JFreeChart
        //  Developer Guide...for more information, see:
        //
        //  >   http://www.object-refinery.com/jfreechart/guide.html
        //
        // ******************************************************************

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
        
        // disable bar outlines...
//        BarRenderer renderer = (BarRenderer) plot.getRenderer();
//        renderer.setDrawBarOutline(false);
//
//        // set up gradient paints for series...
//        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
//                0.0f, 0.0f, new Color(0, 0, 64));
//        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green,
//                0.0f, 0.0f, new Color(0, 64, 0));
//        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
//                0.0f, 0.0f, new Color(64, 0, 0));
//        renderer.setSeriesPaint(0, gp0);
//        renderer.setSeriesPaint(1, gp1);
//        renderer.setSeriesPaint(2, gp2);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        Math.PI / 3.0));
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }
    
    private CategoryDataset createDataset(WarningAnalysisResultData waResultData, Vector<LocalDate> sDateV, Vector<LocalDate> eDateV) {

        // row keys...
        String series1 = "First";
        
        // column keys...
       
        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd");
        //labelPeriodValue.setText(formatter.print(inputStartDate)+"~"+formatter.print(inputEndDate));
        
        Vector<String> sInjury = waInputData.getsInjury();
        Vector<String> sDamage = waInputData.getsDamage();
        
        for(int i = 0 ; i < sDateV.size() ; i++){
        	
        	LocalDate subStartDate = sDateV.get(i);
        	LocalDate subEndDate = eDateV.get(i);
        	
        	WarningAnalyzer wa = new WarningAnalyzer(waInputData, waResultData.getAnalDepth(), 0, "20080118", subStartDate, subEndDate);
        	
        	Map<String, Integer> im = wa.getWaResultData().getInjuryLevelDescMatrix().get(selectedDesc);
        	Map<String, Integer> am = wa.getWaResultData().getAircraftDamageDescMatrix().get(selectedDesc);
        	
        	//System.out.println("selectedDesc : "+selectedDesc);
        	//System.out.println(wa.getWaResultData().getInjuryLevelDescMatrix());
        	String periodString = formatter.print(sDateV.get(i))+"~"+formatter.print(eDateV.get(i)); 
        	
        	for(String s : sInjury){
        		Integer v = im.get(s);
        		if(v == null) v = 0;
        		dataset.addValue(v, s+"(injury)", periodString);	
        	}
        	for(String s : sDamage){
        		Integer v = am.get(s);
        		if(v == null) v = 0;
        		dataset.addValue(v, s+"(damage)", periodString);	
        	}
        	
        }
        
       
        return dataset;

    }
}
