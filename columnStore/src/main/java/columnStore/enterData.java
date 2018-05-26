package columnStore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class enterData {
	@SuppressWarnings("rawtypes")
	ArrayList<String> colnames = new ArrayList<String>();

	public void insertCSV(String path,String selectedDB) {

		try {
			String splitBy = ",";
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine(); // we have done this to bypass the first line which is just the name of tables
			String[] b = line.split(splitBy);

			for (int i = 0; i < b.length; i++) {

				colnames.add(b[i]);
			}

			System.out.println(colnames);
			Class.forName("com.mysql.jdbc.Driver"); // it is to load driver

			String url = "jdbc:mysql://localhost:3306/"+selectedDB; // database name
			String uname = yourInfo.username;
			String pass = yourInfo.pass;

			Connection conn = DriverManager.getConnection(url, uname, pass);

			System.out.println("insertion of rows started......");
			while ((line = br.readLine()) != null) {
				b = line.split(splitBy);
				// System.out.println(b[0]);
				// DatabaseMetaData md = conn.getMetaData();
				// ResultSet rs = md.getTables(null,null, "%", null);

				// System.out.println(b.length);
				for (int i = 0; i < b.length; i++) {
					// System.out.println(colnames.get(i));
					// rs.next();
					// st.execute("INSERT INTO "+rs.getString(3)+" VALUES ('','"+b[i]+"')");
					PreparedStatement preparedStatement = conn.prepareStatement(
							"INSERT INTO " + colnames.get(i) + "(" + colnames.get(i) + ") VALUES ('" + b[i] + "')");
					// st.execute("INSERT INTO "+colnames.get(i)+"("+colnames.get(i)+") VALUES
					// ('"+b[i]+"')");
					preparedStatement.executeUpdate();

				}

			}
			conn.close();

			System.out.println("done insertion of rows");
			JOptionPane.showMessageDialog(null, "CSV entered into the database!");
			br.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

}
