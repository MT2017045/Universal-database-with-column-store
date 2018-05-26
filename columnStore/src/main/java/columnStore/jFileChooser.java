package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;

public class jFileChooser  {

	//private JPanel contentPane;
	private JFileChooser fileChooser = new JFileChooser();
	private String extension;
	private int mode;
	public static final int MODE_OPEN = 1;
	public static final int MODE_SAVE = 2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jFileChooser frame = new jFileChooser();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jFileChooser() {	
		this.mode=MODE_OPEN;
		fileChooser.setBounds(12, 12, 423, 261);
		fileChooser.setAcceptAllFileFilterUsed(false);
	}
	String selectedPath()
	{
		if (mode == MODE_OPEN) {
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
				return fileChooser.getSelectedFile().getAbsolutePath();
				else
					return null;
			
		} else if (mode == MODE_SAVE) {
			
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) 
				exportFile.textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			
				return fileChooser.getSelectedFile().getAbsolutePath();
				
		}
			return null;
	}
	public void addFileTypeFilter(String extension, String description) {
		FileTypeFilter filter = new FileTypeFilter(extension, description);
		this.extension = extension;
		fileChooser.addChoosableFileFilter(filter);
	}
	public void setMode(int mode) {
		this.mode = mode;
	}


	
}
