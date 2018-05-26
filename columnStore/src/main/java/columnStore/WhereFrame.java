package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.Statement;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class WhereFrame extends JFrame {

	private JPanel contentPane;
	public String path;
	static String whereResult;
	static String group;
	JComboBox comboBox;
	JComboBox comboBox_1;
	JTextArea textArea;
	JList list;
	ArrayList<ArrayList<String>> x = ColStoreMakeQueryGUI.globalselectquery;
	ArrayList<String> all = x.get(x.size()-1);
	fireQuery fireQuery=new fireQuery();
	String selectquery;
	fireQuery firequery;
	ArrayList<String> wherecols = new ArrayList<String>();
	ArrayList<String> operators = new ArrayList<String>();
	ArrayList<String> textvalues = new ArrayList<String>();
	ArrayList<String> conditions = new ArrayList<String>();
	private JTextField textField;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	

	public void fillcombobox()
	{	
		comboBox.addItem("=");
		comboBox.addItem("<");
		comboBox.addItem("<=");
		comboBox.addItem(">");
		comboBox.addItem(">=");
		
		comboBox_1.addItem("AND");
		comboBox_1.addItem("OR");
		comboBox_1.addItem("Done");
	}
	
	public void whereDescription(ArrayList<String> wherecols, ArrayList<String> operators, ArrayList<String> textvalues, ArrayList<String> conditions)
	{
		fireQuery.selectClause(all);
		String qq =	fireQuery.getQq();
		System.out.println(qq);
		
		String [] splittedquery = qq.split("FROM");
		 String noSpaceStr = splittedquery[1].replaceAll("\\s", "");
		String [] splittedfrom = noSpaceStr.split("NATURALJOIN");
		ArrayList<String> frompart = new ArrayList<String>();
		for (String a : splittedfrom)
            frompart.add(a);
		
		for(int i =0; i<wherecols.size(); i++)
		{
			if(!frompart.contains(wherecols.get(i))&& wherecols.get(i)!=null)
			{
				splittedquery[1]+= " "+"NATURAL JOIN"+" "+wherecols.get(i);
			}
		}
		
		String afterfromquery = "";
		afterfromquery+=" "+"FROM"+" "+splittedquery[1];
		
		String qqfinal = "";
		qqfinal+=splittedquery[0]+afterfromquery;
		System.out.println("hi where frame");
		
		
		String str = qqfinal;
		if(comboBox_1.getSelectedItem().equals("Done")) {
			str+= " "+"WHERE"+" ";
		
		
		for(int i = 1; i<operators.size(); i++)
		{
			str+=wherecols.get(i)+" "+operators.get(i)+" \'"+textvalues.get(i)+"\' "+conditions.get(i)+" ";
		}
		
		}
		qqfinal=str;
		System.out.println(qqfinal);
		group=qqfinal;
		textArea.setText(str);
		
		
	}

	public WhereFrame() {
		
		//System.out.println("inside where frame");
		File folder = new File("./hello");
		File[] listOfFiles = folder.listFiles();
		String extension;

		 ArrayList<String> al = new ArrayList<String>();
		sqlconn connection = new sqlconn();
		try {
			Connection conn = (Connection) connection.createConn();
			Statement st =  (Statement) conn.createStatement();
		  

		    ResultSet rs = null;
		    DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();
		    rs = meta.getTables(null, null, null, new String[]{"TABLE"});
		   
		    
		    while (rs.next()) {
		      String tableName = rs.getString("TABLE_NAME");
		      al.add(tableName);
		    }

		    st.close();
		    conn.close();
		  }

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		String[] s = al.toArray(new String[0]);

		
		
		
		
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // center on screen
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 111, 200, 400);
		contentPane.add(scrollPane);
		//System.out.println("populating the jlist");
		 list = new JList(s);
		 scrollPane.setViewportView(list);
		 list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		
		comboBox.setBounds(315, 134, 90, 34);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 25));
		textField.setBounds(483, 135, 146, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String col4 = (String)comboBox_1.getSelectedItem();
				String col2 = (String)comboBox.getSelectedItem();
				String col3 = textField.getText();
				String col1 = (String)list.getSelectedValue();
				//int flag = 0;
				wherecols.add(col1);
				operators.add(col2);
				
				System.out.println("Conditions : "+conditions);
				
				textvalues.add(col3);
				if(comboBox_1.getSelectedItem().equals("Done")) 
						conditions.add("");
				else
					conditions.add(col4);
				
					
				whereDescription(wherecols, operators, textvalues, conditions);
				
				list.clearSelection();
				textField.setText(null);
				comboBox.setSelectedIndex(-1);
				comboBox_1.setSelectedIndex(-1);
				
			}
			
		});
		
		
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		comboBox_1.setBounds(722, 134, 113, 34);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("Select Column");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblNewLabel.setBounds(68, 58, 300, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Operator");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(276, 57, 300, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Value");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblNewLabel_2.setBounds(483, 58, 200, 28);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Logical(AND/OR)");
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblNewLabel_3.setBounds(695, 58, 367, 28);
		contentPane.add(lblNewLabel_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(315, 312, 666, 184);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 22));
		scrollPane_1.setViewportView(textArea);
		
		JLabel lblQueryBuiltTill = new JLabel("Query to be executed: ");
		lblQueryBuiltTill.setFont(new Font("Dialog", Font.BOLD, 25));
		lblQueryBuiltTill.setBounds(315, 257, 367, 28);
		contentPane.add(lblQueryBuiltTill);
		
		JButton btnResult = new JButton("NEXT");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				whereResult = textArea.getText();
				groupBy frame = new groupBy();
				frame.setVisible(true);
			/*	if(!(whereResult.charAt(whereResult.length()-1) == ';'))
					whereResult=whereResult+"";
				if(whereResult.charAt(whereResult.length()-3) == ';')
					whereResult=whereResult.substring(0,whereResult.length()-2);
				System.out.println(whereResult);
				System.out.println(whereResult.charAt(whereResult.length()-1));
				System.out.println(whereResult.charAt(whereResult.length()-2));
				System.out.println(whereResult.charAt(whereResult.length()-3));
				
				
				if((whereResult.charAt(whereResult.length() - 2) == ';') || (whereResult.charAt(whereResult.length() - 1) == ';'))
					{
						contentPane.setVisible(false);
						//DisplayResult displayResult = new DisplayResult();
						//displayResult.setVisible(true);
						groupBy frame = new groupBy();
						frame.setVisible(true);
					}
				else
					JOptionPane.showMessageDialog(null,"Please check the syntax and enter correctly");
				*/
				
			}
		});
		btnResult.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnResult.setBounds(695, 254, 200, 34);
		contentPane.add(btnResult);
		fillcombobox();
		
	}
}