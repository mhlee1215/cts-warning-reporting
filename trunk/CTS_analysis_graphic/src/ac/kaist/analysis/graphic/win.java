package ac.kaist.analysis.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jfree.ui.RefineryUtilities;

// Create a simple GUI window
public class win {
	
	
    private static void createWindow() {

        // Create and set up the window.
        JFrame frame = new JFrame("Warning Analysis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // My edit
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel label1 = new JLabel("Loaded file");
        JLabel label2 = new JLabel("Status");
        JLabel label3 = new JLabel("Message");

        JTextField current = new JTextField();
        JTextField dest = new JTextField();
        JTextArea preview = new JTextArea();

        JButton choose1 = new JButton("Chromatic");
        JButton choose2 = new JButton("Risk Score");
        JButton algo1 = new JButton("Trend Analysis");
        JButton algo2 = new JButton("BFMR");
        JButton algo3 = new JButton("Mine");
        algo2.setVisible(false);
        algo3.setVisible(false);
        // Horizontal arrangement
        layout.setHorizontalGroup(layout
                .createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(label1)
                                .addComponent(label2).addComponent(label3))
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(current)
                                .addComponent(dest).addComponent(preview))
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(choose1)
                                .addComponent(choose2).addComponent(algo1).addComponent(algo2).addComponent(algo3)));

        layout.linkSize(SwingConstants.HORIZONTAL, choose1, choose2, algo1, algo2, algo3);

        // Vertical arrangement
        layout.setVerticalGroup(layout
                .createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label1)
                                .addComponent(current).addComponent(choose1))
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label2)
                                .addComponent(dest).addComponent(choose2))
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(label3)
                                .addComponent(preview)
                                .addGroup(
                                        layout.createSequentialGroup().addComponent(algo1).addComponent(algo2)
                                                .addComponent(algo3))));

        
        choose1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        });
        
        choose2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        });
        
        algo1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			}
        	
        });
        
        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("exit.png");

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem lMenuItem = new JMenuItem("Load", icon);
        lMenuItem.setMnemonic(KeyEvent.VK_L);
        lMenuItem.setToolTipText("Load File");
        lMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	JFileChooser fileChooser = new JFileChooser();
            	
            	 /* Enabling Multiple Selection */
                fileChooser.setMultiSelectionEnabled(true);

                /* Setting Current Directory */
                fileChooser.setCurrentDirectory(new File("C:\\"));
                
                fileChooser.showDialog(new MenuMain(),
                        "File Chooser Example");
                
                //System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
                
                //Some action for notification about loaded file.
            }
        });


        file.add(lMenuItem);
        
        JMenuItem eMenuItem = new JMenuItem("Exit", icon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        file.add(eMenuItem);
        
        

        menubar.add(file);

        frame.setJMenuBar(menubar);
        
        // Display the window.
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.pack();
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        createWindow();

    }
}