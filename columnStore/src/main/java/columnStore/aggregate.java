package columnStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.event.ActionEvent;

public class aggregate extends JFrame {

	private JPanel contentPane;
	private JTextField columns;
	int i=0;
	String text="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aggregate frame = new aggregate();
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
	public aggregate() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // center on screen
		
		
		final JRadioButton min = new JRadioButton("min");
		min.setBounds(155, 40, 149, 23);
		contentPane.add(min);
		
		final JRadioButton max = new JRadioButton("max");
		max.setBounds(155, 67, 149, 23);
		contentPane.add(max);
		
		final JRadioButton sum = new JRadioButton("sum");
		sum.setBounds(155, 121, 149, 23);
		contentPane.add(sum);
		
		final JRadioButton count = new JRadioButton("count");
		count.setBounds(155, 148, 149, 23);
		contentPane.add(count);
		
		final JRadioButton average = new JRadioButton("average");
		average.setBounds(155, 94, 149, 23);
		contentPane.add(average);
		
		final ButtonGroup group = new ButtonGroup();
	        group.add(min);
	        group.add(max);
	        group.add(average);
	        group.add(sum);
	        group.add(count);
		
		final ArrayList<String> s=new ArrayList<String>(groupBy.s);
		final ArrayList<String> function=new ArrayList<String>();
		final int length=s.size();
		System.out.println(s);
		columns = new JTextField();
		columns.setBounds(33, 42, 114, 19);
		columns.setText(s.get(i));
		contentPane.add(columns);
		columns.setColumns(10);
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(54, 190, 341, 74);
		contentPane.add(textArea);
		
		//System.out.println(i);
		
		JButton btnNext = new JButton("next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, WhereFrame.whereResult);
				if(i<(length)) {
					//System.out.println("before wala"+i);
					columns.setText(s.get(i));
						if(min.isSelected()||max.isSelected()||average.isSelected()||count.isSelected()||sum.isSelected())
						{
							//columns.setText(s.get(i));
							for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
					            AbstractButton button = buttons.nextElement();
		
					            if (button.isSelected()) {
					            	function.add(button.getText());
					            	append(button.getText()+"("+columns.getText()+")");
					                textArea.setText(text);
					                
					                i++;					                
					               // System.out.println("after wala"+i);
					                //if(i==length-1)
					                //break;
					                if(i==length) {
										columns.setText(" ");
										group.clearSelection();
									}
					                else {
					                	columns.setText(s.get(i));
					                	group.clearSelection();
					                }
					            }
					        }
							
						}
				}
				
				else
				{
					String aggregate=WhereFrame.group;
					for(int i=0;i<s.size();i++) {
					aggregate=aggregate.replaceFirst(s.get(i),function.get(i)+"("+s.get(i)+")");
					
					}
					WhereFrame.group=aggregate;
					DisplayResult displayResult = new DisplayResult();
					displayResult.setVisible(true);
				}
			}
		});
		btnNext.setBounds(312, 119, 117, 25);
		contentPane.add(btnNext);
		
		
	}
	void append(String s)
	{
		text=text+s+" ";
	}
}