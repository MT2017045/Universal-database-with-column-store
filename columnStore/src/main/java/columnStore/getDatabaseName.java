package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class getDatabaseName extends JFrame {

	private JPanel contentPane;
	public static String database;

	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getXsdName frame = new getXsdName();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public getDatabaseName() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null); // center on screen
		contentPane.setLayout(null);
		adminDatabase databaseName=new adminDatabase();

		String[] names = databaseName.getDBName();
		
		
		final JComboBox comboBox = new JComboBox(names);
		comboBox.setSelectedIndex(-1);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				database=comboBox.getSelectedItem().toString();
			}
		});
		comboBox.setBounds(258, 53, 153, 24);
		contentPane.add(comboBox);
		
		
		JLabel lblSelectXsdFile = new JLabel("select database  to fire query");
		lblSelectXsdFile.setBounds(27, 34, 313, 63);
		contentPane.add(lblSelectXsdFile);
		
		JButton btnRunQuery = new JButton("run New Query");
		btnRunQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {

			
						try {
							
							ColStoreMakeQueryGUI window = new ColStoreMakeQueryGUI();
							//window.setPath((String) comboBox.getSelectedItem());
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnRunQuery.setBounds(48, 150, 172, 25);
		contentPane.add(btnRunQuery);
		
		JButton btnViewNamedQueries = new JButton("view named queries");
		btnViewNamedQueries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				namedQuery namedQuery=new namedQuery();
				namedQuery.setVisible(true);
			}
		});
		btnViewNamedQueries.setBounds(232, 150, 179, 25);
		contentPane.add(btnViewNamedQueries);
	}
}
