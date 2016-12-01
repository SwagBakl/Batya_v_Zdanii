


import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 28.10.2016.
 */
public class DBWorker {

    public static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?";
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String URL = "jdbc:mysql://localhost:3306/bank_roll";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public User checkUser(String login, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getInt("role"));


            } else {
                JOptionPane.showMessageDialog(null, "SOMETHING INCORRECT!");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
            statement.close();
        }
        return user;
    }

    public List<User> getAllUser() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = null;
        List<User> users = new ArrayList<User>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            statement.close();
        }
        return users;
    }



    /*public static ArrayList<User> Start_table_data() throws SQLException {

        ArrayList<User> users = new ArrayList<User>();
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = null;
        ResultSet rs;
        User user;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL_SELECT_ALL_USERS);

            while (rs.next()) {
                user = new User();
                rs.getInt("Id");
                rs.getString("name");
                rs.getString("second_name");
                rs.getString("login");
                rs.getString("password");
                rs.getInt("role");
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        DefaultTableModel model = new DefaultTableModel();
        Object[] columnsName = new Object[6];
        columnsName[0] = "Id";
        columnsName[1] = "Name";
        columnsName[2] = "Second name";
        columnsName[3] = "Login";
        columnsName[4] = "Password";
        columnsName[5] = "Role";

        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[6];

        for (int i = 0; i < users.size(); i++)
        {
            rowData[0] = users.get(i).getId();
            rowData[1] = users.get(i).getName();
            rowData[2] = users.get(i).getSecond_Name();
            rowData[3] = users.get(i).getLogin();
            rowData[4] = users.get(i).getPassword();
            rowData[5] = users.get(i).getRole();

            model.addRow(rowData);
        }

        return users;
    }*/
}
