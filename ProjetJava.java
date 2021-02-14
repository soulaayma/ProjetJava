import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.sql.*;
import de.wannawork.jcalendar.TestFrame2;
import de.wannawork.jcalendar.JMonthPanel;
import de.wannawork.jcalendar.JCalendarPanel;
import de.wannawork.jcalendar.DayLabel;
import de.wannawork.jcalendar.FlatButton;
import de.wannawork.jcalendar.JCalendarDialog;
import java.awt.Component;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdatepicker.DateModel;
import java.util.Properties;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

public class ProjetJava extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjetJava frame = new ProjetJava();
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
	public ProjetJava() {
		getContentPane().setBackground(new Color(244, 247, 252));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Location de voiture de numero de serie");
		lblNewLabel.setFont(new Font("Verdana Pro", Font.PLAIN, 20));
		lblNewLabel.setBounds(40, 31, 555, 31);
		getContentPane().add(lblNewLabel);
		
		JLabel clientId = new JLabel("Client ID");
		clientId.setFont(new Font("Verdana", Font.PLAIN, 20));
		clientId.setBounds(41, 97, 152, 31);
		getContentPane().add(clientId);
		
		textField = new JTextField();
		textField.setBounds(271, 97, 246, 31);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lbdd = new JLabel("Date Debut");
		lbdd.setFont(new Font("Verdana", Font.PLAIN, 20));
		lbdd.setBounds(41, 178, 152, 31);
		getContentPane().add(lbdd);
		
		JLabel lbdf = new JLabel("Date Fin");
		lbdf.setFont(new Font("Verdana", Font.PLAIN, 20));
		lbdf.setBounds(41, 259, 152, 31);
		getContentPane().add(lbdf);
		
		JLabel lblMethodePayement = new JLabel("Methode Payement");
		lblMethodePayement.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblMethodePayement.setBounds(40, 340, 208, 31);
		getContentPane().add(lblMethodePayement);
		
		JComboBox methodepayement = new JComboBox();
		methodepayement.setModel(new DefaultComboBoxModel(new String[] {"", "Chaque", "Espece", "Carte Bancaire"}));
		methodepayement.setBounds(271, 340, 246, 31);
		getContentPane().add(methodepayement);
		
		JComboBox day1 = new JComboBox();
		day1.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		day1.setBounds(271, 182, 48, 30);
		getContentPane().add(day1);
		
		JComboBox month1 = new JComboBox();
		month1.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		month1.setBounds(329, 182, 48, 30);
		getContentPane().add(month1);
		
		JComboBox year1 = new JComboBox();
		year1.setModel(new DefaultComboBoxModel(new String[] {"", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027"}));
		year1.setBounds(387, 182, 70, 30);
		getContentPane().add(year1);
		
		JComboBox day2 = new JComboBox();
		day2.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		day2.setBounds(271, 263, 48, 30);
		getContentPane().add(day2);
		
		JComboBox month2 = new JComboBox();
		month2.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		month2.setBounds(329, 263, 48, 30);
		getContentPane().add(month2);
		
		JComboBox year2 = new JComboBox();
		year2.setModel(new DefaultComboBoxModel(new String[] {"", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027"}));
		year2.setBounds(387, 263, 70, 30);
		getContentPane().add(year2);
		
		JButton btnNewButton = new JButton("Valider Location");
		  btnNewButton.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent arg0) {
			  try {
				Class.forName("com.java.jdbc.Driver");
				  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","");
				  Statement stm1 = con.createStatement();
				  String sql1 = "select * from client where Id='"+textField.getText();
				  ResultSet rs = stm1.executeQuery(sql1);
				  if(rs.next()) {
					  if(((day1.getSelectedItem().toString()=="31")&&(month1.getSelectedItem().toString()=="02"||"04"||"06"||"09"||"11"))||(day2.getSelectedItem().toString()=="31")&&(month2.getSelectedItem().toString()=="02"||"04"||"06"||"09"||"11"))
						  JOptionPane.showMessageDialog(null, "Date Incorrecte", "Error", 2);
					  else {
						  Statement stm2 = con.createStatement();
					      String sql2 = "INSERT INTO `location`(`Id`, `DateDebut`, `DateFin`, `ModePayement`) VALUES ('"+textField.getText()+"','"+  +  +"','"+methodepayement.getText()+"')'";
					      ResultSet rs = stm1.executeQuery(sql1);
					  } }
				  else 
					  JOptionPane.showMessageDialog(null, "Id client n existe pas", "Error", 2);
					  
		              
			  }catch(exception e) {System.out.print(e);}
		  }
		  });
		btnNewButton.setBounds(209, 406, 89, 23);
		getContentPane().add(btnNewButton);
		
		setTitle("Location De Voiture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();

		
	}
public 
}
