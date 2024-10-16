package me.yohhan.dev.owgrevamp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import owgdata.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class adminFunction implements Initializable{

    ObservableList<users> listM; //USERS
    ObservableList<vouchers> listV; // VOUCHERS
    ObservableList<products> listP; //INVENTORY
    ObservableList<products> listP1; //INVENTORY FROM ORDER AND DELIVERIES

    ObservableList<receipt> listR; //RECEIPT

    ObservableList<delivery> listD; //DELIVERY

    @FXML
    private TextField admin_addAddress;

    @FXML
    private TextField admin_addAdminField;

    @FXML
    private DatePicker admin_addBirthday;


    @FXML
    private TextField admin_addEmail;

    @FXML
    private TextField admin_addFirstname;

    @FXML
    private Label admin_addLabel;

    @FXML
    private TextField admin_addLastname;

    @FXML
    private TextField admin_addPassword;

    @FXML
    private TextField admin_addPhone;

    @FXML
    private TextField admin_addUsername;

    @FXML
    private DatePicker admin_birthday;

    @FXML
    private TableColumn<users, Integer> admin_col_ID;

    @FXML
    private TableColumn<users, String> admin_col_address;

    @FXML
    private TableColumn<users, String> admin_col_birthday;

    @FXML
    private TableColumn<users, String> admin_col_email;

    @FXML
    private TableColumn<users, String> admin_col_firstname;

    @FXML
    private TableColumn<users, String> admin_col_group;

    @FXML
    private TableColumn<users, String> admin_col_lastname;

    @FXML
    private TableColumn<users, String> admin_col_password;

    @FXML
    private TableColumn<users, String> admin_col_phone;

    @FXML
    private TableColumn<users, Integer> admin_col_purchase;

    @FXML
    private TableColumn<users, String> admin_col_username;

    @FXML
    private TextField admin_deleteField;

    @FXML
    private StackPane admin_fxml;

    @FXML
    private Button admin_inventoryButton;

    @FXML
    private Button admin_logoutButton;

    @FXML
    private Button admin_mainMenuButton;

    @FXML
    private TextField admin_removeAdminField;

    @FXML
    private TableView<users> admin_table_users;

    @FXML
    private Button admin_transactionButton;

    @FXML
    private TextField admin_updateAddress;

    @FXML
    private TextField admin_updateEmail;

    @FXML
    private TextField admin_updateFirstname;

    @FXML
    private TextField admin_updateId;

    @FXML
    private Label admin_updateLabel;

    @FXML
    private TextField admin_updateLastname;

    @FXML
    private TextField admin_updatePassword;

    @FXML
    private TextField admin_updatePhone;

    @FXML
    private TextField admin_updateUsername;

    @FXML
    private Label admin_userLabel;

    @FXML
    private Button admin_voucherButton;

    // INVENTORY FXMLs
    @FXML
    private Button backAdminButton;

    @FXML
    private Label inventory_addLabel;

    @FXML
    private TextField inventory_addName;

    @FXML
    private TextField inventory_addPrice;

    @FXML
    private ComboBox<String> inventory_addType;

    @FXML
    private TableColumn<products, Integer> inventory_col_id;

    @FXML
    private TableColumn<products, String> inventory_col_name;
    @FXML
    private TableColumn<products, String> inventory_col_type;
    @FXML
    private TableColumn<products, String> inventory_col_status;

    @FXML
    private TableColumn<products, Double> inventory_col_price;

    @FXML
    private TableColumn<products, Integer> inventory_col_stock;

    @FXML
    private TableView<products> inventory_table_products;

    @FXML
    private TextField inventory_deleteId;

    @FXML
    private Label inventory_deleteLabel;

    @FXML
    private StackPane inventory_fxml;

    @FXML
    private ComboBox<String> inventory_status;


    @FXML
    private TextField inventory_updateId;

    @FXML
    private Label inventory_updateLabel;

    @FXML
    private TextField inventory_updateName;

    @FXML
    private TextField inventory_updatePrice;

    @FXML
    private ComboBox<String> inventory_updateType;


    Connection owgConnection = null;
    ResultSet owgResult = null;
    PreparedStatement owgData = null;



    //SALES FXML

    @FXML
    private BarChart<?, ?> sales_customerchart;


    @FXML
    private AreaChart<?, ?> sales_incomechart;

    @FXML
    private Label sales_numcustomer;

    @FXML
    private Label sales_soldproduct;

    @FXML
    private Label sales_todayincome;

    @FXML
    private Label sales_totalincome;

    public void sales_displaynumbercustomer(){


        String sql = "SELECT COUNT(id) FROM owgReceipt";

        owgConnection = ConnectionDB.getConnection();


        try{
            int nc = 0;
            owgData = owgConnection.prepareStatement(sql);
            owgResult = owgData.executeQuery();

            if (owgResult.next())
            {
                nc = owgResult.getInt("COUNT(id)");

            }
            sales_numcustomer.setText(String.valueOf(nc));




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sales_displaytodayincome() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "SELECT SUM(prod_total) AS total_income FROM owgReceipt WHERE prod_date = ?";

        Connection owgConnection = null;
        PreparedStatement owgData = null;
        ResultSet owgResult = null;

        try {
            owgConnection = ConnectionDB.getConnection();
            owgData = owgConnection.prepareStatement(sql);
            owgData.setDate(1, sqlDate);
            owgResult = owgData.executeQuery();

            double ti = 0;
            if (owgResult.next()) {
                ti = owgResult.getDouble("total_income");
            }

            sales_todayincome.setText("₱" + ti);
            System.out.println("Today's Total Income: ₱" + ti);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null) owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void sales_displaytotalincome() {
        String sql = "SELECT SUM(prod_total) AS total_income FROM owgReceipt";
        Connection owgConnection = null;
        PreparedStatement owgData = null;
        ResultSet owgResult = null;

        try {
            owgConnection = ConnectionDB.getConnection();
            owgData = owgConnection.prepareStatement(sql);
            owgResult = owgData.executeQuery();

            double ti = 0;
            if (owgResult.next()) {
                ti = owgResult.getDouble("total_income");
            }

            sales_totalincome.setText("₱" + ti);
            System.out.println("Total: ₱" + ti);

        } catch (SQLException e) {
            // Catch SQL-specific exceptions
            throw new RuntimeException(e);
        } finally {
            // Properly close the resources
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null) owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void sales_displayincomechart(){
        sales_incomechart.getData().clear();

        String sql = "SELECT prod_Date, SUM(prod_total) FROM owgReceipt GROUP BY prod_date ORDER BY TIMESTAMP(prod_date)";
        owgConnection = ConnectionDB.getConnection();
        XYChart.Series chart = new XYChart.Series();
        try {
            owgData = owgConnection.prepareStatement(sql);
            owgResult = owgData.executeQuery();

            while (owgResult.next()) {
                chart.getData().add(new XYChart.Data<>(owgResult.getString(1), owgResult.getFloat(2)));
            }

            sales_incomechart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sales_displaycustomerchart(){
        sales_customerchart.getData().clear();

        String sql = "SELECT prod_date, COUNT(ID) FROM owgReceipt GROUP BY prod_date ORDER BY TIMESTAMP(prod_date)";
        owgConnection = ConnectionDB.getConnection();
        XYChart.Series chart = new XYChart.Series();
        try {
            owgData = owgConnection.prepareStatement(sql);
            owgResult = owgData.executeQuery();

            while (owgResult.next()) {
                chart.getData().add(new XYChart.Data<>(owgResult.getString(1), owgResult.getInt(2)));
            }

            sales_customerchart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


  /*  public void sales_displaysoldproduct(){
        String sql = "SELECT COUNT(prod_amount) FROM owguserReceipt";


        owgConnection = ConnectionDB.getConnection();

        try{

            double q = 0;


            owgData = owgConnection.prepareStatement(sql);
            if(owgResult.next()){
                q = owgResult.getDouble("COUNT(prod_amount)");

            }
            sales_soldproduct.setText(String.valueOf(q));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   */


    @FXML
    void inventory_addProduct(ActionEvent event) {
        PreparedStatement owgData = null;
        ResultSet owgResult = null;
        Connection owgConnection = ConnectionDB.getConnection();
        try {

            owgData = owgConnection.prepareStatement("SELECT * FROM owgProducts WHERE NAME = ?");
            owgData.setString(1, inventory_addName.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                inventory_addLabel.setStyle("-fx-text-fill: red;");
                inventory_addLabel.setText("Product name already existed.");

            } else if (inventory_addName.getText().isBlank() || inventory_addPrice.getText().isBlank() ||inventory_addType.getValue() == null) {
                inventory_addLabel.setStyle("-fx-text-fill: red;");
                inventory_addLabel.setText("Please fill out the product details.");

            } else {
                owgData = owgConnection.prepareStatement("INSERT INTO owgProducts (NAME, PRICE, STOCK, TYPE, STATUS) VALUES (?, ?, '0', ?, 'Unavailable')");
                owgData.setString(1, inventory_addName.getText());
                owgData.setString(2, inventory_addPrice.getText());
                //  owgData.setString(3, inventory_addStocks.getText());
                owgData.setString(3, inventory_addType.getValue());


                int rowsAffected = owgData.executeUpdate();
                if (rowsAffected > 0) {
                    inventory_addLabel.setStyle("-fx-text-fill: #01FF13;");
                    inventory_addLabel.setText("Product has been added"); //BALIK DIRI
                    order_orderlist.setItems(FXCollections.observableArrayList(ConnectionDB.productNames()));
                    listP = ConnectionDB.getProducts();
                    inventory_table_products.setItems(listP);
                    listP1 = ConnectionDB.getProducts();
                    inventory_table_products1.setItems(listP1);
                    order_stocklist.setItems(FXCollections.observableArrayList(ConnectionDB.deliveries()));
                } else {
                    inventory_addLabel.setStyle("-fx-text-fill: red;");
                    inventory_addLabel.setText("Failed to add the product. Please check for some errors");
                }
            }
        } catch (SQLException ex) {
            inventory_addLabel.setStyle("-fx-text-fill: red;");
            inventory_addLabel.setText("update ang mysql any guba");
            ex.printStackTrace();
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null)
                    owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @FXML
    void inventory_backAdmin(ActionEvent event) {
        inventory_fxml.setVisible(false);
        admin_fxml.setVisible(true);

    }

    @FXML
    void inventory_deleteProduct(ActionEvent event) {
        PreparedStatement owgData = null;
        ResultSet owgResult = null;
        Connection owgConnection = ConnectionDB.getConnection();

        try {

            owgData = owgConnection.prepareStatement("SELECT * FROM owgProducts WHERE ID = ?");
            owgData.setString(1, inventory_deleteId.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                owgData = owgConnection.prepareStatement("DELETE FROM owgProducts WHERE ID = ?");
                owgData.setString(1, inventory_deleteId.getText());
                int rowsAffected = owgData.executeUpdate();
                if (rowsAffected > 0) {
                    inventory_deleteLabel.setStyle("-fx-text-fill: #01FF13;");
                    inventory_deleteLabel.setText("Product has been deleted");
                    // Refresh the table view
                    listP = ConnectionDB.getProducts();
                    inventory_table_products.setItems(listP);
                    listP1 = ConnectionDB.getProducts();
                    inventory_table_products1.setItems(listP1);
                }
                else {
                    inventory_deleteLabel.setStyle("-fx-text-fill: red;");
                    inventory_deleteLabel.setText("Failed to delete the product. Please check for some errors");
                }
            } else if (inventory_deleteId.getText().isBlank()) {
                inventory_deleteLabel.setStyle("-fx-text-fill: red;");
                inventory_deleteLabel.setText("Please fill out the product ID.");

            } else {
                inventory_deleteLabel.setStyle("-fx-text-fill: red;");
                inventory_deleteLabel.setText("Product ID doesn't exist");
            }
        } catch (SQLException ex) {
            inventory_deleteLabel.setStyle("-fx-text-fill: red;");
            inventory_deleteLabel.setText("update ang mysql any guba");
            ex.printStackTrace();
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null)
                    owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void inventory_orderButton(ActionEvent event) {
        order_fxml.setVisible(true);
        inventory_fxml.setVisible(false);
        order_orderlist.setItems(FXCollections.observableArrayList(ConnectionDB.productNames()));
        order_stocklist.setItems(FXCollections.observableArrayList(ConnectionDB.deliveries()));
    }

    @FXML
    void inventory_updateProduct(ActionEvent event) {
        PreparedStatement owgData = null;
        ResultSet owgResult = null;
        Connection owgConnection = ConnectionDB.getConnection();
        try {


            if (inventory_updateName.getText().isBlank() || inventory_updatePrice.getText().isBlank() || inventory_updatePrice.getText().isBlank() || inventory_updateType.getValue() == null) {
                inventory_updateLabel.setStyle("-fx-text-fill: red;");
                inventory_updateLabel.setText("Please fill out the product details.");

            } else {
                owgData = owgConnection.prepareStatement("UPDATE owgProducts SET NAME = ?, PRICE = ?, STOCK = STOCK, TYPE = ?, STATUS = STATUS WHERE ID = ?;");
                owgData.setString(1, inventory_updateName.getText());
                owgData.setString(2, inventory_updatePrice.getText());
                //              owgData.setString(3, inventory_updateStocks.getText());
                owgData.setString(4, inventory_updateId.getText());
                owgData.setString(3, inventory_updateType.getValue());



                int rowsAffected = owgData.executeUpdate();
                if (rowsAffected > 0) {
                    inventory_updateLabel.setStyle("-fx-text-fill: #01FF13;");
                    inventory_updateLabel.setText("Product has been updated");
                    listP = ConnectionDB.getProducts();
                    inventory_table_products.setItems(listP);
                } else {
                    inventory_updateLabel.setStyle("-fx-text-fill: red;");
                    inventory_updateLabel.setText("Failed to update the product. Please check for some errors");
                }
            }
        } catch (SQLException ex) {
            inventory_updateLabel.setStyle("-fx-text-fill: red;");
            inventory_updateLabel.setText("update ang mysql any guba");
            ex.printStackTrace();
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null)
                    owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @FXML
    void inventory_updatestatusProduct(ActionEvent event) {
        PreparedStatement owgData = null;
        ResultSet owgResult = null;
        Connection owgConnection = ConnectionDB.getConnection();

        try {

            owgData = owgConnection.prepareStatement("SELECT * FROM owgProducts WHERE ID = ?");
            owgData.setString(1, inventory_deleteId.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                owgData = owgConnection.prepareStatement("UPDATE owgProducts SET NAME = NAME, PRICE = PRICE, STOCK = STOCK, TYPE = TYPE, STATUS = ? WHERE ID = ?;");
                owgData.setString(2, inventory_deleteId.getText());
                owgData.setString(1, inventory_status.getValue());
                int rowsAffected = owgData.executeUpdate();
                if (rowsAffected > 0) {
                    inventory_deleteLabel.setStyle("-fx-text-fill: #01FF13;");
                    inventory_deleteLabel.setText("Product has been updated");
                    // Refresh the table view
                    listP = ConnectionDB.getProducts();
                    inventory_table_products.setItems(listP);
                }
                else {
                    inventory_deleteLabel.setStyle("-fx-text-fill: red;");
                    inventory_deleteLabel.setText("Failed to update the product. Please check for some errors");
                }
            } else if (inventory_deleteId.getText().isBlank() || inventory_status.getValue() == null) {
                inventory_deleteLabel.setStyle("-fx-text-fill: red;");
                inventory_deleteLabel.setText("Please fill out all details needed.");

            } else {
                inventory_deleteLabel.setStyle("-fx-text-fill: red;");
                inventory_deleteLabel.setText("Product ID doesn't exist");
            }
        } catch (SQLException ex) {
            inventory_deleteLabel.setStyle("-fx-text-fill: red;");
            inventory_deleteLabel.setText("update ang mysql any guba");
            ex.printStackTrace();
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null)
                    owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    //VOUCHER FXMLs
    @FXML
    private TextField voucher_addCode;

    @FXML
    private Label voucher_addLabel;

    @FXML
    private TextField voucher_addPercent;

    @FXML
    private Button voucher_backAdminButton;

    @FXML
    private TableColumn<vouchers, Integer> voucher_col_id;

    @FXML
    private TableColumn<vouchers, String> voucher_col_code;

    @FXML
    private TableColumn<vouchers, Double> voucher_col_percent;

    @FXML
    private TextField voucher_deleteId;

    @FXML
    private Label voucher_deleteLabel;

    @FXML
    private StackPane voucher_fxml;

    @FXML
    private TableView<vouchers> voucher_table_voucher;

    @FXML
    void voucher_addVoucher(ActionEvent event) {
        PreparedStatement owgData = null;
        ResultSet owgResult = null;
        Connection owgConnection = ConnectionDB.getConnection();
        try{
            owgData = owgConnection.prepareStatement("SELECT * FROM owgVouchers WHERE CODE = ?");
            owgData.setString(1, voucher_addCode.getText());
            owgResult = owgData.executeQuery();
            if(owgResult.next()){
                voucher_addLabel.setStyle("-fx-text-fill: red;");
                voucher_addLabel.setText("Voucher Code already exist");

            }
            else if(voucher_addCode.getText().isBlank() || voucher_addPercent.getText().isBlank()){
                voucher_addLabel.setStyle("-fx-text-fill: red;");
                voucher_addLabel.setText("Please fill out the details");

            }
            else{
                owgData = owgConnection.prepareStatement("INSERT INTO owgVouchers (CODE, PERCENT) VALUES (?, ?);");
                owgData.setString(1, voucher_addCode.getText());
                owgData.setString(2, voucher_addPercent.getText());
            }
            int rowsAffected = owgData.executeUpdate();
            if (rowsAffected > 0)
            {
                voucher_addLabel.setStyle("-fx-text-fill: #01FF13;");
                voucher_addLabel.setText("Successfully added voucher");
                listV = ConnectionDB.getVouchers();
                voucher_table_voucher.setItems(listV);
            }
            else {
                voucher_addLabel.setStyle("-fx-text-fill: red;");
                voucher_addLabel.setText("Nay guba");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void voucher_backAdmin(ActionEvent event) {
        voucher_fxml.setVisible(false);
        admin_fxml.setVisible(true);

    }

    @FXML
    void voucher_deleteVoucher(ActionEvent event) {
        PreparedStatement owgData = null;
        ResultSet owgResult = null;
        Connection owgConnection = ConnectionDB.getConnection();
        try{
            owgData = owgConnection.prepareStatement("SELECT * FROM owgVouchers WHERE ID = ?");
            owgData.setString(1, voucher_deleteId.getText());
            owgResult = owgData.executeQuery();
            if(owgResult.next()){
                owgData = owgConnection.prepareStatement("DELETE FROM owgVouchers WHERE ID = ?");
                owgData.setString(1, voucher_deleteId.getText());

                int rowsAffected = owgData.executeUpdate();

                if(rowsAffected > 0){
                    voucher_deleteLabel.setStyle("-fx-text-fill: #01FF13;");
                    voucher_deleteLabel.setText("Successfully deleted the voucher");
                    listV = ConnectionDB.getVouchers();
                    voucher_table_voucher.setItems(listV);
                }

            }
            else if(voucher_deleteId.getText().isBlank()){
                voucher_deleteLabel.setStyle("-fx-text-fill: red;");
                voucher_deleteLabel.setText("Please put the voucher id");

            }
            else{
                voucher_deleteLabel.setStyle("-fx-text-fill: red;");
                voucher_deleteLabel.setText("Voucher ID doesn't exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //TRANSACTION FXMLs
    @FXML
    private Button transaction_backAdminButton1;


    @FXML
    private TableColumn<receipt, Integer> transaction_col_id;
    @FXML
    private TableColumn<receipt, String> transaction_col_name;
    @FXML
    private TableColumn<receipt, Integer> transaction_col_customerid;
    @FXML
    private TableColumn<receipt, Double> transaction_col_total;
    @FXML
    private TableColumn<receipt, Double> transaction_col_change;
    @FXML
    private TableColumn<receipt, java.sql.Date> transaction_col_date;
    @FXML
    private TableView<receipt> transasction_table;


    @FXML
    private TextField transaction_deleteId;

    @FXML
    private Label transaction_deleteLabel;

    @FXML
    private StackPane transaction_fxml;

    @FXML
    private Button transaction_salesButton;


    @FXML
    void transaction_backAdmin(ActionEvent event) {
        transaction_fxml.setVisible(false);
        admin_fxml.setVisible(true);


    }

    @FXML
    void transaction_deleteTransaction(ActionEvent event) {
        PreparedStatement owgData = null;
        ResultSet owgResult = null;
        Connection owgConnection = ConnectionDB.getConnection();
        try{
            owgData = owgConnection.prepareStatement("SELECT * FROM owgReceipt WHERE ID = ?");
            owgData.setString(1, transaction_deleteId.getText());
            owgResult = owgData.executeQuery();
            if(owgResult.next()){
                owgData = owgConnection.prepareStatement("DELETE FROM owgReceipt WHERE ID = ?");
                owgData.setString(1, transaction_deleteId.getText());

                int rowsAffected = owgData.executeUpdate();

                if(rowsAffected > 0){
                    transaction_deleteLabel.setStyle("-fx-text-fill: #01FF13;");
                    transaction_deleteLabel.setText("Successfully deleted the voucher");
                    listR = ConnectionDB.getReceipt();
                    transasction_table.setItems(listR);
                  /*  listM = ConnectionDB.getVouchers();
                    table_transactions.setItems(listM);

                   */
                }

            }
            else if(transaction_deleteId.getText().isBlank()){
                transaction_deleteLabel.setStyle("-fx-text-fill: red;");
                transaction_deleteLabel.setText("Please put the transaction ID");

            }
            else{
                transaction_deleteLabel.setStyle("-fx-text-fill: red;");
                transaction_deleteLabel.setText("Transaction ID doesn't exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void transaction_salesButton(ActionEvent event) {
     //   sales_soldproduct.setText(String.valueOf(menuFunction.soldproduct));
        transaction_fxml.setVisible(false);
        sales_fxml.setVisible(true);

        try{

            owgConnection = ConnectionDB.getConnection();

            owgData = null;
            owgData = owgConnection.prepareStatement("SELECT * FROM owgSoldProduct WHERE ID = 1");
            owgResult = owgData.executeQuery();

            if (owgResult.next()){
                sales_soldproduct.setText(owgResult.getString("prod_amount"));
            }
            //            String insertPay = "INSERT INTO owgReceipt (customer_username, customer_id, prod_total, prod_date, prod_change) "
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    //ORDER FXMLs
    @FXML
    private TableColumn<products, Integer> inventory_col_id1;

    @FXML
    private TableColumn<products, String> inventory_col_name1;
    @FXML
    private TableColumn<products, String> inventory_col_type1;
    @FXML
    private TableColumn<products, String> inventory_col_status1;

    @FXML
    private TableColumn<products, Double> inventory_col_price1;

    @FXML
    private TableColumn<products, Integer> inventory_col_stock1;

    @FXML
    private TableView<products> inventory_table_products1;


    @FXML
    private Label order_cancellabel;

    @FXML
    private TableColumn<delivery, Integer> order_col_deliveryid;
    @FXML
    private TableColumn<delivery, Integer> order_col_stock;

    @FXML
    private TableColumn<delivery, String> order_col_name;
    @FXML
    private TableColumn<delivery, String> order_col_date;
    @FXML
    private TableView<delivery> order_table_delivery;


    @FXML
    private StackPane order_fxml;

    @FXML
    private TextField order_orderamount;

    @FXML
    private TextField order_ordercancel;

    @FXML
    private DatePicker order_orderdate;

    @FXML
    private Label order_orderlabel;

    @FXML
    private ComboBox<String> order_orderlist;

    @FXML
    private TextField order_stockamount;

    @FXML
    private Label order_stocklabel;

    @FXML
    private ComboBox<String> order_stocklist;
    @FXML
    void order_backInventory(ActionEvent event) {

        inventory_fxml.setVisible(true);

        order_fxml.setVisible(false);


    }

    @FXML
    void order_cancel(ActionEvent event) {
        Connection owgConnection = null;
        PreparedStatement owgData = null;
        ResultSet owgResult = null;

        try {
            owgConnection = ConnectionDB.getConnection();
            owgData = owgConnection.prepareStatement("SELECT * FROM owgDelivery WHERE ID = ?");
            owgData.setString(1, order_ordercancel.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                owgData.close();
                owgData = owgConnection.prepareStatement("DELETE FROM owgDelivery WHERE ID = ?");
                owgData.setString(1, order_ordercancel.getText());
                int rowsAffect = owgData.executeUpdate();
                if (rowsAffect > 0){
                    order_cancellabel.setStyle("-fx-text-fill: #01FF13;");
                    order_cancellabel.setText("Successfully canceled the delivery order");
                    listD= ConnectionDB.getDelivery();
                    order_table_delivery.setItems(listD);
                }
            } else {
                order_cancellabel.setStyle("-fx-text-fill: red;");
                order_cancellabel.setText("Delivery ID doesn't exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null) owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void order_order(ActionEvent event) {
        try (Connection owgConnection = ConnectionDB.getConnection();
             PreparedStatement owgData = owgConnection.prepareStatement("SELECT * FROM owgDelivery WHERE NAME = ? AND DATE = ?");
        ) {
            if (order_orderdate.getValue() == null || order_orderlist.getValue().isBlank() || order_orderamount.getText().isBlank()) {
                order_orderlabel.setStyle("-fx-text-fill: red;");
                order_orderlabel.setText("Please fill out the order details.");
                return;
            }

            owgData.setString(1, order_orderlist.getValue());
            owgData.setString(2, String.valueOf(order_orderdate.getValue()));

            try (ResultSet owgResult = owgData.executeQuery()) {
                if (owgResult.next()) {
                    int existingStock = owgResult.getInt("STOCK");
                    int additionalStock = Integer.parseInt(order_orderamount.getText());
                    int newStock = existingStock + additionalStock;

                    try (PreparedStatement updateStatement = owgConnection.prepareStatement("UPDATE owgDelivery SET STOCK = ? WHERE NAME = ? AND DATE = ?")) {
                        updateStatement.setInt(1, newStock);
                        updateStatement.setString(2, order_orderlist.getValue());
                        updateStatement.setString(3, String.valueOf(order_orderdate.getValue()));
                        int rowsAffected = updateStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            listD = ConnectionDB.getDelivery();
                            order_table_delivery.setItems(listD);
                            order_orderlabel.setStyle("-fx-text-fill: #01FF13;");
                            order_orderlabel.setText("Order Successful (Stock Updated)");
                            order_stocklist.setItems(FXCollections.observableArrayList(ConnectionDB.deliveries()));

                        } else {
                            order_orderlabel.setStyle("-fx-text-fill: red;");
                            order_orderlabel.setText("Order unsuccessful. Please try again");
                        }
                    }
                } else {
                    try (PreparedStatement insertStatement = owgConnection.prepareStatement("INSERT INTO owgDelivery (NAME, STOCK, DATE) VALUES (?, ?, ?)")) {
                        insertStatement.setString(1, order_orderlist.getValue());
                        insertStatement.setString(2, order_orderamount.getText());
                        insertStatement.setString(3, String.valueOf(order_orderdate.getValue()));
                        int rowsAffected = insertStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            order_orderlabel.setStyle("-fx-text-fill: #01FF13;");
                            order_orderlabel.setText("Order Successful (New Stock Added)");
                            order_stocklist.setItems(FXCollections.observableArrayList(ConnectionDB.deliveries()));
                            listD = ConnectionDB.getDelivery();
                            order_table_delivery.setItems(listD);
                        } else {
                            order_orderlabel.setStyle("-fx-text-fill: red;");
                            order_orderlabel.setText("Order unsuccessful. Please try again");
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            order_orderlabel.setStyle("-fx-text-fill: red;");
            order_orderlabel.setText("update ang mysql any guba");
            ex.printStackTrace();
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null)
                    owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void order_transferstock(ActionEvent event) {
        try {
            owgConnection = ConnectionDB.getConnection();

            if (order_stockamount.getText().isBlank() || order_stocklist.getValue() == null) {
                order_stocklabel.setStyle("-fx-text-fill: red;");
                order_stocklabel.setText("Please fill out all the details.");
                return;
            }

            PreparedStatement owgData = owgConnection.prepareStatement("SELECT * FROM owgDelivery WHERE ID = ?");
            owgData.setString(1, order_stocklist.getValue());
            ResultSet owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                String deliveryDateStr = owgResult.getString("DATE");
                int currentStock = owgResult.getInt("STOCK");
                int minusStock = Integer.parseInt(order_stockamount.getText());

                LocalDate deliveryDate = LocalDate.parse(deliveryDateStr);
                LocalDate currentDate = LocalDate.now();

                if (currentDate.isBefore(deliveryDate)) {
                    order_stocklabel.setStyle("-fx-text-fill: red;");
                    order_stocklabel.setText("Stock transfer is only allowed on or after the delivery date.");
                    return;
                }

                if (currentStock >= minusStock) {
                    int newStock = currentStock - minusStock;
                    owgData = owgConnection.prepareStatement("UPDATE owgDelivery SET STOCK = ? WHERE ID = ?");
                    owgData.setInt(1, newStock);
                    owgData.setString(2, order_stocklist.getValue());
                    int rowsAffected = owgData.executeUpdate();

                    if (rowsAffected > 0) {
                        PreparedStatement updateProduct = owgConnection.prepareStatement("UPDATE owgProducts SET STOCK = STOCK + ? WHERE NAME = ?");
                        updateProduct.setInt(1, minusStock);
                        updateProduct.setString(2, owgResult.getString("NAME"));
                        rowsAffected = updateProduct.executeUpdate();

                        if (rowsAffected > 0) {
                            order_stocklabel.setStyle("-fx-text-fill: #01FF13;");
                            order_stocklabel.setText("Restock Successful");
                            order_stockamount.clear();
                            listD = ConnectionDB.getDelivery();
                            order_table_delivery.setItems(listD);
                            listP1 = ConnectionDB.getProducts();
                            inventory_table_products1.setItems(listP1);
                            listP = ConnectionDB.getProducts();
                            inventory_table_products.setItems(listP);

                            if (newStock <= 0) {
                                PreparedStatement deleteDelivery = owgConnection.prepareStatement("DELETE FROM owgDelivery WHERE ID = ?");
                                deleteDelivery.setString(1, order_stocklist.getValue());
                                listD = ConnectionDB.getDelivery();
                                order_table_delivery.setItems(listD);
                                order_stocklist.setValue(null);
                                rowsAffected = deleteDelivery.executeUpdate();

                                if (rowsAffected > 0) {
                                    listD = ConnectionDB.getDelivery();
                                    order_table_delivery.setItems(listD);
                                    order_stocklist.setItems(FXCollections.observableArrayList(ConnectionDB.deliveries()));
                                }
                            } else {
                                order_stocklist.setValue(null);
                            }
                            return;
                        }
                    }
                } else {
                    order_stocklabel.setStyle("-fx-text-fill: red;");
                    order_stocklabel.setText("You don't have enough stocks.");
                    return;
                }
            }
            order_stocklabel.setStyle("-fx-text-fill: red;");
            order_stocklabel.setText("Restock Error. Please try again");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (owgConnection != null) {
                    owgConnection.close();
                }
            } catch (SQLException e) {
                // Handle the exception
            }
        }
    }

    //SALES FXMLs
    @FXML
    private AnchorPane sales_fxml;

    @FXML
    void sales_transactionButton(ActionEvent event) {
        sales_fxml.setVisible(false);
        transaction_fxml.setVisible(true);

    }


    @FXML
    void admin_addUser(ActionEvent event) {
        PreparedStatement owgData = null;
        ResultSet owgResult = null;
        Connection owgConnection = null;
        try {
            owgConnection = ConnectionDB.getConnection();

            owgData = owgConnection.prepareStatement("SELECT * FROM owgCoffee WHERE EMAIL = ? OR USERNAME = ?");
            owgData.setString(1, admin_addEmail.getText());
            owgData.setString(2, admin_addUsername.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                admin_addLabel.setStyle("-fx-text-fill: red;");
                admin_addLabel.setText("Email or username already exists.");
            } else if (admin_addEmail.getText().isBlank() || admin_addUsername.getText().isBlank() || admin_addPassword.getText().isBlank() || admin_addFirstname.getText().isBlank() || admin_addLastname.getText().isBlank() || admin_addPhone.getText().isBlank() || admin_addAddress.getText().isBlank()) {
                admin_addLabel.setStyle("-fx-text-fill: red;");
                admin_addLabel.setText("Please fill out all your credentials.");
            } else if (admin_addPassword.getLength() < 5) {
                admin_addLabel.setStyle("-fx-text-fill: red;");
                admin_addLabel.setText("Your password must atleast 5 digits/letter longer.");
            } else {
                owgData = owgConnection.prepareStatement("INSERT INTO owgCoffee (EMAIL, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, PHONE, ADDRESS, BIRTHDAY, PURCHASE, TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0, 'client')");
                owgData.setString(1, admin_addEmail.getText());
                owgData.setString(2, admin_addUsername.getText());
                owgData.setString(3, admin_addPassword.getText());
                owgData.setString(4, admin_addFirstname.getText());
                owgData.setString(5, admin_addLastname.getText());
                owgData.setString(6, admin_addPhone.getText());
                owgData.setString(7, admin_addAddress.getText());
                LocalDate selectedDate = admin_addBirthday.getValue();
                if (selectedDate != null) {
                    owgData.setString(8, selectedDate.toString());
                }
                else {
                    admin_addLabel.setStyle("-fx-text-fill: red;");
                    admin_addLabel.setText("Please fill out your credentials.");
                }
                //owgData.setString(8, admin_birthday.getValue().toString());
                int rowsAffected = owgData.executeUpdate();
                if (rowsAffected > 0) {
                    admin_addLabel.setStyle("-fx-text-fill: #01FF13;");
                    admin_addLabel.setText("Registration Successful");
                    listM = ConnectionDB.getDatausers();
                    admin_table_users.setItems(listM);

                } else {
                    admin_addLabel.setStyle("-fx-text-fill: red;");
                    admin_addLabel.setText("Registration Failed. Please try again.");
                }
            }
        } catch (SQLException ex) {
            admin_addLabel.setStyle("-fx-text-fill: red;");
            admin_addLabel.setText("update ang mysql any guba");
            ex.printStackTrace();
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null)
                    owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void admin_deleteUser(ActionEvent event) {
        Connection owgConnection = null;
        PreparedStatement owgData = null;
        ResultSet owgResult = null;

        try {
            owgConnection = ConnectionDB.getConnection();
            owgData = owgConnection.prepareStatement("SELECT * FROM owgCoffee WHERE ID = ?");
            owgData.setString(1, admin_deleteField.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                String type = owgResult.getString("TYPE");
                boolean isAdmin = type.equalsIgnoreCase("admin");

                if (isAdmin) {
                    admin_userLabel.setStyle("-fx-text-fill: red;");
                    admin_userLabel.setText("User is an admin and cannot be deleted");
                } else {
                    owgData.close();
                    owgData = owgConnection.prepareStatement("DELETE FROM owgCoffee WHERE ID = ?");
                    owgData.setString(1, admin_deleteField.getText());
                    int rowsAffect = owgData.executeUpdate();
                    if (rowsAffect > 0){
                        admin_userLabel.setStyle("-fx-text-fill: #01FF13;");
                        admin_userLabel.setText("Successfully deleted the user");
                        listM = ConnectionDB.getDatausers();
                        admin_table_users.setItems(listM);
                    }
                    else {
                        admin_userLabel.setStyle("-fx-text-fill: red;");
                        admin_userLabel.setText("Failed to delete the user.");
                    }
                }
            } else {
                admin_userLabel.setStyle("-fx-text-fill: red;");
                admin_userLabel.setText("ID doesn't exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null) owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void admin_setAdmin() {
        Connection owgConnection = null;
        PreparedStatement owgData = null;
        ResultSet owgResult = null;

        try {
            owgConnection = ConnectionDB.getConnection();
            owgData = owgConnection.prepareStatement("SELECT * FROM owgCoffee WHERE ID = ?");
            owgData.setString(1, admin_addAdminField.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                String type = owgResult.getString("TYPE");
                boolean isAdmin = type.equalsIgnoreCase("admin");

                if (isAdmin) {
                    admin_userLabel.setStyle("-fx-text-fill: red;");
                    admin_userLabel.setText("User is already an admin");
                } else {
                    owgData.close();
                    owgData = owgConnection.prepareStatement("UPDATE owgCoffee SET TYPE = 'admin' WHERE id = ?;");
                    owgData.setString(1, admin_addAdminField.getText());
                    owgData.executeUpdate();
                    admin_userLabel.setStyle("-fx-text-fill: #01FF13;");
                    admin_userLabel.setText("Successfully updated user group");
                   listM = ConnectionDB.getDatausers();
                    admin_table_users.setItems(listM);
                }
            } else {
                admin_userLabel.setStyle("-fx-text-fill: red;");
                admin_userLabel.setText("ID doesn't exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null) owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    @FXML
    void admin_logoutAction(ActionEvent event) {
        try {
            int result = JOptionPane.showConfirmDialog(null,
                    "You are logging out from the admin panel. Are you sure?",
                    "OWG | COFFEESHOP ",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userUI.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = (Stage) admin_logoutButton.getScene().getWindow();

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setFullScreen(false);
                stage.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void admin_mainmenuAction(ActionEvent event) {
        try {
            int result = JOptionPane.showConfirmDialog(null,
                    "You are logging out from the admin panel. Are you sure?",
                    "OWG | COFFEESHOP ",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuUI.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = (Stage) admin_mainMenuButton.getScene().getWindow();

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setFullScreen(false);
                stage.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void admin_inventoryAction(ActionEvent event) {
        admin_fxml.setVisible(false);
        inventory_fxml.setVisible(true);

    }


    @FXML
    void admin_setAdmin(ActionEvent event) {
        Connection owgConnection = null;
        PreparedStatement owgData = null;
        ResultSet owgResult = null;

        try {
            owgConnection = ConnectionDB.getConnection();
            owgData = owgConnection.prepareStatement("SELECT * FROM owgCoffee WHERE ID = ?");
            owgData.setString(1, admin_addAdminField.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                String type = owgResult.getString("TYPE");
                boolean isAdmin = type.equalsIgnoreCase("admin");

                if (isAdmin) {
                    admin_userLabel.setStyle("-fx-text-fill: red;");
                    admin_userLabel.setText("User is already an admin");
                } else {
                    owgData.close();
                    owgData = owgConnection.prepareStatement("UPDATE owgCoffee SET TYPE = 'admin' WHERE id = ?;");
                    owgData.setString(1, admin_addAdminField.getText());
                    owgData.executeUpdate();
                    admin_userLabel.setStyle("-fx-text-fill: #01FF13;");
                    admin_userLabel.setText("Successfully updated user group");
                    listM = ConnectionDB.getDatausers();
                    admin_table_users.setItems(listM);
                }
            } else {
                admin_userLabel.setStyle("-fx-text-fill: red;");
                admin_userLabel.setText("ID doesn't exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null) owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void admin_transactionAction(ActionEvent event) {
        admin_fxml.setVisible(false);
        transaction_fxml.setVisible(true);

    }

    @FXML
    void admin_unsetAdmin(ActionEvent event) {
        Connection owgConnection = null;
        PreparedStatement owgData = null;
        ResultSet owgResult = null;

        try {
            owgConnection = ConnectionDB.getConnection();
            owgData = owgConnection.prepareStatement("SELECT * FROM owgCoffee WHERE ID = ?");
            owgData.setString(1, admin_removeAdminField.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                String type = owgResult.getString("TYPE");
                boolean isClient = type.equalsIgnoreCase("client");

                if (isClient) {
                    admin_userLabel.setStyle("-fx-text-fill: red;");
                    admin_userLabel.setText("User is already a client");
                } else {
                    owgData.close();
                    owgData = owgConnection.prepareStatement("UPDATE owgCoffee SET TYPE = 'client' WHERE id = ?;");
                    owgData.setString(1, admin_removeAdminField.getText());
                    owgData.executeUpdate();
                    admin_userLabel.setStyle("-fx-text-fill: #01FF13;");
                    admin_userLabel.setText("Successfully updated user group");
                    listM = ConnectionDB.getDatausers();
                    admin_table_users.setItems(listM);
                }
            } else {
                admin_userLabel.setStyle("-fx-text-fill: red;");
                admin_userLabel.setText("ID doesn't exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null) owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void admin_updateUser(ActionEvent event) {
        PreparedStatement owgData = null;
        ResultSet owgResult = null;
        Connection owgConnection = null;
        try {
            owgConnection = ConnectionDB.getConnection();

            owgData = owgConnection.prepareStatement("SELECT * FROM owgCoffee WHERE EMAIL = ?");
            owgData.setString(1, admin_updateEmail.getText());
          //  owgData.setString(2, admin_updateUsername.getText());
            owgResult = owgData.executeQuery();

            if (owgResult.next()) {
                admin_updateLabel.setStyle("-fx-text-fill: red;");
                admin_updateLabel.setText("Email or username already exists.");
            } else if (admin_updateEmail.getText().isBlank() || admin_updateUsername.getText().isBlank() || admin_updatePassword.getText().isBlank() || admin_updateFirstname.getText().isBlank() || admin_updateLastname.getText().isBlank() || admin_updatePhone.getText().isBlank() || admin_updateAddress.getText().isBlank()) {
                admin_updateLabel.setStyle("-fx-text-fill: red;");
                admin_updateLabel.setText("Please fill out all your credentials.");
            } else if (admin_updatePassword.getLength() < 5) {
                admin_updateLabel.setStyle("-fx-text-fill: red;");
                admin_updateLabel.setText("Your password must atleast 5 digits/letter longer.");
            } else {
                owgData = owgConnection.prepareStatement("UPDATE owgCoffee SET EMAIL = ?, USERNAME = ?, PASSWORD = ?, FIRSTNAME = ?, LASTNAME = ?, PHONE = ?, ADDRESS = ?, BIRTHDAY = ?, PURCHASE = 0, TYPE = 'client' WHERE ID = ?;");
                owgData.setString(1, admin_updateEmail.getText());
                owgData.setString(2, admin_updateUsername.getText());
                owgData.setString(3, admin_updatePassword.getText());
                owgData.setString(4, admin_updateFirstname.getText());
                owgData.setString(5, admin_updateLastname.getText());
                owgData.setString(6, admin_updatePhone.getText());
                owgData.setString(7, admin_updateAddress.getText());
                owgData.setString(9, admin_updateId.getText());
                LocalDate selectedDate = admin_birthday.getValue();
                if (selectedDate != null) {
                    owgData.setString(8, selectedDate.toString());
                }
                else {
                    admin_addLabel.setStyle("-fx-text-fill: red;");
                    admin_addLabel.setText("Please fill out your credentials.");
                }
                //owgData.setString(8, admin_birthday.getValue().toString());
                int rowsAffected = owgData.executeUpdate();
                if (rowsAffected > 0) {
                 /*   loginButtonActionRegister();
                    LoginAction loginAction = new LoginAction();
                    loginAction.registerMessage();*/
                    admin_updateLabel.setStyle("-fx-text-fill: #01FF13;");
                    admin_updateLabel.setText("Updated Successfully");
                    listM = ConnectionDB.getDatausers();
                    admin_table_users.setItems(listM);
                } else {
                    admin_updateLabel.setStyle("-fx-text-fill: red;");
                    admin_updateLabel.setText("Registration Failed. Please try again.");
                }
            }
        } catch (SQLException ex) {
            admin_updateLabel.setStyle("-fx-text-fill: red;");
            admin_updateLabel.setText("update ang mysql any guba");
            ex.printStackTrace();
        } finally {
            try {
                if (owgResult != null) owgResult.close();
                if (owgData != null)
                    owgData.close();
                if (owgConnection != null) owgConnection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void admin_voucherAction(ActionEvent event) {
        admin_fxml.setVisible(false);
        voucher_fxml.setVisible(true);

    }

    @FXML
    private TextField admin_broadcast;

    @FXML
    private Label admin_promolabel;

    @FXML
    void admin_broadcast(){
        if (admin_broadcast.getText().isBlank() || admin_broadcast.getText().isEmpty()){
            admin_promolabel.setStyle("-fx-text-fill: red;");
            admin_promolabel.setText("Please fill out the broadcast message");

        }
        else {
            try{
                owgData = null;
                owgData = owgConnection.prepareStatement("UPDATE owgPromo SET promo_message = ? WHERE ID = 1;");
                owgData.setString(1, admin_broadcast.getText());
                int rowsAffected = owgData.executeUpdate();
                if (rowsAffected > 0) {
                    admin_promolabel.setStyle("-fx-text-fill: #01FF13;");
                    admin_promolabel.setText("Message has been broadcast"); //BALIK DIRI
                }
                else {
                    admin_promolabel.setStyle("-fx-text-fill: red;");
                    admin_promolabel.setText("There were some errors broadcasting the messsage");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // USER DATABASE RELOAD

        listM = ConnectionDB.getDatausers();
        admin_table_users.setItems(listM);


        admin_fxml.setVisible(true);
        transaction_fxml.setVisible(false);
        voucher_fxml.setVisible(false);
        inventory_fxml.setVisible(false);

        // USER DATABASE

        admin_col_ID.setCellValueFactory(new PropertyValueFactory<users, Integer>("id"));
        admin_col_username.setCellValueFactory(new PropertyValueFactory<users, String>("username"));
        admin_col_password.setCellValueFactory(new PropertyValueFactory<users, String>("password"));
        admin_col_email.setCellValueFactory(new PropertyValueFactory<users, String>("email"));
        admin_col_firstname.setCellValueFactory(new PropertyValueFactory<users, String>("firstname"));
        admin_col_lastname.setCellValueFactory(new PropertyValueFactory<users, String>("lastname"));
        admin_col_phone.setCellValueFactory(new PropertyValueFactory<users, String>("phone"));
        admin_col_address.setCellValueFactory(new PropertyValueFactory<users, String>("address"));
        admin_col_birthday.setCellValueFactory(new PropertyValueFactory<users, String>("birthday"));
        admin_col_purchase.setCellValueFactory(new PropertyValueFactory<users, Integer>("purchase"));
        admin_col_group.setCellValueFactory(new PropertyValueFactory<users, String>("group"));


        // VOUCHER DATABASE
        voucher_col_id.setCellValueFactory(new PropertyValueFactory<vouchers, Integer>("id"));
        voucher_col_code.setCellValueFactory(new PropertyValueFactory<vouchers, String>("code"));
        voucher_col_percent.setCellValueFactory(new PropertyValueFactory<vouchers, Double>("percent"));

        listV = ConnectionDB.getVouchers();
        voucher_table_voucher.setItems(listV);


        // INVENTORY DATABASE

        inventory_col_id.setCellValueFactory(new PropertyValueFactory<products, Integer>("id"));
        inventory_col_name.setCellValueFactory(new PropertyValueFactory<products, String>("name"));
        inventory_col_price.setCellValueFactory(new PropertyValueFactory<products, Double>("price"));
        inventory_col_stock.setCellValueFactory(new PropertyValueFactory<products, Integer>("stock"));
        inventory_col_type.setCellValueFactory(new PropertyValueFactory<products, String>("type"));
        inventory_col_status.setCellValueFactory(new PropertyValueFactory<products, String>("status"));

        listP = ConnectionDB.getProducts();
        inventory_table_products.setItems(listP);

        // RECEIPT DATABASE

        transaction_col_date.setCellValueFactory(new PropertyValueFactory<receipt, java.sql.Date>("prod_date"));
        transaction_col_id.setCellValueFactory(new PropertyValueFactory<receipt, Integer>("id"));
        transaction_col_name.setCellValueFactory(new PropertyValueFactory<receipt, String>("customer_username"));
        transaction_col_customerid.setCellValueFactory(new PropertyValueFactory<receipt, Integer>("customer_id"));
        transaction_col_total.setCellValueFactory(new PropertyValueFactory<receipt, Double>("prod_total"));
        transaction_col_change.setCellValueFactory(new PropertyValueFactory<receipt, Double>("prod_change"));


        listR = ConnectionDB.getReceipt();
        transasction_table.setItems(listR);

        // COMBO BOX STUFF
        inventory_addType.setItems(FXCollections.observableArrayList("Meal", "Coffee", "Pastries"));
        inventory_updateType.setItems(FXCollections.observableArrayList("Meal", "Coffee", "Pastries"));
        inventory_status.setItems(FXCollections.observableArrayList("Available", "Unavailable"));



        //ORDER AND DELIVERY DATABASE
        inventory_col_id1.setCellValueFactory(new PropertyValueFactory<products, Integer>("id"));
        inventory_col_name1.setCellValueFactory(new PropertyValueFactory<products, String>("name"));
        inventory_col_price1.setCellValueFactory(new PropertyValueFactory<products, Double>("price"));
        inventory_col_stock1.setCellValueFactory(new PropertyValueFactory<products, Integer>("stock"));
        inventory_col_type1.setCellValueFactory(new PropertyValueFactory<products, String>("type"));
        inventory_col_status1.setCellValueFactory(new PropertyValueFactory<products, String>("status"));

        listP1 = ConnectionDB.getProducts();
        inventory_table_products1.setItems(listP1);

        // DELIVERY ORDER DATABASE

        order_col_deliveryid.setCellValueFactory(new PropertyValueFactory<delivery, Integer>("id"));
        order_col_name.setCellValueFactory(new PropertyValueFactory<delivery, String>("name"));
        order_col_stock.setCellValueFactory(new PropertyValueFactory<delivery, Integer>("stock"));
        order_col_date.setCellValueFactory(new PropertyValueFactory<delivery, String>("date"));
        listD = ConnectionDB.getDelivery();
        order_table_delivery.setItems(listD);


        //SALES

        sales_displaynumbercustomer();
       //    sales_displaysoldproduct();
        sales_displaytodayincome();
        sales_displaytotalincome();

        sales_displayincomechart();
        sales_displaycustomerchart();
        sales_soldproduct.setText(String.valueOf(menuFunction.soldproduct));
        System.out.println(menuFunction.soldproduct);
        try{

            owgConnection = ConnectionDB.getConnection();

            owgData = null;
            owgData = owgConnection.prepareStatement("SELECT * FROM owgSoldProduct WHERE ID = 1");
            owgResult = owgData.executeQuery();

            if (owgResult.next()){
                sales_soldproduct.setText(owgResult.getString("prod_amount"));
            }
            //            String insertPay = "INSERT INTO owgReceipt (customer_username, customer_id, prod_total, prod_date, prod_change) "
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
