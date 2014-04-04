package ac.kaist.analysis.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.joda.time.LocalDate;

import swing.ax.CustomCellRenderer;
import swing.ax.MyTableModel;
import swing.ax.RiskMatrixCellRenderer;
import swing.ax.VerticalFlowLayout;
import ac.kaist.analysis.WarningAnalyzer;
import ac.kaist.analysis.model.RiskMatrixData;
import ac.kaist.analysis.model.WarningAnalysisInputData;
import ac.kaist.analysis.model.WarningAnalysisResultData;
import ac.kaist.analysis.utils.WarningAnalysisLoadExcel;
import ac.kaist.analysis.utils.WarningAnalysisWriteExcel;

public class SafetyAnalysisRiskMatrix {
	WarningAnalysisResultData waResultData;
	String outPath;
	
	JComboBox cbEvent;
	JComboBox cbHazard;
	int riskType = 1;
	
	JPanel riskPanel4;
	
	String ev_id;
	String hz_id;
	
	Vector<RiskMatrixData> rmData;
	
	public SafetyAnalysisRiskMatrix(WarningAnalysisResultData waResultData, String outPath){
		this.waResultData = waResultData;
		this.outPath = outPath;
	}
	
	public static void main(String[] argv){
		String inputPath = "E:/ext_work/respace/workspace/CTS_analysis/input/Process2.xls";
		String inputSheetName = "input data";
		int descriptor_depth = 4;
		
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
				
	    
		SafetyAnalysisRiskMatrix sarm = new SafetyAnalysisRiskMatrix(waResultData, "E:/ext_work/respace/workspace/CTS_analysis/input");
		
		frame.getContentPane().add( sarm.createPanel() );
		frame.setVisible(true);
		frame.setSize( 1024, 500 ); 
	}
	
	public JPanel createPanel(){
		JPanel m = new JPanel();
		
		JButton selectEvent = new JButton("Select Event");
		selectEvent.setPreferredSize(new Dimension(150, 20));
		selectEvent.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cbEvent.setEnabled(true);
				cbHazard.setEnabled(false);
				riskType = 1;
			}
		});

		cbEvent = new JComboBox(waResultData.getpEv_id().toArray());
		
		//JTextField tfEventId = new JTextField();
		cbEvent.setPreferredSize(new Dimension(300, 20));
		JButton selectHazard = new JButton("Select Hazard");
		selectHazard.setPreferredSize(new Dimension(150, 20));
		selectHazard.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cbEvent.setEnabled(false);
				cbHazard.setEnabled(true);
				riskType = 2;
			}
		});
		cbHazard = new JComboBox(waResultData.getpDesc().toArray());
		cbHazard.setEnabled(false);
		//JTextField tfHazard = new JTextField();
		cbHazard.setPreferredSize(new Dimension(300, 20));
		JPanel riskPanel_main = new JPanel();
		JPanel riskPanel = new JPanel();
		riskPanel.setPreferredSize(new Dimension(600, 100));
		riskPanel.setLayout(new VerticalFlowLayout()); 
		m.removeAll();
		m.setLayout(new BorderLayout());
		m.add("North",riskPanel);
		
		JPanel riskPanel1 = new JPanel();
		riskPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		riskPanel1.add(selectEvent);
		riskPanel1.add(cbEvent);
		riskPanel.add(riskPanel1);
		
		JPanel riskPanel2 = new JPanel();
		riskPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		riskPanel2.add(selectHazard);
		riskPanel2.add(cbHazard);
		riskPanel.add(riskPanel2);
		
	
		
		JPanel riskPanel3 = new JPanel();
		riskPanel3.setPreferredSize(new Dimension(600, 30));
		//riskPanel3.setBorder(new TitledBorder(""));
		riskPanel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton riskAnalyze = new JButton("Analyze");
		riskAnalyze.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				riskPanel4.removeAll();
				riskPanel4.setLayout(new BorderLayout());
				ev_id = (String) cbEvent.getItemAt(cbEvent.getSelectedIndex());
				//String[] hz_array = (String[]) waInputData.getsDesc().toArray();
				hz_id = (String) cbHazard.getItemAt(cbHazard.getSelectedIndex());
				JScrollPane tablePane = createRiskMatrix(ev_id, hz_id,  riskType);
				riskPanel4.add("Center", tablePane);
				riskPanel4.revalidate();
			}
			
		});
		riskAnalyze.setPreferredSize(new Dimension(100, 20));
		JButton riskExport = new JButton("Export");
		riskExport.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String filename = "";
				
				if(riskType == 1)
					filename = ev_id.replace("/",  "_") + "_Risk_Matrix.png";
				else if(riskType == 2)
					filename = hz_id.replace("/",  "_") + "_Risk_Matrix.png";
				String path = outPath+"/"+filename;
				try {
					OutputStream os;
					os = new FileOutputStream(path);
					
				    int w = riskPanel4.getWidth();
				    int h = riskPanel4.getHeight();
				    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				    Graphics2D g = bi.createGraphics();
				    riskPanel4.paintAll(g);
				    
				    Component component = riskPanel4;
				    component.setSize(component.getPreferredSize());
			        layoutComponent(component);
				    BufferedImage img = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TRANSLUCENT);
			        Graphics2D g2d = (Graphics2D) img.getGraphics();
			        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			        component.paintAll(g2d);
			    
					ImageIO.write( img, "png", os );
					os.close();
					
					riskPanel4.removeAll();
					riskPanel4.setLayout(new BorderLayout());
					JScrollPane tablePane = createRiskMatrix(ev_id, hz_id,  riskType);
					riskPanel4.add("Center", tablePane);
					riskPanel4.revalidate();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Risk Matrix Figure was saved in "+path);
				
				if(riskType == 1)
					filename = ev_id.replace("/",  "_") + "_Risk_Matrix.xls";
				else if(riskType == 2)
					filename = hz_id.replace("/",  "_") + "_Risk_Matrix.xls";
				path = outPath+"/"+filename;
				WarningAnalysisWriteExcel.writeRiskMatrix(rmData, riskType, path);
				JOptionPane.showMessageDialog(null, "Risk Matrix Data was saved in "+path);
				   
			}
			
		});
		riskExport.setPreferredSize(new Dimension(100, 20));
		riskPanel3.add(riskAnalyze);
		riskPanel3.add(riskExport);
		riskPanel.add(riskPanel3);
		
		riskPanel4 = new JPanel();
		//riskPanel4.setBorder(new TitledBorder(""));
		//riskPanel4.setPreferredSize(new Dimension(300, 300));
//		SafetyAnalysisRiskMatrix rm = new SafetyAnalysisRiskMatrix();
//		JScrollPane tablePane = rm.createRiskMatrix(waResultData, "", 1);
//		riskPanel4.add(tablePane);
		//riskPanel.add(riskPanel4);
		m.add("Center",riskPanel4);
		
		return m;
	}
	
	private static void layoutComponent(Component c) {
	    synchronized (c.getTreeLock()) {
	        c.doLayout();
	        if (c instanceof Container) {
	            for (Component child : ((Container) c).getComponents()) {
	                layoutComponent(child);
	            }
	        }
	    }
	}
	
	private Vector<RiskMatrixData> getRiskMatrixData(String ev_id, String hz_id, int type){
		Vector<RiskMatrixData> rm = new Vector<RiskMatrixData>();
		//Using EV ID
		if(type==1){
			Vector<String> occurredHz = new Vector<String>();
			for(String hz : waResultData.getOccurrenceMatrix().get(ev_id).keySet()){
				String hz_ID = hz;
				String ev_ID = ev_id;
				System.out.println("ev_id: "+ev_id+", hz_id:"+hz_ID);
				System.out.println( waResultData.getMFDescMatrix());
				Float mf = waResultData.getMFDescMatrix().get(hz_ID);
				//System.out.println("mf:"+mf);
				//System.out.println(waResultData.getLikelihoodQuantizedMap());
				Integer liklihood = waResultData.getLikelihoodQuantizedMap().get(hz_ID);
				Integer worst = waResultData.getWorstSeverityMap().get(hz_ID);
				Integer most = waResultData.getWorstSeverityMap().get(hz_ID);
				Integer today = waResultData.getTodayWorstSeverity().get(hz_ID);
				
				if(liklihood == null) liklihood = 0;
				if(worst == null) worst = 0;
				if(most == null) most = 0;
				if(today == null) today = 0;
				if(mf == null) mf = 0.0f;
				//System.out.println(ev_id+" "+hz_ID+" "+ worst+" "+most+" "+today+" type:"+type);
				if( worst > 0 && most > 0)
					rm.add(new RiskMatrixData(ev_ID, hz_ID, worst, most, today, liklihood, mf));
			}
		}
		//Using Hz ID
		else if(type ==2){
			String hz_ID = hz_id;
			String ev_ID = "";
			float mf = 0.0f;
			int liklihood = waResultData.getLikelihoodQuantizedMap().get(hz_ID);
			int worst = waResultData.getWorstSeverityMap().get(hz_ID);
			int most = waResultData.getWorstSeverityMap().get(hz_ID);
			
			System.out.println(ev_id+" "+hz_ID+" "+ worst+" "+most+" type:"+type);
			if( worst > 0 && most > 0)
				rm.add(new RiskMatrixData("", hz_ID, worst, most, 0, liklihood, mf));
		}
		return rm;
	}
	public JScrollPane createRiskMatrix(String ev_id, String hz_id, int type){
				
		
		rmData = getRiskMatrixData(ev_id, hz_id, type);
		System.out.println(rmData);
		//rmData.add(new RiskMatrixData("a", "B", 3, 5, 4, 3, 0.5f));
		//rmData.add(new RiskMatrixData("aa", "Bb", 3, 5, 4, 3, 0.5f));
		
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new VerticalFlowLayout());
		

		int cellWidth = 70;
		int inst_height = 40;
		int inst_height2 = 60;
		int pad_height = 20;
		int tableWidth = cellWidth*7;
		for(RiskMatrixData rm : rmData){
			Vector<Vector> rowData = new Vector<Vector>();
			Vector header = new Vector();
			for(int i = 0 ; i < 7 ; i++){
				Vector oneRow = new Vector();
				for(int j = 0 ; j < 7 ; j++){
					
					if( j== 0 && i==0){
						oneRow.add("Sev");
					}else if( j== 0 && i==1){
						oneRow.add("A");
					}else if( j== 0 && i==2){
						oneRow.add("B");
					}else if( j== 0 && i==3){
						oneRow.add("C");
					}else if( j== 0 && i==4){
						oneRow.add("D");
					}else if( j== 0 && i==5){
						oneRow.add("E");
					}else if( j== 1 && i==6){
						oneRow.add("1");
					}else if( j== 2 && i==6){
						oneRow.add("2");
					}else if( j== 3 && i==6){
						oneRow.add("3");
					}else if( j== 4 && i==6){
						oneRow.add("4");
					}else if( j== 5 && i==6){
						oneRow.add("5");
					}else if( j== 6 && i==6){
						oneRow.add("Lik");
					}
					else
						oneRow.add("");
					
					
				}
				rowData.add(oneRow);
				
				header.add("");
			}
			
			
			rowData.get(6-rm.most).set(rm.liklihood, rowData.get(6-rm.most).get(rm.liklihood)+RiskMatrixCellRenderer.CHAR_MOST);
			
			rowData.get(6-rm.worst).set(rm.liklihood, rowData.get(6-rm.worst).get(rm.liklihood)+RiskMatrixCellRenderer.CHAR_WORST);
			if( rm.today > 0)
				rowData.get(6-rm.today).set(rm.liklihood, rowData.get(6-rm.today).get(rm.liklihood)+RiskMatrixCellRenderer.CHAR_CURRENT);
			
			
			MyTableModel mm = new MyTableModel();
		    mm.setData(rowData);
		    mm.setColumnNames(header);
			JTable table = new JTable(mm)
		    {
				 @Override
				   public TableCellRenderer getCellRenderer(int row, int column) {
				    // TODO Auto-generated method stub
				    return new RiskMatrixCellRenderer();
				   }
			};
			
			TableColumnModel cm = table.getColumnModel();
			
			
			MyTableModel inst = new MyTableModel();
			Vector<Vector> inst_content = new Vector<Vector>();
			inst_content.add(new Vector());
			if(rm.eventID.length() > 0)
				inst_content.get(0).add(RiskMatrixCellRenderer.CHAR_WORST+" Worst, "+RiskMatrixCellRenderer.CHAR_MOST+" Most, "+RiskMatrixCellRenderer.CHAR_CURRENT+" Current");
			else
				inst_content.get(0).add(RiskMatrixCellRenderer.CHAR_WORST+" Worst, "+RiskMatrixCellRenderer.CHAR_MOST+" Most");
			inst.setData(inst_content);
			Vector<String> inst_column = new Vector<String>();
			inst_column.add("");
			inst.setColumnNames(inst_column);
			JTable table_inst = new JTable(inst){
				@Override
				   public TableCellRenderer getCellRenderer(int row, int column) {
				    // TODO Auto-generated method stub
				    return new RiskMatrixCellRenderer();
				   }
			};
			
			table_inst.setRowHeight(inst_height);
			table_inst.setPreferredSize(new Dimension(cellWidth*7, inst_height));
			
			
			MyTableModel inst2 = new MyTableModel(true);
			Vector<Vector> inst_content2 = new Vector<Vector>();
			inst_content2.add(new Vector());
			
			JTextArea idArea = new JTextArea();
			
			
			
			String explanation = "";
			if(rm.eventID.length() > 0)
				explanation = "Risk Matrix for EVENT ID<"+rm.eventID+">, Hazard<"+rm.hazardID+">, M.F. = "+Float.toString(rm.mf);
			else
				explanation = "Risk Matrix for Hazard <"+rm.hazardID+">";
			
			idArea.setPreferredSize(new Dimension(tableWidth-2, 60));
			idArea.setLineWrap(true);
			idArea.setWrapStyleWord(true);
			idArea.setBackground(RiskMatrixCellRenderer.evenRowBG);
			idArea.setText(explanation);
			idArea.setEditable(false);
			
			JPanel areaPanel = new JPanel();
			areaPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			areaPanel.setLayout(new BorderLayout());
			areaPanel.add("Center", idArea);
			
			inst_content2.get(0).add(explanation);
			
			inst2.setData(inst_content2);
			Vector<String> inst_column2 = new Vector<String>();
			inst_column2.add("");
			inst2.setColumnNames(inst_column);
			JTable table_inst2 = new JTable(inst2){
				@Override
				   public TableCellRenderer getCellRenderer(int row, int column) {
				    // TODO Auto-generated method stub
				    return new RiskMatrixCellRenderer(15);
				   }
			};
			
			
			table_inst2.setRowHeight(inst_height2);
			table_inst2.setPreferredSize(new Dimension(tableWidth, inst_height2));
			
			
			
			table.setRowHeight(cellWidth);
			table.setPreferredSize(new Dimension(tableWidth, tableWidth));
//			JPanel tp = new JPanel();
//			tp.setLayout(new FlowLayout());
//			tp.add(table);
			

			
			
			//panel_main.setBorder(new TitledBorder(""));
			
			
			panel_main.add(table_inst);
			
//			JPanel tPanel = new JPanel();
//			tPanel.setLayout(new BorderLayout());
//			tPanel.add("Center", table);
			//tPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			panel_main.add(table);
			//panel_main.add(table_inst2);
			panel_main.add(areaPanel);
			JLabel padding = new JLabel("");
			padding.setPreferredSize(new Dimension(10, pad_height));
			panel_main.add(padding);
		}
		

		
		
		JPanel panel_main2 = new JPanel();
		panel_main2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel_main2.add(panel_main);
		JScrollPane sPane = new JScrollPane(panel_main2);
		
		
		int pane_height = (inst_height + inst_height2 + tableWidth)*rmData.size()+pad_height*(rmData.size()-1);
				
		
		panel_main.setPreferredSize(new Dimension(tableWidth, pane_height));		

		//sPane.setPreferredSize(new Dimension(800, 400));
		
		return sPane;
	}
	
//	public static void main(String[] argv) throws IOException{
//		String inputPath = "E:/ext_work/respace/workspace/CTS_analysis/input/Process2.xls";
//		String inputSheetName = "input data";
//		int descriptor_depth = 3;
//		
//		//Load from excel file
//		WarningAnalysisLoadExcel load = new WarningAnalysisLoadExcel(inputPath, inputSheetName, descriptor_depth);
//		WarningAnalysisInputData waInputData = load.getWaInputData();
//			
//		
//		//Analysis
//		int totalDeparture = 192847;
//		WarningAnalyzer wa = new WarningAnalyzer(waInputData, totalDeparture, "20080118");
//		//Get Result data
//		WarningAnalysisResultData waResultData = wa.getWaResultData();
//		
//		JFrame frame = new JFrame("");
//		SafetyAnalysisRiskMatrix rm = new SafetyAnalysisRiskMatrix();
//		frame.add(rm.createRiskMatrix(waResultData, "", "Aircraft-Aircraft handling/service-(general)", 2));
//		frame.setVisible(true);
//		frame.setSize(1024, 768);
//	}
}
