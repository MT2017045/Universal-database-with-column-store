package columnStore;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class newRunGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newRunGUI window = new newRunGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public newRunGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); // center on screen
		
		JButton XSDUpload = new JButton("INSERT XSD");
		XSDUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dbName dbName=new dbName();
				dbName.setVisible(true);
				
			}
		});
		
		XSDUpload.setBounds(24, 118, 117, 25);
		frame.getContentPane().add(XSDUpload);
		
		JButton CSVUpload = new JButton("UPLOAD CSV");
		CSVUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectDB db=new selectDB();
				db.setVisible(true);
	
			}
		});
		
		CSVUpload.setBounds(153, 118, 139, 25);
		frame.getContentPane().add(CSVUpload);
		
		JButton runQuery = new JButton("RUN QUERY");
		runQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {

						try {
							getDatabaseName xsdname=new getDatabaseName();
							xsdname.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		runQuery.setBounds(304, 118, 117, 25);
		frame.getContentPane().add(runQuery);
	}
}
