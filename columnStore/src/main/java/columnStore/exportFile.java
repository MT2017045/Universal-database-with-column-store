package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;
import javax.swing.JLabel;

public class exportFile extends JFrame {

	private JPanel contentPane;
	private String textFieldLabel;
	private String buttonLabel;
	private String path;
	static public JTextField textField;
	private JButton exportButton;
	
	private String extension;
	
	private JFileChooser fileChooser;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					exportFile frame=new exportFile();
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
	public exportFile() {
		//this.textFieldLabel = textFieldLabel;
		//this.buttonLabel = buttonLabel;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // center on screen
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(12, 111, 178, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonActionPerformed(evt);
			}
		});
		btnBrowse.setBounds(202, 116, 95, 25);
		contentPane.add(btnBrowse);
		
		exportButton = new JButton("export");
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				exportActionPerformed(evt);
			}
		});
		
		exportButton.setBounds(309, 116, 117, 25);
		contentPane.add(exportButton);
		
		JLabel lblSelectDirectoryTo = new JLabel("select directory to save the output of the named query");
		lblSelectDirectoryTo.setBounds(12, 73, 414, 15);
		contentPane.add(lblSelectDirectoryTo);
	}
	
	private void buttonActionPerformed(ActionEvent evt) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jFileChooser frame = new jFileChooser();
					frame.setMode(2);
					frame.addFileTypeFilter("xlsx", "Excel file");
					textField.setText(frame.selectedPath());
					path = textField.getText().toString();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void exportActionPerformed(ActionEvent evt) {
		
		DisplayNamedResult displayNamedResult=new DisplayNamedResult();
		displayNamedResult.export(path);
	
	}
}