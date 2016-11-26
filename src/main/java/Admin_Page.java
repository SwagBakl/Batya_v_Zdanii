import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Денис on 22.11.2016.
 */
public class Admin_Page extends DBWorker{

    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users where role='1'";
    private static final String URL = "jdbc:mysql://localhost:3306/bank_roll";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public static void Admin_Page_Start(){
        try {

    final JFrame jf = new JFrame("Admin page");
    jf.setSize(640, 480);
    jf.setVisible(true);

    List<User> users = new ArrayList<User>();
    JButton add_user_button = new JButton("Add user");
    JButton delete_user_button = new JButton("Delete user");
    JButton refresh_user_button = new JButton("Refresh");
    final JTextField name_field = new JTextField();
    final JTextField second_Name_field = new JTextField();
    final JTextField login_field = new JTextField();
    final JTextField password_field = new JTextField();
    final JTextField role_field = new JTextField();

    JLabel name_label = new JLabel("Name: ");
    JLabel second_name_label = new JLabel("Second name: ");
    JLabel login_label = new JLabel("Login: ");
    JLabel password_label = new JLabel("Password: ");
    JLabel role_label = new JLabel("Role: ");


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

       JTable user_list_table = new JTable(data, column);
       JScrollPane scrollPane = new JScrollPane(user_list_table);

    add_user_button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                   // Add_Button_Query();

            }catch (Exception p){
                p.printStackTrace();
            }

        }
    });

    jf.setLayout(null);

    name_label.setBounds(96, 40, 50, 25);
    name_field.setBounds(140, 40, 150, 25);
    second_name_label.setBounds(50, 70, 100, 25);
    second_Name_field.setBounds(140, 70, 150, 25);
    login_label.setBounds(370, 40, 50, 25);
    login_field.setBounds(410, 40, 150, 25);
    password_label.setBounds(343, 70, 100, 25);
    password_field.setBounds(410, 70, 150, 25);
    add_user_button.setBounds(170,170,100,25);
    delete_user_button.setBounds(270,170,100,25);
    refresh_user_button.setBounds(370,170,100,25);
    user_list_table.setBounds(0, 200, 640, 239);
    role_label.setBounds(376, 100, 50, 25);
    role_field.setBounds(410, 100, 50, 25);

    jf.add(add_user_button);
    jf.add(delete_user_button);
    jf.add(refresh_user_button);
    jf.add(name_label);
    jf.add(second_name_label);
    jf.add(login_label);
    jf.add(password_label);
    jf.add(name_field);
    jf.add(second_Name_field);
    jf.add(login_field);
    jf.add(password_field);
    jf.add(user_list_table);
    jf.add(scrollPane);
    jf.add(role_label);
    jf.add(role_field);



    jf.setVisible(true);
    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   /* public static void Add_Button_Query() throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;


        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
        String sql = ("Insert into users (name,second_name,login,password,role) VALUES ()") ;
        rs = statement.executeQuery(sql);

    }*/
}
