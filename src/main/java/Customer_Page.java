import javax.swing.*;
import java.sql.*;
import java.util.Vector;

/**
 * Created by Денис on 28.11.2016.
 */
public class Customer_Page {

    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM customers";
    private static final String URL = "jdbc:mysql://localhost:3306/bank_roll";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public void Customer_Page_Start() throws SQLException {
        JFrame jf = new JFrame("Customers");
        jf.setSize(640,480);
        jf.setVisible(true);


        JButton add_customer_button = new JButton("Add user");
        JButton delete_customer_button = new JButton("Delete user");
        JButton refresh_customer_button = new JButton("Refresh");
        final JTextField name_field = new JTextField();
        final JTextField adress_field = new JTextField();
        final JTextField number_of_projects_field = new JTextField();
        final JTextField finished_projects_field = new JTextField();


        JLabel name_label = new JLabel("Name: ");
        JLabel adress_label = new JLabel("Adress: ");
        JLabel number_of_projects_label = new JLabel("Number of projects: ");
        JLabel finished_projects_label = new JLabel("Finished projects: ");

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;


        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
        rs = statement.executeQuery(SQL_SELECT_ALL_USERS);
        ResultSetMetaData rsmd = rs.getMetaData();
        int c = rsmd.getColumnCount();
        Vector column = new Vector(c);
        for(int i=1;i<=c;i++){
            column.add(rsmd.getCatalogName(i));
        }
        Vector data = new Vector();
        Vector row;
        while (rs.next()){
            row = new Vector(c);
            for(int i =1; i <= c; i++){
                row.add(rs.getString(i));
            }
            data.add(row);

        }

        JTable customer_list_table = new JTable(data, column);
        JScrollPane scrollPane = new JScrollPane(customer_list_table);

        jf.setLayout(null);

        name_label.setBounds(96, 40, 50, 25);
        name_field.setBounds(140, 40, 150, 25);
        adress_label.setBounds(88, 70, 100, 25);
        adress_field.setBounds(140, 70, 150, 25);
        number_of_projects_label.setBounds(320, 40, 150, 25);
        number_of_projects_field.setBounds(440, 40, 150, 25);
        finished_projects_label.setBounds(332, 70, 150, 25);
        finished_projects_field.setBounds(440, 70, 150, 25);
        add_customer_button.setBounds(170,110,100,25);
        delete_customer_button.setBounds(270,110,100,25);
        refresh_customer_button.setBounds(370,110,100,25);
        customer_list_table.setBounds(0, 200, 640, 239);

        jf.add(add_customer_button);
        jf.add(delete_customer_button);
        jf.add(refresh_customer_button);
        jf.add(name_label);
        jf.add(adress_label);
        jf.add(number_of_projects_label);
        jf.add(finished_projects_label);
        jf.add(name_field);
        jf.add(adress_field);
        jf.add(number_of_projects_field);
        jf.add(finished_projects_field);
        jf.add(customer_list_table);
        jf.add(scrollPane);

    }

}
