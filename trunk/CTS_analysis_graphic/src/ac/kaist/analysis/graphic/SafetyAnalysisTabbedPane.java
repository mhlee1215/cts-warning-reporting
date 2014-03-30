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
        
        //jp_inputData.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));
        jp_inputData.setLayout(new BorderLayout());
        JLabel pad1 = new JLabel();
        pad1.setPreferredSize(new Dimension(10, 10));
        jp_inputData.add("Center", jp1_p);
        jp_inputData.add("North", pad1);

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
        btnDefault.setPreferredSize(new Dimension(100, 20));
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
        btnApply.setPreferredSize(new Dimension(100, 20));
        btnApply.setEnabled(false);
        btnApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            	LocalDate analStartDate = new LocalDate(textFieldYYYYs.getText()+"-"+textFieldMMs.getText()+"-"+textFieldDDs.getText());
            	LocalDate analEndDate = new LocalDate(textFieldYYYYe.getText()+"-"+textFieldMMe.getText()+"-"+textFieldDDe.getText());
            	
            	
            	//Get Parmas
            	totalDeparture = Integer.parseInt(textFieldMonthlyDeparture.getText());
            	//Analysis
        		
        		WarningAnalyzer wa = new WarningAnalyzer(waInputData, totalDeparture, "20080118", analyzeStartDate, analyzeEndDate);
        		//Get Result data
        		waResultData = wa.getWaResultData();
                //Some action for notification about loaded file.
            	
            	jtp_main.setEnabledAt(1, true);
            	jtp_main.setEnabledAt(2, true);
                jtp_main.setEnabledAt(3, true);
                jtp_main.setEnabledAt(4, true);
            	
                //Trend Analysis
            	SafetyAnalysisTrendAnalysis ta = new SafetyAnalysisTrendAnalysis(waResultData, waInputData, analStartDate, analEndDate, textFieldSelectPath.getText());
				jp_trendAnalysis.setLayout(new BorderLayout());
				jp_trendAnalysis.removeAll();
				jp_trendAnalysis.add("Center", ta.createPanel());
            	

                //Chromatography
                SafetyAnalysisChromatography sac = new SafetyAnalysisChromatography(waResultData, textFieldSelectPath.getText());
				jp_chromatography.setLayout(new BorderLayout());
				jp_chromatography.removeAll();
				jp_chromatography.add("Center", sac.createPanel());
				
				
				//RiskMatrix
				SafetyAnalysisRiskMatrix sarm = new SafetyAnalysisRiskMatrix(waResultData, textFieldSelectPath.getText());
				jp_riskMatrix.setLayout(new BorderLayout());
				jp_riskMatrix.removeAll();
				jp_riskMatrix.add("Center",sarm.createPanel());
								
				
				//Scoring Method
				SafetyAnalysisScoringMethod sasm = new SafetyAnalysisScoringMethod(waResultData, textFieldSelectPath.getText());
				jp_scoringMethod.setLayout(new BorderLayout());
				jp_scoringMethod.removeAll();
				jp_scoringMethod.add("Center", sasm.createPanel());
				
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
    	    		.addComponent(buttonOpenFile, 20, 20, 20)
    	    		.addComponent(textFieldOpenFile)
    	    	)
    	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    	    		.addComponent(buttonSelectPath, 20, 20, 20)
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
					.addComponent(buttonAnalyzePeriod, 20, 20, 20)
						
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
        				.addComponent(textFieldMonthlyDeparture, 100, 100, 100)
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