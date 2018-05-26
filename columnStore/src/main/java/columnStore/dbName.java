package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class dbName extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dbName frame = new dbName();
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
	public dbName() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null); // center on screen
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(97, 121, 265, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String databaseName = textField.getText();
				
				//new OptionSelector(".xsd", "XSD Schema File",databaseName);
				if(!databaseName.isEmpty()) {
				//adminDatabase enterName=new adminDatabase();
				//enterName.enterName(databaseName);
				jFilePicker filePicker=new jFilePicker("XSD Schema File", ".xsd", databaseName);
				filePicker.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please enter a database Name");
				}
			}
		});
		btnNewButton.setBounds(171, 169, 117, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblEnterDatabaseName = new JLabel("enter database name");
		lblEnterDatabaseName.setBounds(144, 94, 218, 15);
		contentPane.add(lblEnterDatabaseName);
	}
}
