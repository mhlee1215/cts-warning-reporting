package ac.kaist.analysis.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DefaultFormatterFactory;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.calendar.DatePickerFormatter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.joda.time.LocalDate;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import swing.ax.CustomCellRenderer;
import swing.ax.ImagePanel;
import swing.ax.VerticalFlowLayout;
import ac.kaist.analysis.WarningAnalyzer;
import ac.kaist.analysis.model.WarningAnalysisInputData;
import ac.kaist.analysis.model.WarningAnalysisResultData;
import ac.kaist.analysis.utils.WarningAnalysisLoadExcel;
import ac.kaist.analysis.utils.WarningAnalysisWriteExcel;

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
	
	JButton btnExport;
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
    
    JComboBox comboPivotColumn;
    int descriptor_depth = 4;
    
    JTextField textFieldPivotDate;
    JXDatePicker datePicker;
    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    JFrame mainFrame;
    WarningAnalysisLoadExcel load = null;
    
    Map<String, Set<String> > errColumns;
    JDialog  d5;
    
    @Override
    public void setSize(Dimension d) {
    	// TODO Auto-generated method stub
    	super.setSize(d);
    }
    
    private static WindowListener closeWindow = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            e.getWindow().dispose();
        }
    };
    
    private static void open(URI uri) {
	    if (Desktop.isDesktopSupported()) {
	      try {
	        Desktop.getDesktop().browse(uri);
	      } catch (IOException e) { /* TODO: error handling */ }
	    } else { /* TODO: error handling */ }
	  }
    
   
    
    public SafetyAnalysisTabbedPane() throws URISyntaxException {
    	
    	mainFrame = this;
        
        setTitle("Aviation Risk Identification and Assessment (ARIA) (v"+GlobalVariables.version+")");
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
        jp1_p.setBackground(Color.white);
        
        //jp_inputData.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));
        jp_inputData.setLayout(new BorderLayout());
        jp_inputData.setBackground(Color.white);
        
        final URI uri = new URI("http://true.kaist.ac.kr/");
        
        class OpenUrlAction implements ActionListener {
            @Override public void actionPerformed(ActionEvent e) {
              open(uri);
            }
          }
        
        JButton goWebButton = new JButton();
        goWebButton.setText("<HTML>http://true.kaist.ac.kr</HTML>");
        goWebButton.setPreferredSize(new Dimension(135, 20));
        goWebButton.setHorizontalAlignment(SwingConstants.LEFT);
        goWebButton.setBorderPainted(false);
        goWebButton.setOpaque(false);
        goWebButton.setBackground(Color.WHITE);
        goWebButton.setToolTipText(uri.toString());
        goWebButton.addActionListener(new OpenUrlAction());
        
        
        
        JTextField webUrl = new JTextField("aaa");
        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        urlPanel.setBackground(Color.white);
        urlPanel.add(goWebButton);
        
        ImagePanel logoPane = new ImagePanel();
        logoPane.setPreferredSize(new Dimension(585, 35));
        JPanel logoPanel= new JPanel();
        logoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        logoPanel.setBackground(Color.white);
        logoPanel.add(logoPane);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.white);
        
        
        
        
        topPanel.add("East", logoPanel);
        topPanel.add("South", urlPanel);
        
        //JButton button = new JButton(new ImageIcon(getClass().getResource("logo.jpg")));
        
        JLabel pad1 = new JLabel();
        pad1.setPreferredSize(new Dimension(10, 10));
        jp_inputData.add("Center", jp1_p);
        jp_inputData.add("South", topPanel);
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
                
                //JFrame parent = new MenuMain();
                
                
                int returnVal = fileChooser.showDialog(new JFrame(), "Open File Path");;
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                	
                	
                	
                	textFieldOpenFile.setText(fileChooser.getSelectedFile().getAbsolutePath());
                	textFieldSelectPath.setText(fileChooser.getSelectedFile().getParent());
                    //String inputPath = "E:/ext_work/respace/workspace/CTS_analysis/input/Process2.xls";//fileChooser.getSelectedFile().getAbsolutePath()
                    String inputPath = fileChooser.getSelectedFile().getAbsolutePath();
            		String inputSheetName = "input data";
            		
            		
            		//Load from excel file
            		
    				try {
    					load = new WarningAnalysisLoadExcel(inputPath, inputSheetName);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (Exception e){
    					e.printStackTrace();
    					JOptionPane.showMessageDialog(null, "Wrong Input Data. Please try to use proper input file.");
    					return;
    				}
            		waInputData = load.getWaInputData();
            		waInputDataDefault = waInputData;
            			
            		tfInjuryFATL.setText(Float.toString(waInputData.getInjuryWeight().get("FATL")));
            		tfInjurySERS.setText(Float.toString(waInputData.getInjuryWeight().get("SERS")));
            		tfInjuryMINR.setText(Float.toString(waInputData.getInjuryWeight().get("MINR")));
            		tfInjuryNONE.setText(Float.toString(waInputData.getInjuryWeight().get("NONE")));
            		
            		//System.out.println(waInputData.getDamageWeight());
            		tfDamageDEST.setText(Float.toString(waInputData.getDamageWeight().get("DEST")));
            		tfDamageSUBS.setText(Float.toString(waInputData.getDamageWeight().get("SUBS")));
            		tfDamageMINR.setText(Float.toString(waInputData.getDamageWeight().get("MINR")));
            		tfDamageNONE.setText(Float.toString(waInputData.getDamageWeight().get("NONE")));
            		
            		
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
                    
                    //comboPivotColumn = new JComboBox(waInputData.getFactors().toArray());
                    for(String c : waInputData.getFactors()){
                    	comboPivotColumn.addItem(c);	
                    }
                    comboPivotColumn.setSelectedIndex(descriptor_depth-1);
                	comboPivotColumn.setEnabled(true);
                	jp_inputData.revalidate();
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
                
                int returnVal = fileChooser.showDialog(new JFrame(), "Export File Path");
                
                
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
        
        
        JLabel labelPivotColumn = new JLabel("Pivot Column : ");
        comboPivotColumn = new JComboBox();
        comboPivotColumn.setEnabled(false);
        
        JLabel labelPivotDate = new JLabel("Pivot Date : ");
        textFieldPivotDate = new JTextField();
        textFieldPivotDate.setAlignmentX(RIGHT_ALIGNMENT);
                
        datePicker = new JXDatePicker(new Date());
        
          
        textFieldPivotDate.setText(format.format(datePicker.getDate()));
          
        datePicker.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textFieldPivotDate.setText(format.format(datePicker.getDate()));
        	}
        });
  
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
        		
        		//System.out.println(waInputData.getDamageWeight());
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
            	//Check Data Validity.
       	
            	
            	errColumns = load.dataValidityCheck();
            	
            	if(errColumns.size() > 0){
            	    
            	    Vector<Vector> rowData = new Vector<Vector>();
            		for(String s : errColumns.keySet()){
            			Vector oneRow = new Vector();
            			oneRow.add(s);
            			oneRow.add(errColumns.get(s));

            			rowData.add(oneRow);
            			
            		}
            				

            	    Vector<String> columnNames = new Vector<String>();
            	    columnNames.addElement("Event_ID");
            	    columnNames.addElement("Inconsistent column(s)");

            		
            		//DefaultTableModel model = new DefaultTableModel(data, columnNames);
            		JTable table = new JTable(rowData, columnNames){
            			 @Override
            			   public TableCellRenderer getCellRenderer(int row, int column) {
            			    // TODO Auto-generated method stub
            			    return new CustomCellRenderer();
            			   }
            		};
            		
            		TableColumnModel cm = table.getColumnModel();
            	    cm.getColumn(0).setPreferredWidth(10);
            	    
            	    table.getTableHeader().setForeground(Color.white);
            	    table.getTableHeader().setBackground(CustomCellRenderer.headerBG);
            		table.setAutoCreateRowSorter(true);
            		JScrollPane scrollPane = new JScrollPane( table );
            		
            		          		

            		int dw = 500;
                    int dh = 300;
                    
                    
                    
                	d5 = new JDialog(mainFrame, "Data Inconsistency Detected", Dialog.ModalityType.DOCUMENT_MODAL);
                	d5.setBounds(mainFrame.getLocation().x+mainFrame.getBounds().width/2-dw/2, mainFrame.getLocation().y+mainFrame.getBounds().height/2-dh/2, dw, dh);
                	d5.addWindowListener(closeWindow);
                    JLabel l5 = new JLabel("Inconsistent data row(s) are represented as follows. Please check the data file.");
                    l5.setHorizontalAlignment(SwingConstants.CENTER);

                    JButton b5 = new JButton("Stop");
                    b5.setHorizontalAlignment(SwingConstants.CENTER);
                    b5.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	//Do nothing.
                        	JOptionPane.showMessageDialog(mainFrame,
                        		    "Process was stopped.",
                        		    "Parsing error",
                        		    JOptionPane.ERROR_MESSAGE);
                        	d5.setVisible(false);                        	
                        	enableTabs(false);
                        }
                    });
                    JButton b6 = new JButton("Ignore inconsistent data");
                    b6.setHorizontalAlignment(SwingConstants.CENTER);
                    b6.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	JOptionPane.showMessageDialog(mainFrame,
                        		    "Inconsistent data will be ignored in the results.",
                        		    "Ignore inconsistent data",
                        		    JOptionPane.WARNING_MESSAGE);
                        	waInputData.setErrColumns(errColumns);
                        	generateTabs(errColumns);
                        	d5.setVisible(false);
                        }
                    });
                    Container cp5 = d5.getContentPane();
                    // add label, text field and button one after another into a single column
                    cp5.setLayout(new BorderLayout());
                    cp5.add(l5, BorderLayout.NORTH);
                    cp5.add(scrollPane, BorderLayout.CENTER);
                    JPanel p5 = new JPanel();
                    p5.setLayout(new FlowLayout());
                    p5.add(b5);
                    p5.add(b6);
                    cp5.add(p5, BorderLayout.SOUTH);
                    d5.setVisible(true);
            	}
            	else 
            		generateTabs(null);
                
				
            }
        });
        
        btnExport = new JButton("Export");
        btnExport.setPreferredSize(new Dimension(100, 20));
        btnExport.setEnabled(false);
        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
            	String outputPath = textFieldSelectPath.getText()+"/AnalyzeResult.xls";//"E:/ext_work/respace/workspace/CTS_analysis/input/Process_out3.xls";
        		try {
        			//System.out.println(waResultData.getOccurrenceMatrix());
        			//System.out.println(waResultData.getMFDescMatrix());
					WarningAnalysisWriteExcel waWrite = new WarningAnalysisWriteExcel(outputPath, waInputData, waResultData);
					waWrite.write();
					JOptionPane.showMessageDialog(null, "Analyze result was saved in "+outputPath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
        bBtnPanel2.add(btnExport);
        
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
            				.addComponent(labelPivotColumn, 120, 120, 120)
            				.addComponent(comboPivotColumn, 200, 200, 200)
            		)
            		.addGroup(layout_b.createSequentialGroup()
            				.addComponent(labelPivotDate, 120, 120, 120)
            				.addComponent(textFieldPivotDate, 100, 100, 100)
            				.addComponent(datePicker, 30, 30, 30)
            		)
        			.addGroup(layout_b.createSequentialGroup()
        				.addComponent(labelMonthlyDeparture, 120, 120, 120)
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
            	    	.addComponent(labelPivotColumn)      	 
            	    	.addComponent(comboPivotColumn, 20, 20, 20)
            	)
            	.addGroup(layout_b.createParallelGroup(GroupLayout.Alignment.BASELINE)
            	    	.addComponent(labelPivotDate)      	 
            	    	.addComponent(textFieldPivotDate, 20, 20, 20)
            	    	.addComponent(datePicker, 20, 20, 20)
            	)
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
        
        enableTabs(false);

    }
    
    public void enableTabs(boolean isEnable){
    	jtp_main.setEnabledAt(1, isEnable);
        jtp_main.setEnabledAt(2, isEnable);
        jtp_main.setEnabledAt(3, isEnable);
        jtp_main.setEnabledAt(4, isEnable);
    }
    
    public void generateTabs(Map<String, Set<String> > errColumns){
    	btnExport.setEnabled(true);
    	descriptor_depth = comboPivotColumn.getSelectedIndex()+1;
    	
    	
    	analyzeStartDate = new LocalDate(textFieldYYYYs.getText()+"-"+textFieldMMs.getText()+"-"+textFieldDDs.getText());
    	analyzeEndDate = new LocalDate(textFieldYYYYe.getText()+"-"+textFieldMMe.getText()+"-"+textFieldDDe.getText());
    	
    	
    	//Get Parmas
    	totalDeparture = Integer.parseInt(textFieldMonthlyDeparture.getText());
    	//Analysis
		//System.out.println("Anal Depth : "+descriptor_depth);
		WarningAnalyzer wa = new WarningAnalyzer(waInputData, descriptor_depth, totalDeparture, textFieldPivotDate.getText(), errColumns, analyzeStartDate, analyzeEndDate);
		//Get Result data
		waResultData = wa.getWaResultData();
        //Some action for notification about loaded file.
    	
    	
    	
        //Trend Analysis
    	SafetyAnalysisTrendAnalysis ta = new SafetyAnalysisTrendAnalysis(waResultData, waInputData, analyzeStartDate, analyzeEndDate, textFieldSelectPath.getText());
		jp_trendAnalysis.setLayout(new BorderLayout());
		jp_trendAnalysis.removeAll();
		jp_trendAnalysis.add("Center", ta.createPanel());
    	

        //Chromatography
        SafetyAnalysisChromatography sac = new SafetyAnalysisChromatography(waResultData, textFieldSelectPath.getText());
		jp_chromatography.setLayout(new BorderLayout());
		jp_chromatography.removeAll();
		jp_chromatography.add("Center", sac.createPanel());
		
		
		//RiskMatrix
		SafetyAnalysisRiskMatrix sarm = new SafetyAnalysisRiskMatrix(waResultData, waInputData, textFieldSelectPath.getText());
		jp_riskMatrix.setLayout(new BorderLayout());
		jp_riskMatrix.removeAll();
		jp_riskMatrix.add("Center",sarm.createPanel());
						
		
		//Scoring Method
		SafetyAnalysisScoringMethod sasm = new SafetyAnalysisScoringMethod(waResultData, textFieldSelectPath.getText());
		jp_scoringMethod.setLayout(new BorderLayout());
		jp_scoringMethod.removeAll();
		jp_scoringMethod.add("Center", sasm.createPanel());
		
		enableTabs(true);
    }
    
    public static void main(String[] args) throws URISyntaxException {
        
        SafetyAnalysisTabbedPane tp = new SafetyAnalysisTabbedPane();
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setVisible(true);
        tp.setSize(1024, 600);
    }
}