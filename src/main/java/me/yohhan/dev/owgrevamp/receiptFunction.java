package me.yohhan.dev.owgrevamp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import owgdata.ConnectionDB;
import owgdata.userreceipt;
import owgdata.users;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


public class receiptFunction implements Initializable {


    @FXML
    private TableColumn<userreceipt, Double> receipt_col_price;

    @FXML
    private TableColumn<userreceipt, String> receipt_col_prodname;

    @FXML
    private TableColumn<userreceipt, String> receipt_col_prodtype;

    @FXML
    private TableColumn<userreceipt, Integer> receipt_col_quantity;
    @FXML
    private TableView<userreceipt> receipt_table;
    @FXML
    private Label receipt_change;
    @FXML
    private Label receipt_discount;

    @FXML
    private Label receipt_paid;

    @FXML
    private Label receipt_subtotal;
    @FXML
    private Button receiptbackButton;


    @FXML
    private Label receipt_total;

    @FXML
    private Label receipt_vat;


    public void receiptbackButton() {
        Alert alertreceipt = new Alert(Alert.AlertType.CONFIRMATION);
        alertreceipt.setTitle("Receipt Generation");
        alertreceipt.setHeaderText(null);
        alertreceipt.setContentText("Are you sure?");

        //  receipt_table.setItems(listU);
        Optional<ButtonType> optionreceipt = alertreceipt.showAndWait();

        if (optionreceipt.get().equals(ButtonType.OK)) {
            Stage stage = (Stage) receiptbackButton.getScene().getWindow();
            stage.close();
        }
        Connection owgConnection;
        PreparedStatement owgData;
        owgConnection = null;
        owgData = null;
        PreparedStatement creating = null;

        try {
            owgConnection = ConnectionDB.getConnection();

            try {
                owgData = owgConnection.prepareStatement("DROP TABLE IF EXISTS owguserReceipt");
                int affectedRows = owgData.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Table owguserReceipt dropped successfully.");
                } else {
                    System.out.println("Table owguserReceipt did not exist.");
                }
            } catch (SQLException e) {
                System.out.println("Failed to drop the table.");
                e.printStackTrace();
                return;
            } finally {
                if (owgData != null) {
                    try {
                        owgData.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }


            try {
                creating = owgConnection.prepareStatement("CREATE TABLE owguserReceipt (" +
                        "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                        "customer_id INT(100) NOT NULL, " +
                        "prod_name VARCHAR(100) NOT NULL, " +
                        "customer_username VARCHAR(100) NOT NULL, " +
                        "prod_amount INT(100) NOT NULL, " +
                        "prod_type VARCHAR(100) NOT NULL, " +
                        "prod_price DOUBLE NOT NULL)");
                creating.executeUpdate();
                System.out.println("Created the table owguserReceipt again.");
            } catch (SQLException e) {
                System.out.println("Failed to create the table.");
                e.printStackTrace();
            } finally {
                if (creating != null) {
                    try {
                        creating.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        } finally {
            if (owgConnection != null) {
                try {
                    owgConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    /*    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("OWG COFFEE SHOP");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure? The receipt will be permanently deleted if you press " + "OK" + ".");
     //   System.out.println(amount);
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            Connection owgConnection;
            //receipt_fxml.setVisible(false);
            menu_fxml.setVisible(true);

            receipt_subtotal.setText("₱0.00");
            receipt_vat.setText("₱0.00 | 12%");
            receipt_discount.setText("₱0.00 (0%)");
            receipt_total.setText("₱0.00");
            receipt_paid.setText("₱0.00");
            receipt_change.setText("₱0.00");

            PreparedStatement owgData;
            owgConnection = null;
            owgData = null;
            PreparedStatement creating = null;

            try {
                owgConnection = ConnectionDB.getConnection();

                try {
                    owgData = owgConnection.prepareStatement("DROP TABLE IF EXISTS owguserReceipt");
                    int affectedRows = owgData.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Table owguserReceipt dropped successfully.");
                    } else {
                        System.out.println("Table owguserReceipt did not exist.");
                    }
                } catch (SQLException e) {
                    System.out.println("Failed to drop the table.");
                    e.printStackTrace();
                    return;
                } finally {
                    if (owgData != null) {
                        try {
                            owgData.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }


                try {
                    creating = owgConnection.prepareStatement("CREATE TABLE owguserReceipt (" +
                            "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                            "customer_id INT(100) NOT NULL, " +
                            "prod_name VARCHAR(100) NOT NULL, " +
                            "customer_username VARCHAR(100) NOT NULL, " +
                            "prod_amount INT(100) NOT NULL, " +
                            "prod_type VARCHAR(100) NOT NULL, " +
                            "prod_price DOUBLE NOT NULL)");
                    creating.executeUpdate();
                    System.out.println("Created the table owguserReceipt again.");
                } catch (SQLException e) {
                    System.out.println("Failed to create the table.");
                    e.printStackTrace();
                } finally {
                    if (creating != null) {
                        try {
                            creating.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } finally {
                if (owgConnection != null) {
                    try {
                        owgConnection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }


        }

     */
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        receipt_subtotal.setText(String.format("₱%.2f", menuFunction.receipt_subtotalR));
        receipt_vat.setText(String.format("₱%.2f | 12%%", menuFunction.vatamount));
        receipt_discount.setText(String.format("₱%.2f", menuFunction.discount));
        receipt_total.setText(String.format("₱%.2f", menuFunction.totalP));
        receipt_paid.setText(String.format("₱%.2f", menuFunction.receipt_paidR));
        receipt_change.setText(String.format("₱%.2f", menuFunction.receipt_changeR));

        ObservableList<userreceipt> listU = ConnectionDB.getuserReceipt();
        receipt_table.setItems(listU);


        receipt_col_prodname.setCellValueFactory(new PropertyValueFactory<userreceipt, String>("prod_name"));
        receipt_col_price.setCellValueFactory(new PropertyValueFactory<userreceipt, Double>("prod_price"));
        receipt_col_prodtype.setCellValueFactory(new PropertyValueFactory<userreceipt, String>("prod_type"));
        receipt_col_quantity.setCellValueFactory(new PropertyValueFactory<userreceipt, Integer>("prod_amount"));



    }
}
