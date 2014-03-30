package ac.kaist.analysis.graphic;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import swing.ax.CustomCellRenderer;
import swing.ax.MyTableModel;
import swing.ax.RiskMatrixCellRenderer;
import swing.ax.VerticalFlowLayout;
import ac.kaist.analysis.WarningAnalyzer;
import ac.kaist.analysis.model.WarningAnalysisInputData;
import ac.kaist.analysis.model.WarningAnalysisResultData;
import ac.kaist.analysis.utils.WarningAnalysisLoadExcel;

public class SafetyAnalysisRiskMatrix {
	
	private class RiskMatrixData{
		public RiskMatrixData(String eventID, String hazardID, int worst, int most, int today, int likelihood, float mf){
			this.eventID = eventID;
			this.hazardID = hazardID;
			this.worst = worst;
			this.most = most;
			this.today = today;
			this.liklihood = likelihood;
			this.mf = mf;
		}
		float mf;
		int liklihood;
		int worst;
		int most;
		int today;
		String eventID;
		String hazardID;
	}
	
	private Vector<RiskMatrixData> getRiskMatrixData(WarningAnalysisResultData waResultData, String ev_id, String hz_id, int type){
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
				System.out.println("mf:"+mf);
				int liklihood = waResultData.getLikelihoodQuantizedMap().get(hz_ID);
				int worst = waResultData.getWorstSeverityMap().get(hz_ID);
				int most = waResultData.getWorstSeverityMap().get(hz_ID);
				int today = waResultData.getTodayWorstSeverity().get(hz_ID);
				
				System.out.println(ev_id+" "+hz_ID+" "+ worst+" "+most+" "+today+" type:"+type);
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
	public JScrollPane createRiskMatrix(WarningAnalysisResultData waResultData, String ev_id, String hz_id, int type){
				
		
		Vector<RiskMatrixData> rmData= getRiskMatrixData(waResultData, ev_id, hz_id, type);
		//rmData.add(new RiskMatrixData("a", "B", 3, 5, 4, 3, 0.5f));
		//rmData.add(new RiskMatrixData("aa", "Bb", 3, 5, 4, 3, 0.5f));
		
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new VerticalFlowLayout());
		

		int cellWidth = 70;
		int inst_height = 40;
		int inst_height2 = 40;
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
			
			if(rm.eventID.length() > 0)
				inst_content2.get(0).add("Risk Matrix for EVENT ID<"+rm.eventID+">, Hazard<"+rm.hazardID+">, M.F. = "+Float.toString(rm.mf));
			else
				inst_content2.get(0).add("Risk Matrix for Hazard <"+rm.hazardID+">");
			
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
			panel_main.add(table);
			panel_main.add(table_inst2);
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
	
	public static void main(String[] argv) throws IOException{
		String inputPath = "E:/ext_work/respace/workspace/CTS_analysis/input/Process2.xls";
		String inputSheetName = "input data";
		int descriptor_depth = 3;
		
		//Load from excel file
		WarningAnalysisLoadExcel load = new WarningAnalysisLoadExcel(inputPath, inputSheetName, descriptor_depth);
		WarningAnalysisInputData waInputData = load.getWaInputData();
			
		
		//Analysis
		int totalDeparture = 192847;
		WarningAnalyzer wa = new WarningAnalyzer(waInputData, totalDeparture, "20080118");
		//Get Result data
		WarningAnalysisResultData waResultData = wa.getWaResultData();
		
		JFrame frame = new JFrame("");
		SafetyAnalysisRiskMatrix rm = new SafetyAnalysisRiskMatrix();
		frame.add(rm.createRiskMatrix(waResultData, "", "Aircraft-Aircraft handling/service-(general)", 2));
		frame.setVisible(true);
		frame.setSize(1024, 768);
	}
}
