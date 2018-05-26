package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.Connection;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DisplayNamedResult extends JFrame {

	private JPanel contentPane;
	int count = 0;
	private JTable table;
	ResultSet rs;
	DefaultTableModel dm = new DefaultTableModel();
	String query = namedQuery.getquery;
	ArrayList<String> colnames = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayNamedResult frame = new DisplayNamedResult();
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
		String [] splittedquery = query.split("FROM");
		String [] xx = splittedquery[0].split("SELECT");
		String [] yy = xx[1].split(",");
		String [] zz = new String[yy.length];
		
		for (int i = 0; i < yy.length; i++) {
			zz[i] = yy[i].replaceAll("\\s", "");
			
			dm.addColumn(zz[i]);
			colnames.add(zz[i]);
		}
		
		
		sqlconn connection = new sqlconn();
		PreparedStatement preparedstmnt;
		try {
			Connection conn = (Connection) connection.createConn();
			preparedstmnt=	(PreparedStatement) conn.prepareStatement(query);
			rs = preparedstmnt.executeQuery();
			
			while(rs.next())
			{
				String [] rowdata = new String[zz.length];
				for (int j = 0; j < zz.length; j++)
				{
					rowdata[j] = rs.getString(zz[j]);
				} 

				dm.addRow(rowdata);
				count++;
			}
			table.setModel(dm);
		
		}catch(Exception e) {e.printStackTrace();}
			
			
		
	}

	
	public DisplayNamedResult() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // center on screen

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 86, 892, 378);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnExportData = new JButton("Export Data");

		btnExportData.addActionListener(new ActionListener() {

		

			public void actionPerformed(ActionEvent arg0) {
				
				exportFile frame=new exportFile();
				frame.setVisible(true);
				
				
			}
		});
		btnExportData.setFont(new Font("Dialog", Font.PLAIN, 22));
		btnExportData.setBounds(447, 491, 166, 37);
		contentPane.add(btnExportData);

		JLabel lblOutput = new JLabel("Output ");
		lblOutput.setFont(new Font("Dialog", Font.BOLD, 24));
		lblOutput.setBounds(510, 45, 166, 37);
		contentPane.add(lblOutput);
		loadtable();
	}
	public void export(String path)
	{
		XSSFWorkbook workbook;
		workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		TreeMap<String, Object[]> data = new TreeMap<String,Object[]>();
		Object[] objects = new Object[colnames.size()];
		for (int i = 0; i < colnames.size(); i++) {
			objects[i] = colnames.get(i).toString();
		}

		data.put("-1", objects);

		System.out.println("================================================");
		System.out.println(count);
		
		for (int i = 0; i < count; i++) 
		{
			Object[] o = new Object[colnames.size()];
		
			for (int j = 0; j < colnames.size(); j++)
			{
				String str = dm.getValueAt(i, j).toString();
				o[j] = str;
			}
			data.put(Integer.toString(i), o);

		}
		Set<String> ids = data.keySet();
		XSSFRow row;
		int rowID = 0;
		for (String key : ids) {
			row = sheet.createRow(rowID++);
			Object[] values = data.get(key);

			int cellID = 0;
			for (Object oo : values) {
				Cell cell = row.createCell(cellID++);
				cell.setCellValue(oo.toString());
			}
		}

		

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path+".xlsx");
			workbook.write(fileOutputStream);
			fileOutputStream.close();
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,
				"Data is exported to the file!");
		
	}
}