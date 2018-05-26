package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class namedQuery extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	static String getquery;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					namedQuery frame = new namedQuery();
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
	public namedQuery() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // center on screen
		
		JLabel lblSelectNamedQueries = new JLabel("select named queries");
		lblSelectNamedQueries.setBounds(43, 58, 191, 15);
		contentPane.add(lblSelectNamedQueries);
		final adminDatabase databaseName=new adminDatabase();

		String[] names = databaseName.getQueryName(getDatabaseName.database);
		final JComboBox comboBox = new JComboBox(names);
		comboBox.setSelectedIndex(-1);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(databaseName.getExactQuery(comboBox.getSelectedItem().toString()));
				getquery = databaseName.getExactQuery(comboBox.getSelectedItem().toString());
			}
		});
		
		
		comboBox.setBounds(221, 53, 139, 24);
		contentPane.add(comboBox);
	
		
		JButton button = new JButton("run query");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DisplayNamedResult frame = new DisplayNamedResult();
				frame.setVisible(true);
			}
		});
		button.setBounds(174, 202, 117, 25);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(23, 134, 415, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("selected query:");
		lblNewLabel.setBounds(28, 107, 139, 15);
		contentPane.add(lblNewLabel);
	}
}
