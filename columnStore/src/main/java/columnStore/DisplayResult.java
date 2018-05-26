package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DisplayResult extends JFrame {

	private JPanel contentPane;
	private JTable table;
	String query = WhereFrame.group;
	JTextArea textArea;
	private JTextField namedQuery;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayResult frame = new DisplayResult();
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
	
	public void loadtable()
	{
		sqlconn connection = new sqlconn();
		PreparedStatement preparedstmnt;
		try {
			Connection conn = (Connection) connection.createConn();
			preparedstmnt=	(PreparedStatement) conn.prepareStatement(query);
			ResultSet rs = preparedstmnt.executeQuery();
			//if(!rs.next()) {
				//JOptionPane.showMessageDialog(null,"Invalid query!" );
			//}
			//else {
				table.setModel(DbUtils.resultSetToTableModel(rs));
				textArea.setText(query);
			//}
			
			
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public DisplayResult() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1183, 761);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // center on screen
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 92, 1107, 515);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblOutput = new JLabel("OUTPUT");
		lblOutput.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblOutput.setBounds(448, 36, 310, 37);
		contentPane.add(lblOutput);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(32, 628, 726, 73);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JLabel lblEnterNameFor = new JLabel("enter name for your query");
		lblEnterNameFor.setBounds(785, 641, 210, 15);
		contentPane.add(lblEnterNameFor);
		
		namedQuery = new JTextField();
		namedQuery.setBounds(813, 664, 134, 19);
		contentPane.add(namedQuery);
		namedQuery.setColumns(10);
		
		JButton saveQuery = new JButton("Save Query");
		saveQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nameOfQuery=namedQuery.getText();
				
				adminDatabase adminDatabase=new adminDatabase();
				adminDatabase.enterNamedQuery(nameOfQuery, query, getDatabaseName.database);
				
			}
		});
		saveQuery.setBounds(1008, 661, 117, 25);
		contentPane.add(saveQuery);
		
		loadtable();
	}
}