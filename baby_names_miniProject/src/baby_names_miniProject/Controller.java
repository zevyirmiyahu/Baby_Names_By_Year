package baby_names_miniProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Controller implements ActionListener {
	
	JFrame frame;
	Viewer viewer;
	
	Controller(JFrame frame) {
		this.frame = frame;
	}
	
	Controller(Viewer viewer) {
		this.viewer = viewer;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Analyze")) {
			
		}
		else if(e.getActionCommand().equals("About This App")) {
			System.out.println("working");
		}
	}
}
