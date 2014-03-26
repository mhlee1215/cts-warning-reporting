package swing.ax;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class RiskMatrixCellRenderer extends DefaultTableCellRenderer {
	
	public static String CHAR_WORST = "$";
	public static String CHAR_MOST = "%";
	public static String CHAR_CURRENT = "^";
	
	public static Color headerBG = new Color(79, 129, 189);
	public static Color focusRowBG = new Color(168, 176, 192);
	public static Color oddRowBG = new Color(208, 216, 232);
	public static Color evenRowBG = new Color(233, 237, 244);
	
	public static Color level1BG = new Color(0, 176, 240);
	public static Color level2BG = new Color(146, 208, 80);
	public static Color level3BG = new Color(255, 255, 0);
	public static Color level4BG = new Color(255, 192, 0);
	public static Color level5BG = new Color(233, 0, 0);
	
	int fontSize = 20;
	public RiskMatrixCellRenderer(){
		this.fontSize = 20;
	}
	public RiskMatrixCellRenderer(int fontSize){
		this.fontSize = fontSize;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent
	 * (javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component rendererComp = null;
		rendererComp = super.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
//		if(value.getClass() == java.lang.String.class){
//			rendererComp = super.getTableCellRendererComponent(table,
//					(java.lang.String)value, isSelected, hasFocus, row, column);
//		}else if(value.getClass() == javax.swing.ImageIcon.class){
//			ImageIcon icon = (javax.swing.ImageIcon)value;
//			//rendererComp = super.getTableCellRendererComponent(table,
//			//		icon, isSelected, hasFocus, row, column);
//			
//			rendererComp = new JLabel("");
//			((JLabel)rendererComp).setIcon(icon);
//			
//		}else{
//			rendererComp = super.getTableCellRendererComponent(table,
//					value, isSelected, hasFocus, row, column);
//		}
		
		
		rendererComp = new JPanel(); 
		JLabel t = new JLabel();
		t.setHorizontalAlignment(SwingConstants.CENTER);
		
		GridBagConstraints c = new GridBagConstraints();
		Font labelFont = t.getFont();
		
		if(value.getClass() == java.lang.String.class){
			String str = (String)value;
			
			
				
			((JPanel)rendererComp).setLayout(new GridBagLayout());
			
			for(int i = 0 ; i < str.length() ; i++){
				boolean isIcon = false;
				String color = "red";
				if(str.subSequence(i,  i+1).equals(CHAR_WORST)){
					color = "red";
					isIcon = true;
				}else if(str.subSequence(i,  i+1).equals(CHAR_MOST)){
					color = "blue";
					isIcon = true;
				}else if(str.subSequence(i,  i+1).equals(CHAR_CURRENT)){
					color = "green";
					isIcon = true;
				}
				
				if(isIcon){
					ImageIcon icon = new ImageIcon(getClass().getResource(
							color+"_circle_check.png"));
					JLabel tt = new JLabel(icon);
					((JPanel)rendererComp).add(tt, c);	
				}else{
					JLabel tt = new JLabel();
					tt.setText(str.substring(i,  i+1));
					tt.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSize));
					((JPanel)rendererComp).add(tt, c);	
				}
				
			}
			
			
			
		}
		
		 

		// Set foreground color
		
		setHorizontalAlignment( JLabel.CENTER );
		
		if(row == 0 || row == 6 || column == 0 || column == 6){
			rendererComp.setBackground(evenRowBG);
		}else{
			if( 6 - row + column < 4){
				rendererComp.setBackground(level1BG);
			}
			else if( 6 - row + column < 5){
				rendererComp.setBackground(level2BG);
			}
			else if( 6 - row + column < 7){
				rendererComp.setBackground(level3BG);
			}
			else if( 6 - row + column < 9){
				rendererComp.setBackground(level4BG);
			}
			else {
				rendererComp.setBackground(level5BG);
			}
		}
		
//		rendererComp.setForeground(Color.red);
//
//		// Set background color
//		rendererComp.setBackground(Color.blue);
		
		

		return rendererComp;
	}

}