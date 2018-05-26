package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class selectDB extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectDB frame = new selectDB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public selectDB() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // center on screen
		
		adminDatabase databaseName=new adminDatabase();

		String[] names = databaseName.getDBName();
		
		final JComboBox comboBox = new JComboBox(names);
		comboBox.setBounds(68, 135, 136, 24);
		contentPane.add(comboBox);
		comboBox.setSelectedIndex(-1);
		
		JButton select = new JButton("Proceed");
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedBook = (String) comboBox.getSelectedItem();
				jFilePicker filePicker=new jFilePicker("CSV File", ".csv", selectedBook);
				filePicker.setVisible(true);
				//JOptionPane.showMessageDialog(null, selectedBook);
				//new OptionSelector(".csv", "CSV File",selectedBook);
			}
		});
		select.setBounds(234, 135, 117, 25);
		contentPane.add(select);
		
		JLabel lblSelectDatabaseName = new JLabel("select database name to enter csv");
		lblSelectDatabaseName.setBounds(83, 95, 333, 15);
		contentPane.add(lblSelectDatabaseName);
	}
}
