package OrTorahCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelListener;
public class GUI {
	private JFrame myFrame;
	
	public GUI(ShulCalendar c){
		makeFrame(c);
	}
	
	public void makeFrame(ShulCalendar c){
		myFrame=new JFrame("GUI example");
		Container contentPane=myFrame.getContentPane();
		JTextArea j=new JTextArea(c.toString());
		 JScrollBar hbar = new JScrollBar(
	                JScrollBar.HORIZONTAL, 30, 20, 0, 300);
	        JScrollBar vbar = new JScrollBar(
	                JScrollBar.VERTICAL, 30, 40, 0, 300);
	        
	        hbar.setUnitIncrement(2);
	        hbar.setBlockIncrement(1);
	        
	        hbar.addAdjustmentListener(new MyAdjustmentListener());
	        vbar.addAdjustmentListener(new MyAdjustmentListener());
	        
	        j.add(hbar, BorderLayout.SOUTH);
	        j.add(vbar, BorderLayout.EAST);	
	        vbar.setVisible(true);
	        hbar.setVisible(true);
	        j.setEditable(false);
	        j.setFocusable(true);
		contentPane.add(j, BorderLayout.EAST);
		//contentPane.add(r);
		myFrame.pack();
		myFrame.setVisible(true);
		
	    }
	    
	    class MyAdjustmentListener implements AdjustmentListener {
	        public void adjustmentValueChanged(AdjustmentEvent e) {
	            repaint();
	        }
	    }
	
		
	



public static void main(String[] args){
	
	ShulCalendar ShulCalendar=new ShulCalendar();
	ShulCalendar.readFile("C:\\Users\\Toviah\\Downloads\\skokieuni.txt");
	GUI p= new GUI(ShulCalendar);
}

public void repaint() {
	// TODO Auto-generated method stub
	
}
}
