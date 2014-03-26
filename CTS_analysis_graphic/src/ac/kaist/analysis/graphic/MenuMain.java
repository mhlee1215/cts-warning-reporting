package ac.kaist.analysis.graphic;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.plaf.metal.MetalIconFactory;

public class MenuMain extends JFrame {

    public MenuMain() {
        initUI();
    }

    private void initUI() {

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

        setJMenuBar(menubar);
        
        final JButton buttonChromatic = new JButton("Chromatic");
        final JButton buttonRiskScore = new JButton("Risk Score");
        final JButton buttonTrendAnalysis = new JButton("Trend Analysis");
        

        setTitle("Simple menu");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	MenuMain ex = new MenuMain();
                ex.setVisible(true);
            }
        });
    }
}