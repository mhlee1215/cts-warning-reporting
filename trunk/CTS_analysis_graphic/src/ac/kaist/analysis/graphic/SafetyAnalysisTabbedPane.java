package ac.kaist.analysis.graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.joda.time.LocalDate;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import swing.ax.VerticalFlowLayout;
import ac.kaist.analysis.WarningAnalyzer;
import ac.kaist.analysis.model.WarningAnalysisInputData;
import ac.kaist.analysis.model.WarningAnalysisResultData;
import ac.kaist.analysis.utils.WarningAnalysisLoadExcel;

public class SafetyAnalysisTabbedPane extends JFrame {
    
	JTextField textFieldOpenFile;
    JTextField textFieldSelectPath;
    
    int analyzerPeriodCnt = 0;
    
    JButton buttonAnalyzePeriod;
    JTabbedPane jtp_main;
    
    JTextField textFieldYYYYs;
    JTextField textFieldMMs;
    JTextField textFieldDDs;
    JTextField textFieldYYYYe;
    JTextField textFieldMMe;
    JTextField textFieldDDe;
    
    WarningAnalysisInputData waInputDataDefault;
    WarningAnalysisInputData waInputData;
    WarningAnalysisResultData waResultData;
    
    int totalDeparture = 192847;
    
    LocalDate analyzeEndDate;
    LocalDate analyzeStartDate;
    
    JTextField textFieldMonthlyDeparture;
    JTextField tfInjuryFATL;
    JTextField tfInjurySERS;
    JTextField tfInjuryMINR;
    JTextField tfInjuryNONE;
    JTextField tfDamageDEST;
    JTextField tfDamageSUBS;
    JTextField tfDamageMINR;
    JTextField tfDamageNONE;
    
    JPanel jp_inputData;
    JPanel jp_trendAnalysis;
    JPanel jp_riskMatrix;
    JPanel jp_scoringMethod;
    JPanel jp_chromatography;
    
    JFreeChart chromatographyChart = null;
    ChartPanel chromatographyChartpanel;
    JFreeChart scoringMethodChart = null;
	ChartPanel scoringMethodChartpanel = null;
	
	JButton btnApply;
	JButton btnDefault;
	
	JLabel labelPeriodValue;
	
	//Analyze Period 
	LocalDate inputStartDate;
    LocalDate inputEndDate;
    
    JComboBox cbEvent;
    JComboBox cbHazard;
    JPanel riskPanel4;
    int riskType = 1;
    
    @Override
    public void setSize(Dimension d) {
    	// TODO Auto-generated method stub
    	super.setSize(d);
    	System.out.println("hi");
    }
    public SafetyAnalysisTabbedPane() {
        
        setTitle("Aviation Safety Data Analysis Package");
        jtp_main = new JTabbedPane();
        getContentPane().add(jtp_main);
        
        jp_inputData = new JPanel();
        jp_trendAnalysis = new JPanel();
        jp_riskMatrix = new JPanel();
        jp_scoringMethod = new JPanel();
        jp_chromatography = new JPanel();
        
        //Input Data pane

        
        //Input Data pane
        JPanel jp1_p = new JPanel();
        jp1_p.setPreferredSize(new Dimension(600, 300));
        
        GroupLayout layout = new GroupLayout(jp1_p);
        jp1_p.setLayout(layout);
        
        jp_inputData.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));
        jp_inputData.add(jp1_p);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        JButton buttonOpenFile = new JButton("Open File");
        buttonOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	JFileChooser fileChooser = new JFileChooser();
            	
            	 /* Enabling Multiple Selection */
                //fileChooser.setMultiSelectionEnabled(true);

                /* Setting Current Directory */
                fileChooser.setCurrentDirectory(new File("E:/ext_work/respace/workspace/CTS_analysis/input"));
                
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel 97 File","xls"));
                
                JFrame parent = new MenuMain();
                
                
                int returnVal = fileChooser.showDialog(parent, "Open File Path");;
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                	textFieldOpenFile.setText(fileChooser.getSelectedFile().getAbsolutePath());
                	textFieldSelectPath.setText(fileChooser.getSelectedFile().getParent());
                    String inputPath = "E:/ext_work/respace/workspace/CTS_analysis/input/Process2.xls";//fileChooser.getSelectedFile().getAbsolutePath()
            		String inputSheetName = "input data";
            		int descriptor_depth = 4;
            		
            		//Load from excel file
            		WarningAnalysisLoadExcel load = null;
    				try {
    					load = new WarningAnalysisLoadExcel(inputPath, inputSheetName, descriptor_depth);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
            		waInputData = load.getWaInputData();
            		waInputDataDefault = waInputData;
            			
            		tfInjuryFATL.setText(Float.toString(waInputData.getInjuryWeight().get("FATL")));
            		tfInjurySERS.setText(Float.toString(waInputData.getInjuryWeight().get("SERS")));
            		tfInjuryMINR.setText(Float.toString(waInputData.getInjuryWeight().get("MINR")));
            		tfInjuryNONE.setText(Float.toString(waInputData.getInjuryWeight().get("NONE")));
            		
            		System.out.println(waInputData.getDamageWeight());
            		tfDamageDEST.setText(Float.toString(waInputData.getDamageWeight().get("DEST")));
            		tfDamageSUBS.setText(Float.toString(waInputData.getDamageWeight().get("SUBS")));
            		tfDamageMINR.setText(Float.toString(waInputData.getDamageWeight().get("MINR")));
            		tfDamageNONE.setText(Float.toString(waInputData.getDamageWeight().get("NONE")));
            		//tfInjuryFATL = new JTextField();
//            		JTextField tfInjurySERS;
//            	    JTextField tfInjuryMINR;
//            	    JTextField tfInjuryNONE;
//            	    JTextField tfDamageDSET;
//            	    JTextField tfDamageSUBS;
//            	    JTextField tfDamageMINR;
//            	    JTextField tfDamageNONE;
            		
            		
            		jtp_main.setEnabledAt(1, false);
                    jtp_main.setEnabledAt(2, false);
                    jtp_main.setEnabledAt(3, false);
                    jtp_main.setEnabledAt(4, false);   
                    
                    
                    buttonAnalyzePeriod.setEnabled(true);
                    btnDefault.setEnabled(true);
                    btnApply.setEnabled(true);
                    
                    inputStartDate = waInputData.getInputStartDate();
                    inputEndDate = waInputData.getInputEndDate();
                    
                    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd");
                    labelPeriodValue.setText(formatter.print(inputStartDate)+"~"+formatter.print(inputEndDate));
                    
                    analyzeEndDate = inputEndDate;//new LocalDate();
                    textFieldYYYYe.setText(Integer.toString(analyzeEndDate.getYear()));
                    textFieldMMe.setText(Integer.toString(analyzeEndDate.getMonthOfYear()));
                    textFieldDDe.setText(Integer.toString(analyzeEndDate.getDayOfMonth()));
                    analyzeStartDate = analyzeEndDate.plusMonths(-1);
                    if(analyzeStartDate.compareTo(inputStartDate) < 0){
                    	analyzeStartDate = inputStartDate;
                    }
                    textFieldYYYYs.setText(Integer.toString(analyzeStartDate.getYear()));
                    textFieldMMs.setText(Integer.toString(analyzeStartDate.getMonthOfYear()));
                    textFieldDDs.setText(Integer.toString(analyzeStartDate.getDayOfMonth()));
                }
                
                
                
            }
        });
        JButton buttonSelectPath = new JButton("Select Path");
        buttonSelectPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	JFileChooser fileChooser = new JFileChooser();
            	
            	 /* Enabling Multiple Selection */
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                
                /* Setting Current Directory */
                fileChooser.setCurrentDirectory(new File(textFieldSelectPath.getText()));
                
                int returnVal = fileChooser.showDialog(new MenuMain(), "Export File Path");
                
                
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    textFieldSelectPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
                //Some action for notification about loaded file.
            }
        });
        textFieldOpenFile = new JTextField();
        textFieldOpenFile.setEditable(false);
        textFieldOpenFile.setText("Input Data Path");
        textFieldSelectPath = new JTextField();
        textFieldSelectPath.setEditable(false);
        textFieldSelectPath.setText("Export Data Path");
        
        JLabel labelPeriod = new JLabel("Period of Data :");
        labelPeriodValue = new JLabel("YYYY/MM/DD ~ YYYY/MM/DD");
        
        JLabel labelAnalyzePeriod = new JLabel("Period of Analyze :");
        textFieldYYYYs = new JTextField();
        textFieldMMs = new JTextField();
        textFieldDDs = new JTextField();
        JLabel labelTilde = new JLabel(" ~ ");
        textFieldYYYYe = new JTextField();
        textFieldMMe = new JTextField();
        textFieldDDe = new JTextField();
        
        
        //MutableDateTime mdt = new Mutable
        
        buttonAnalyzePeriod = new JButton("Recent 1 Month");
        buttonAnalyzePeriod.setEnabled(false);
        buttonAnalyzePeriod.setSize(new Dimension(200, 10));
        buttonAnalyzePeriod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            	analyzerPeriodCnt++;
            	
            	if (analyzerPeriodCnt == 6) analyzerPeriodCnt = 0;
            	
            	if(analyzerPeriodCnt == 0){
            		buttonAnalyzePeriod.setText("Recent 1 Month");
	            	analyzeStartDate = analyzeEndDate.plusMonths(-1);
            	}else if(analyzerPeriodCnt == 1){
            		buttonAnalyzePeriod.setText("Recent 3 Month");
            		analyzeStartDate = analyzeEndDate.plusMonths(-3);
            	}else if(analyzerPeriodCnt == 2){
            		buttonAnalyzePeriod.setText("Recent 6 Month");
            		analyzeStartDate = analyzeEndDate.plusMonths(-6);
            	}else if(analyzerPeriodCnt == 3){
            		buttonAnalyzePeriod.setText("Recent 1 Year ");
            		analyzeStartDate = analyzeEndDate.plusYears(-1);	                
            	}else if(analyzerPeriodCnt == 4){
            		buttonAnalyzePeriod.setText("Recent 3 Year ");
            		analyzeStartDate = analyzeEndDate.plusYears(-3);
            	}else if(analyzerPeriodCnt == 5){
            		buttonAnalyzePeriod.setText("Full Period");
            		analyzeStartDate = analyzeEndDate.plusYears(-9999);
	               
            	}
            	
            	 if(analyzeStartDate.compareTo(inputStartDate) < 0){
                 	analyzeStartDate = inputStartDate;
                 }
            	 
            	 textFieldYYYYs.setText(Integer.toString(analyzeStartDate.getYear()));
	             textFieldMMs.setText(Integer.toString(analyzeStartDate.getMonthOfYear()));
	             textFieldDDs.setText(Integer.toString(analyzeStartDate.getDayOfMonth()));
            }
        });

        JPanel bPanel = new JPanel();
        bPanel.setBorder(new TitledBorder(""));
        
        JLabel labelMonthlyDeparture = new JLabel("Monthly Departure : ");
        textFieldMonthlyDeparture = new JTextField();
        textFieldMonthlyDeparture.setText(Integer.toString(totalDeparture));

        JLabel labelBlank1 = new JLabel("");
        JLabel labelBlank2 = new JLabel("");
        JLabel labelInjuryWeight = new JLabel("Human Injury Severity Weight (Mil$) ");
        JLabel labelDamageWeight = new JLabel("Aircraft Damage Seveirty Weight (Mil$) ");
        JLabel labelFATL = new JLabel("FATL");
        JLabel labelSERS = new JLabel("SERS");
        JLabel labelMINR = new JLabel("MINR");
        JLabel labelNONE = new JLabel("NONE");
        JLabel labelDEST = new JLabel("DEST");
        JLabel labelSUBS = new JLabel("SUBS");
        JLabel labelMINR2 = new JLabel("MINR");
        JLabel labelNONE2 = new JLabel("NONE");
        tfInjuryFATL = new JTextField();
        tfInjurySERS = new JTextField();
        tfInjuryMINR = new JTextField();
        tfInjuryNONE = new JTextField();
        tfDamageDEST = new JTextField();
        tfDamageSUBS = new JTextField();
        tfDamageMINR = new JTextField();
        tfDamageNONE = new JTextField();
        
        btnDefault = new JButton("Default");
        btnDefault.setEnabled(false);
        btnDefault.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	waInputData = waInputDataDefault;
    			
        		tfInjuryFATL.setText(Float.toString(waInputData.getInjuryWeight().get("FATL")));
        		tfInjurySERS.setText(Float.toString(waInputData.getInjuryWeight().get("SERS")));
        		tfInjuryMINR.setText(Float.toString(waInputData.getInjuryWeight().get("MINR")));
        		tfInjuryNONE.setText(Float.toString(waInputData.getInjuryWeight().get("NONE")));
        		
        		System.out.println(waInputData.getDamageWeight());
        		tfDamageDEST.setText(Float.toString(waInputData.getDamageWeight().get("DEST")));
        		tfDamageSUBS.setText(Float.toString(waInputData.getDamageWeight().get("SUBS")));
        		tfDamageMINR.setText(Float.toString(waInputData.getDamageWeight().get("MINR")));
        		tfDamageNONE.setText(Float.toString(waInputData.getDamageWeight().get("NONE")));
            }
        });
        btnApply = new JButton("Apply");
        btnApply.setEnabled(false);
        btnApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            	//Get Parmas
            	totalDeparture = Integer.parseInt(textFieldMonthlyDeparture.getText());
            	//Analysis
        		
        		WarningAnalyzer wa = new WarningAnalyzer(waInputData, totalDeparture, "20080118", analyzeStartDate, analyzeEndDate);
        		//Get Result data
        		waResultData = wa.getWaResultData();
                //Some action for notification about loaded file.
            	
            	jtp_main.setEnabledAt(1, true);
            	
            	SafetyAnalysisTrendAnalysis ta = new SafetyAnalysisTrendAnalysis(waResultData, waInputData, inputStartDate, inputEndDate);
            	JScrollPane trendTable = ta.createTable();
				JPanel periodPanel = new JPanel();
				periodPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
				JLabel p = new JLabel("Analyze Period : ");
            	JLabel p2 = new JLabel(textFieldYYYYs.getText()+"/"+textFieldMMs.getText()+"/"+textFieldDDs.getText()+" ~ "+textFieldYYYYe.getText()+"/"+textFieldMMe.getText()+"/"+textFieldDDe.getText());
            	
            	periodPanel.add(p);
            	periodPanel.add(p2);
            	
				jp_trendAnalysis.setLayout(new BorderLayout());
				jp_trendAnalysis.removeAll();
				jp_trendAnalysis.add("North", periodPanel);
				jp_trendAnalysis.add("Center", trendTable);
				
				JPanel jp_trendAnalysisButton = new JPanel();
				jp_trendAnalysisButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
				JButton buttonTrendAnalysisExport = new JButton("Export");
				
				
				buttonTrendAnalysisExport.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
//						try {
//							String outPath = textFieldSelectPath.getText();
//							
//						} catch (FileNotFoundException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						 
					}
				});
				
				jp_trendAnalysisButton.add(buttonTrendAnalysisExport);
				jp_trendAnalysis.add("South", jp_trendAnalysisButton);
            	
                jtp_main.setEnabledAt(2, true);
                jtp_main.setEnabledAt(3, true);
                jtp_main.setEnabledAt(4, true);
                
                
                
                //Chromatography
                chromatographyChart = SafetyAnalysisChromatography.createChart(waResultData);
				chromatographyChartpanel = new ChartPanel(chromatographyChart);
				jp_chromatography.setLayout(new BorderLayout());
				jp_chromatography.removeAll();
				jp_chromatography.add("Center", chromatographyChartpanel);
				
				JPanel jp_chromatographyButton = new JPanel();
				jp_chromatographyButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
				JButton buttonChromatographyExport = new JButton("Export");
				buttonChromatographyExport.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						Dimension size = chromatographyChartpanel.getSize();
						try {
							String outPath = textFieldSelectPath.getText();
							String filename = "chromatography.png";
							OutputStream os = new FileOutputStream(outPath+"/"+filename);
							System.out.println(outPath+"/"+filename+"///"+size.width + " " + size.height);
							BufferedImage chartImage = chromatographyChart.createBufferedImage( size.width, size.height, null);
							ImageIO.write( chartImage, "png", os );
							os.close();
							JOptionPane.showMessageDialog(null, "Chart image was saved in "+filename);
							
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 
					}
				});
				jp_chromatographyButton.add(buttonChromatographyExport);
				jp_chromatography.add("South", jp_chromatographyButton);
				
				
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
				cbEvent = new JComboBox(waInputData.getsEv_id().toArray());
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
				cbHazard = new JComboBox(waInputData.getsDesc().toArray());
				cbHazard.setEnabled(false);
				//JTextField tfHazard = new JTextField();
				cbHazard.setPreferredSize(new Dimension(300, 20));
				JPanel riskPanel_main = new JPanel();
				JPanel riskPanel = new JPanel();
				riskPanel.setPreferredSize(new Dimension(600, 100));
				riskPanel.setLayout(new VerticalFlowLayout()); 
				jp_riskMatrix.setLayout(new BorderLayout());
				jp_riskMatrix.add("North",riskPanel);
				
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
				riskPanel3.setBorder(new TitledBorder(""));
				riskPanel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
				JButton riskAnalyze = new JButton("Analyze");
				riskAnalyze.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						riskPanel4.removeAll();
						SafetyAnalysisRiskMatrix rm = new SafetyAnalysisRiskMatrix();
						//String[] ev_array = (String[]) waInputData.getsEv_id().toArray();
						String ev_id = (String) cbEvent.getItemAt(cbEvent.getSelectedIndex());
						//String[] hz_array = (String[]) waInputData.getsDesc().toArray();
						String hz_id = (String) cbHazard.getItemAt(cbHazard.getSelectedIndex());
						JScrollPane tablePane = rm.createRiskMatrix(waResultData, ev_id, hz_id,  riskType);
						riskPanel4.add(tablePane);
						riskPanel4.revalidate();
					}
					
				});
				riskAnalyze.setPreferredSize(new Dimension(100, 20));
				JButton riskExport = new JButton("Export");
				riskExport.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
				});
				riskExport.setPreferredSize(new Dimension(100, 20));
				riskPanel3.add(riskAnalyze);
				riskPanel3.add(riskExport);
				riskPanel.add(riskPanel3);
				
				riskPanel4 = new JPanel();
				riskPanel4.setBorder(new TitledBorder(""));
				//riskPanel4.setPreferredSize(new Dimension(300, 300));
//				SafetyAnalysisRiskMatrix rm = new SafetyAnalysisRiskMatrix();
//				JScrollPane tablePane = rm.createRiskMatrix(waResultData, "", 1);
//				riskPanel4.add(tablePane);
				//riskPanel.add(riskPanel4);
				jp_riskMatrix.add("Center",riskPanel4);
				
				
				
				
				scoringMethodChart = SafetyAnalysisScoringMethod.createChart(waResultData);
				scoringMethodChartpanel = new ChartPanel(scoringMethodChart);
				
				
				JScrollPane scoreTalbeP = SafetyAnalysisScoringMethod.createScoreTable(waResultData);
				//scoreTalbeP.setSize(new Dimension(500, 400));
				//jp_scoringMethod.add("West", scoreTalbeP);
				
				JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scoreTalbeP,scoringMethodChartpanel);
				sp.setDividerLocation(400);
				jp_scoringMethod.setLayout(new BorderLayout());
				jp_scoringMethod.removeAll();
				jp_scoringMethod.add("Center", sp);
				
				
				JPanel jp_scoringMethodButton = new JPanel();
				jp_scoringMethodButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
				JButton buttonScoringMethodExport = new JButton("Export");
				buttonScoringMethodExport.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						Dimension size = scoringMethodChartpanel.getSize();
						try {
							String outPath = textFieldSelectPath.getText();
							String filename = "scoringMethod.png";
							OutputStream os = new FileOutputStream(outPath+"/"+filename);
							BufferedImage chartImage = scoringMethodChart.createBufferedImage( size.width, size.height, null);
							ImageIO.write( chartImage, "png", os ); 
							os.close();
							JOptionPane.showMessageDialog(null, "Chart image was saved in "+filename);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				jp_scoringMethodButton.add(buttonScoringMethodExport);
				jp_scoringMethod.add("South", jp_scoringMethodButton);
                
                
            }
        });
        
        JPanel bBtnPanel = new JPanel();
        //bBtnPanel.setBorder(new TitledBorder(""));
        //bBtnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bBtnPanel.setLayout(new BorderLayout());
        
        JPanel bBtnPanel2 = new JPanel();
        bBtnPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //bBtnPanel2.setLayout(new BorderLayout());
        bBtnPanel2.add(btnDefault);
        bBtnPanel2.add(btnApply);
        
        bBtnPanel.add("East", bBtnPanel2);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
        	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(buttonOpenFile)
        				.addComponent(buttonSelectPath)
        				.addComponent(labelPeriod)
        				.addComponent(labelAnalyzePeriod)
        			)
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    					.addComponent(textFieldOpenFile)
    					.addComponent(textFieldSelectPath)
    					.addComponent(labelPeriodValue)
    					.addGroup(layout.createSequentialGroup()
    						.addComponent(textFieldYYYYs, 40, 40, 40)
    						.addComponent(textFieldMMs, 25, 25, 25)
    						.addComponent(textFieldDDs, 25, 25, 25)
    						.addComponent(labelTilde)
    						.addComponent(textFieldYYYYe, 40, 40, 40)
    						.addComponent(textFieldMMe, 25, 25, 25)
    						.addComponent(textFieldDDe, 25, 25, 25)
    						.addComponent(buttonAnalyzePeriod, 150, 150, 150)
    					)
        			)
        		)
	    		.addComponent(bPanel)
    		)
    		
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
    	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
    	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	    		.addComponent(buttonOpenFile)
    	    		.addComponent(textFieldOpenFile)
    	    	)
    	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	    		.addComponent(buttonSelectPath)
    	    		.addComponent(textFieldSelectPath)
    	    	)
    	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	    		.addComponent(labelPeriod)
    	    		.addComponent(labelPeriodValue)
    	    	)
    	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	    		.addComponent(labelAnalyzePeriod)
    	    		.addComponent(textFieldYYYYs)
					.addComponent(textFieldMMs)
					.addComponent(textFieldDDs)
					.addComponent(labelTilde)
					.addComponent(textFieldYYYYe)
					.addComponent(textFieldMMe)
					.addComponent(textFieldDDe)
					.addComponent(buttonAnalyzePeriod)
						
    	    	)
    	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	    		.addComponent(bPanel)
    	    	)
    	    			
    	);
        
        
        
        JPanel bbPanel = new JPanel();
        bbPanel.setBorder(new TitledBorder(""));
        bPanel.setLayout(new BorderLayout());       
        bPanel.add("Center", bbPanel);
        GroupLayout layout_b = new GroupLayout(bbPanel);
        bbPanel.setLayout(layout_b);

        layout_b.setAutoCreateGaps(true);
        layout_b.setAutoCreateContainerGaps(true);
        
        layout_b.setHorizontalGroup(layout_b.createSequentialGroup()
        		.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.LEADING)
        			.addGroup(layout_b.createSequentialGroup()
        				.addComponent(labelMonthlyDeparture)
        				.addComponent(textFieldMonthlyDeparture)
        			)
        			.addGroup(layout_b.createSequentialGroup()
        				.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.LEADING)
        					.addComponent(labelBlank1)
        					.addComponent(labelInjuryWeight)
        				)
        				.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.LEADING)
        					.addComponent(labelFATL)
        					.addComponent(tfInjuryFATL, 40, 40, 40)
        				)
        				.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.LEADING)
    						.addComponent(labelSERS)
        					.addComponent(tfInjurySERS, 40, 40, 40)
        				)
        				.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.LEADING)
    						.addComponent(labelMINR)
        					.addComponent(tfInjuryMINR, 40, 40, 40)
        				)
        				.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.LEADING)
    						.addComponent(labelNONE)
        					.addComponent(tfInjuryNONE, 40, 40, 40)
        				)
        			)
        			.addGroup(layout_b.createSequentialGroup()
    					.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.LEADING)
        					.addComponent(labelBlank2)
        					.addComponent(labelDamageWeight)
        				)
        				.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.LEADING)
        					.addComponent(labelDEST)
        					.addComponent(tfDamageDEST, 40, 40, 40)
        				)
        				.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.LEADING)
    						.addComponent(labelSUBS)
        					.addComponent(tfDamageSUBS, 40, 40, 40)
        				)
        				.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.LEADING)
    						.addComponent(labelMINR2)
        					.addComponent(tfDamageMINR, 40, 40, 40)
        				)
        				.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.LEADING)
    						.addComponent(labelNONE2)
        					.addComponent(tfDamageNONE, 40, 40, 40)
        				)
        			)
        			.addGroup(layout_b.createSequentialGroup()
        				.addComponent(bBtnPanel)
        				//.addComponent(btnApply)
        			)
            	)
        );
        
        layout_b.setVerticalGroup(layout_b.createSequentialGroup()
        	    .addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	    	.addComponent(labelMonthlyDeparture)      	 
        	    	.addComponent(textFieldMonthlyDeparture)
        	    )
        	    .addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	    	.addComponent(labelBlank1)
        	    	.addComponent(labelFATL)
        			.addComponent(labelSERS)
        			.addComponent(labelMINR)
        			.addComponent(labelNONE)
        	    )
        	    .addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	    	.addComponent(labelInjuryWeight)
        	    	.addComponent(tfInjuryFATL)
        	    	.addComponent(tfInjurySERS)
        	    	.addComponent(tfInjuryMINR)
        	    	.addComponent(tfInjuryNONE)
        	    )
        	    .addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	    	.addComponent(labelBlank2)
        	    	.addComponent(labelDEST)
        			.addComponent(labelSUBS)
        			.addComponent(labelMINR2)
        			.addComponent(labelNONE2)
        	    )
        	    .addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	    	.addComponent(labelDamageWeight)
        	    	.addComponent(tfDamageDEST)
        	    	.addComponent(tfDamageSUBS)
        	    	.addComponent(tfDamageMINR)
        	    	.addComponent(tfDamageNONE)
        	    )
        	    .addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	    		.addComponent(bBtnPanel)
    				//.addComponent(btnApply)
        	    )
        );
        
        

        //Trend Analysis
        
        //Risk Matrix
        
        //Scoring Method
        
        //Chromatography
        
        

        jtp_main.addTab("Input Data", jp_inputData);
        jtp_main.addTab("Trend Analysis", jp_trendAnalysis);
        jtp_main.addTab("Risk Matrix", jp_riskMatrix);
        jtp_main.addTab("Scoring Method", jp_scoringMethod);
        jtp_main.addTab("Chromatography", jp_chromatography);
        
        jtp_main.setEnabledAt(1, false);
        jtp_main.setEnabledAt(2, false);
        jtp_main.setEnabledAt(3, false);
        jtp_main.setEnabledAt(4, false);
        
        
        
        
    }
    public static void main(String[] args) {
        
        SafetyAnalysisTabbedPane tp = new SafetyAnalysisTabbedPane();
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setVisible(true);
        tp.setSize(1024, 600);
    }
}