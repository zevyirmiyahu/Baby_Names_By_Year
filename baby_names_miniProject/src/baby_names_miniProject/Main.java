package baby_names_miniProject;

import javax.swing.SwingUtilities;

/*
 * @author Zev Yirmiyahu 
 */

public class Main {
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			new Viewer();
			}
		});
	}
}
