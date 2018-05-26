package columnStore;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.Statement;

public class ColStoreMakeQueryGUI {

	public JFrame frame;
	public String path;
	static ArrayList<ArrayList<String>> globalselectquery = new ArrayList<ArrayList<String>>();
	static ArrayList<String> queryCols;
	//ArrayList<String> columnNames;
	
	ArrayList<String> al = new ArrayList<String>();
	Connection conn = null;

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public ColStoreMakeQueryGUI() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()  {

		System.out.println("Inside initialize");
		try {
			sqlconn conn = new sqlconn();
			conn.createConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame = new JFrame();
		
		frame.setBounds(100, 100, 713, 378);
		frame.setLocationRelativeTo(null); // center on screen
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		  File folder = new File("./hello");
		  File[] listOfFiles = folder.listFiles();
		  String extension;
		 
//		  for (int i = 0; i < listOfFiles.length; i++) {
//			  
//					  String fileName =listOfFiles[i].toString();
//				  
//				  int j = fileName.lastIndexOf('.'); 
//				  if (j > 0)
//				  { 
//					  extension =fileName.substring(j + 1);
//					 
//					  if (extension.equalsIgnoreCase("xsd"))
//					  {  
//						  path = fileName; 
//					  break;
//					  }
//				  }
//		  }
//		 
//		
//		  XsdReader reader = new XsdReader(); 
//		  reader.parseSchema(path);
//		  
//		 
//		 
//		ArrayList<String> columnNames = reader.gettablenames();
		  
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

		String[] stringArrayColNames = al.toArray(new String[0]);

		// System.out.println(stringArrayColNames[0]);

		final DualListBox dual = new DualListBox();

		dual.addSourceElements(stringArrayColNames);
		dual.setBounds(44, 50, 368, 232);
		frame.getContentPane().add(dual);

		JButton btnCreateQuery = new JButton("NEXT PAGE");
		btnCreateQuery.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(dual.getDestinationChoicesTitle());
				Iterator itr = dual.destinationIterator();
				queryCols = new ArrayList<String>();
				while (itr.hasNext()) {
					queryCols.add((String) itr.next());
				}
//				
				globalselectquery.add(queryCols);
//				
//				
				frame.dispose();
		
			
				WhereFrame where = new WhereFrame();
				
				where.setVisible(true);

			}
		});
		btnCreateQuery.setBounds(486, 125, 138, 34);
		frame.getContentPane().add(btnCreateQuery);

		JLabel lblSelectClause = new JLabel("SELECT CLAUSE - Select the columns you want to display");
		lblSelectClause.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectClause.setBounds(45, 11, 367, 28);
		frame.getContentPane().add(lblSelectClause);
	}
}