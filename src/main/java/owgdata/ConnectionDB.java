package owgdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ConnectionDB {
    static String user = "root";
    static String password = "yohhan123";
    static String url = "jdbc:mysql://localhost:3306/login"; //#"jdbc:mysql://localhost:3306/login";

    //  static String url = "jdbc:mysql://49.12.174.144:3306/jvm_db"; //#"jdbc:mysql://localhost:3306/login";
    static String driver = "com.mysql.jdbc.Driver";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            try {
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database working"); //debug
                return connection;

            } catch (SQLException e) {
                return null;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<users> getDatausers() {
        Connection owgConnection = getConnection();
        ObservableList<users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement owgData = null; //owgConnection.prepareStatement("SELECT * FROM owgCoffee");
            owgData = owgConnection.prepareStatement("SELECT * FROM owgCoffee");
            ResultSet owgResult = owgData.executeQuery();

            while (owgResult.next()) {
                list.add(new users(
                        Integer.parseInt(owgResult.getString("ID")),
                        owgResult.getString("EMAIL"),
                        owgResult.getString("USERNAME"),
                        owgResult.getString("PASSWORD"),
                        owgResult.getString("FIRSTNAME"),
                        owgResult.getString("LASTNAME"),
                        owgResult.getString("PHONE"),
                        owgResult.getString("ADDRESS"),
                        owgResult.getString("BIRTHDAY"),
                        Integer.parseInt(owgResult.getString("PURCHASE")),
                        owgResult.getString("TYPE")
                ));
            }
        } catch (SQLException e) {
        } finally {

            try {

            } catch (Exception e) {
            }
        }
        return list;
    }
    public static ObservableList<products> getProducts() {
        Connection owgConnection = getConnection();
        ObservableList<products> productslist = FXCollections.observableArrayList();
        try {
            PreparedStatement owgData = null; //owgConnection.prepareStatement("SELECT * FROM owgCoffee");
            owgData = owgConnection.prepareStatement("SELECT * FROM owgProducts");
            ResultSet owgResult = owgData.executeQuery();

            while (owgResult.next()) {
                productslist.add(new products(
                        Integer.parseInt(owgResult.getString("ID")),
                        owgResult.getString("NAME"),
                        Double.parseDouble(owgResult.getString("PRICE")),
                        Integer.parseInt(owgResult.getString("STOCK")),
                        owgResult.getString("TYPE"),
                        owgResult.getString("STATUS")
                ));
            }
        } catch (SQLException e) {
        } finally {

            try {

            } catch (Exception e) {
            }
        }
        return productslist;
    }

    public static ObservableList<receipt> getReceipt() {
        Connection owgConnection = getConnection();
        ObservableList<receipt> receiptList = FXCollections.observableArrayList();
        try {
            PreparedStatement owgData = null; //owgConnection.prepareStatement("SELECT * FROM owgCoffee");
            owgData = owgConnection.prepareStatement("SELECT * FROM owgReceipt");
            ResultSet owgResult = owgData.executeQuery();

            while (owgResult.next()) {
                receiptList.add(new receipt( //    public receipt(int id, int customer_id, String customer_name, double prod_total, double prod_change, Date prod_date){
                        owgResult.getInt("ID"),
                        owgResult.getInt("customer_id"),
                        owgResult.getString("customer_username"),
                        owgResult.getDouble("prod_total"),
                        owgResult.getDouble("prod_change"),
                        owgResult.getDate("prod_date")
                ));
            }
        } catch (SQLException e) {
        } finally {

            try {

            } catch (Exception e) {
            }
        }
        return receiptList;
    }
    public static ObservableList<vouchers> getVouchers() {
        Connection owgConnection = getConnection();
        ObservableList<vouchers> voucherslist = FXCollections.observableArrayList();
        try {
            PreparedStatement owgData = null; //owgConnection.prepareStatement("SELECT * FROM owgCoffee");
            owgData = owgConnection.prepareStatement("SELECT * FROM owgVouchers");
            ResultSet owgResult = owgData.executeQuery();

            while (owgResult.next()) {
                voucherslist.add(new vouchers(
                        Integer.parseInt(owgResult.getString("ID")),
                        owgResult.getString("CODE"),
                        Double.parseDouble(owgResult.getString("PERCENT"))
                ));
            }
        } catch (SQLException e) {
        } finally {

            try {

            } catch (Exception e) {
            }
        }
        return voucherslist;
    }

    public static ObservableList<transaction> getTransaction() {
        Connection owgConnection = getConnection();
        ObservableList<transaction> transactionList = FXCollections.observableArrayList();
        try {
            PreparedStatement owgData = null; //owgConnection.prepareStatement("SELECT * FROM owgCoffee");
            owgData = owgConnection.prepareStatement("SELECT * FROM owgTransaction");
            ResultSet owgResult = owgData.executeQuery();

            while (owgResult.next()) {
                transactionList.add(new transaction(
                        owgResult.getInt("ID"),
                        owgResult.getString("prod_name"),
                        owgResult.getInt("prod_amount"),
                        owgResult.getDouble("prod_price")
                ));
            }
        } catch (SQLException e) {
        } finally {

            try {

            } catch (Exception e) {
            }
        }
        return transactionList;
    }
    public static ObservableList<userreceipt> getuserReceipt() {
        Connection owgConnection = getConnection();
        ObservableList<userreceipt> userreceiptList = FXCollections.observableArrayList();
        try {
            PreparedStatement owgData = null; //owgConnection.prepareStatement("SELECT * FROM owgCoffee");
            owgData = owgConnection.prepareStatement("SELECT * FROM owguserReceipt");
            ResultSet owgResult = owgData.executeQuery();

            while (owgResult.next()) {
                userreceiptList.add(new userreceipt(
                        owgResult.getInt("ID"),
                        owgResult.getString("prod_name"),
                        owgResult.getInt("prod_amount"),
                        owgResult.getDouble("prod_price"),
                        owgResult.getString("prod_type")

                        ));
            }
        } catch (SQLException e) {
        } finally {

            try {

            } catch (Exception e) {
            }
        }
        return userreceiptList;
    }
    public static ObservableList<delivery> getDelivery() {
        Connection owgConnection = getConnection();
        ObservableList<delivery> deliverylist = FXCollections.observableArrayList();
        try {
            PreparedStatement owgData = null; //owgConnection.prepareStatement("SELECT * FROM owgCoffee");
            owgData = owgConnection.prepareStatement("SELECT * FROM owgDelivery");
            ResultSet owgResult = owgData.executeQuery();

            while (owgResult.next()) {
                deliverylist.add(new delivery(
                        Integer.parseInt(owgResult.getString("ID")),
                        owgResult.getString("NAME"),
                        Integer.parseInt(owgResult.getString("STOCK")),
                        owgResult.getString("DATE")
                ));
            }
        } catch (SQLException e) {
        } finally {

            try {

            } catch (Exception e) {
            }
        }
        return deliverylist;
    }
    public static ObservableList<String> productNames() {
        Connection connection = getConnection();
        ObservableList<String> namesList = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT NAME FROM owgProducts";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                namesList.add(resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return namesList;
    }
    public static ObservableList<String> deliveries() {
        Connection connection = getConnection();
        ObservableList<String> namesList = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT ID FROM owgDelivery";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                namesList.add(resultSet.getString("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return namesList;
    }
}