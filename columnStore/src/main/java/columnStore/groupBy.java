package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;

public class groupBy extends JFrame {

	private JPanel contentPane;
	static public ArrayList<String> s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					groupBy frame = new groupBy();
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
	public groupBy() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // center on screen
		
		s = new ArrayList<String>(ColStoreMakeQueryGUI.queryCols);
		System.out.println(s);
		/*s.add("hanshika");
		s.add("minal");
		s.add("A");
		s.add("b");
		s.add("c");
		s.add("d");*/
		
		ArrayList<String> aggregate = new ArrayList<String>();
	
		
		final DualListBox dualListBox = new DualListBox();
		
		dualListBox.addSourceElements(s.toArray());
		dualListBox.setBounds(12, 38, 297, 250);
		contentPane.add(dualListBox);
		
		JButton btnNext = new JButton("next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String groupBy="";
				System.out.println(dualListBox.getDestinationChoicesTitle());
				Iterator itr = dualListBox.destinationIterator();
				ArrayList<String> next = new ArrayList<String>();
				int flag=0;
				while (itr.hasNext()) {
					if(flag==0) {
						groupBy=" GROUP BY ";
						flag=1;
					}
					String n=(String) itr.next();
					next.add(n); //jo group by me jayega
					groupBy=groupBy+n+",";
				}
				if(groupBy.length()>0) {
				if(groupBy.charAt(groupBy.length() - 1) == ',')
				groupBy=groupBy.substring(0, groupBy.length()-1);
				}
				
				WhereFrame.group+=groupBy;
				System.out.println("hi group by");
				System.out.println(WhereFrame.group);
				System.out.println(groupBy);
				remove(next);
				
				if(next.size()!=0 && s.size()!=0) {
					aggregate frame = new aggregate();
					frame.setVisible(true);
				}
				else
				{
					DisplayResult displayResult = new DisplayResult();
					displayResult.setVisible(true);
				}
			}
		});
		
		
		btnNext.setBounds(321, 156, 117, 25);
		contentPane.add(btnNext);
		
		//ColStoreMakeQueryGUI.queryCols.toArray()
		
		JLabel lblSelectClause = new JLabel("SELECT CLAUSE - Select the columns you want to display");
		lblSelectClause.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectClause.setBounds(45, 11, 367, 28);
	}
	void remove(ArrayList<String> next)
	{
		s.removeAll(next);
	}
}