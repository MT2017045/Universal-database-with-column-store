package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class jFilePicker extends JFrame {

	private JPanel contentPane;
	private String textFieldLabel;
	private String buttonLabel;
	private JTextField textField;
	private JButton uploadButton;
	private String selectedDB;
	private String extension;
	private String path;
	private String type;
	private String desc;
	private JFileChooser fileChooser;
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
					jFilePicker frame = new jFilePicker( "XSD Schema File",".xsd","MARKET_FACT");
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
	public jFilePicker(String desc, String type,String selectedDB) {
		//this.textFieldLabel = textFieldLabel;
		//this.buttonLabel = buttonLabel;
		this.type = type;
		this.selectedDB=selectedDB;
		this.desc=desc;
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
		
		uploadButton = new JButton("upload");
		uploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				uploadActionPerformed(evt);
			}
		});
		
		uploadButton.setBounds(309, 116, 117, 25);
		contentPane.add(uploadButton);
		
		JLabel lblBrowseFileTo = new JLabel("Browse file to upload");
		lblBrowseFileTo.setBounds(116, 82, 221, 15);
		contentPane.add(lblBrowseFileTo);
	}
	
	private void buttonActionPerformed(ActionEvent evt) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jFileChooser frame = new jFileChooser();
					frame.addFileTypeFilter(type, desc);
					textField.setText(frame.selectedPath());
					path = textField.getText().toString();
					//frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void uploadActionPerformed(ActionEvent evt) {
		

		File source = new File(textField.getText().toString());
		File dest = new File("./hello");
		try {
			FileUtils.copyFileToDirectory(source, dest);
			//JOptionPane.showMessageDialog(null,textField.getText().toString());
			if (type == ".xsd") {

				XsdReader xsdReader = new XsdReader();
				LinkedHashMap<String, String> hashmap = xsdReader.parseSchema(path);
				JDBCConnection jdbc = new JDBCConnection();
				jdbc.doTheJDBCStuffs(hashmap,selectedDB);
				adminDatabase enterName=new adminDatabase();
				enterName.enterName(selectedDB);

			} else {             // csv ka kaaam yaha database ka naam bhi bhejna hai
				enterData data = new enterData();
				data.insertCSV(path,selectedDB);
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error! Try again");
		}
	}
}
